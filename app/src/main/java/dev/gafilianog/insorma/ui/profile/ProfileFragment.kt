package dev.gafilianog.insorma.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.gafilianog.insorma.R
import dev.gafilianog.insorma.data.local.InsormaDatabase
import dev.gafilianog.insorma.data.local.LoggedInUserDatastoreImpl
import dev.gafilianog.insorma.data.local.repos.UserRepository
import dev.gafilianog.insorma.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    // TODO: DELETE ACCOUNT

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        val application = requireNotNull(this.activity).application
        val dao = InsormaDatabase.getDatabase(application).usersDao
        val repository = UserRepository(dao)
        val loggedInUserData = LoggedInUserDatastoreImpl(application)
        val factory = ProfileViewModelFactory(repository, loggedInUserData)

        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        binding.profileViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.editUsernameStatus.observe(viewLifecycleOwner) { isEditable ->
            if (isEditable == true) {
                binding.btnEditUsername.visibility = View.GONE
                binding.btnSaveUsername.visibility = View.VISIBLE
                binding.tvUsernameProfile.visibility = View.GONE
                binding.etUsernameProfile.visibility = View.VISIBLE
            } else {
                binding.btnEditUsername.visibility = View.VISIBLE
                binding.btnSaveUsername.visibility = View.GONE
                binding.tvUsernameProfile.visibility = View.VISIBLE
                binding.etUsernameProfile.visibility = View.GONE
            }
        }

        viewModel.editErrStatus.observe(viewLifecycleOwner) { isError ->
            if (isError == true) {
                Toast.makeText(
                    requireContext(),
                    getString(viewModel.editErrMsg),
                    Toast.LENGTH_LONG
                ).show()
                viewModel.showErrDone()
            }
        }

        viewModel.navigateToLogin.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Tes", "lmao")
    }
}