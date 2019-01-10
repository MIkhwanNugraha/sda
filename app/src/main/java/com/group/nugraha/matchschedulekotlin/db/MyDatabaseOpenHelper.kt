package com.group.nugraha.matchschedulekotlin.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper (ctx: Context) : ManagedSQLiteOpenHelper
    (ctx, "PreferredMatch.db", null, 1) {

    companion object {
        private var instancee: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstancez(ctx: Context): MyDatabaseOpenHelper{
            if (instancee == null) {
                instancee = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instancee as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db.createTable(Preferred.TABLE_PREFERRED, true,
            Preferred.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Preferred.ID_EVENT to TEXT,
            Preferred.DATE_EVENT to TEXT,
            Preferred.ID_HOME to TEXT,
            Preferred.HOME_SCORE to TEXT,
            Preferred.HOME_NAME to TEXT,
            Preferred.ID_AWAY to TEXT,
            Preferred.AWAY_SCORE to TEXT,
            Preferred.AWAY_NAME to TEXT
        )
   }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db.dropTable()
    }
}