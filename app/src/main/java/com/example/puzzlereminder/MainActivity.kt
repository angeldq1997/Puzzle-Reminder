package com.example.puzzlereminder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puzzlereminder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.bPuzzle.setOnClickListener(){
            val i = Intent(this, PuzzleActivity::class.java)
            startActivity(i)
        }

        binding.bMap.setOnClickListener(){
            //val i = Intent(this, MapActivity::class.java)
            //startActivity(i)
        }

        binding.bMath.setOnClickListener(){
            //val i = Intent(this, MathActivity::class.java)
            //startActivity(i)
        }
    }
}