package com.example.studyandroid.activity

import android.os.Bundle
import android.util.Log
import com.example.studyandroid.BuildConfig
import com.example.studyandroid.databinding.ActivityWeatherBinding
import com.example.studyandroid.view.MessageView

class WeatherActivity : BaseActivity() {
    private lateinit var binding: ActivityWeatherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.root)

        binding.btnSearch.setOnClickListener {
            val city = binding.edtCity.text.toString().trim()

            if (city.isEmpty()) {
                binding.edtCity.error = "Vui long nhap ten thanh pho"
                binding.edtCity.requestFocus()
                return@setOnClickListener
            }

            fetchWeather(city)
        }

    }

    private fun fetchWeather(city: String) {
        val apiKey = BuildConfig.OPENWEATHER_API_KEY
        if (apiKey.isEmpty()) {
            MessageView.showError(
                this@WeatherActivity,
                "Chưa cấu hình API key"
            )
            return
        }

        binding.progressBar.visibility = android.view.View.VISIBLE

        val url =
            "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$apiKey&units=metric&lang=vi"

        val request = okhttp3.Request.Builder()
            .url(url)
            .get()
            .build()

        val client = okhttp3.OkHttpClient()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                runOnUiThread {
                    binding.progressBar.visibility = android.view.View.GONE
                    MessageView.showError(
                        this@WeatherActivity,
                        "Lỗi mạng"
                    )
                }
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val body = response.body?.string()
                Log.d("WeatherAPI", "code = ${response.code}")
                Log.d("WeatherAPI", "body = $body")

                runOnUiThread {
                    binding.progressBar.visibility = android.view.View.GONE

                    if (!response.isSuccessful || body.isNullOrEmpty()) {
                        MessageView.showError(
                            this@WeatherActivity,
                            "Không lấy được dữ liệu (${response.code})"
                        )
                        return@runOnUiThread
                    }

                    val data = com.google.gson.Gson().fromJson(
                        body,
                        com.example.studyandroid.model.weather.WeatherResponse::class.java
                    )

                    binding.txtCity.text = data.name
                    binding.txtTemp.text = "${data.main.temp}°C"
                    binding.txtDesc.text =
                        data.weather.firstOrNull()?.description.orEmpty()
                    binding.txtHumidity.text = "Độ ẩm: ${data.main.humidity}%"
                }
            }
        })
    }
}