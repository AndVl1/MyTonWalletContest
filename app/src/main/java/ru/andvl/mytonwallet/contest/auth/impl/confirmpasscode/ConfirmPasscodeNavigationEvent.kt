package ru.andvl.mytonwallet.contest.auth.impl.confirmpasscode

sealed interface ConfirmPasscodeNavigationEvent {
    data object NavigateBack : ConfirmPasscodeNavigationEvent
    data object NavigateToBiometricLock : ConfirmPasscodeNavigationEvent
}