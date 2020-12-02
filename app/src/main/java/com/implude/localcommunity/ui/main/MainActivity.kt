package com.implude.localcommunity.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.implude.localcommunity.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
    }

    fun setupViews() {
        var navController = findNavController(R.id.fragNavHost)

        CircleImageView_Profile.setOnClickListener {
            navController.navigate(R.id.profileFragment)
        }

        bottomNavView.setupWithNavController(navController)

        var appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homeFragment,
                R.id.searchFragment,
                R.id.memberFragment,
                R.id.messengerFragment
            )
        )
    }
}