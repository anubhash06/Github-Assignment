package com.android.githubassignment

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.githubassignment.core.platform.BaseActivity
import com.android.githubassignment.core.platform.BaseFragment
import com.android.githubassignment.ui.home.RepoDisplayData
import com.android.githubassignment.ui.repodetail.RepositoryContract

class MainActivity : BaseActivity(), RepositoryContract {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

         navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun layoutId() = R.layout.activity_main
    override fun openDetail(displayData: RepoDisplayData) {
        //open using direction
    }


}