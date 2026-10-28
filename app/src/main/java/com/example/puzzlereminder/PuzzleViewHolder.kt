package com.example.puzzlereminder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup

class PuzzleViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val tvTitle : TextView = view.findViewById(R.id.p_title)
    private val imageView : ImageView = view.findViewById(R.id.p_iv_central)
    private val button1 : MaterialButton = view.findViewById(R.id.button_1)
    private val button2 : MaterialButton = view.findViewById(R.id.button_2)
    private val button3 : MaterialButton = view.findViewById(R.id.button_3)
    private val pBgButtons : MaterialButtonToggleGroup = view.findViewById(R.id.p_bg_buttons)
    private val pHint : TextView = view.findViewById(R.id.p_hint)
    fun bind(puzzle : Puzzle){
        tvTitle.text = puzzle.name
        imageView.setImageResource(puzzle.image)
        imageView.setOnClickListener(){
            Toast.makeText(button1.context, puzzle.name,Toast.LENGTH_SHORT).show()
        }
        button1.setOnClickListener {
            pBgButtons.check(button1.id)
            pHint.text = puzzle.hint1
        }
        button2.setOnClickListener {
            pBgButtons.check(button2.id)
            pHint.text = puzzle.hint2
        }
        button3.setOnClickListener {
            pBgButtons.check(button3.id)
            pHint.text = puzzle.hint3
        }
    }
}