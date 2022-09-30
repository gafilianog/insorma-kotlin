package dev.gafilianog.insorma.ui.login

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
import dev.gafilianog.insorma.data.local.LoggedInUserDatastoreImpl
import dev.gafilianog.insorma.data.local.InsormaDatabase
import dev.gafilianog.insorma.data.local.repos.UserRepository
import dev.gafilianog.insorma.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        val application = requireNotNull(this.activity).application
        val dao = InsormaDatabase.getDatabase(application).usersDao
        val repository = UserRepository(dao)
        val loggedInUserData = LoggedInUserDatastoreImpl(application)
        val factory = LoginViewModelFactory(repository, loggedInUserData)

        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loginErrStatus.observe(viewLifecycleOwner) { isError ->
            if (isError == true) {
                Toast.makeText(requireContext(), getString(viewModel.loginErrMsg), Toast.LENGTH_LONG).show()
                viewModel.showErrDone()
            }
        }

        viewModel.navigateToHome.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                )
                viewModel.moveToHomeDone()
            }
        }

        viewModel.navigateToRegister.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                )
                viewModel.moveToRegisterDone()
            }
        }
    }
}