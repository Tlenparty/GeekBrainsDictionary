package com.geekbrains.geekbrainsdictionary.ui.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.geekbrainsdictionary.databinding.ItemWordBinding
import com.geekbrains.geekbrainsdictionary.model.data.DataModel

class HistoryAdapter : ListAdapter<DataModel, HistoryAdapter.HistoryViewHolder>(HistoryCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class HistoryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        ItemWordBinding.inflate(parent.inflater(), parent, false).root
    ) {

        fun bind(data: DataModel) {
            val binding = ItemWordBinding.bind(itemView)

            binding.headerTextviewRecyclerItem.text = data.text
            binding.descriptionTextviewRecyclerItem.text =
                data.meaning?.firstOrNull()?.translation?.translation
        }
    }

    private fun ViewGroup.inflater() = LayoutInflater.from(context)
}

object HistoryCallback : DiffUtil.ItemCallback<DataModel>() {

    // Равен новый item предыдущему? Анимация замены
    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

    // Если item равны то не поменялись ли они вобще. Анимация смены контента
    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

}