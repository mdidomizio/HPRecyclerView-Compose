package com.example.hp_recyclerview_compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager = GridLayoutManager(this, 3)

        val data = ArrayList<HarryPotterData>()
       //TODO check how to set this part
        // data.add(HarryPotterData(image = , name = ))

        /*for (i in 1..150) {
            data.add(HarryPotterData(R.drawable.ic_baseline_folder_shared_24, "Item $i"))*/

            val adapter = CustomAdapter(data)

            recyclerView.adapter = adapter
       // }
    }
}