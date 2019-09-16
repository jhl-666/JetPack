package com.ljh.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ljh.room.db.AppDataBase
import com.ljh.room.db.dao.StudentDao
import com.ljh.room.db.table.Student

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sDao: StudentDao = AppDataBase.instance.getStudentDao()
/*
        var s_1 = Student(1, "s1", "小学")
        var s_2 = Student(2, "s2", "小学")
        var s_3 = Student(3, "s3", "小学")
        var s_6 = Student(6, "s6", "大学")
        var s_5 = Student(5, "s5", "大学")
        var s_4 = Student(4, "s4", "大学")

        var sList: MutableList<Student> = mutableListOf<Student>()

        sList.add(s_1)
        sList.add(s_2)
        sList.add(s_3)
        sList.add(s_6)
        sList.add(s_5)
        sList.add(s_4)

//可以直接把list传进去，也可以一个一个单独添加
        sDao.insertAll(sList)*/
        //注意，这里我用的是insert，而不是 update
        sDao.insert(Student(1, "s1", "大学"))
        var sList_select_1: MutableList<Student> = sDao.getAllStudents()

        for (i in sList_select_1.indices) {
            println(sList_select_1.get(i))
        }



    }
}
