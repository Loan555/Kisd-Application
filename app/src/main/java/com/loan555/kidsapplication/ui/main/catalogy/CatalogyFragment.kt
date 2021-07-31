package com.loan555.kidsapplication.ui.main.catalogy

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loan555.kidsapplication.R

class CatalogyFragment : Fragment() {

    companion object {
        fun newInstance() = CatalogyFragment()
    }

    private lateinit var viewModel: CatalogyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.catalogy_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CatalogyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}