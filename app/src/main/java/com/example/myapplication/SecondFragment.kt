package com.example.myapplication

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        activity?.title="Login"

        binding.botonDevolver.setOnClickListener {
            if (comprobar())
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun comprobar() : Boolean{
        if(binding.etNombre.text.isEmpty()
            || binding.etApellidos.text.isEmpty()
            || binding.etEdad.text.isEmpty()){
            Toast.makeText(activity,"No has insertado todos los datos",Toast.LENGTH_LONG).show()
            return false
        }
        else {
            (activity as MainActivity).nombre = binding.etNombre.text.toString()
            (activity as MainActivity).apellidos = binding.etApellidos.text.toString()
            (activity as MainActivity).edad = binding.etEdad.text.toString().toInt()
            return true
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.action_login)?.isVisible=false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_comprar -> {
                if (comprobar())
                    findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}