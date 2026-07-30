package com.example.studyandroid.view

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.studyandroid.databinding.LayoutMessageBinding

class MessageView {

    fun showError(parent: ViewGroup, message: String) {

        val binding = LayoutMessageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.txtMessage.text = message

        parent.addView(binding.root)

        binding.root.translationY = -300f
        binding.root.alpha = 0f

        binding.root.animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(300)
            .start()

        Handler(Looper.getMainLooper()).postDelayed({

            binding.root.animate()
                .translationY(-300f)
                .alpha(0f)
                .setDuration(300)
                .withEndAction {
                    parent.removeView(binding.root)
                }

        }, 2000)
    }
}