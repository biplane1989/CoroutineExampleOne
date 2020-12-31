package com.example.coroutineexampleone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.coroutineexampleone.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //    private val homeViewModel: HomeViewModel() by viewModels()
    private lateinit var homeViewModel: HomeViewModel
    val TAG = "TAG"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.root.postDelayed(object : Runnable {
//            override fun run() {
//                Log.d(TAG, "runing: ")
//                binding.root.postDelayed(this, 200)
//            }
//
//        }, 200)

         binding.btnA.setOnClickListener(View.OnClickListener {
             homeViewModel.start()
         })

         binding.btnB.setOnClickListener(View.OnClickListener {
             homeViewModel.stop()
         })

         binding.btnFlow.setOnClickListener {
             homeViewModel.runCollect()
         }

         homeViewModel.number.observe(viewLifecycleOwner) {
             binding.tvHome.text = it.toString()
         }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}