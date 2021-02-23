package com.example.login3.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login3.entity.Network
import com.example.login3.repo.NetRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NetViewModel @ViewModelInject constructor(private val repository: NetRepo) :
    ViewModel() {

    val networksLiveData: LiveData<List<Network>> = repository.getNetworkList()

    fun getNetworks() = networksLiveData

    var oneNetwork = MutableLiveData<Network>()

    fun setNetwork(network: Network) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setNetworks(network)
        }
    }

    fun setDetailNetwork (network: Network){
        oneNetwork.value = network
    }

    fun updateNetwork (network: Network){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNetwork(network)
        }
    }

    fun deleteNetwork(network: Network) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNetwork(network)
        }
    }
}