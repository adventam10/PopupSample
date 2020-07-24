package com.example.popupsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


class SnackBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack_bar)
        val root = findViewById<View>(R.id.root)
        findViewById<Button>(R.id.button_normal).setOnClickListener {
            Snackbar
                .make(root, "Normal", Snackbar.LENGTH_SHORT)
                .show()
        }

        findViewById<Button>(R.id.button_action).setOnClickListener {
            Snackbar
                .make(root, "Add Action", Snackbar.LENGTH_SHORT)
                .setAction("action") {
                    Toast.makeText(this, "Action", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        findViewById<Button>(R.id.button_center).setOnClickListener {
            Snackbar
                .make(findViewById(R.id.center), "Center", Snackbar.LENGTH_SHORT)
                .show()
        }

        findViewById<Button>(R.id.button_left).setOnClickListener {
            Snackbar
                .make(findViewById(R.id.coordinatorLayout), "Left", Snackbar.LENGTH_SHORT)
                .show()
        }

        findViewById<Button>(R.id.button_custom).setOnClickListener {
            Snackbar
                .make(root, "Custom", Snackbar.LENGTH_LONG)
                .setAction("action") {
                    Toast.makeText(this, "Action", Toast.LENGTH_SHORT).show()
                }
                .setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setBackgroundTint(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                .setAnchorView(R.id.fab)
                .show()
        }
    }
}
