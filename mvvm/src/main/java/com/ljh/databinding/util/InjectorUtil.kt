package com.ljh.databinding.util

import com.ljh.databinding.ui.MainModelFactory
import com.ljh.databinding.data.network.MainNetWork
import com.ljh.databinding.data.MainRepository
import com.ljh.room.db.AppDataBase

object InjectorUtil {
    private fun getMainRepository() = MainRepository.getInstance(AppDataBase.instance.getStudentDao(), MainNetWork.getInstance())

    fun getMainFactory() = MainModelFactory(getMainRepository())
}