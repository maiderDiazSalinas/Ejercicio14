package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        activity?.title="Bienvenido"

        if ((activity as MainActivity).nombre!=null
            && (activity as MainActivity).apellidos!=null
            && (activity as MainActivity).edad!=null){
            binding.tvDatos.text="Hola " + (activity as MainActivity).nombre + " "+ (activity as MainActivity).apellidos
        }

        if((activity as MainActivity).vehiculo!=null){
            Toast.makeText(activity,
                String.format("Compra realizada correctamente.\n Articulo comprado: " +
                        "${(activity as MainActivity).vehiculo}"), Toast.LENGTH_LONG).show()
        }


        binding.boton1.setOnClickListener {
            login()
        }
        binding.boton2.setOnClickListener {
            comprar()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun login(){
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    private fun comprar(){
        if(binding.tvDatos.text.isEmpty()){
            Toast.makeText(activity,"Tienes que insertar primero tus datos",Toast.LENGTH_LONG).show()
        }
        else {
            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_login -> login()
            R.id.action_comprar -> comprar()
        }
        return super.onOptionsItemSelected(item)
    }
}