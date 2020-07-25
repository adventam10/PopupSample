package com.example.popupsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.content.res.ResourcesCompat

class ListPopupWindowActivity : AppCompatActivity() {

    var popupWindow: ListPopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_popup_window)

        findViewById<Button>(R.id.button1).setOnClickListener {
            setupPopupWindow()
            popupWindow?.also { popupWindow ->
                popupWindow.anchorView = it
                popupWindow.show()
            }
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            setupPopupWindow()
            popupWindow?.also { popupWindow ->
                popupWindow.anchorView = it
                val width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    240f,
                    resources.displayMetrics
                )
                val height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    200f,
                    resources.displayMetrics
                )
                popupWindow.width = width.toInt()
                popupWindow.height = height.toInt()
                popupWindow.promptPosition = ListPopupWindow.POSITION_PROMPT_ABOVE
                popupWindow.verticalOffset = 24
                // ボタン中央に合わせる
                popupWindow.horizontalOffset = (it.width - width.toInt())/2
                popupWindow.setBackgroundDrawable(ResourcesCompat.getDrawable(resources, R.drawable.popup_background, null))
                popupWindow.show()
            }
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            setupPopupWindow()
            popupWindow?.also { popupWindow ->
                popupWindow.anchorView = it
                popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
                popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
                popupWindow.show()
            }
        }

        findViewById<Button>(R.id.button4).setOnClickListener {
            setupPopupWindow()
            popupWindow?.also { popupWindow ->
                popupWindow.anchorView = it
                val width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    240f,
                    resources.displayMetrics
                )
                val height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    200f,
                    resources.displayMetrics
                )
                popupWindow.width = width.toInt()
                popupWindow.height = height.toInt()
                // StartかEnd
                popupWindow.setDropDownGravity(Gravity.END)
                // 上部にView表示
                val imageView = ImageView(this)
                imageView.setImageResource(R.drawable.ic_launcher_foreground)
                popupWindow.setPromptView(imageView)
                popupWindow.show()
            }
        }

        findViewById<Button>(R.id.button5).setOnClickListener {
            setupPopupWindow()
            popupWindow?.also { popupWindow ->
                popupWindow.anchorView = it
                // 下部にView表示
                val imageView = ImageView(this)
                imageView.setImageResource(R.drawable.ic_launcher_foreground)
                popupWindow.setPromptView(imageView)
                popupWindow.promptPosition = ListPopupWindow.POSITION_PROMPT_BELOW
                popupWindow.show()
            }
        }
    }

    private fun setupPopupWindow() {
        popupWindow?.also {
            if (it.isShowing) {
                it.dismiss()
            }
        }
        popupWindow = null
        popupWindow = ListPopupWindow(this).also {
            val list = listOf("Dialog", "PopupWindow", "ListPopupWindow", "Toast", "SnackBar", "Menu")
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
            it.setAdapter(adapter)
            it.setOnItemClickListener { _, _, position, _ ->
                val text = adapter.getItem(position)
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
                it.dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // DismissせずにActivity終了するとクラッシュする
        popupWindow?.also {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }
}
