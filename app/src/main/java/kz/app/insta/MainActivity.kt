package kz.app.insta

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        navController = findNavController(R.id.nav_host_fragment)
        bottomNav = findViewById(R.id.bottom_navigation)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (navController.currentDestination?.id == R.id.homeFragment) {
                    finish()
                } else {
                    navController.popBackStack() // Для навигации назад
                    updateBottomNavigationSelection(navController.currentDestination?.id)
                }
            }
        })

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    if (navController.currentDestination?.id != R.id.homeFragment) {
                        navController.popBackStack(R.id.homeFragment, false)
                        navController.navigate(R.id.homeFragment)
                    }
                    true
                }
                R.id.searchFragment -> {
                    if (navController.currentDestination?.id != R.id.searchFragment) {
                        navController.popBackStack(R.id.searchFragment, false)
                        navController.navigate(R.id.searchFragment)
                    }
                    true
                }
                R.id.addPostFragment -> {
                    if (navController.currentDestination?.id != R.id.addPostFragment) {
                        navController.popBackStack(R.id.addPostFragment, false)
                        navController.navigate(R.id.addPostFragment)
                    }
                    true
                }
                R.id.notificationsFragment -> {
                    if (navController.currentDestination?.id != R.id.notificationsFragment) {
                        navController.popBackStack(R.id.notificationsFragment, false)
                        navController.navigate(R.id.notificationsFragment)
                    }
                    true
                }
                R.id.profileFragment -> {
                    if (navController.currentDestination?.id != R.id.profileFragment) {
                        navController.popBackStack(R.id.profileFragment, false)
                        navController.navigate(R.id.profileFragment)
                    }
                    true
                }
                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> bottomNav.menu.findItem(R.id.homeFragment).isChecked = true
                R.id.searchFragment -> bottomNav.menu.findItem(R.id.searchFragment).isChecked = true
                R.id.addPostFragment -> bottomNav.menu.findItem(R.id.addPostFragment).isChecked = true
                R.id.profileFragment -> bottomNav.menu.findItem(R.id.profileFragment).isChecked = true
                R.id.notificationsFragment -> bottomNav.menu.findItem(R.id.notificationsFragment).isChecked = true
            }
        }
    }

    private fun updateBottomNavigationSelection(destinationId: Int?) {
        when (destinationId) {
            R.id.homeFragment -> bottomNav.selectedItemId = R.id.homeFragment
            R.id.searchFragment -> bottomNav.selectedItemId = R.id.searchFragment
            R.id.addPostFragment -> bottomNav.selectedItemId = R.id.addPostFragment
            R.id.notificationsFragment -> bottomNav.selectedItemId = R.id.notificationsFragment
            R.id.profileFragment -> bottomNav.selectedItemId = R.id.profileFragment
        }
    }
}