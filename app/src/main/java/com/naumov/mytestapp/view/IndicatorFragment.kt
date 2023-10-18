package com.naumov.mytestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.naumov.mytestapp.databinding.FragmentFirstBinding
import com.naumov.mytestapp.databinding.FragmentIndicatorBinding

class IndicatorFragment:Fragment() {

    private var _binding: FragmentIndicatorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIndicatorBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        fun getInstant():IndicatorFragment = IndicatorFragment()
    }
}