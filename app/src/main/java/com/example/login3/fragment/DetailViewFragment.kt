package com.example.login3.fragment

import android.app.Activity
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.login3.R
import com.example.login3.entity.Network
import com.example.login3.viewmodel.NetViewModel
import kotlinx.android.synthetic.main.fragment_detail_view.*

class DetailViewFragment : Fragment() {

    private val netViewModel: NetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        netViewModel.oneNetwork.observe(viewLifecycleOwner, ::detailView)

        detailSaveNetworkButton.setOnClickListener {
            startAnimationSave()
            updateNetwork(netViewModel.oneNetwork.value!!)
            closeKeyboard()
        }

        detailBackButton.setOnClickListener {
            startAnimationBack()
            closeKeyboard()
            findNavController().popBackStack()
        }
    }

    private fun detailView(network: Network) {
        edit_network_detail.setText(network.network)
        edit_login_detail.setText(network.login)
        edit_password_detail.setText(network.password)
    }

    private fun startAnimationBack() {
        detailBackButton.playAnimation()
        detailBackButton.setMinAndMaxFrame(20, 120)
    }

    private fun startAnimationSave() {
        detailSaveNetworkButton.playAnimation()
    }

    private fun updateNetwork(network: Network) {
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH).toString()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val actualTime: String = if (minute < 10) {
            "0$minute"
        } else {
            "$minute"
        }
        val time = "$hour:$actualTime"
        var monthText = ""

        when (month) {
            0 -> monthText = "Січня"
            1 -> monthText = "Лютого"
            2 -> monthText = "Березня"
            3 -> monthText = "Квітня"
            4 -> monthText = "Травня"
            5 -> monthText = "Червня"
            6 -> monthText = "Липня"
            7 -> monthText = "Серпня"
            8 -> monthText = "Вересня"
            9 -> monthText = "Жовтня"
            10 -> monthText = "Листопада"
            11 -> monthText = "Грудня"
        }

        val net = edit_network_detail.text.toString()
        val log = edit_login_detail.text.toString()
        val pass = edit_password_detail.text.toString()

        if (net.isBlank() || log.isBlank() || pass.isBlank()) {
            Toast.makeText(context, "Введіть дані", Toast.LENGTH_SHORT).show()
            return
        }
        val updatedNetwork = Network(
            id = network.id,
            network = net,
            login = log,
            password = pass,
            day = day,
            month = monthText,
            time = time
        )
        netViewModel.updateNetwork(updatedNetwork)
        findNavController().popBackStack()
    }

    private fun closeKeyboard() {
        val imm: InputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
