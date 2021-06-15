package com.csson.example.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.csson.example.contentprovider.database.AppDatabase
import com.csson.example.contentprovider.database.user.User
import com.csson.example.contentprovider.database.user.UserDao

class MyContentProvider : ContentProvider() {
    private var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    private var db: AppDatabase? = null
    private var userDao: UserDao? = null


    override fun onCreate(): Boolean {
        db = AppDatabase.getInstance(context!!)
        userDao = db?.userDao()

        return true
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }



    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return when(uriMatcher.match(uri)) {
            1 -> {
                userDao?.getUserById(0)
                Log.e("scs", "query 여기까지옴")

                null
            }
            else -> {
                throw Exception("")
            }
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return when(uriMatcher.match(uri)) {
            1 -> {
                if(values?.size() ?: 0 > 0 ) {
                    if (values != null) {
                        userDao?.update(User(1, values.get("first_name").toString(), values.get("last_name").toString()))
                    }
                }
                1
            }
            else -> throw java.lang.Exception("")
        }
    }

    companion object {
        const val AUTHORITY = "com.sooil.dana.provider"
        val URI_USER: Uri = Uri.parse(
            "content://$AUTHORITY"
        )
    }

    init {
        uriMatcher.apply {
            addURI(AUTHORITY, null, 1)
        }
    }
}