package hu.ait.cookbook.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import hu.ait.cookbook.R
import hu.ait.cookbook.data.Meal
import kotlinx.android.synthetic.main.meal_dialog.view.*

class MealDialog : DialogFragment(){

    interface ItemHandler{
        fun itemCreated(item: Meal)
    }

    lateinit var itemHandler: ItemHandler


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ItemHandler){
            itemHandler = context
        } else {
            throw RuntimeException(
                "The Activity is not implementing the ItemHandler interface.")
        }
    }

    lateinit var etMeal: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("Meal")

        val dialogView = requireActivity().layoutInflater.inflate(
            R.layout.meal_dialog, null
        )

        etMeal = dialogView.etMeal


        dialogBuilder.setView(dialogView)
        setDialogButtons(dialogBuilder)

        return dialogBuilder.create()
    }

    fun setDialogButtons(dialogBuilder: AlertDialog.Builder){
        dialogBuilder.setPositiveButton("Ok") {
                dialog, which ->
        }
        dialogBuilder.setNegativeButton("Cancel") {
                dialog, which ->
        }
    }

    override fun onResume() {
        super.onResume()
        val positiveButton = (dialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            if (etMeal.text.isNotEmpty()) {
                handleCityCreate()
                dialog!!.dismiss()
            }else{
                etMeal.error = "This field can not be empty"
            }
        }
    }

    private fun handleCityCreate() {
        itemHandler.itemCreated(
            Meal(
                null,
                etMeal.text.toString()
            )
        )
    }

}