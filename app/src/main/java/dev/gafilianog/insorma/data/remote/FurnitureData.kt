package dev.gafilianog.insorma.data.remote

import dev.gafilianog.insorma.data.model.Product
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object FurnitureData {
    var products: List<Product> = emptyList()

    fun getFurnitureData() {
        GlobalScope.launch {
            try {
                products = ApiClient.api.getProducts().furnitures
            } catch (e: Exception) {
                products = emptyList()
                e.printStackTrace()
            }
        }
    }
}
