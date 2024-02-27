package com.example.practica2_android

import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.InputStream


class QuestionDatabase(context : Context) {

    // Lee el json y lo convierte en un String
    //val json = File("resources/kotlin_questions.json").readText()

    val inputStream: InputStream = context.resources.openRawResource(R.raw.kotlin_questions)
    val jsonString = inputStream.bufferedReader().use { it.readText() }


    // Transforma tal String a una lista de objetos Question
    val questionListType = object: TypeToken<List<Question>>() {}.type
    val questions: List<Question> = Gson().fromJson(jsonString, questionListType)

    // Imprime todas las preguntas en orden (no utilizada)
    fun showQuestions() {
        for (q in questions) {
            println("Pregunta: ${q.text}")
            println("Opciones: ${q.options.joinToString()}")
            println("Respuesta: ${q.correctAnswerIndex} \n")
        }
    }

    fun getShuffledQuestionsList(): List<Question> {
        return questions.shuffled()
    }
}