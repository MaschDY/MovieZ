package br.com.maschdy.moviez.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.maschdy.moviez.presentation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
