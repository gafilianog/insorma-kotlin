package dev.gafilianog.insorma.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {

    private val _image = MutableLiveData<String>()
    val image: LiveData<String>
        get() = _image

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _price = MutableLiveData<String>()
    val price: LiveData<String>
        get() = _price

    private val _rating = MutableLiveData<String>()
    val rating: LiveData<String>
        get() = _rating

    private val _desc = MutableLiveData<String>()
    val desc: LiveData<String>
        get() = _desc

    init {

    }

    fun buy() {

    }
}