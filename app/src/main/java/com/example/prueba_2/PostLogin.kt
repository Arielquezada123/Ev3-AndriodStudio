package com.example.prueba_2

import SettingsFragment
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba_2.databinding.ActivityPostLoginBinding


class PostLogin : AppCompatActivity() {

    // Agregar viewbinding
    private lateinit var binding: ActivityPostLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar bottomNav
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    // Muestra el fragment de home
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, HomeFragment()).commit()
                    true
                }
                R.id.nav_settings -> {
                    // Muestra el fragment de settings
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, SettingsFragment()).commit()
                    true
                }
                R.id.nav_agregar -> {
                    // Muestra el fragment de agregar
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, AgregarFragment()).commit()
                    true
                }
                else -> false
            }
        }

        // Controla las ocasiones en que el usuario presiona nuevamente el bottomnavigation
        binding.bottomNav.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.nav_home -> Toast.makeText(this, "Ya estás en la vista home", Toast.LENGTH_LONG).show()
                R.id.nav_settings -> Toast.makeText(this, "Ya estás en la vista datos", Toast.LENGTH_LONG).show()
            }
        }
    }
}