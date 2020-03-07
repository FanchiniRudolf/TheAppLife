package mx.itesm.lupa

import kotlin.random.Random

class LuPa {

    var numOf1 = 0
        private set
    var numOf0 = 0
        private set

    var gameValuesArr = IntArray(7)
        private set
    var won = false
        private set

    fun shuffleGame(){
        numOf1 = 0
        numOf0 = 0

        for (i in gameValuesArr.indices){
            if(Random.nextBoolean()){
                gameValuesArr[i] = 1
                numOf1++
            }else{
                gameValuesArr[i] = 0
                numOf0++
            }
        }

        if (numOf0 == 0 || numOf1 == 1){
            shuffleGame()
        }
    }

    fun hasWon(): Boolean {
        if (numOf0 == 0 || numOf1 == 1){
            won = true
        }

        return won
    }

    fun changeVal(index: Int){
        if (index == 0 ){
            gameValuesArr[index] = gameValuesArr[index].inv()
            gameValuesArr[index+1] = gameValuesArr[index+1].inv()
        }else if(index == gameValuesArr.size) {
            gameValuesArr[index] = gameValuesArr[index].inv()
            gameValuesArr[index-1] = gameValuesArr[index-1].inv()
        }else{
            gameValuesArr[index] = gameValuesArr[index].inv()
            gameValuesArr[index+1] = gameValuesArr[index+1].inv()
            gameValuesArr[index-1] = gameValuesArr[index-1].inv()
        }
    }
}