package com.example.popupsample

import android.os.Bundle
import android.transition.Slide
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat


class PopupWindowActivity : AppCompatActivity() {

    var popupWindow: PopupWindow? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_window)

        findViewById<Button>(R.id.button1).setOnClickListener {
            setupPopupWindow("Width: MATCH_PARENT\nHeight: WRAP_CONTENT\nshowAsDropDown")
            popupWindow?.also { popupWindow ->
                popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
                popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
                popupWindow.showAsDropDown(it)
            }
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            setupPopupWindow("Width: WRAP_CONTENT\nHeight: MATCH_PARENT\nshowAsDropDown")
            popupWindow?.also { popupWindow ->
                popupWindow.width = ViewGroup.LayoutParams.WRAP_CONTENT
                popupWindow.height = ViewGroup.LayoutParams.MATCH_PARENT
                popupWindow.showAsDropDown(it)
            }
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            setupPopupWindow("Width: 240dp\nHeight: 200dp\nshowAsDropDown")
            popupWindow?.also { popupWindow ->
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
                popupWindow.showAsDropDown(it)
            }
        }

        findViewById<Button>(R.id.button4).setOnClickListener {
            setupPopupWindow("Width: WRAP_CONTENT\nHeight: MATCH_PARENT" +
                    "\nisFocusable: true\nisOutsideTouchable: true\nshowAsDropDown")
            popupWindow?.also { popupWindow ->
                popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
                popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
                popupWindow.isFocusable = true
                popupWindow.isOutsideTouchable = true
                popupWindow.showAsDropDown(it)
            }
        }

        findViewById<Button>(R.id.button5).setOnClickListener {
            setupPopupWindow("Width: WRAP_CONTENT\nHeight: MATCH_PARENT" +
                    "\nisFocusable: true\nisOutsideTouchable: true\nshowAtLocation: CENTER" +
            "\nenterTransition: Slide(Gravity.LEFT)\nexitTransition: Slide(Gravity.RIGHT)")
            popupWindow?.also { popupWindow ->
                popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
                popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
                popupWindow.isFocusable = true
                popupWindow.isOutsideTouchable = true
                popupWindow.enterTransition = Slide(Gravity.LEFT)
                popupWindow.exitTransition = Slide(Gravity.RIGHT)
                popupWindow.showAtLocation(findViewById(R.id.root), Gravity.CENTER, 0, 0)
            }
        }
    }

    private fun setupPopupWindow(text: String) {
        popupWindow?.also {
            if (it.isShowing) {
                it.dismiss()
            }
        }
        popupWindow = null
        val inflater = layoutInflater
        val popView = inflater.inflate(R.layout.popup_window, null)
        popupWindow = PopupWindow().also { popupWindow ->
            popView.findViewById<TextView>(R.id.text).text = text
            popView.findViewById<Button>(R.id.button).setOnClickListener {
                popupWindow.dismiss()
            }
            popupWindow.contentView = popView
            popupWindow.setBackgroundDrawable(ResourcesCompat.getDrawable(resources, R.drawable.popup_background, null))
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
