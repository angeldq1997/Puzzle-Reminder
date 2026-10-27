package com.example.puzzlereminder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PuzzleViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val tvTitle : TextView = view.findViewById(R.id.p_title)
    //TODO Check how to properly bind images to RecyclerView
    private val image : Int = R.drawable.puzzle1
    private val imageView : ImageView = view.findViewById(R.id.p_iv_central)

    fun bind(puzzle : Puzzle){
        tvTitle.text = puzzle.name
        imageView.setImageResource(puzzle.image)
    }
}