package com.example.blogapp.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.blogapp.R
import com.example.blogapp.databinding.LayoutActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {
    private lateinit var viewBinding: LayoutActivityPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = LayoutActivityPrincipalBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)
        configureNavigationFragment()
    }

    private fun configureNavigationFragment() {
        val navController = findNavController(R.id.nav_controller_view)
        viewBinding.navView.setupWithNavController(navController)

        viewBinding.navView.setOnItemSelectedListener { item ->
            setToolbar(item.title.toString())
            if (navController.currentDestination?.id == R.id.nav_controller_view) {
                false
            } else {
                onNavDestinationSelected(
                    item,
                    navController
                )
            }
        }
    }

    private fun setToolbar(title: String?) {
        viewBinding.toolbar.textTitleFragment.text = title.toString()

        viewBinding.toolbar.imageBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}