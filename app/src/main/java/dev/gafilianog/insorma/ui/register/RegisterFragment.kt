package dev.gafilianog.insorma.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.gafilianog.insorma.R
import dev.gafilianog.insorma.data.local.db.InsormaDatabase
import dev.gafilianog.insorma.data.local.repos.UserRepository
import dev.gafilianog.insorma.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        val application = requireNotNull(this.activity).application
        val dao = InsormaDatabase.getDatabase(application).usersDao
        val repository = UserRepository(dao)
        val factory = RegisterViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

        binding.registerViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.registerErrStatus.observe(viewLifecycleOwner) { isError ->
            if (isError == true) {
                Toast.makeText(
                    requireContext(),
                    getString(viewModel.registerErrMsg),
                    Toast.LENGTH_LONG
                ).show()
                viewModel.showErrDone()
            }
        }

        viewModel.navigateToLogin.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                findNavController().navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                )
                viewModel.moveToLoginDone()
            }
        }
    }
}