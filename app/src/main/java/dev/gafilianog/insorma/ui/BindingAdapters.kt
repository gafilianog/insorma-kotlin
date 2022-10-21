package dev.gafilianog.insorma.ui

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.gafilianog.insorma.R
import dev.gafilianog.insorma.data.model.Product
import dev.gafilianog.insorma.ui.home.FurnitureAdapter

@BindingAdapter("listData")
fun bindRecyclerView(rv: RecyclerView, data: List<Product>?) {
    val adapter = rv.adapter as FurnitureAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            error(R.drawable.ic_baseline_broken_image_24)
        }
    }
}