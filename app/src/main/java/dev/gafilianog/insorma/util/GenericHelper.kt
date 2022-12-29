package dev.gafilianog.insorma.util

import android.content.Context
import android.widget.Toast
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

class GenericHelper {

    companion object {
        fun toastMaker(ctx: Context?, msg: String?) {
            Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show()
        }

        fun currencyFormatter(nominal: Int) : String {
            return NumberFormat.getCurrencyInstance(Locale.US).format(nominal)
//            val currencyRp = DecimalFormat.getCurrencyInstance() as DecimalFormat
//            val formatRp = DecimalFormatSymbols()
//
//            formatRp.currencySymbol = "Rp "
//            formatRp.groupingSeparator = '.'
//            formatRp.monetaryDecimalSeparator = ','
//
//            currencyRp.decimalFormatSymbols = formatRp
//
//            return currencyRp.format(nominal)
        }
    }
}