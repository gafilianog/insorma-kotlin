package dev.gafilianog.insorma.ui.detail

import android.widget.Toast
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.gafilianog.insorma.util.GenericHelper

//import dev.gafilianog.insorma.util.toastMaker

class DetailViewModel : ViewModel(), Observable {

    var PRODUCT_PRICE = 0

    private val _qty = MutableLiveData<String?>()
    val qty: LiveData<String?>
        get() = _qty

    private val _qtyErrStatus = MutableLiveData<Boolean>()
    val qtyErrStatus: LiveData<Boolean>
        get() = _qtyErrStatus

    private val _buyStatus = MutableLiveData<Boolean>()
    val buyStatus: LiveData<Boolean>
        get() = _buyStatus

    private var _totalPrice = 0
    val totalPrice: Int
        get() = _totalPrice

    private var qtyTrack: Int = 0

    init {
        _qty.value = "0"
    }

    fun increaseQty() {
        ++qtyTrack
        _qty.value = qtyTrack.toString()
    }

    fun decreaseQty() {
        if (qtyTrack != 0) {
            --qtyTrack
            _qty.value = qtyTrack.toString()
        }
    }

    fun buy() {
        if (qtyTrack < 1) {
            _qtyErrStatus.value = true
        } else {
            _totalPrice = qtyTrack * PRODUCT_PRICE
            _buyStatus.value = true
        }
    }

    fun showErrDone() {
        _qtyErrStatus.value = false
    }

    fun buyDone() {
        _buyStatus.value = false
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}