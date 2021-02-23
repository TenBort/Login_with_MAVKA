package com.example.login3.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.login3.R
import com.example.login3.entity.Network
import kotlinx.android.synthetic.main.one_network.view.*
import java.util.*

class ListAdapter(
    private val DeleteClickListener: (Network) -> Unit,
    private val viewDetailClickListener: (Network) -> Unit
) :
    RecyclerView.Adapter<ListAdapter.NetViewHolder>() {

    var list = listOf<Network>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.one_network, parent, false)
        return NetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NetViewHolder, position: Int) {
        holder.root.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_anim)
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.colorLine.setCardBackgroundColor(color)
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class NetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view.rootView
        private var networkItem = view.item_network
        private var loginItem = view.item_login
        private var passwordItem = view.item_password
        private var timeItem = view.timeItem
        private var dayItem = view.dayItem
        private var monthItem = view.monthItem
        val colorLine = view.color_line

        fun bind(network: Network) {

            networkItem.text = network.network
            loginItem.text = network.login
            passwordItem.text = network.password
            timeItem.text = network.time
            dayItem.text = network.day
            monthItem.text = network.month

            itemView.delete_item.setOnClickListener {
                DeleteClickListener.invoke(network)
            }
            itemView.rootView.setOnLongClickListener {
                viewDetailClickListener.invoke(network)
                true
            }
        }

    }
}
