package com.example.popupsample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment


class DialogActivity : AppCompatActivity(), SimpleDialogFragment.SimpleDialogListener,
    SimpleListDialogFragment.SimpleListDialogFragmentListener,
    SimpleRadioDialogFragment.SimpleRadioDialogFragmentListener,
    SimpleCheckboxDialogFragment.SimpleCheckboxDialogFragmentListener,
    CustomDialogFragment.CustomDialogFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        findViewById<Button>(R.id.button1).setOnClickListener {
            val dialog = SimpleDialogFragment.newInstance("タイトル", "メッセージ", "OK",
                "Cancel", "Neutral")
            dialog.show(supportFragmentManager, "A")
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            val dialog = SimpleDialogFragment.newInstance("タイトル", "メッセージ", "OK",
                "Cancel")
            dialog.show(supportFragmentManager, "B")
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            val dialog = SimpleDialogFragment.newInstance("タイトル", "メッセージ", "OK")
            dialog.show(supportFragmentManager, "C")
        }

        findViewById<Button>(R.id.button4).setOnClickListener {
            val dialog = SimpleDialogFragment.newInstance(message = "メッセージ", positiveTitle = "OK",
                negativeTitle = "Cancel", neutralTitle = "Neutral")
            dialog.show(supportFragmentManager, "D")
        }

        findViewById<Button>(R.id.button5).setOnClickListener {
            val dialog = SimpleListDialogFragment.newInstance("タイトル", arrayOf("Red", "Green", "Blue"))
            dialog.show(supportFragmentManager, "D")
        }

        findViewById<Button>(R.id.button6).setOnClickListener {
            val dialog = SimpleRadioDialogFragment.newInstance("タイトル", arrayOf("Red", "Green", "Blue"),
                1, "OK", "Cancel", "Neutral")
            dialog.show(supportFragmentManager, "D")
        }

        findViewById<Button>(R.id.button7).setOnClickListener {
            val dialog = SimpleCheckboxDialogFragment.newInstance("タイトル", arrayOf("Red", "Green", "Blue"),
                arrayListOf(1, 2), "OK", "Cancel", "Neutral")
            dialog.show(supportFragmentManager, "D")
        }

        findViewById<Button>(R.id.button8).setOnClickListener {
            val dialog = CustomDialogFragment.newInstance("タイトル",
                "OK", "Cancel", "Neutral")
            dialog.show(supportFragmentManager, "D")
        }

        findViewById<Button>(R.id.button9).setOnClickListener {
            val dialog = CustomProgressDialogFragment.newInstance("タイトル", "メッセージ")
            dialog.show(supportFragmentManager, "D")
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                // ３秒後に非表示
                dialog.dismiss()
            }, 3000)
        }
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        Log.d("TAG", dialog.tag)
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Log.d("TAG", dialog.tag)
    }

    override fun onDialogNeutralClick(dialog: DialogFragment) {
        Log.d("TAG", dialog.tag)
    }

    override fun onListDialogClick(dialog: DialogFragment, which: Int) {
        Log.d("TAG", which.toString())
    }

    override fun onRadioDialogPositiveClick(dialog: DialogFragment, checkedItem: Int) {
        Log.d("TAG", checkedItem.toString())
    }

    override fun onRadioDialogNegativeClick(dialog: DialogFragment, checkedItem: Int) {
        Log.d("TAG", checkedItem.toString())
    }

    override fun onRadioDialogNeutralClick(dialog: DialogFragment, checkedItem: Int) {
        Log.d("TAG", checkedItem.toString())
    }

    override fun onRadioDialogPositiveClick(dialog: DialogFragment, checkedItems: IntArray) {
        Log.d("TAG", checkedItems.contentToString())
    }

    override fun onRadioDialogNegativeClick(dialog: DialogFragment, checkedItems: IntArray) {
        Log.d("TAG", checkedItems.contentToString())
    }

    override fun onRadioDialogNeutralClick(dialog: DialogFragment, checkedItems: IntArray) {
        Log.d("TAG", checkedItems.contentToString())
    }
}
