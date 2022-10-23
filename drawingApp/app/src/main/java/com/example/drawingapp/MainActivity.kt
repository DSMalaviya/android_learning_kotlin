package com.example.drawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private var drawingView: DrawingView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20f);

        val ib_brush:ImageButton=findViewById(R.id.ib_brush)
        ib_brush.setOnClickListener{
            showBrushSizeChooserDialog()
        }
    }

    private fun showBrushSizeChooserDialog(){
        val brushDialog=Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush size: ")
        val smallBtn=brushDialog.findViewById<ImageButton>(R.id.ib_small_brush);
        smallBtn.setOnClickListener{
            drawingView?.setSizeForBrush(10f)
            brushDialog.dismiss()
        }

        val mediumBtn=brushDialog.findViewById<ImageButton>(R.id.ib_medium_brush);
        mediumBtn.setOnClickListener{
            drawingView?.setSizeForBrush(20f)
            brushDialog.dismiss()
        }

        val largeBtn=brushDialog.findViewById<ImageButton>(R.id.ib_large_brush);
        largeBtn.setOnClickListener{
            drawingView?.setSizeForBrush(30f)
            brushDialog.dismiss()
        }

        brushDialog.show()
    }
}