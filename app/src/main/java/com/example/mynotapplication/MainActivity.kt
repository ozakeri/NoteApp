package com.example.mynotapplication

import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.mynotapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setupNavigationComponent()

    }

    private fun setupNavigationComponent() {
        val navHostFragment:NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        AppBarConfiguration(setOf(R.id.homeFragment,R.id.planFragment),activityMainBinding.drawerLayout)
        activityMainBinding.navView.setupWithNavController(navController)

        val popupMenu = PopupMenu(this,null)
        popupMenu.inflate(R.menu.menu_bottom)
        val menu = popupMenu.menu
        activityMainBinding.bottomBar.setupWithNavController(menu,navController)

    }

}