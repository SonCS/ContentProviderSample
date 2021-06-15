package com.csson.example.app_update

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contentResolver.query(
            Uri.parse("content://com.sooil.dana.provider"),

        )







        contentResolver.update(
            Uri.parse("content://com.sooil.dana.provider"),
            ContentValues().apply {
                put("first_name", "last")
                put("last_name", "first")
            },
            null,
            null
        )


//        val cursor = contentResolver.query(
//            Uri.parse("content://com.sooil.dana.provider/"),
//        null,
//        null,
//        null, null)
//
//        if(cursor != null) {
//            Log.e("scs", "cursor.columnCount = ${cursor.columnCount}")
//        }
//        cursor?.close()
    }
}