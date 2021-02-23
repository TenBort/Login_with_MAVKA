package com.example.login3.repo

import com.example.login3.entity.Network
import com.example.login3.room.NetDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class NetRepo @Inject constructor(private val netDao: NetDao) :
    CoroutineScope {

    private val networks = netDao.getNetworks()

    fun getNetworkList() = networks


    suspend fun updateNetwork(network: Network) {
        netDao.updateNetwork(network)
    }
    suspend fun setNetworks(network: Network) {
        netDao.setNetwork(network)
    }

    suspend fun deleteNetwork(network: Network) {
        netDao.deleteNetworks(network)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default

}