package dev.gafilianog.insorma.ui.detail

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.gafilianog.insorma.R
import dev.gafilianog.insorma.data.remote.FurnitureData
import dev.gafilianog.insorma.databinding.FragmentDetailBinding
import dev.gafilianog.insorma.util.GenericHelper

private const val PRODUCT_POS = "pos"

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    //    private lateinit var viewModel: DetailViewModel
    private val viewModel: DetailViewModel by viewModels()

    private var productPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            productPosition = it.getInt(PRODUCT_POS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        binding.detailViewModel = viewModel
        binding.lifecycleOwner = this
        val product = FurnitureData.products[productPosition]

        binding.product = product
        viewModel.PRODUCT_PRICE = product.price

        binding.topAppBar.setNavigationOnClickListener { activity?.onBackPressed() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.qtyErrStatus.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                GenericHelper.toastMaker(requireContext(), "At least quantity has amount of 1")
                viewModel.showErrDone()
            }
        }

        viewModel.buyStatus.observe(viewLifecycleOwner) { isBought ->
            if (isBought) {
                val totalPrice = GenericHelper.currencyFormatter(viewModel.totalPrice)

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Confirmation")
                    .setMessage("Total price: $totalPrice\nare you sure want to buy?")
                    .setNegativeButton("No") { _, _ -> }
                    .setPositiveButton("Yes") { _, _ ->
//                        TODO("Add to transaction")
                        GenericHelper.toastMaker(requireContext(), "Successful purchase!")
//                        TODO("Back to Home navigation")
//                        findNavController().navigate(
//                            DetailFragmentDirections.actionDetailFragmentToHomeFragment()
//                        )
                    }
                    .show()

                viewModel.buyDone()
            }
        }
    }
}