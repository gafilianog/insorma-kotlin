package dev.gafilianog.insorma.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
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

//@BindingAdapter("qty")
//fun setQty(view: TextView, qty: Int?) {
//    if (view.text != qty.toString()) {
//        view.text = "" + qty
//    }
//}
//
//@InverseBindingAdapter(attribute = "qty")
//fun getQty(view: TextView): Int {
//    return view.text.toString().toIntOrNull() ?: 0
//}