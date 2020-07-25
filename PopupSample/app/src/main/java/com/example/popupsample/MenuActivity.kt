package com.example.popupsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        registerForContextMenu(findViewById(R.id.button_context_menu))
        
        findViewById<Button>(R.id.button_popup).setOnClickListener {
            val popup = PopupMenu(this, it)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.popup_menu, popup.menu)
            popup.setOnMenuItemClickListener {
                return@setOnMenuItemClickListener when (it.itemId) {
                    R.id.section1_menu1 -> {
                        Toast.makeText(this, "メニュー１", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.section1_menu2 -> {
                        Toast.makeText(this, "メニュー２", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.section2_menu1 -> {
                        Toast.makeText(this, "メニュー３", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.section2_menu2 -> {
                        Toast.makeText(this, "メニュー４", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.section3 -> {
                        Toast.makeText(this, "セクション３", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.section1_menu1 -> {
                Toast.makeText(this, "メニュー１", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.section1_menu2 -> {
                Toast.makeText(this, "メニュー２", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.section2_menu1 -> {
                Toast.makeText(this, "メニュー３", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.section2_menu2 -> {
                Toast.makeText(this, "メニュー４", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.section3 -> {
                Toast.makeText(this, "セクション３", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, 
                                     menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        menu?.setHeaderTitle("ヘッダー")
        inflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.section1_menu1 -> {
                Toast.makeText(this, "メニュー１", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.section1_menu2 -> {
                Toast.makeText(this, "メニュー２", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.section2_menu1 -> {
                Toast.makeText(this, "メニュー３", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.section2_menu2 -> {
                Toast.makeText(this, "メニュー４", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.section3 -> {
                Toast.makeText(this, "セクション３", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
