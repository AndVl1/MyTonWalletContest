package ru.andvl.mytonwallet.contest.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val SETTINGS = "SETTINGS"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SETTINGS)

class UserSettingsRepositoryImpl(
    private val context: Context
) : UserSettingsRepository {
    override suspend fun updateWalletAddress(address: String) {
        try {
            context.dataStore.edit { settings ->
                settings[WALLET_ADDRESS] = address
            }
        } catch (e: Exception) {
            e.localizedMessage
        }
    }

    override suspend fun updateWalletAccountId(accountId: String) {
        try {
            context.dataStore.edit { settings ->
                settings[ACCOUNT_ID] = accountId
            }
        } catch (e: Exception) {
            e.localizedMessage
        }
    }

    override suspend fun updateAuthByFingerPrint(isByFingerprint: Boolean) {
        try {
            context.dataStore.edit { settings ->
                settings[IS_BY_FINGERPRINT] = isByFingerprint
            }
        } catch (e: Exception) {
            e.localizedMessage
        }
    }

    override suspend fun updatePasscode(passcode: String) {
        try {
            context.dataStore.edit { settings ->
                settings[PASSCODE] = passcode
            }
        } catch (e: Exception) {
            e.localizedMessage
        }
    }

    override fun getWalletAddress(): Flow<String> {
        return context.dataStore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { settings ->
                settings[WALLET_ADDRESS] ?: ""
            }
    }

    override fun getWalletAccountId(): Flow<String> {
        return context.dataStore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { settings ->
                settings[ACCOUNT_ID] ?: ""
            }
    }

    override fun getAuthByFingerPrint(): Flow<Boolean> {
        return context.dataStore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { settings ->
                settings[IS_BY_FINGERPRINT] ?: false
            }
    }

    override fun getPasscode(): Flow<String> {
        return context.dataStore.data
            .catch {
                if (it is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { settings ->
                settings[PASSCODE] ?: ""
            }
    }

    companion object {
        val WALLET_ADDRESS = stringPreferencesKey("wallet_address")
        val ACCOUNT_ID = stringPreferencesKey("account_id")
        val IS_BY_FINGERPRINT = booleanPreferencesKey("is_by_fingerprint")
        val PASSCODE = stringPreferencesKey("passcode")
    }
}