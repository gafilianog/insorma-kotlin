package dev.gafilianog.insorma.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.gafilianog.insorma.R
import dev.gafilianog.insorma.data.local.LoggedInUserDatastoreImpl
import dev.gafilianog.insorma.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
//    private lateinit var loggedInUserDatastoreImpl: LoggedInUserDatastoreImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.lifecycleOwner = this
        binding.homeViewModel = viewModel
        binding.rvFurniture.adapter = FurnitureAdapter()

        //TODO: Get username bind to appbar

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.img_to_profile -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Tes", "lmao from home")
    }
}
