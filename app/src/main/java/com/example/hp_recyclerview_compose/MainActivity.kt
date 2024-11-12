package com.example.hp_recyclerview_compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hp_recyclerview_compose.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val data = ArrayList<HarryPotterData>() //TODO check this part

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.layoutManager =GridLayoutManager(this, 3)

        getAllCharacters()


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview) //TODO check this part



        /*for (i in 1..150) {
            data.add(HarryPotterData(R.drawable.ic_baseline_folder_shared_24, "Item $i"))*/

            val adapter = HarryPotterAdapter(data)

            recyclerView.adapter = adapter
       // }
    }

    private fun getAllCharacters() {

    }
}