package com.ljh.databinding.data.network

import com.ljh.databinding.data.network.api.WeatherService
import com.ljh.room.db.table.Student
import retrofit2.await

class MainNetWork {

    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    suspend fun fetchBingPic() = weatherService.getBingPic().await()

    fun fetchStudents(): MutableList<Student> {//临时写死数据,没有从网络请求
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

        return sList
    }

    companion object {

        private var network: MainNetWork? = null

        fun getInstance(): MainNetWork {
            if (network == null) {
                synchronized(MainNetWork::class.java) {
                    if (network == null) {
                        network = MainNetWork()
                    }
                }
            }
            return network!!
        }

    }
}