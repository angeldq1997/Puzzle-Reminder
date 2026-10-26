package com.example.puzzlereminder

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.puzzlereminder.databinding.ActivityPuzzleBinding

class PuzzleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPuzzleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuzzleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val puzzles = listOf(
            Puzzle("Puzzle 1: Ladder Lottery", R.drawable.puzzle1, R.drawable.ic_launcher_background),
            Puzzle("Puzzle 2: Ladder Lottery", R.drawable.puzzle1, R.drawable.ic_launcher_background),
            Puzzle("Puzzle 3: Ladder Lottery", R.drawable.puzzle1, R.drawable.ic_launcher_background),
            Puzzle("Puzzle 4: Ladder Lottery", R.drawable.puzzle1, R.drawable.ic_launcher_background),
            Puzzle("Puzzle 5: Ladder Lottery", R.drawable.puzzle1, R.drawable.ic_launcher_background)
        )
    }
}