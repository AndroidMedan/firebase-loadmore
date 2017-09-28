package com.app.chatbot.loadmore

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.chatbot.R
import com.app.chatbot.databinding.UsersBinding

/**
 * Created by putraxor on 27/09/17.
 */
class UserActivity : AppCompatActivity() {
    private val viewModel = UserViewModel()

    private val binding by lazy {
        DataBindingUtil.setContentView<UsersBinding>(this, R.layout.users)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.model = viewModel
        viewModel.loadMore()
        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.postDelayed({
                viewModel.loadMore()
                binding.swipeLayout.isRefreshing = false
            }, 500L)
        }
    }
}