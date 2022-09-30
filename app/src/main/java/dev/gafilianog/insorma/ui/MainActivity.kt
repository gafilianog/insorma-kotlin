package dev.gafilianog.insorma.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.gafilianog.insorma.R
import dev.gafilianog.insorma.data.local.InsormaDatabase
import dev.gafilianog.insorma.data.model.User
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nav: BottomNavigationView = findViewById(R.id.bottom_nav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        nav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, nd, _ ->
            if (nd.id == R.id.loginFragment || nd.id == R.id.registerFragment || nd.id == R.id.profileFragment) {
                nav.visibility = View.GONE
            } else nav.visibility = View.VISIBLE
        }

//        dummyDb()
    }

    private fun dummyDb() {
        val db: InsormaDatabase by lazy { InsormaDatabase.getDatabase(this) }

        lifecycleScope.launch {
            db.usersDao.insertUser(User(
                1,
                "admin@mail.com",
                "admin",
                "08123456789",
                "a1"
            )
            )
        }
    }
}