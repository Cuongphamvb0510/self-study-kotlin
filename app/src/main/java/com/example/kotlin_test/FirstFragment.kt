package com.example.kotlin_test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.example.kotlin_test.databinding.FragmentFirstBinding

/**
 * Bấm nút để chạy demo Unit Converter (xem Logcat tag: UnitConverter).
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // Null safety: _binding có thể null sau onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val lines = UnitConverterDemo.runSamples()
            lines.forEach { Log.d("UnitConverter", it) }

            // Hiện 1 dòng kết quả trên UI
            val preview = lines.getOrNull(1) ?: "Xem Logcat: UnitConverter"
            Snackbar.make(view, preview, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}