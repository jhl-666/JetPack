package com.ljh.room.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ljh.room.MyApplication
import com.ljh.room.db.dao.StudentDao
import com.ljh.room.db.dao.TeacherDao
import com.ljh.room.db.table.Student
import com.ljh.room.db.table.Teacher

@Database(entities = [Student::class, Teacher::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getStudentDao(): StudentDao

    abstract fun getTeacherDao(): TeacherDao

    companion object {

        val instance = Single.sin

    }

    private object Single {

        val sin :AppDataBase= Room.databaseBuilder(
            MyApplication.instance(),
            AppDataBase::class.java,
            "User.db"
        )
            .allowMainThreadQueries()
            .build()
    }

}