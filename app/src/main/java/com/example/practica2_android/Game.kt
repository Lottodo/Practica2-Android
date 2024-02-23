package com.example.practica2_android

class Game {

    var puntos: Int = 0
    val qDatabase : QuestionDatabase = QuestionDatabase()
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

    fun doQuestion() {
        println("Pregunta #${noQuestion+1}")
        println(questionsShuffled.elementAt(noQuestion).text)
        questionsShuffled.elementAt(noQuestion).options.forEachIndexed { index, o ->
            print("$index. $o    ")
        }
        print("\n")
    }

    fun checkQuestion(answer : Int) {
        if (answer == questionsShuffled.elementAt(noQuestion).correctAnswerIndex) {
            puntos += 1
            println("Correcto! +1 punto\nPuntos: $puntos\n\n")
            noQuestion += 1
        }
        else {
            println("Incorrecto.\nPuntos: $puntos\n\n")
            noQuestion +=1
        }
    }

    fun restartGame() {
        noQuestion = 0
        puntos = 0
        questionsShuffled = qDatabase.getShuffledQuestionsList()
    }
}