package com.loan555.kidsapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.loan555.kidsapplication.R
import com.loan555.kidsapplication.databinding.MainActivityBinding
import com.loan555.kidsapplication.ui.main.account.AccountFragment
import com.loan555.kidsapplication.ui.main.news.NewsFragment
import com.loan555.kidsapplication.ui.viewmodel.ApiTaiKhoanViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainActivityBinding
    private lateinit var accountViewModel: ApiTaiKhoanViewModel
    private val NUM_PAGES = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        accountViewModel = ViewModelProvider(this).get(ApiTaiKhoanViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initController()
    }

    private fun initController() {
        initViewPager()
        initNavigation()
    }

    private fun initNavigation() {
        binding.navBottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_news -> {
                    binding.pagerMain.currentItem = 0
                    true
                }
                R.id.nav_video -> {
                    binding.pagerMain.currentItem = 1
                    true
                }
                R.id.nav_image -> {
                    binding.pagerMain.currentItem = 2
                    true
                }
                R.id.nav_family -> {
                    binding.pagerMain.currentItem = 3
                    true
                }
                else -> true
            }
        }
    }

    private fun initViewPager() {
        binding.pagerMain.adapter = PagerAdapter(supportFragmentManager)
        binding.pagerMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.navBottom.menu.findItem(R.id.nav_news).isChecked = true
                        supportActionBar?.setTitle(R.string.title_home)
                    }
                    1 -> {
                        binding.navBottom.menu.findItem(R.id.nav_video).isChecked = true
                        supportActionBar?.setTitle(R.string.title_video)
                    }
                    2 -> {
                        binding.navBottom.menu.findItem(R.id.nav_image).isChecked = true
                        supportActionBar?.setTitle(R.string.anh)
                    }
                    3 -> {
                        binding.navBottom.menu.findItem(R.id.nav_family).isChecked = true
                        supportActionBar?.setTitle(R.string.title_family)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

    private inner class PagerAdapter(fa: FragmentManager) : FragmentStatePagerAdapter(fa) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment {
            return when (position) {
                3 -> {
                    AccountFragment()
                }
                else -> NewsFragment()
            }
        }
    }
}