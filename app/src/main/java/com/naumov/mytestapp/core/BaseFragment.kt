package com.naumov.mytestapp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment<ViewModel:BaseViewModel, Binding: ViewBinding>(layoutId: Int) : Fragment(layoutId){

    private lateinit var binding: Binding
    private lateinit var viewModel:ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun setupView(){
    }

    fun setupListener(){}
    fun setupObserver(){}
}