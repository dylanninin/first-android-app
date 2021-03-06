package com.example.kotlindemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotlindemo.api.API
import com.example.kotlindemo.databinding.FragmentFirstBinding
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.random_button).setOnClickListener {
            val textView = view.findViewById<TextView>(R.id.textview_first)
            var count = textView.text.toString().toInt()
            var action =
                com.example.kotlindemo.FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                    count
                )
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.toast_button).setOnClickListener {
            val myToast = Toast.makeText(context, "Hello Toast", Toast.LENGTH_SHORT)
            myToast.show()
        }

        view.findViewById<Button>(R.id.count_button).setOnClickListener {
            countMe(view)

            requestAPI()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun countMe(view: View) {
        val textView = view.findViewById<TextView>(R.id.textview_first)
        var count = textView.text.toString().toInt()
        count++
        textView.text = count.toString()
    }

    private fun requestAPI() {
        val job = Job()
        val errorHandler = CoroutineExceptionHandler {  _, exception ->
            println("API: error $exception")
        }

        val scope = CoroutineScope(job + Dispatchers.Main)
        scope.launch(errorHandler) {
            val body = API().httpPost()
            println("API: response $body")
        }
    }
}