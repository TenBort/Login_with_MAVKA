package com.example.login3.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login3.R
import com.example.login3.adapter.ListAdapter
import com.example.login3.entity.Network
import com.example.login3.viewmodel.NetViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private val netViewModel: NetViewModel by activityViewModels()
    private val listAdapter = ListAdapter(::deleteItem, ::detailItem)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val llm = LinearLayoutManager(this.context)
        network_recycler.layoutManager = llm
        network_recycler.adapter = listAdapter

        onObserve()
        floatingActionButton.setOnClickListener {
            addNewNetwork()
        }
    }

    private fun deleteItem(network: Network) {
        netViewModel.deleteNetwork(network)
    }

    private fun detailItem(network: Network) {
        netViewModel.setDetailNetwork(network)
        findNavController().navigate(R.id.action_listFragment_to_detailViewFragment)
    }

    private fun addNewNetwork() {
        findNavController().navigate(R.id.action_listFragment_to_newNetworkFragment)
    }

    private fun onObserve() {
        netViewModel.getNetworks()
        netViewModel.networksLiveData.observe(viewLifecycleOwner, Observer {
            listAdapter.list = it
            counter.text = "Всього записів: ${it.size} "
            listAdapter.notifyDataSetChanged()
        })
    }
}