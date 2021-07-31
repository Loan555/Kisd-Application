package com.loan555.kidsapplication.ui.detailnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.loan555.kidsapplication.R
import com.loan555.kidsapplication.databinding.DetailActivityBinding
import com.loan555.kidsapplication.ui.detailnew.detail.DetailViewModel
import com.loan555.kidsapplication.ui.main.MainViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: DetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.detail_activity)
        binding.lifecycleOwner = this
        binding.detailViewModel = detailViewModel
        binding.viewModel = viewModel

        val id = intent.getStringExtra("detail")
        Log.d("aaa", "id= $id")
        if (id != null) {
            viewModel.loadDetail(id).observe(this, {
                binding.ngayDang.text = it.ngayDang.substring(0, 10)
                binding.tenBaiViet.text = it.tenBaiViet
                binding.chuThich.text = "#${it.chuThich}"
                binding.loaiTin.text = "#${it.loaiThongTinTuyenTruyen.tenLoai}"
                binding.tacGia.text = it.tacGia
                binding.noiDung.text = android.text.Html.fromHtml(it.noiDung)
                Glide.with(this).load(it.anhDaiDien).into(binding.anhDaiDien)
            })
        }
    }
}