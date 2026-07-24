package com.example.kotlin_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.kotlin_test.databinding.FragmentFirstBinding

/**
 * Giao diện Unit Converter — dùng UnitConverter (enum, data class, when, null safety).
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val converter = UnitConverter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val units = converter.supportedUnits()
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            units
        )
        binding.spinnerFrom.adapter = adapter
        binding.spinnerTo.adapter = adapter

        // Mặc định: cm → m
        binding.spinnerFrom.setSelection(units.indexOf("cm").coerceAtLeast(0))
        binding.spinnerTo.setSelection(units.indexOf("m").coerceAtLeast(0))

        binding.buttonConvert.setOnClickListener {
            convertAndShow()
        }
    }

    private fun convertAndShow() {
        val valueText = binding.editValue.text?.toString()
        val from = binding.spinnerFrom.selectedItem as? String
        val to = binding.spinnerTo.selectedItem as? String

        val result = converter.convert(valueText, from, to)
        binding.textResult.text = converter.describe(result)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
