package com.example.puzzlereminder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonGroup

class PuzzleViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val tvTitle : TextView = view.findViewById(R.id.p_title)
    //TODO Check how to properly bind images to RecyclerView
    private val initialImage : Int = view.findViewById(R.id.p_iv_central)
    private val imageView : ImageView = view.findViewById(R.id.p_iv_central)
    private val buttonGroup : MaterialButtonGroup = view.findViewById(R.id.p_bg_buttons)
    private val button1 : MaterialButton = view.findViewById(R.id.button_1)
    private val button2 : MaterialButton = view.findViewById(R.id.button_2)
    private val button3 : MaterialButton = view.findViewById(R.id.button_3)
    private val button4 : MaterialButton = view.findViewById(R.id.button_4)

    fun bind(puzzle : Puzzle){
        tvTitle.text = puzzle.name
        imageView.setImageResource(puzzle.initialImage)
    }
}