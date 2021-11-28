package com.example.lotto

import android.app.Activity
import android.inputmethodservice.Keyboard
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lotto.databinding.ActivityMainBinding
import com.example.lotto.databinding.RowItemBinding

class MainActivity : AppCompatActivity() {
    val drawings = mutableListOf<Drawing>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val range = Triple(0,69,5)
        val luckyRange = Triple(1,26,1)
        drawings.add(Drawing(range, luckyRange))
        drawings.add(Drawing(range, luckyRange))
        //      connect adapter
        val drawingAdapter = DrawingAdapter(drawings)
        binding.recyclerView.adapter = drawingAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.newDrawing.setOnClickListener {
            // do american 5/69 (white balls) powerball for now
            val range = Triple(0,69,5)
            // 1/26 (Powerballs)
            val luckyRange = Triple(1,26,1)
            val newDraw = Drawing(range, luckyRange)
            val insertIndex = drawingAdapter.myDrawings.size
            drawingAdapter.myDrawings.add(insertIndex, newDraw)
//            drawings.add(insertIndex, newDraw)
            drawingAdapter.notifyItemInserted(insertIndex)
//            binding.recyclerView.adapter.notifyItemInserted(drawings.size)
            Log.d("ITM","new drawing ${newDraw.numbers.toString()} .. now ${drawings.size.toString()} big")
        }


    }
    class DrawingAdapter(val myDrawings: MutableList<Drawing>): RecyclerView.Adapter<DrawingAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val drawing = myDrawings.get(position)
            holder.bind(drawing)
        }

        override fun getItemCount(): Int {
            return myDrawings.size
        }

        class ViewHolder(val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(drawing: Drawing) {
                binding.num1.text = drawing.numbers.get(0).toString()
                binding.num2.text = drawing.numbers.get(1).toString()
                binding.num3.text = drawing.numbers.get(2).toString()
                binding.num4.text = drawing.numbers.get(3).toString()
                binding.num5.text = drawing.numbers.get(4).toString()
                binding.num6.text = drawing.numbers.get(5).toString()
            }
        }
    }
}