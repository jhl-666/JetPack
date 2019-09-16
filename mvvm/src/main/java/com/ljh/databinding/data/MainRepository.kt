package com.ljh.databinding.data

import com.ljh.databinding.data.network.MainNetWork
import com.ljh.room.db.dao.StudentDao
import com.ljh.room.db.table.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository private constructor(private val dao: StudentDao, private val network: MainNetWork) {
    fun getStudents(): MutableList<Student> {
        var students = dao.getAllStudents()

        if (students.isEmpty()) {
            students = network.fetchStudents()
            dao.insertAll(students)
        }

        return students
    }
    /*suspend fun getBingPic(): String {//暂时注掉缓存逻辑
      *//*  var url = dao.getCachedBingPic()
        if (url == null) url = requestBingPic()
        return url*//*
        return requestBingPic()
    }*/
    suspend fun requestBingPic() = withContext(Dispatchers.IO) {
        val url = network.fetchBingPic()
        //weatherDao.cacheBingPic(url)
        url
    }
    companion object {

        private var instance: MainRepository? = null

        fun getInstance(dao: StudentDao, network: MainNetWork): MainRepository {
            if (instance == null) {
                synchronized(MainRepository::class.java) {
                    if (instance == null) {
                        instance =
                            MainRepository(dao, network)
                    }
                }
            }
            return instance!!
        }

    }
}