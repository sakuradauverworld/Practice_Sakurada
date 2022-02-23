package com.example.imageviewerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.imageviewerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    class MyAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

        private val resources = listOf(
            R.drawable.gu, R.drawable.choki, R.drawable.pa
        )

        override fun getItemCount(): Int = resources.size

        override fun createFragment(position: Int): Fragment
                = ImageFragment.newInstance(resources[position])
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = MyAdapter(this)
    }
}