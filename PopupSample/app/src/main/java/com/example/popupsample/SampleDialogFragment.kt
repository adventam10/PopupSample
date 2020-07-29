package com.example.popupsample

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class SimpleDialogFragment : DialogFragment() {

    interface SimpleDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
        fun onDialogNeutralClick(dialog: DialogFragment)
    }

    private var listener: SimpleDialogListener? = null
    companion object {
        private const val ARGUMENTS_TITLE = "ARGUMENTS_TITLE"
        private const val ARGUMENTS_MESSAGE = "ARGUMENTS_MESSAGE"
        private const val ARGUMENTS_POSITIVE_TITLE = "ARGUMENTS_POSITIVE_TITLE"
        private const val ARGUMENTS_NEGATIVE_TITLE = "ARGUMENTS_NEGATIVE_TITLE"
        private const val ARGUMENTS_NEUTRAL_TITLE = "ARGUMENTS_NEUTRAL_TITLE"

        fun newInstance(title: String? = null, message: String? = null,
                        positiveTitle: String? = null, negativeTitle: String? = null,
                        neutralTitle: String? = null): SimpleDialogFragment
            = SimpleDialogFragment().apply {
                isCancelable = false
                arguments = Bundle().apply {
                    putString(ARGUMENTS_TITLE, title)
                    putString(ARGUMENTS_MESSAGE, message)
                    putString(ARGUMENTS_POSITIVE_TITLE, positiveTitle)
                    putString(ARGUMENTS_NEGATIVE_TITLE, negativeTitle)
                    putString(ARGUMENTS_NEUTRAL_TITLE, neutralTitle)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? SimpleDialogListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var title: String? = null
        var message: String? = null
        var positiveTitle: String? = null
        var negativeTitle: String? = null
        var neutralTitle: String? = null
        arguments?.let {
            title = it.getString(ARGUMENTS_TITLE)
            message = it.getString(ARGUMENTS_MESSAGE)
            positiveTitle = it.getString(ARGUMENTS_POSITIVE_TITLE)
            negativeTitle = it.getString(ARGUMENTS_NEGATIVE_TITLE)
            neutralTitle = it.getString(ARGUMENTS_NEUTRAL_TITLE)
        }
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            title?.let {
                builder.setTitle(it)
            }
            message?.let {
                builder.setMessage(it)
            }
            positiveTitle?.let {
                builder.setPositiveButton(it) { _, _ ->
                    listener?.onDialogPositiveClick(this)
                }
            }
            negativeTitle?.let {
                builder.setNegativeButton(it) { _, _ ->
                    listener?.onDialogNegativeClick(this)
                }
            }
            neutralTitle?.let {
                builder.setNeutralButton(it) { _, _ ->
                    listener?.onDialogNeutralClick(this)
                }
            }
            return builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

class SimpleListDialogFragment : DialogFragment() {
    interface SimpleListDialogFragmentListener {
        fun onListDialogClick(dialog: DialogFragment, which: Int)
    }

    private var listener: SimpleListDialogFragmentListener? = null
    companion object {
        private const val ARGUMENTS_TITLE = "ARGUMENTS_TITLE"
        private const val ARGUMENTS_ITEMS = "ARGUMENTS_ITEMS"

        fun newInstance(title: String? = null, items: Array<String>): SimpleListDialogFragment
                = SimpleListDialogFragment().apply {
            isCancelable = false
            arguments = Bundle().apply {
                putString(ARGUMENTS_TITLE, title)
                putStringArray(ARGUMENTS_ITEMS, items)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? SimpleListDialogFragmentListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var title: String? = null
        var items: Array<String>? = null
        arguments?.let {
            title = it.getString(ARGUMENTS_TITLE)
            items = it.getStringArray(ARGUMENTS_ITEMS)
        }
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            title?.let {
                builder.setTitle(it)
            }
            items?.let {
                builder.setItems(it) { _, which ->
                    listener?.onListDialogClick(this, which)
                }
            }
            return builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

class SimpleRadioDialogFragment : DialogFragment() {
    interface SimpleRadioDialogFragmentListener {
        fun onRadioDialogPositiveClick(dialog: DialogFragment, checkedItem: Int)
        fun onRadioDialogNegativeClick(dialog: DialogFragment, checkedItem: Int)
        fun onRadioDialogNeutralClick(dialog: DialogFragment, checkedItem: Int)
    }

    private var defaultCheckedItem = 0
    private var checkedItem = 0
    private var listener: SimpleRadioDialogFragmentListener? = null
    companion object {
        private const val ARGUMENTS_TITLE = "ARGUMENTS_TITLE"
        private const val ARGUMENTS_ITEMS = "ARGUMENTS_ITEMS"
        private const val ARGUMENTS_POSITIVE_TITLE = "ARGUMENTS_POSITIVE_TITLE"
        private const val ARGUMENTS_NEGATIVE_TITLE = "ARGUMENTS_NEGATIVE_TITLE"
        private const val ARGUMENTS_NEUTRAL_TITLE = "ARGUMENTS_NEUTRAL_TITLE"
        private const val ARGUMENTS_DEFAULT_CHECKED_ITEM = "ARGUMENTS_DEFAULT_CHECKED_ITEM"

        fun newInstance(title: String? = null, items: Array<String>, defaultCheckedItem: Int = 0,
                        positiveTitle: String? = null, negativeTitle: String? = null,
                        neutralTitle: String? = null): SimpleRadioDialogFragment
                = SimpleRadioDialogFragment().apply {
            isCancelable = false
            arguments = Bundle().apply {
                putString(ARGUMENTS_TITLE, title)
                putStringArray(ARGUMENTS_ITEMS, items)
                putInt(ARGUMENTS_DEFAULT_CHECKED_ITEM, defaultCheckedItem)
                putString(ARGUMENTS_POSITIVE_TITLE, positiveTitle)
                putString(ARGUMENTS_NEGATIVE_TITLE, negativeTitle)
                putString(ARGUMENTS_NEUTRAL_TITLE, neutralTitle)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? SimpleRadioDialogFragmentListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var title: String? = null
        var items: Array<String>? = null
        var positiveTitle: String? = null
        var negativeTitle: String? = null
        var neutralTitle: String? = null
        arguments?.let {
            title = it.getString(ARGUMENTS_TITLE)
            items = it.getStringArray(ARGUMENTS_ITEMS)
            positiveTitle = it.getString(ARGUMENTS_POSITIVE_TITLE)
            negativeTitle = it.getString(ARGUMENTS_NEGATIVE_TITLE)
            neutralTitle = it.getString(ARGUMENTS_NEUTRAL_TITLE)
            defaultCheckedItem = it.getInt(ARGUMENTS_DEFAULT_CHECKED_ITEM)
        }
        checkedItem = defaultCheckedItem
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            title?.let {
                builder.setTitle(it)
            }
            items?.let {
                builder.setSingleChoiceItems(items, defaultCheckedItem) { dialog, which ->
                    checkedItem = which
                }
            }
            positiveTitle?.let {
                builder.setPositiveButton(it) { _, _ ->
                    listener?.onRadioDialogPositiveClick(this, checkedItem)
                }
            }
            negativeTitle?.let {
                builder.setNegativeButton(it) { _, _ ->
                    listener?.onRadioDialogNegativeClick(this, checkedItem)
                }
            }
            neutralTitle?.let {
                builder.setNeutralButton(it) { _, _ ->
                    listener?.onRadioDialogNeutralClick(this, checkedItem)
                }
            }
            return builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

class SimpleCheckboxDialogFragment : DialogFragment() {
    interface SimpleCheckboxDialogFragmentListener {
        fun onRadioDialogPositiveClick(dialog: DialogFragment, checkedItems: IntArray)
        fun onRadioDialogNegativeClick(dialog: DialogFragment, checkedItems: IntArray)
        fun onRadioDialogNeutralClick(dialog: DialogFragment, checkedItems: IntArray)
    }

    private var defaultCheckedItems = ArrayList<Int>()
    private var checkedItems = ArrayList<Int>()
    private var listener: SimpleCheckboxDialogFragmentListener? = null
    companion object {
        private const val ARGUMENTS_TITLE = "ARGUMENTS_TITLE"
        private const val ARGUMENTS_ITEMS = "ARGUMENTS_ITEMS"
        private const val ARGUMENTS_POSITIVE_TITLE = "ARGUMENTS_POSITIVE_TITLE"
        private const val ARGUMENTS_NEGATIVE_TITLE = "ARGUMENTS_NEGATIVE_TITLE"
        private const val ARGUMENTS_NEUTRAL_TITLE = "ARGUMENTS_NEUTRAL_TITLE"
        private const val ARGUMENTS_DEFAULT_CHECKED_ITEMS = "ARGUMENTS_DEFAULT_CHECKED_ITEMS"

        fun newInstance(title: String? = null, items: Array<String>, defaultCheckedItems: ArrayList<Int> = arrayListOf(),
                        positiveTitle: String? = null, negativeTitle: String? = null,
                        neutralTitle: String? = null): SimpleCheckboxDialogFragment
                = SimpleCheckboxDialogFragment().apply {
            isCancelable = false
            arguments = Bundle().apply {
                putString(ARGUMENTS_TITLE, title)
                putStringArray(ARGUMENTS_ITEMS, items)
                putIntegerArrayList(ARGUMENTS_DEFAULT_CHECKED_ITEMS, defaultCheckedItems)
                putString(ARGUMENTS_POSITIVE_TITLE, positiveTitle)
                putString(ARGUMENTS_NEGATIVE_TITLE, negativeTitle)
                putString(ARGUMENTS_NEUTRAL_TITLE, neutralTitle)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? SimpleCheckboxDialogFragmentListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var title: String? = null
        var items: Array<String>? = null
        var positiveTitle: String? = null
        var negativeTitle: String? = null
        var neutralTitle: String? = null
        arguments?.let {
            title = it.getString(ARGUMENTS_TITLE)
            items = it.getStringArray(ARGUMENTS_ITEMS)
            positiveTitle = it.getString(ARGUMENTS_POSITIVE_TITLE)
            negativeTitle = it.getString(ARGUMENTS_NEGATIVE_TITLE)
            neutralTitle = it.getString(ARGUMENTS_NEUTRAL_TITLE)
            it.getIntegerArrayList(ARGUMENTS_DEFAULT_CHECKED_ITEMS)?.let {items ->
                defaultCheckedItems = items
            }
        }
        checkedItems = defaultCheckedItems
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            title?.let {
                builder.setTitle(it)
            }

            items?.let {
                var checkedBoolItems = ArrayList<Boolean>()
                it.forEachIndexed { index, _ ->
                    checkedBoolItems.add(checkedItems.contains(index))
                }
                builder.setMultiChoiceItems(items, checkedBoolItems.toBooleanArray()) { dialog, which, isChecked ->
                    if (isChecked) {
                        checkedItems.add(which)
                    } else {
                        checkedItems.remove(which)
                    }
                }
            }
            positiveTitle?.let {
                builder.setPositiveButton(it) { _, _ ->
                    listener?.onRadioDialogPositiveClick(this, checkedItems.toIntArray())
                }
            }
            negativeTitle?.let {
                builder.setNegativeButton(it) { _, _ ->
                    listener?.onRadioDialogNegativeClick(this, checkedItems.toIntArray())
                }
            }
            neutralTitle?.let {
                builder.setNeutralButton(it) { _, _ ->
                    listener?.onRadioDialogNeutralClick(this, checkedItems.toIntArray())
                }
            }
            return builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

class CustomDialogFragment : DialogFragment() {

    interface CustomDialogFragmentListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
        fun onDialogNeutralClick(dialog: DialogFragment)
    }

    private var listener: CustomDialogFragmentListener? = null
    companion object {
        private const val ARGUMENTS_TITLE = "ARGUMENTS_TITLE"
        private const val ARGUMENTS_POSITIVE_TITLE = "ARGUMENTS_POSITIVE_TITLE"
        private const val ARGUMENTS_NEGATIVE_TITLE = "ARGUMENTS_NEGATIVE_TITLE"
        private const val ARGUMENTS_NEUTRAL_TITLE = "ARGUMENTS_NEUTRAL_TITLE"

        fun newInstance(title: String? = null,
                        positiveTitle: String? = null, negativeTitle: String? = null,
                        neutralTitle: String? = null): CustomDialogFragment
                = CustomDialogFragment().apply {
            isCancelable = false
            arguments = Bundle().apply {
                putString(ARGUMENTS_TITLE, title)
                putString(ARGUMENTS_POSITIVE_TITLE, positiveTitle)
                putString(ARGUMENTS_NEGATIVE_TITLE, negativeTitle)
                putString(ARGUMENTS_NEUTRAL_TITLE, neutralTitle)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? CustomDialogFragmentListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var title: String? = null
        var positiveTitle: String? = null
        var negativeTitle: String? = null
        var neutralTitle: String? = null
        arguments?.let {
            title = it.getString(ARGUMENTS_TITLE)
            positiveTitle = it.getString(ARGUMENTS_POSITIVE_TITLE)
            negativeTitle = it.getString(ARGUMENTS_NEGATIVE_TITLE)
            neutralTitle = it.getString(ARGUMENTS_NEUTRAL_TITLE)
        }
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.custom_dialog, null)
            builder.setView(view)
            title?.let {
                view.findViewById<TextView>(R.id.title).text = it
            }
            positiveTitle?.let {
                builder.setPositiveButton(it) { _, _ ->
                    listener?.onDialogPositiveClick(this)
                }
            }
            negativeTitle?.let {
                builder.setNegativeButton(it) { _, _ ->
                    listener?.onDialogNegativeClick(this)
                }
            }
            neutralTitle?.let {
                builder.setNeutralButton(it) { _, _ ->
                    listener?.onDialogNeutralClick(this)
                }
            }
            return builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

class CustomProgressDialogFragment : DialogFragment() {

    companion object {
        private const val ARGUMENTS_TITLE = "ARGUMENTS_TITLE"
        private const val ARGUMENTS_MESSAGE = "ARGUMENTS_MESSAGE"

        fun newInstance(title: String? = null, message: String? = null): CustomProgressDialogFragment
                = CustomProgressDialogFragment().apply {
            isCancelable = false
            arguments = Bundle().apply {
                putString(ARGUMENTS_TITLE, title)
                putString(ARGUMENTS_MESSAGE, message)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var title: String? = null
        var message: String? = null
        arguments?.let {
            title = it.getString(ARGUMENTS_TITLE)
            message = it.getString(ARGUMENTS_MESSAGE)
        }
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.custom_progress_dialog, null)
            builder.setView(view)
            title?.let {
                builder.setTitle(it)
            }
            message?.let {
                view.findViewById<TextView>(R.id.message).text = it
            }
            return builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}