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
        won = false
        for (i in gameValuesArr.indices){
            if(Random.nextBoolean()){
                gameValuesArr[i] = 1
                numOf1++
            }else{
                gameValuesArr[i] = 0
                numOf0++
            }
        }

        if (numOf0 == 0 || numOf1 == 0){
            shuffleGame()
        }
    }

    fun hasWon(): Boolean {
        won = numOf0 == 0 || numOf1 == 0

        return won
    }

    private 
    fun changeVal(index: Int){
        if (gameValuesArr[index] == 0){
            numOf1++
            numOf0--
        }else{
            numOf0++
            numOf1--
        }
        gameValuesArr[index] = gameValuesArr[index] xor 1

    }

    fun changeContiguos(index: Int){
        if (index == 0 ){
            changeVal(index)
            changeVal(index+1)
        }else if(index == gameValuesArr.size-1) {
            changeVal(index)
            changeVal(index-1)
        }else{
            changeVal(index)
            changeVal(index+1)
            changeVal(index-1)
        }
    }
}