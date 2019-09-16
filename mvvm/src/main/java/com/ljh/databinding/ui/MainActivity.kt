package com.ljh.databinding.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.ljh.databinding.R
import com.ljh.databinding.databinding.ActivityMainBinding
import com.ljh.databinding.ui.listbind.RecyclerViewBindingActivity
import com.ljh.databinding.util.InjectorUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this,InjectorUtil.getMainFactory()).get(MyViewModel::class.java) }
    private val binding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this,
        R.layout.activity_main
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this//这句非常重要，当LiveData更新的时候，会调用LiveDataListener中的onChange方法，然后binding会刷新对应FieldID的UI。

        viewModel.name.value = "还有谁"

        viewModel.url.value = "http://pic25.nipic.com/20121112/9252150_150552938000_2.jpg"

        textView.setOnClickListener {
            startActivity(Intent(this, RecyclerViewBindingActivity::class.java))
        }

        observe()
    }


    private fun observe() {
       /* viewModel.name.ob serve(this, Observer {
            textView2.text = it
        })
        viewModel.students.observe(this, Observer {
            it.forEach {
                textView3.text =  "${textView3.text.toString()}\n${it.studentName}"
            }
        })*/

        viewModel.getStudents()
    }

}
