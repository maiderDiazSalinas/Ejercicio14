package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFirstBinding
import com.example.myapplication.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {


    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).edad?.let { it ->
            if (it >= 18) {
                binding.rbcoche1.visibility = View.VISIBLE
                binding.rbcoche2.visibility = View.VISIBLE
                binding.rbcoche3.visibility = View.VISIBLE
            } else {
                binding.rbcoche1.visibility = View.GONE
                binding.rbcoche2.visibility = View.GONE
                binding.rbcoche3.visibility = View.GONE
            }
        } ?: findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)

        binding.boton.setOnClickListener {
            (activity as MainActivity).vehiculo=
                when {
                    binding.rbcoche1.isChecked->binding.rbcoche1.text.toString()
                    binding.rbcoche2.isChecked->binding.rbcoche2.text.toString()
                    binding.rbcoche3.isChecked->binding.rbcoche3.text.toString()
                    binding.rbmoto1.isChecked->binding.rbmoto1.text.toString()
                    binding.rbmoto2.isChecked->binding.rbmoto2.text.toString()
                    binding.rbmoto3.isChecked->binding.rbmoto3.text.toString()
                    else->null
                }

            findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}