package com.example.login2

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.login2.databinding.MainActivityBinding
import com.example.login2.ui.theme.Login2Theme

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuracion de la toolbar
        setSupportActionBar(binding.toolbar)
        //Configuracion del NavController principal
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        if (navHostFragment != null){
            navController = navHostFragment.navController
            setupActionBarWithNavController(navController)
        } else {
            throw RuntimeException("NavHostFragment not found with ID R.id_nav_host_fragment")
        }
    }

    private fun setupDestinationChangedListener(){
        navController.addOnDestinationChangedListener { _, destination, _->
            Log.d(  "MainActivity", "Navigated to ${destination.label}")
            when (destination.id){
                R.id.registerFragment -> {
                    supportActionBar?.title = "registro"
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                }
                R.id.registerFragment -> {
                    supportActionBar?.title = "HomeApp"
                }
            }

        }
    }

}



