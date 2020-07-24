package com.example.popupsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class ToastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)
        findViewById<Button>(R.id.button_normal).setOnClickListener {
            Toast.makeText(this, "Normal", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.button_center).setOnClickListener {
            val toast = Toast.makeText(this, "Center", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }

        findViewById<Button>(R.id.button_top_left).setOnClickListener {
            val toast = Toast.makeText(this, "Top Left", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP or Gravity.LEFT, 0, 0)
            toast.show()
        }

        findViewById<Button>(R.id.button_custom).setOnClickListener {
            val inflater = layoutInflater
            val container = findViewById<LinearLayout>(R.id.custom_toast_container)
            val layout = inflater.inflate(R.layout.custom_toast, container)
            val text = layout.findViewById<TextView>(R.id.text)
            text.text = "This is a custom toast"
            with (Toast(this)) {
                setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                duration = Toast.LENGTH_LONG
                view = layout
                show()
            }
        }
    }
}
