package com.example.puzzlereminder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puzzlereminder.databinding.ActivityPuzzleBinding

class PuzzleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPuzzleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuzzleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val puzzles = listOf(
            Puzzle("Puzzle 1: Ladder Lottery", R.drawable.puzzle1, R.string.p1_hint1.toString(), R.string.p1_hint2.toString(), R.string.p1_hint3.toString()),
            Puzzle("Puzzle 2: Ladder Lottery", R.drawable.puzzle2, R.string.p2_hint1.toString(), R.string.p2_hint2.toString(), R.string.p3_hint3.toString()),
            Puzzle("Puzzle 3: Ladder Lottery", R.drawable.puzzle3, R.string.p3_hint1.toString(), R.string.p3_hint2.toString(), R.string.p3_hint3.toString()),
            Puzzle("Puzzle 4: Ladder Lottery", R.drawable.puzzle4, R.string.p4_hint1.toString(), R.string.p4_hint2.toString(), R.string.p4_hint3.toString()),
        )
    }
}