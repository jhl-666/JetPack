package com.ljh.databinding.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ljh.databinding.data.MainRepository

class MainModelFactory(private val repository: MainRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(repository) as T
    }

}