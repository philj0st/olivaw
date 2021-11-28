package com.example.databindings

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.databindings.databinding.ActivityMainBinding

const val AMOUNT_KEY = "AMOUNT_KEY"

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this
        binding.data = Calc(0)


        binding.button2.setOnClickListener{
            binding.data?.appendDigit(2)
        }
        binding.button1.setOnClickListener{
            binding.data?.appendDigit(1)
        }
        binding.button3.setOnClickListener{
            binding.data?.appendDigit(3)
        }
        binding.button4.setOnClickListener{
            binding.data?.appendDigit(4)
        }
        binding.button5.setOnClickListener{
            binding.data?.appendDigit(5)
        }
        binding.button6.setOnClickListener{
            binding.data?.appendDigit(6)
        }
        binding.button7.setOnClickListener{
            binding.data?.appendDigit(7)
        }
        binding.button8.setOnClickListener{
            binding.data?.appendDigit(8)
        }
        binding.button9.setOnClickListener{
            binding.data?.appendDigit(9)
        }
        binding.button0.setOnClickListener{
            binding.data?.appendDigit(0)
        }

        binding.buttonClear.setOnClickListener{
            binding.data?.clear()
        }

    }

    // Save UI state changes to the savedInstanceState.
    // This bundle will be passed to onCreate if the process is
    // killed and restarted.
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState);
        binding.data?.amount?.value?.let { savedInstanceState.putInt(AMOUNT_KEY, it) }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.data = Calc(savedInstanceState.getInt(AMOUNT_KEY))
    }
}