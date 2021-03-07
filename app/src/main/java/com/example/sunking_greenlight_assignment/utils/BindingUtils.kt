package com.example.sunking_greenlight_assignment.utils

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.sunking_greenlight_assignment.models.*


class BindingUtils {

    companion object {

        @JvmStatic
        @BindingAdapter("bindData")
        fun bindData(textView: TextView, data: Any) {
            when (data) {
                is Country -> {
                    textView.text = data.country
                }
                is Zone -> {
                    textView.text = data.zone
                }
                is Area -> {
                    textView.text = data.area
                }
                is Region -> {
                    textView.text = data.region
                }
                is Employee -> {
                    textView.text = data.name
                }
            }
        }
    }


}