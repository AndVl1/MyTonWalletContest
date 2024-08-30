package ru.andvl.mytonwallet.contest.auth.impl.recoverylist

import ru.andvl.mytonwallet.contest.arch.State

data class RecoveryListState(
    val recoveryWords: List<Pair<Int, String>> = listOf(
        1 to "keep", 2 to "secret", 3 to "word", 4 to "keep",
        5 to "secret", 6 to "word", 7 to "keep", 8 to "secret",
        9 to "word", 10 to "keep", 11 to "secret", 12 to "word",
        13 to "keep", 14 to "secret", 15 to "word", 16 to "keep",
        17 to "secret", 18 to "word", 19 to "keep", 20 to "secret",
        21 to "word", 22 to "keep", 23 to "secret", 24 to "word"
    )
) : State
