package ru.andvl.mytonwallet.contest.auth.impl.setpasscode

sealed interface SetPasscodeNavigationEvent {
    data object NavigateBack : SetPasscodeNavigationEvent
    data object NavigateToBiometricLock : SetPasscodeNavigationEvent
}