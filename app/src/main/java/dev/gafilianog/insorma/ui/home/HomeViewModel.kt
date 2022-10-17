package dev.gafilianog.insorma.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gafilianog.insorma.data.model.Product
import dev.gafilianog.insorma.data.remote.InsormaApi
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    init {
        getFurnitures()
    }

    private fun getFurnitures() {
        viewModelScope.launch {
            val listFurniture = InsormaApi.retrofitService.getProducts()
            _products.value = listFurniture.furnitures
            Log.d("WLAWLEO", _products.value!!.size.toString())
        }
    }
}