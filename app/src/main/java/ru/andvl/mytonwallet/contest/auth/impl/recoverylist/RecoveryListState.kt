package ru.andvl.mytonwallet.contest.auth.impl.recoverylist

import ru.andvl.mytonwallet.contest.arch.State

data class RecoveryListState(
    val recoveryWords: List<String> = listOf(
        "keep",
        "secret",
        "word",
        "keep",
        "secret",
        "word",
        "keep",
        "secret",
        "word",
        "keep",
        "secret",
        "word",
        "keep",
        "secret",
        "word",
        "keep",
        "secret",
        "word",
        "keep",
        "secret",
        "word",
        "keep",
        "secret",
        "word",
    ),
) : State
