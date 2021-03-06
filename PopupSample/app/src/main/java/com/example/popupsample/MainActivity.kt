package com.example.popupsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.list)
        val list = listOf("Dialog", "PopupWindow", "ListPopupWindow", "Toast", "SnackBar", "Menu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val text = adapter.getItem(position)
            val intent = when (Menu.valueOf(text!!)) {
                Menu.Dialog -> Intent(this, DialogActivity::class.java)
                Menu.PopupWindow -> Intent(this, PopupWindowActivity::class.java)
                Menu.ListPopupWindow -> Intent(this, ListPopupWindowActivity::class.java)
                Menu.Toast -> Intent(this, ToastActivity::class.java)
                Menu.SnackBar -> Intent(this, SnackBarActivity::class.java)
                Menu.Menu -> Intent(this, MenuActivity::class.java)
            }

            startActivity(intent)
        }
    }
}

enum class Menu {
    Dialog, PopupWindow, ListPopupWindow, Toast, SnackBar, Menu;
}