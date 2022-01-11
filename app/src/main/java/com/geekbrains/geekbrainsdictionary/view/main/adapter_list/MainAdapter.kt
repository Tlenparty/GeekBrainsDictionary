package com.geekbrains.geekbrainsdictionary.view.main.adapter_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.geekbrainsdictionary.databinding.ItemWordBinding
import com.geekbrains.geekbrainsdictionary.model.data.DataModel

class MainAdapter(
    private val itemClickListener: (DataModel) -> Unit,
) : ListAdapter<DataModel, MainAdapter.MainViewHolder>(MainCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        // Создаем ViewHolder
        return MainViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        ItemWordBinding.inflate(parent.inflater(),parent,false).root
    ) {

        fun bind(data:DataModel) {
            val binding = ItemWordBinding.bind(itemView)

            binding.headerTextviewRecyclerItem.text = data.text
            binding.descriptionTextviewRecyclerItem.text =
                data.meaning?.firstOrNull()?.translation?.translation

            binding.root.setOnClickListener{ itemClickListener(data) }
        }
    }

    private fun ViewGroup.inflater() = LayoutInflater.from(context)
}

object MainCallback : DiffUtil.ItemCallback<DataModel>() {

    // Равен новый item предыдущему? Анимация замены
    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

    // Если item равны то не поменялись ли они вобще. Анимация смены контента
    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }

}