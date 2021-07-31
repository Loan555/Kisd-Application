package com.loan555.kidsapplication.ui.main.news

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.loan555.kidsapplication.R
import com.loan555.kidsapplication.databinding.NewsFragmentBinding
import com.loan555.kidsapplication.model.LoaiThongTinTuyenTruyen
import com.loan555.kidsapplication.model.ThongTinTuyenTruyen
import com.loan555.kidsapplication.ui.adapter.LoaiTinAdapter
import com.loan555.kidsapplication.ui.adapter.NewsAdapter
import com.loan555.kidsapplication.ui.detailnew.DetailActivity
import com.loan555.kidsapplication.ui.main.MainViewModel
import java.lang.Exception

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: NewsFragmentBinding

    private var isLoadingLoaiTin = true
    private var isLoadingTin = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = activity?.let {
            ViewModelProviders.of(it).get(MainViewModel::class.java)
        } ?: throw Exception("Activity is null")
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false)
        binding.lifecycleOwner = this
        binding.newsViewModel = newsViewModel
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initController()
        initEvent()
        initData()
    }

    private fun initData() {
        viewModel.loadLoaiTin()
        viewModel.loadListThongTin(20)
    }

    private fun initEvent() {
        binding.swipeRefresh.setOnRefreshListener {
            newsViewModel.isPageLoading.value = true
            viewModel.loadLoaiTin()
            when (newsViewModel.danhMucHienTai.value) {
                "trangchu" -> {
                    viewModel.loadListThongTin(20)
                }
                else -> {
                    if (newsViewModel.danhMucHienTai.value != null)
                        viewModel.loadListThongTinTheoTheLoai(
                            newsViewModel.danhMucHienTai.value!!,
                            20
                        )
                }
            }
        }
    }

    private fun initController() {
        newsViewModel.isPageLoading.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = it
        })
        //-------------loai tin
        binding.recycleLoaiTin.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapterLoaiTin = LoaiTinAdapter(this.requireContext(), onItemClick)
        viewModel.getLoaiTin().observe(viewLifecycleOwner, {
            adapterLoaiTin.setList(it)
            isLoadingLoaiTin = false
            if (!isLoadingLoaiTin && !isLoadingTin)
                newsViewModel.isPageLoading.value = false
        })
        binding.recycleLoaiTin.adapter = adapterLoaiTin

        //-----------------tin
        binding.recycleListTin.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapterTin = NewsAdapter(this.requireContext(), onNewsClick)
        viewModel.getListThongTinTuyenTruyen().observe(viewLifecycleOwner, {
            adapterTin.setList(it)
            if (it.isNotEmpty()) {
                isLoadingTin = false
                if (!isLoadingLoaiTin && !isLoadingTin)
                    newsViewModel.isPageLoading.value = false
            }
        })
        viewModel.getListThongTinTuyenTruyenTheoTheLoai().observe(viewLifecycleOwner, {
            adapterTin.setList(it)
            isLoadingTin = false
            if (!isLoadingTin && !isLoadingTin)
                newsViewModel.isPageLoading.value = false
        })
        binding.recycleListTin.adapter = adapterTin
    }

    private val onItemClick: (Int, LoaiThongTinTuyenTruyen) -> Unit = { pos, loaiTin ->
        newsViewModel.title.value = loaiTin.tenLoai
        newsViewModel.isPageLoading.value = true
        newsViewModel.danhMucHienTai.value = loaiTin.id
        when (loaiTin.id) {
            "trangchu" -> {
                viewModel.loadListThongTin(20)
            }
            else -> {
                viewModel.loadListThongTinTheoTheLoai(loaiTin.id, 20)
            }
        }
    }

    private val onNewsClick: (Int, ThongTinTuyenTruyen) -> Unit = { pos, tin ->
        val intent = Intent(this.context, DetailActivity::class.java)
        intent.putExtra("detail", tin.id)
        startActivity(intent)
    }
}