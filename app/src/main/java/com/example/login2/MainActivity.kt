package com.example.login2

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.login2.databinding.MainActivityBinding


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
            setupDestinationChangedListener()
        } else {
            throw RuntimeException("NavHostFragment not found with ID R.id_nav_host_fragment")
        }
    }

    private fun setupDestinationChangedListener(){
        navController.addOnDestinationChangedListener { _, destination, _->
            Log.d(  "MainActivity", "Navigated to ${destination.label}")
            when (destination.id){
                R.id.registerFragment -> {
                    supportActionBar?.title = "Registro"
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                }
//                R.id.registerFragment -> {
//                    supportActionBar?.title = "HomeApp"
//                }
               else -> {
                   supportActionBar?.title = "Login"
                   supportActionBar?.setDisplayHomeAsUpEnabled(false)

               }
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
         return super.onPrepareOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.action_logout ->{
                navController.navigate(R.id.action_homeFragment_to_loginFragment)
                return true

            }
            R.id.action_settings ->{
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
                return true
            }

        }
        return super.onOptionsItemSelected(item)


    }


}



