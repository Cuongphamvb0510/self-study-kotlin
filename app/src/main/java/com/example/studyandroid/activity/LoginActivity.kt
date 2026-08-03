package com.example.studyandroid.activity

import android.content.Intent
import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.ImageView
import com.caverock.androidsvg.SVG
import com.example.studyandroid.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var isPasswordVisible = false

    private fun loadSvg(imageView: ImageView, fileName: String) {
        val inputStream = assets.open(fileName)
        val svg = SVG.getFromInputStream(inputStream)

        imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        imageView.setImageDrawable(
            PictureDrawable(svg.renderToPicture())
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.root)
        var edtPhoneNumber = binding.phoneNumber
        var edtPassword = binding.password
        val btnLogin = binding.btnLogin

        loadSvg(binding.imgEye, "eye_off.svg")


        binding.imgEye.setOnClickListener {

            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {

                // Hiện mật khẩu
                edtPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

                loadSvg(binding.imgEye, "eye.svg")

            } else {

                // Ẩn mật khẩu
                edtPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

                loadSvg(binding.imgEye, "eye_off.svg")
            }

            // Đưa con trỏ về cuối
            binding.password.setSelection(binding.password.text.length)
        }



        btnLogin.setOnClickListener() {
            var isValid = true
            val phone = edtPhoneNumber.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

//            if (phone.isEmpty()) {
//                edtPhoneNumber.error = "Vui lòng nhập mật khẩu"
//                edtPhoneNumber.requestFocus()
//                isValid = false
////                btnLogin.isEnabled = false
//            }
//
//            if (password.isEmpty()) {
//                edtPassword.error = "Vui lòng nhập mật khẩu"
//                edtPassword.requestFocus()
//                isValid = false
////                btnLogin.isEnabled = false
//            }

//            if (isValid) {
//                if (phone == "0347881708" && password == "aaaa1234") {
//                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//
//                } else {
//                    val messageView = MessageView()
//                    messageView.showError(
//                        binding.root,
//                        "Sai tài khoản hoặc mật khẩu"
//                    )
//                }
//
//            }
            // Đóng LoginActivity để không quay lại khi nhấn Back
        }

        binding.btnWeather.setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))
        }

        binding.btnPosts.setOnClickListener {
            startActivity(Intent(this, PostsActivity::class.java))
        }
    }
}