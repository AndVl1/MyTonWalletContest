package ru.andvl.mytonwallet.contest.blockchain.impl.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ApiActivity {
    abstract val kind: String

    @Serializable
    @SerialName("transaction")
    data class ApiTransactionActivity(
        val id: String,
        override val kind: String = "transaction",
        val txId: String,
        val timestamp: Long,
        val amount: String,
        val fromAddress: String,
        val toAddress: String,
        val comment: String? = null,
        val encryptedComment: String? = null,
        val fee: String,
        val slug: String,
        val isIncoming: Boolean,
        val normalizedAddress: String,
        val inMsgHash: String,
        val shouldHide: Boolean? = null,
        val type: ApiTransactionType? = null,
        val metadata: ApiTransactionMetadata? = null,
        val nft: ApiNft? = null
    ) : ApiActivity()

    @Serializable
    @SerialName("swap")
    data class ApiSwapActivity(
        override val kind: String = "swap",
        val shouldHide: Boolean? = null,
        val id: String,
        val timestamp: Long,
        val lt: Long? = null,
        val from: String,
        val fromAmount: String,
        val to: String,
        val toAmount: String,
        val networkFee: Double,
        val swapFee: String,
        val status: SwapStatus,
        val txIds: List<String>,
        val cex: CexTransaction? = null
    ) : ApiActivity()
}

// TODO delete
//2024-09-12 19:39:13.250 24176-24282 onApiUpdate             ru.andvl.mytonwallet.contest         D  {"type":"newActivities","activities":[{"txId":"47021300000001:arOnTC3fCGUbrCvr7hJs11E5myrCVBvMLtRs/zzoK18=","timestamp":1718097546000,"isIncoming":true,"fromAddress":"UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn","toAddress":"UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn","amount":"1","slug":"toncoin","fee":"1","inMsgHash":"q3W1P8KgaCZhtSZWwbRpt/uoXWlBW7kOx2B683uV+Ps=","normalizedAddress":"EQDd6cAX_GWbV7jxdpHxFuQCbkB_QvqvBfyVMmZwm3F4DKPQ","nft":{"index":0,"name":"USDT Voucher","address":"EQDd6cAX_GWbV7jxdpHxFuQCbkB_QvqvBfyVMmZwm3F4DKPQ","image":"https://cache.tonapi.io/imgproxy/d5OaB7xIgJW3-n5867cHUURCA8AcGdezG4AIlMsTR1I/rs:fill:1500:1500:1/g:no/aHR0cHM6Ly90b25lc2thbDI0MS5wYWdlcy5kZXYvSGFtczUxMzV0ZXIucG5n.webp","thumbnail":"https://cache.tonapi.io/imgproxy/pfe7X5WXjiVHOkOuZk_xsXRdNbs5nfonqw5PuLzZ-dQ/rs:fill:500:500:1/g:no/aHR0cHM6Ly90b25lc2thbDI0MS5wYWdlcy5kZXYvSGFtczUxMzV0ZXIucG5n.webp","isOnSale":false,"isHidden":false,"isScam":false,"description":"Voucher #1924"},"type":"nftReceived","metadata":{},"kind":"transaction","id":"47021300000001:arOnTC3fCGUbrCvr7hJs11E5myrCVBvMLtRs/zzoK18="},{"txId":"47020970000019:A3UdGCEnsUxFjOVT9ruC/KmdR3P2Fbwwc/k15IDeMCs=","timestamp":1718096038000,"isIncoming":true,"fromAddress":"UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn","toAddress":"UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn","amount":"1","slug":"toncoin","fee":"1","inMsgHash":"e/uhemiB6E8Jkx+yARlvfAdrSNbB/sShoIQ913sAf10=","normalizedAddress":"EQAFwAlhSOiy0Xm3vsfrjLkBXUGLFe9JH8ag_KVbLIOdp8W9","nft":{"index":0,"name":"1,000,000 $HAM Voucher","address":"EQAFwAlhSOiy0Xm3vsfrjLkBXUGLFe9JH8ag_KVbLIOdp8W9","image":"https://cache.tonapi.io/imgproxy/d5OaB7xIgJW3-n5867cHUURCA8AcGdezG4AIlMsTR1I/rs:fill:1500:1500:1/g:no/aHR0cHM6Ly90b25lc2thbDI0MS5wYWdlcy5kZXYvSGFtczUxMzV0ZXIucG5n.webp","thumbnail":"https://cache.tonapi.io/imgproxy/pfe7X5WXjiVHOkOuZk_xsXRdNbs5nfonqw5PuLzZ-dQ/rs:fill:500:500:1/g:no/aHR0cHM6Ly90b25lc2thbDI0MS5wYWdlcy5kZXYvSGFtczUxMzV0ZXIucG5n.webp","isOnSale":false,"isHidden":false,"isScam":false,"description":"Hamster Pre-Market. You will be able to exchange this collectible for a $HAM token."},"type":"nftReceived","metadata":{},"kind":"transaction","id":"47020970000019:A3UdGCEnsUxFjOVT9ruC/KmdR3P2Fbwwc/k15IDeMCs="},{"txId":"47020881000001:ziHHiASvom6kGoLXzXfEGbmmMYW2WYyjrmx0olhhd1o=","timestamp":1718095624000,"isIncoming":false,"fromAddress":"UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn","toAddress":"UQDVUZSdjtpCQgboM2z6N_VWrBRrj9GO03UdALioG00S0Xz0","amount":"-123281191","slug":"toncoin","fee":"2062945","inMsgHash":"MTGsPEl0rDhTeV5ucK3axA2hCrD0jFEPx9UdHV9SS4U=","normalizedAddress":"EQDVUZSdjtpCQgboM2z6N_VWrBRrj9GO03UdALioG00S0SEx","metadata":{},"kind":"transaction","id":"47020881000001:ziHHiASvom6kGoLXzXfEGbmmMYW2WYyjrmx0olhhd1o="},{"txId":"47020868000008:7boS1/mKvZA8FSbQxsXnuRuYGNgVwSqWL0xrsW6jVRY=","timestamp":1718095570000,"isIncoming":true,"fromAddress":"EQBw7Et_8tjPcMB8GR0-jaL5loZnIkcp-3mBlwmfJ4-TR2kb","toAddress":"UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn","amount":"77964768","slug":"toncoin","fee":"396400","inMsgHash":"wXW4IFy+i3IUBNr35f3vM+6pno2KWzobTS0t36B73lo=","normalizedAddress":"EQBw7Et_8tjPcMB8GR0-jaL5loZnIkcp-3mBlwmfJ4-TR2kb","metadata":{},"kind":"transaction","id":"47020868000008:7boS1/mKvZA8FSbQxsXnuRuYGNgVwSqWL0xrsW6jVRY="},{"txId":"47020868000003:biTRGDT9H58H9tfY4xfRoqGleo5U492pdhB4vJM/GS0=","timestamp":1718095570000,"isIncoming":false,"fromAddress":"UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn","toAddress":"UQDVUZSdjtpCQgboM2z6N_VWrBRrj9GO03UdALioG00S0Xz0","amount":"-1000000000","slug":"ton-eqctdrjjoy","fee":"6492038","inMsgHash":"J66WuWAoP6GoCVy01Dxzfl9X2oqIJ2ejD7isB4Pcq+I=","normalizedAddress":"EQDVUZSdjtpCQgboM2z6N_VWrBRrj9GO03UdALioG00S0SEx","metadata":{},"kind":"transaction","id":"47020868000003:biTRGDT9H58H9tfY4xfRoqGleo5U492pdhB4vJM/GS0="},{"txId":"47020868000001:8MeQ/mzcgnTv6BGEohKTGy4LzwbCJBW6xo0B6peghNA=","timestamp":1718095570000,"isIncoming":false,"fromAddress":"UQBg8uZvTO_W9kNKvnljtju1AlaH8c4MvcYJ3Dr8faeDrbFn","toAddress"