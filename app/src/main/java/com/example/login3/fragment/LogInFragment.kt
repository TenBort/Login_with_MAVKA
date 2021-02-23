package com.example.login3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.login3.R
import com.romainpiel.shimmer.Shimmer
import com.romainpiel.shimmer.ShimmerTextView
import kotlinx.android.synthetic.main.fragment_log_in.*


class LogInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        button_login.setOnClickListener {
            if (login.text.toString() == "1111" && password.text.toString() == "1111") {
                onLogIn()
            } else {
                Toast.makeText(context, "Error login and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onLogIn() {
        findNavController().navigate(R.id.action_logInFragment_to_listFragment)
    }

}


