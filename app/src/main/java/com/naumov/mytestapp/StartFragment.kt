package com.naumov.mytestapp

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naumov.mytestapp.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    companion object {
        fun newInstance() = StartFragment()
    }
    private var _binding:FragmentStartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStartBinding.inflate(inflater,container,false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}