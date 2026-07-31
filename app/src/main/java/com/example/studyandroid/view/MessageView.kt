package com.example.studyandroid.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import com.example.studyandroid.databinding.LayoutMessageBinding

enum class MessageType {
    SUCCESS, ERROR, WARNING
}

object MessageView {

    fun showSuccess(
        context: Context,
        message: String,
        durationMs: Long = 5000L
    ) {
        show(context, message, MessageType.SUCCESS, durationMs)
    }

    fun showError(
        context: Context,
        message: String,
        durationMs: Long = 5000L
    ) {
        show(context, message, MessageType.ERROR, durationMs)
    }

    fun showWarning(
        context: Context,
        message: String,
        durationMs: Long = 5000L
    ) {
        show(context, message, MessageType.WARNING, durationMs)
    }

    private fun show(context: Context, message: String, type: MessageType, durationMs: Long) {
        val binding = LayoutMessageBinding.inflate(LayoutInflater.from(context))

        when (type) {
            MessageType.SUCCESS -> {
                binding.root.setBackgroundColor(0xFF4CAF50.toInt())
                binding.imgIcon.setImageResource(android.R.drawable.ic_dialog_info)
            }

            MessageType.ERROR -> {
                binding.root.setBackgroundColor(0xFFF44336.toInt())
                binding.imgIcon.setImageResource(android.R.drawable.ic_dialog_alert)
            }

            MessageType.WARNING -> {
                binding.root.setBackgroundColor(0xFFFF9800.toInt())
                binding.imgIcon.setImageResource(android.R.drawable.ic_dialog_alert)
            }
        }

        binding.txtMessage.text = message

        val toast = Toast(context.applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        @Suppress("DEPRECATION")
        toast.view = binding.root
        toast.setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 100)
        toast.show()
        Handler(Looper.getMainLooper()).postDelayed({
            toast.cancel()
        }, durationMs)
    }
}