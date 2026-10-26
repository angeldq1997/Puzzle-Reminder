package com.example.puzzlereminder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PuzzleAdapter (private val puzzles : List<Puzzle>) :
    RecyclerView.Adapter<PuzzleViewHolder>(){
    override fun onCreateViewHolder(parent:ViewGroup, viewType : Int) : PuzzleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_puzzle, parent, false)
        return PuzzleViewHolder(view)
    }

    override fun onBindViewHolder(holder: PuzzleViewHolder, position: Int) {
        holder.bind(puzzles[position])
    }

    override fun getItemCount(): Int = puzzles.size
}
