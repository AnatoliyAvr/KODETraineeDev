package com.tolikavr.kodetraineedev.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tolikavr.kodetraineedev.R
import com.tolikavr.kodetraineedev.data.models.User


class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersAdapterViewHolder>() {

  private var items: List<User> = listOf()

  fun updateData(newData: List<User>) {

    val diffCallback = object : DiffUtil.Callback() {
      override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return items[oldItemPosition].id == newData[newItemPosition].id
      }

      override fun getOldListSize(): Int = items.size

      override fun getNewListSize(): Int = newData.size

      override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return items[oldItemPosition] == newData[newItemPosition]
      }
    }

    val diffResult = DiffUtil.calculateDiff(diffCallback)
    items = newData
    diffResult.dispatchUpdatesTo(this)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapterViewHolder {
    return UsersAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
  }

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: UsersAdapterViewHolder, position: Int) = holder.bind(items[position])

  class UsersAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivAvatar = itemView.findViewById<ImageView>(R.id.iv_avatar)
    private val tvName = itemView.findViewById<TextView>(R.id.tv_name)
    private val tvPosition = itemView.findViewById<TextView>(R.id.tv_position)
    private val tvInitials = itemView.findViewById<TextView>(R.id.tv_initials)

    @SuppressLint("SetTextI18n")
    fun bind(item: User) {

      Glide.with(itemView)
        .load(item.avatarUrl)
        .into(ivAvatar)

      tvName.text = "${item.firstName} ${item.lastName}"
      tvPosition.text = item.position
      tvInitials.text = item.userTag.lowercase()
    }
  }
}