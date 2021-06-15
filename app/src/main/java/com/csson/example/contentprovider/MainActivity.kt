package com.csson.example.contentprovider

import android.database.ContentObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.loader.app.LoaderManager
import androidx.room.Room
import com.csson.example.contentprovider.database.AppDatabase
import com.csson.example.contentprovider.database.user.User
import com.csson.example.contentprovider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var db: AppDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getInstance(applicationContext)
    }

    override fun onResume() {
        super.onResume()

        Thread {
            if(db?.userDao()?.getAll()?.count() ?: 0 == 0) {
                db?.userDao()?.insert(User(1, "first", "last"))
            }
            db?.userDao()?.getUserById(1)?.let {
                runOnUiThread {
                    binding.txtName.text = "${it.firstName} ${it.lastName}"
                }
            }
        }.start()
    }

    fun onClickReset(view: View) {
        Thread {
            db?.userDao()?.update((User(1, "first", "last")))

            db?.userDao()?.getUserById(1)?.let {
                runOnUiThread {
                    binding.txtName.text = "${it.firstName} ${it.lastName}"
                }
            }
        }.start()
    }
}