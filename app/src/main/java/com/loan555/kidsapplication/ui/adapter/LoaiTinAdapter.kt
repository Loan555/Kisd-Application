package com.loan555.kidsapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.loan555.kidsapplication.R
import com.loan555.kidsapplication.model.LoaiThongTinTuyenTruyen

class LoaiTinAdapter(
    private val context: Context,
    val onItemClick: (Int, LoaiThongTinTuyenTruyen) -> Unit
) :
    RecyclerView.Adapter<LoaiTinAdapter.MyViewHolder>() {
    private var list: List<LoaiThongTinTuyenTruyen> = listOf()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.tenLoaiTin)
        private val itemLayout: FrameLayout = itemView.findViewById(R.id.itemLayout)
        fun onBind(loaiTin: LoaiThongTinTuyenTruyen) {
            name.text = loaiTin.tenLoai
            itemLayout.setOnClickListener {
                onItemClick(layoutPosition, loaiTin)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.loaitin_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setList(newList: List<LoaiThongTinTuyenTruyen>) {
        this.list = listOf(LoaiThongTinTuyenTruyen("trangchu", "Trang chủ", 0))
        this.list += newList
        notifyDataSetChanged()
    }
}