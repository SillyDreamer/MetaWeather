package com.example.metaweather

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.weather.ConsolidatedWeather

class MyDialogFragment : DialogFragment() {

    fun newInstance(consolidatedWeather: ConsolidatedWeather?) : MyDialogFragment {

        val dialog = MyDialogFragment()
        val args = Bundle()
        if (consolidatedWeather != null)
            args.putString(
                DATA_KEY, "${consolidatedWeather.weather_state_name}\n" +
                    "${consolidatedWeather.the_temp.toInt()}°\n" +
                    "${consolidatedWeather.applicable_date} ")
        else
            args.putString(ERROR_KEY, "we could not determine the weather")
        dialog.arguments = args
        return dialog
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Weather")
                .setMessage(arguments?.getString(DATA_KEY) ?: arguments?.getString(ERROR_KEY))
                .setPositiveButton("OK") { dialog, id -> dialog.cancel()}
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        const val ERROR_KEY = "error"
        const val DATA_KEY = "data"
    }
}