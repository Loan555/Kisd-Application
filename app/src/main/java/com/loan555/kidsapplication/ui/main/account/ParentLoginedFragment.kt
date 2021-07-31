package com.loan555.kidsapplication.ui.main.account

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loan555.kidsapplication.R

class ParentLoginedFragment : Fragment() {

    companion object {
        fun newInstance() = ParentLoginedFragment()
    }

    private lateinit var viewModel: ParentLoginedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.parent_logined_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ParentLoginedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}