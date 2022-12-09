package com.example.mymcq.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Exam::class, Questions::class], version = 1, exportSchema = false)
abstract class ExamDataBase: RoomDatabase() {

    abstract fun examDao(): ExamDao


    companion object {
        val DATABASE_NAME: String = "examDb"

        @Volatile
        private var INSTANCE: ExamDataBase? = null


        fun getDataBase(context: Context, scope: CoroutineScope): ExamDataBase {
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExamDataBase::class.java, DATABASE_NAME
                )
                        .addCallback(ProductDatabaseCallBack(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }


    }

    private class ProductDatabaseCallBack(val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE.let { database ->
                scope.launch {
                    if (database != null) {
                        populateDatabase(database.examDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(dao: ExamDao) {

        }
    }
}
