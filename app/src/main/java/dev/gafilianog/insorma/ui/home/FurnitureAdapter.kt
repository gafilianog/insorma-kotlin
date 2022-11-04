package dev.gafilianog.insorma.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.gafilianog.insorma.data.model.Product
import dev.gafilianog.insorma.databinding.ItemRowFurnitureBinding

class FurnitureAdapter : ListAdapter<Product, FurnitureAdapter.FurnitureHolder>(DiffCallback) {

    class FurnitureHolder(
        private var binding: ItemRowFurnitureBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(products: Product) {
            binding.product = products
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FurnitureHolder {
        return FurnitureHolder(
            ItemRowFurnitureBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FurnitureHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)

        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(position)
            )
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.product_name == newItem.product_name
        }
    }
}
