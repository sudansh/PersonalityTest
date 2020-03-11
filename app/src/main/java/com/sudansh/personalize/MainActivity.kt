package com.sudansh.personalize

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sudansh.personalize.databinding.MainActivityBinding
import com.sudansh.personalize.ui.main.MainAdapter
import com.sudansh.personalize.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val mainAdapter by lazy { MainAdapter(this) }
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        viewModel.getValues()

        binding.itemRv.adapter = mainAdapter

        viewModel.questionData.observe(this, Observer {
            mainAdapter.setData(it)
        })
    }

}