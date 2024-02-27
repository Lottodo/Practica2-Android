package com.example.practica2_android

import android.content.Context

class Game(context : Context) {

    var puntos: Int = 0
    val qDatabase : QuestionDatabase = QuestionDatabase(context)
    var questionsShuffled : List<Question> = qDatabase.getShuffledQuestionsList()
    var noQuestion : Int = 0

    fun checkGameOver(): Boolean {
        if (noQuestion == questionsShuffled.size) {
            println("Gracias por jugar!\nTu puntación final fue: $puntos")
            return true
        }
        else
            return false
    }

    fun doQuestion(ronda: Int): List<String> {
        /*
        println("Pregunta #${noQuestion+1}")
        println(questionsShuffled.elementAt(noQuestion).text)
        questionsShuffled.elementAt(noQuestion).options.forEachIndexed { index, o ->
            print("$index. $o    ")
        }

        print("\n")
        */

        val questionValues : List<String> = listOf((ronda+1).toString(), questionsShuffled.elementAt(ronda).text)
        val optionsValues: List<String> = mutableListOf<String>().apply {
            questionsShuffled.elementAt(ronda).options.forEachIndexed { _, o ->
                add(o)
            }
        }.toList()

        return questionValues + optionsValues
    }

    fun checkQuestion(answer : Int) : Boolean {
        if (answer == questionsShuffled.elementAt(noQuestion).correctAnswerIndex) {
            //puntos += 1
            //println("Correcto! +1 punto\nPuntos: $puntos\n\n")
            //noQuestion += 1

            return true
        }
        else {
            //println("Incorrecto.\nPuntos: $puntos\n\n")
            //noQuestion +=1

            return false
        }
    }

    fun restartGame() {
        noQuestion = 0
        puntos = 0
        questionsShuffled = qDatabase.getShuffledQuestionsList()
    }
}