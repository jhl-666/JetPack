package com.ljh.room.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ljh.room.db.table.Student

@Dao
interface StudentDao:BaseDao<Student> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(element: Student)

    @Query("select * from Student")
    fun getAllStudents():MutableList<Student>

    @Query("select * from Student where studentID = :studentID")
    fun getStudnet(studentID:Int): Student

    @Query("select * from Student order by studentID desc ")
    fun getAllByDateDesc():MutableList<Student>

    @Query("delete from Student")
    fun deleteAll()

}