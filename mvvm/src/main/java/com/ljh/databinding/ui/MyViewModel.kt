package com.ljh.databinding.ui

import android.util.Log
import androidx.lifecycle.*
import com.ljh.databinding.data.MainRepository
import kotlinx.coroutines.launch

class MyViewModel(private val repository: MainRepository) : ViewModel() {
    var name = MutableLiveData<String>()

    var url = MutableLiveData<String>()
    val networkUrl = MutableLiveData<String>()
/*
    val networkUrl : LiveData<String> = liveData {
        emit(repository.requestBingPic())
    }
*/

    val user: LiveData<String> = liveData {
        emit("aaa")//可以等待获取数据
    }

    var studentName = MutableLiveData<String>()

    fun updateName() {
        name.value = "我改变啦，哈哈哈"
        getBingPic()
    }

    fun getStudents() {
        launch {
            var students = repository.getStudents()

            students.forEach {
                studentName.value = studentName.value + it.studentName + it.studentType + "\n"
            }
        }
    }

    fun getBingPic() {
        launch {
            networkUrl.value = repository.requestBingPic()
            Log.e("tag", networkUrl.value)
        }
    }

    private fun launch(block: suspend () -> Unit) = viewModelScope.launch {
        try {
            block()
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}