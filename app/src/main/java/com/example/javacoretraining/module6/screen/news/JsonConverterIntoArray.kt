package com.example.javacoretraining.module6.screen.news

import android.content.Context
import java.io.IOException
import java.nio.charset.StandardCharsets.UTF_8

class JsonConverterIntoArray {
    fun getJsonFromAssets(context: Context, fileName: String): String {
        val jsonString: String = try {
            val `is` = context.assets.open(fileName)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null.toString()
        }
        return jsonString
    }
}
