package ru.andvl.mytonwallet.contest.blockchain.impl

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.ApiActivity
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.ApiNewActivitiesDto
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.ApiTransactionType
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.ApiUpdate
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.ApiUpdateType
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.AuthResultDto
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.TokenDto
import ru.andvl.mytonwallet.contest.blockchain.util.MNEMONIC_CHECK_COUNT
import ru.andvl.mytonwallet.contest.blockchain.util.MNEMONIC_COUNT
import ru.andvl.mytonwallet.contest.blockchain.util.MYCOIN_SLUG
import ru.andvl.mytonwallet.contest.blockchain.util.TONCOIN_SLUG
import ru.andvl.mytonwallet.contest.blockchain.util.WebViewHolder
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetToken
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetTokenType
import ru.andvl.mytonwallet.contest.bottombar.impl.model.HistoryActivity
import ru.andvl.mytonwallet.contest.bottombar.impl.model.Nft
import ru.andvl.mytonwallet.contest.bottombar.impl.model.TokenImage
import ru.andvl.mytonwallet.contest.database.daos.BalanceDao
import ru.andvl.mytonwallet.contest.database.daos.StakingStateDao
import ru.andvl.mytonwallet.contest.database.daos.SwapTokenDao
import ru.andvl.mytonwallet.contest.database.daos.TokenDao
import ru.andvl.mytonwallet.contest.database.entities.BalanceEntity
import ru.andvl.mytonwallet.contest.database.entities.StakingStateEntity
import ru.andvl.mytonwallet.contest.datastore.UserSettingsRepository
import ru.andvl.mytonwallet.contest.mappers.toEntity
import ru.andvl.mytonwallet.contest.utils.timestampToDateTime
import java.math.BigInteger
import kotlin.coroutines.resume

class BlockchainRepositoryWebViewImpl(
    private val context: Context,
    private val webView: WebViewHolder,
    private val userSettingsRepository: UserSettingsRepository,
    private val balanceDao: BalanceDao,
    private val tokenDao: TokenDao,
    private val swapTokenDao: SwapTokenDao,
    private val stakingStateDao: StakingStateDao
) : BlockchainRepository {
    private var currentAccountId: String? = null
    private var currentAccountAddress: String? = null
    private val network: String = "mainnet"

    init {
        webView.addJavascriptInterface(WebAppInterface())

        CoroutineScope(Dispatchers.Main).launch {
            userSettingsRepository.getWalletAccountId().collect {
                currentAccountId = it
            }

            userSettingsRepository.getWalletAddress().collect {
                currentAccountAddress = it
            }
        }
    }

    private var continuation: CancellableContinuation<Result<String>>? = null

    override suspend fun checkApiAvailability(): Boolean {
        val jsonString = evaluateJs(
            "callApi('checkApiAvailability', {" +
                    "    blockchainKey: 'ton'," +
                    "    network: '$network'" +
                    "})"
        ).getOrThrow()

        return Json.parseToJsonElement(jsonString).jsonPrimitive.boolean
    }

    override suspend fun getMnemonicWordList(): List<String> {
        val jsonString = evaluateJs("callApi('getMnemonicWordList')").getOrThrow()

        val jsonElement = Json.parseToJsonElement(jsonString)
        return jsonElement.jsonArray.map { it.jsonPrimitive.content }
    }

    override suspend fun generateMnemonic(): List<String> {
        val jsonString = evaluateJs("callApi('generateMnemonic')").getOrThrow()

        val jsonElement = Json.parseToJsonElement(jsonString)
        return jsonElement.jsonArray.map { it.jsonPrimitive.content }
    }

    override fun getMnemonicCheckIndexes(): List<Int> {
        return List(MNEMONIC_COUNT) { it }
            .shuffled()
            .take(MNEMONIC_CHECK_COUNT)
            .sorted()
    }

    override suspend fun validateMnemonic(mnemonic: List<String>): Boolean {
        val mnemonicJson = Json.encodeToString(mnemonic)
        val jsonString = evaluateJs("callApi('validateMnemonic', $mnemonicJson)").getOrThrow()

        return Json.parseToJsonElement(jsonString).jsonPrimitive.boolean
    }

    override suspend fun updateCurrentAccountId(id: String) {
        userSettingsRepository.updateWalletAccountId(id)
    }

    override suspend fun createAccount(
        mnemonic: List<String>,
        passcode: String,
        isImport: Boolean
    ) {
        val method = if (isImport) "importMnemonic" else "createWallet"
        val mnemonicJson = Json.encodeToString(mnemonic)
        val jsonString = evaluateJs(
            """
            callApi('$method', "$network", $mnemonicJson, '$passcode')
            """.trimIndent()
        ).getOrThrow()

        val result: AuthResultDto = Json.decodeFromString(jsonString)

        if (result.error != null || result.accountId == null || result.address == null) {
            throw Exception(result.error)
        } else {
            currentAccountId = result.accountId
            userSettingsRepository.updateWalletAccountId(result.accountId)
            currentAccountAddress = result.address
            userSettingsRepository.updateWalletAddress(result.address)
        }
    }

    override suspend fun activateAccount() {
        val accountId = userSettingsRepository.getWalletAccountId().first()

        evaluateJs(
            """
            callApi('activateAccount', "$accountId")
            """.trimIndent()
        ).getOrThrow()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getCurrentAccountAssetTokens(): Flow<List<AssetToken>> {
        val accountId = userSettingsRepository.getWalletAccountId().first()
        val balances = getCurrentAccountBalances()
        Log.d("balancesCount", balances.first().size.toString())

        return getCurrentAccountBalances().flatMapConcat { balances ->
            val assetTokensFlows = balances.map { balance ->
                tokenDao.getTokenBySlug(balance.slug).map { token ->
                    val stakingState = stakingStateDao.getStakingStateByAccountId(accountId)

                    AssetToken(
                        type = when (token.slug) {
                            TONCOIN_SLUG -> {
                                AssetTokenType.STACKED
                            }

                            MYCOIN_SLUG -> {
                                AssetTokenType.VESTED
                            }

                            else -> {
                                AssetTokenType.SIMPLE
                            }
                        },
                        slug = balance.slug,
                        name = token.name,
                        image = if (token.slug == TONCOIN_SLUG) {
                            TokenImage.Resource(R.drawable.toncoin)
                        } else {
                            token.image?.let { TokenImage.Url(it) }
                        },
                        amount = balance.balance.toBigDecimal().movePointLeft(token.decimals),
                        amountUsd = balance.balance.toBigDecimal()
                            .movePointLeft(token.decimals) * token.priceUsd.toBigDecimal(),
                        price = token.price.toFloat(),
                        symbol = token.symbol,
                        change = token.percentChange24h.toFloat(),
                        apy = if (token.slug == TONCOIN_SLUG) stakingState?.apy else null
                    )
                }
            }
            combine(assetTokensFlows) { assetTokensArray ->
                assetTokensArray.toList()
            }
        }
    }

    override suspend fun getCurrentAccountBalances(): Flow<List<BalanceEntity>> {
        currentAccountId?.let {
            return balanceDao.getBalancesForAccount(it)
        } ?: throw NoSuchElementException()
    }

    override suspend fun getCurrentAccountWalletBalance(): BigInteger {
        val address = userSettingsRepository.getWalletAddress().first()

        val jsonString = evaluateJs(
            """
            callApi('getWalletBalance', "$network", "$address")
            """.trimIndent()
        ).getOrThrow()

        return BigInteger(Json.decodeFromString<String>(jsonString))
    }

    override suspend fun fetchAllActivitySlice(limit: Int): List<HistoryActivity> {
        val accountId = userSettingsRepository.getWalletAccountId().first()
        val jsonString = evaluateJs(
            """
            callApi('fetchAllActivitySlice', "$accountId", {}, $limit)
            """.trimIndent()
        ).getOrThrow()

        val json = Json {
            classDiscriminator = "kind"
            ignoreUnknownKeys = true
        }

        val result: List<ApiActivity> = json.decodeFromString(jsonString)
        return result.map {
            when (it) {
                is ApiActivity.ApiTransactionActivity -> {
                    when (it.type) {
                        ApiTransactionType.NFT_RECEIVED -> {
                            HistoryActivity.NftReceivedTransaction(
                                dateTime = timestampToDateTime(it.timestamp),
                                from = it.fromAddress,
                                fee = it.fee.toFloat(),
                                nft = it.nft!!.let { nft ->
                                    Nft(
                                        index = nft.index,
                                        name = nft.name,
                                        description = nft.description,
                                        image = nft.image,
                                        address = nft.address,
                                        thumbnail = nft.thumbnail,
                                    )
                                }
                            )
                        }

                        ApiTransactionType.NFT_TRANSFERRED -> {
                            HistoryActivity.NftSentTransaction(
                                dateTime = timestampToDateTime(it.timestamp),
                                to = it.toAddress,
                                fee = it.fee.toFloat(),
                                nft = it.nft!!.let { nft ->
                                    Nft(
                                        index = nft.index,
                                        name = nft.name,
                                        description = nft.description,
                                        image = nft.image,
                                        address = nft.address,
                                        thumbnail = nft.thumbnail,
                                    )
                                }
                            )
                        }

//                        ApiTransactionType.SWAP -> {
//                            HistoryActivity.SwappedTransaction(
//                                  TODO
//                            )
//                        }
                        else -> {
                            if (it.isIncoming) {
                                HistoryActivity.ReceivedTransaction(
                                    dateTime = timestampToDateTime(it.timestamp),
                                    amount = it.amount.toBigDecimal(),
                                    amountUsd = it.amount.toBigDecimal() * tokenDao.getTokenBySlug(
                                        it.slug
                                    )
                                        .first().price.toBigDecimal(),
                                    message = it.inMsgHash,
                                    from = it.fromAddress,
                                    fee = it.fee.toFloat(),
                                )
                            } else {
                                HistoryActivity.SentTransaction(
                                    dateTime = timestampToDateTime(it.timestamp),
                                    amount = it.amount.toBigDecimal(),
                                    amountUsd = it.amount.toBigDecimal() * tokenDao.getTokenBySlug(
                                        it.slug
                                    ).first().price.toBigDecimal(),
                                    message = it.comment,
                                    to = it.toAddress,
                                    fee = it.fee.toFloat(),
                                )
                            }
                        }
                    }
                }

                is ApiActivity.ApiSwapActivity -> {
                    HistoryActivity.SwappedTransaction(
                        dateTime = timestampToDateTime(it.timestamp),
                        from = it.from,
                        fromAmount = it.fromAmount.toBigDecimal(),
                        to = it.to,
                        toAmount = it.toAmount.toBigDecimal(),
                        fee = it.swapFee.toFloat()
                    )
                }
            }
        }
    }

    private suspend fun evaluateJs(script: String): Result<String> {
        return suspendCancellableCoroutine { cont ->
            continuation = cont
            webView.evaluateJavascript(script) {}
        }
    }

    fun updateBalances(accountId: String, balancesToUpdate: Map<String, BigInteger>) {
        val balanceEntities = balancesToUpdate.map { (slug, balance) ->
            Log.d("updateBalances", "Updated balances: $slug $balance")
            BalanceEntity(accountId, slug, balance)
        }
        CoroutineScope(Dispatchers.IO).launch {
            balanceDao.insertBalances(balanceEntities)
        }
    }

    fun updateTokens(tokens: List<TokenDto>) {
        val tokenEntities = tokens.map { it.toEntity() }
        Log.d("updateTokens", "Updated tokens: $tokenEntities")

        CoroutineScope(Dispatchers.IO).launch {
            tokenDao.insertTokens(tokenEntities)
        }
    }

    fun updateSwapTokens(update: ApiUpdate.SwapTokens) {
        val swapTokens = update.tokens.values.toList()
        val tokenEntities = swapTokens.map { it.toEntity() }
        Log.d("updateSwapTokens", "Updated swap tokens: $tokenEntities")

        CoroutineScope(Dispatchers.IO).launch {
            swapTokenDao.insertTokens(tokenEntities)
        }
    }

    fun updateStaking(update: ApiUpdate.Stacking) {
        val newStakingState: StakingStateEntity = update.toEntity()

        CoroutineScope(Dispatchers.IO).launch {
            stakingStateDao.updateStakingState(newStakingState)
        }
    }

    fun updateNewActivities(update: ApiNewActivitiesDto) {
        Log.d("updateNewActivities", update.toString())
    }

    // TODO callApi('getWalletInfo', network, fromAddress!),
    // callApi('getWalletInfo', "$network", "$currentAccountAddress")

    // TODO getAccountNewestTxId(accountId: string)
    // TODO 0(accountId: string)
    // TODO getTokenTransactionSlice(accountId: string)
    // TODO fetchTokenActivitySlice
    // TODO fetchAllActivitySlice
    // TODO buildTokenSlug
    // TODO fetchToken
    // TODO fetchDieselState

    private inner class WebAppInterface {
        @JavascriptInterface
        fun onApiResult(result: String) {
            Log.d(TAG, result)
            continuation?.resume(Result.success(result))
            continuation = null
        }

        @JavascriptInterface
        fun onApiUpdate(updateType: String, updateJson: String) {
            val update: ApiUpdateType = Json.decodeFromString(updateType)
            Log.d("onApiUpdate", "type: ${update.name}, content: $updateJson")


            val json = Json {
                ignoreUnknownKeys = true
            }

            when (update) {
                ApiUpdateType.BALANCES -> {
                    val updateBalances: ApiUpdate.Balances = json.decodeFromString(updateJson)
                    val balancesToUpdate =
                        updateBalances.balancesToUpdate.mapValues { (_, balanceString) ->
                            BigInteger(balanceString)
                        }

                    updateBalances(
                        updateBalances.accountId,
                        balancesToUpdate
                    )
                }

                ApiUpdateType.TOKENS -> {
                    val updateTokens: ApiUpdate.Tokens = json.decodeFromString(updateJson)
                    updateTokens(updateTokens.tokens.values.toList())
                }

                ApiUpdateType.SWAP_TOKENS -> {
                    val updateSwapTokens: ApiUpdate.SwapTokens =
                        json.decodeFromString(updateJson)
                    updateSwapTokens(updateSwapTokens)
                }

                ApiUpdateType.STAKING -> {
                    val updateStaking: ApiUpdate.Stacking = json.decodeFromString(updateJson)
                    updateStaking(updateStaking)
                }

                ApiUpdateType.NEW_ACTIVITIES -> {
                    val jsonNewActivities = Json {
                        classDiscriminator = "kind"
                        ignoreUnknownKeys = true
                    }
                    val updateNewActivities: ApiNewActivitiesDto =
                        jsonNewActivities.decodeFromString(updateJson)
                    updateNewActivities(updateNewActivities)
                }
            }
        }
    }

    companion object {
        const val TAG = "BlockchainRepositoryWebViewImpl"
    }
}


//{
//  "type": "newActivities",
//  "activities": [
//    {
//      "txId": "47021300000001:arOnTC3fCGUbrCvr7hJs11E5myrCVBvMLtRs/zzoK18=",
//      "timestamp": 1718097546000,
//      "isIncoming": true,
//      "fromAddress": "UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn",
//      "toAddress": "UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn",
//      "amount": "1",
//      "slug": "toncoin",
//      "fee": "1",
//      "inMsgHash": "q3W1P8KgaCZhtSZWwbRpt/uoXWlBW7kOx2B683uV+Ps=",
//      "normalizedAddress": "EQDd6cAX_GWbV7jxdpHxFuQCbkB_QvqvBfyVMmZwm3F4DKPQ",
//      "nft": {
//        "index": 0,
//        "name": "USDT Voucher",
//        "address": "EQDd6cAX_GWbV7jxdpHxFuQCbkB_QvqvBfyVMmZwm3F4DKPQ",
//        "image": "https://cache.tonapi.io/imgproxy/d5OaB7xIgJW3-n5867cHUURCA8AcGdezG4AIlMsTR1I/rs:fill:1500:1500:1/g:no/aHR0cHM6Ly90b25lc2thbDI0MS5wYWdlcy5kZXYvSGFtczUxMzV0ZXIucG5n.webp",
//        "thumbnail": "https://cache.tonapi.io/imgproxy/pfe7X5WXjiVHOkOuZk_xsXRdNbs5nfonqw5PuLzZ-dQ/rs:fill:500:500:1/g:no/aHR0cHM6Ly90b25lc2thbDI0MS5wYWdlcy5kZXYvSGFtczUxMzV0ZXIucG5n.webp",
//        "isOnSale": false,
//        "isHidden": false,
//        "isScam": false,
//        "description": "Voucher #1924"
//      },
//      "type": "nftReceived",
//      "metadata": {},
//      "kind": "transaction",
//      "id": "47021300000001:arOnTC3fCGUbrCvr7hJs11E5myrCVBvMLtRs/zzoK18="
//    },
//    {
//      "txId": "47020970000019:A3UdGCEnsUxFjOVT9ruC/KmdR3P2Fbwwc/k15IDeMCs=",
//      "timestamp": 1718096038000,
//      "isIncoming": true,
//      "fromAddress": "UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn",
//      "toAddress": "UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn",
//      "amount": "1",
//      "slug": "toncoin",
//      "fee": "1",
//      "inMsgHash": "e/uhemiB6E8Jkx+yARlvfAdrSNbB/sShoIQ913sAf10=",
//      "normalizedAddress": "EQAFwAlhSOiy0Xm3vsfrjLkBXUGLFe9JH8ag_KVbLIOdp8W9",
//      "nft": {
//        "index": 0,
//        "name": "1,000,000 $HAM Voucher",
//        "address": "EQAFwAlhSOiy0Xm3vsfrjLkBXUGLFe9JH8ag_KVbLIOdp8W9",
//        "image": "https://cache.tonapi.io/imgproxy/d5OaB7xIgJW3-n5867cHUURCA8AcGdezG4AIlMsTR1I/rs:fill:1500:1500:1/g:no/aHR0cHM6Ly90b25lc2thbDI0MS5wYWdlcy5kZXYvSGFtczUxMzV0ZXIucG5n.webp",
//        "thumbnail": "https://cache.tonapi.io/imgproxy/pfe7X5WXjiVHOkOuZk_xsXRdNbs5nfonqw5PuLzZ-dQ/rs:fill:500:500:1/g:no/aHR0cHM6Ly90b25lc2thbDI0MS5wYWdlcy5kZXYvSGFtczUxMzV0ZXIucG5n.webp",
//        "isOnSale": false,
//        "isHidden": false,
//        "isScam": false,
//        "description": "Hamster Pre-Market. You will be able to exchange this collectible for a $HAM token."
//      },
//      "type": "nftReceived",
//      "metadata": {},
//      "kind": "transaction",
//      "id": "47020970000019:A3UdGCEnsUxFjOVT9ruC/KmdR3P2Fbwwc/k15IDeMCs="
//    },
//    {
//      "txId": "47020881000001:ziHHiASvom6kGoLXzXfEGbmmMYW2WYyjrmx0olhhd1o=",
//      "timestamp": 1718095624000,
//      "isIncoming": false,
//      "fromAddress": "UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn",
//      "toAddress": "UQDVUZSdjtpCQgboM2z6N_VWrBRrj9GO03UdALioG00S0Xz0",
//      "amount": "-123281191",
//      "slug": "toncoin",
//      "fee": "2062945",
//      "inMsgHash": "MTGsPEl0rDhTeV5ucK3axA2hCrD0jFEPx9UdHV9SS4U=",
//      "normalizedAddress": "EQDVUZSdjtpCQgboM2z6N_VWrBRrj9GO03UdALioG00S0SEx",
//      "metadata": {},
//      "kind": "transaction",
//      "id": "47020881000001:ziHHiASvom6kGoLXzXfEGbmmMYW2WYyjrmx0olhhd1o="
//    },
//    {
//      "txId": "47020868000008:7boS1/mKvZA8FSbQxsXnuRuYGNgVwSqWL0xrsW6jVRY=",
//      "timestamp": 1718095570000,
//      "isIncoming": true,
//      "fromAddress": "EQBw7Et_8tjPcMB8GR0-jaL5loZnIkcp-3mBlwmfJ4-TR2kb",
//      "toAddress": "UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn",
//      "amount": "77964768",
//      "slug": "toncoin",
//      "fee": "396400",
//      "inMsgHash": "wXW4IFy+i3IUBNr35f3vM+6pno2KWzobTS0t36B73lo=",
//      "normalizedAddress": "EQBw7Et_8tjPcMB8GR0-jaL5loZnIkcp-3mBlwmfJ4-TR2kb",
//      "metadata": {},
//      "kind": "transaction",
//      "id": "47020868000008:7boS1/mKvZA8FSbQxsXnuRuYGNgVwSqWL0xrsW6jVRY="
//    },
//    {
//      "txId": "47020868000003:biTRGDT9H58H9tfY4xfRoqGleo5U492pdhB4vJM/GS0=",
//      "timestamp": 1718095570000,
//      "isIncoming": false,
//      "fromAddress": "UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn",
//      "toAddress": "UQDVUZSdjtpCQgboM2z6N_VWrBRrj9GO03UdALioG00S0Xz0",
//      "amount": "-1000000000",
//      "slug": "ton-eqctdrjjoy",
//      "fee": "6492038",
//      "inMsgHash": "J66WuWAoP6GoCVy01Dxzfl9X2oqIJ2ejD7isB4Pcq+I=",
//      "normalizedAddress": "EQDVUZSdjtpCQgboM2z6N_VWrBRrj9GO03UdALioG00S0SEx",
//      "metadata": {},
//      "kind": "transaction",
//      "id": "47020868000003:biTRGDT9H58H9tfY4xfRoqGleo5U492pdhB4vJM/GS0="
//    },
//    {
//      "txId": "47020868000001:8MeQ/mzcgnTv6BGEohKTGy4LzwbCJBW6xo0B6peghNA=",
//      "timestamp": 1718095570000,
//      "isIncoming": false,
//      "fromAddress": "UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8f"
//    }
//  ]
//}
