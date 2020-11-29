package com.android.githubassignment

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.githubassignment.core.extension.setupWithNavController
import com.android.githubassignment.core.platform.BaseActivity
import com.android.githubassignment.ui.home.RepoDisplayData
import com.android.githubassignment.ui.home.RepoFragmentDirections
import com.android.githubassignment.ui.repodetail.RepositoryContract
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : BaseActivity(), RepositoryContract {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }




    private fun setupBottomNavigationBar() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navGraphIds = listOf(
            R.navigation.navigation_home, R.navigation.navigation_dashboard, R.navigation.navigation_notification)

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = navView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun layoutId() = R.layout.activity_main
    override fun openDetail(displayData: RepoDisplayData) {
        //open using direction
        val direction =
            RepoFragmentDirections.actionNavigationHomeToNavigationDetails(displayData)
        currentNavController?.value?.navigate(direction)
    }


    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp()?:false || super.onSupportNavigateUp()
    }


}