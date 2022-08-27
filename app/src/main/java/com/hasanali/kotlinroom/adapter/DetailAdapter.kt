package com.hasanali.kotlinroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hasanali.kotlinroom.databinding.RecyclerRowBinding
import com.hasanali.kotlinroom.model.User

class DetailAdapter(val userList: List<User>): RecyclerView.Adapter<DetailAdapter.DetailHolder>() {

    class DetailHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        holder.binding.textViewId.text = userList[position].userId.toString()
        holder.binding.textViewFirstName.text = userList[position].firstName
        holder.binding.textViewLastName.text = userList[position].lastName
        // linear layout -> long click -> remove? -> delete
    }

    override fun getItemCount(): Int { return userList.size }
}