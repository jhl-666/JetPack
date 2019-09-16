package com.ljh.room.db.dao

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)//有冲突的时候替换
    fun insertAll(list: MutableList<T>)

    @Delete
    fun delete(element: T)

    @Delete
    fun deleteList(elements:MutableList<T>)

    @Delete
    fun deleteSome(vararg elements:T)

    @Update
    fun update(element: T)

}