package com.example.cooperar

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.cooperar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        with(newConfig) {
            println(orientation)
            println(screenLayout)
        }

    }

    /* View */
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    private var currFragment = R.id.homeFragment

    private val onDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            currFragment = destination.id
            when (currFragment) {
                //이 프래그먼트들은 바텀바가 안보임
                    R.id.homeFragment
                -> {
                    binding.bottomNav.visibility = View.GONE
                    binding.toolbar.visibility = View.GONE
                }
                else -> {
                    //나머지 프래그먼트들은 바텀바가 보임
                    binding.bottomNav.visibility = View.VISIBLE
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* view */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(0, 0)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(onDestinationChangedListener)
        /* Bottom Menu */
        binding.bottomNav.apply {
            setupWithNavController(navController)
            setOnItemSelectedListener { item ->
                NavigationUI.onNavDestinationSelected(item, navController, false)
                true
            }
        }


    }
}