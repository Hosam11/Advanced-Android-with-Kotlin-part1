package com.example.minipaint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.widget.Button

class MainActivity : AppCompatActivity() {
    //private lateinit var  canvas: MyCanvasView

    private lateinit var myCanvasView: MyCanvasView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        myCanvasView = MyCanvasView(this)
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        myCanvasView.contentDescription = resources.getString(R.string.canvasContentDescription)
        // To display what you will draw in MyCanvasView, you have to set it
        // as the ContentView of the MainActivity.
        setContentView(myCanvasView)

        // canvas = findViewById(R.id.canves)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem) =  when(item.itemId) {
        R.id.action_clear -> {
            myCanvasView.clear()
            true
        } else -> {
            super.onOptionsItemSelected(item)
        }
    }
}