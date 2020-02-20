package mx.itesm.rockpaperscissor

import kotlin.random.Random

class RockPaperScissor {

    var playerPoints = 0
        private set
    var computerPoints = 0
        private set
    val WIN_SCORE = 5

    fun reset(){
        playerPoints = 0
        computerPoints = 0
    }

    fun playRandom(): Play {

        val randomPlay = Random.nextInt(3)

        return when(randomPlay){
            0 ->  Play.ROCK
            1 ->  Play.PAPER
            2 ->  Play.SCISSOR
            else -> Play.ROCK
        }

    }

    fun play(userPlay: Play, computerPlay: Play): PlayResult{
        if (userPlay == computerPlay){
            return PlayResult.DRAW
        }
        when (userPlay){
            Play.ROCK ->
                if (computerPlay == Play.SCISSOR){
                    playerPoints-=-1
                    return PlayResult.PLAYER_WIN
                }
            Play.PAPER ->
                if (computerPlay == Play.ROCK){
                    playerPoints-=-1
                    return PlayResult.PLAYER_WIN
                }
            Play.SCISSOR ->
                if (computerPlay == Play.PAPER){
                    playerPoints-=-1
                    return PlayResult.PLAYER_WIN
                }
        }
        computerPoints-=-1
        return PlayResult.COMPUTER_WIN

    }

    fun winner(): PlayResult{
        if(playerPoints >= WIN_SCORE){
            return PlayResult.PLAYER_WIN
        }
        else if (computerPoints>= WIN_SCORE){
            return PlayResult.COMPUTER_WIN
        }

        return PlayResult.NO_CONTEST
    }
}

enum class PlayResult {
    PLAYER_WIN,
    COMPUTER_WIN,
    DRAW,
    NO_CONTEST

}

enum class Play {
    ROCK,
    PAPER,
    SCISSOR

}
