package dev.gafilianog.insorma.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gafilianog.insorma.data.local.LoggedInUserDatastoreImpl
import dev.gafilianog.insorma.data.model.Product
import dev.gafilianog.insorma.data.remote.FurnitureData
import dev.gafilianog.insorma.data.remote.InsormaApi
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

//    private val _username = MutableLiveData<String>()
//    val username: LiveData<String> = _username

    init {
        _products.value = FurnitureData.products
    }

//    private fun getFurnitures() {
//        viewModelScope.launch {
//            try {
//                _products.value = InsormaApi.retrofitService.getProducts().furnitures
////                Log.d("WLAWLEO", _products.value!!.size.toString())
//            } catch (e: Exception) {
//                _products.value = listOf()
//                e.printStackTrace()
//            }
//        }
//    }
}