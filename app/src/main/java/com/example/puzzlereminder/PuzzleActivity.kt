package com.example.puzzlereminder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.puzzlereminder.databinding.ActivityPuzzleBinding
import com.google.android.material.button.MaterialButton

class PuzzleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPuzzleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuzzleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val puzzles = listOf(
            Puzzle(getString(R.string.p_name_1), R.drawable.puzzle1, getString(R.string.p1_hint1), getString(R.string.p1_hint2), getString(R.string.p1_hint3)),
            Puzzle(getString(R.string.p_name_2), R.drawable.puzzle2, getString(R.string.p2_hint1), getString(R.string.p2_hint2), getString(R.string.p2_hint3)),
            Puzzle(getString(R.string.p_name_3), R.drawable.puzzle3, getString(R.string.p3_hint1), getString(R.string.p3_hint2), getString(R.string.p3_hint3)),
            Puzzle(getString(R.string.p_name_4), R.drawable.puzzle4, getString(R.string.p4_hint1), getString(R.string.p4_hint2), getString(R.string.p4_hint3)),
        )

        binding.pRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.pRecyclerView.adapter = PuzzleAdapter(puzzles)
        initComponents()
        setListeners()
    }

    private fun initComponents() {

    }

    private fun setListeners() {

    }

}