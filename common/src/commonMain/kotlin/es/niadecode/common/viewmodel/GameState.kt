package es.niadecode.common.viewmodel

sealed class GameState(
    open val currentScore: Int,
    open val maxScore: Int,
    open val lastUserName: String,
    open val lastUserNameMVP: String
) {
    object Start : GameState(0,0,"","")
    data class Play(
        override val currentScore: Int,
        override val maxScore: Int,
        override val lastUserName: String,
        override val lastUserNameMVP: String
    ) : GameState(currentScore, maxScore, lastUserName, lastUserNameMVP)

    data class GameOver(
        override val maxScore: Int,
        override val lastUserName: String,
        override val lastUserNameMVP: String
    ) : GameState(0, maxScore, lastUserName, lastUserNameMVP)
}

data class GameStateBo(
    var currentScore: Int,
    var maxScore: Int,
    var lastUserName: String,
    var lastUserNameMVP: String
)

fun GameState.mapToBo(): GameStateBo {
    return when (this) {
        is GameState.GameOver -> {
            GameStateBo(0, maxScore, lastUserName, lastUserNameMVP)
        }

        is GameState.Play -> {
            GameStateBo(currentScore, maxScore, lastUserName, lastUserNameMVP)
        }

        GameState.Start -> {
            GameStateBo(0, 0, "", "")
        }
    }
}

fun GameStateBo.mapToVo() : GameState {
    return  GameState.Play(currentScore, maxScore, lastUserName, lastUserNameMVP)
}