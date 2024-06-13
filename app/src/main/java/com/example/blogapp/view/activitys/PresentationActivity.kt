package com.example.blogapp.view.activitys

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import com.example.blogapp.databinding.LayoutActivityPresentationBinding


class PresentationActivity : ComponentActivity() {
    private lateinit var viewBinding: LayoutActivityPresentationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = LayoutActivityPresentationBinding.inflate(layoutInflater)

        initializePresentation()
        setContentView(viewBinding.root)
    }

    private fun initializePresentation() {
        val time = object : CountDownTimer(2999, 1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                openNewActivity()
            }
        }
        time.start()
    }

    private fun openNewActivity() {
        val intent = Intent(this, PrincipalActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun onResume() {
        super.onResume()
        initializePresentation()
    }
}
