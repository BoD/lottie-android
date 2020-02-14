package com.airbnb.lottie.issues

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Reproduce any issues here.

        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        lottieAnimationView.setAnimation(R.raw.lottie_logo1)

        //
        // IMPORTANT NOTE: TO REPRO THIS ISSUE, YOU MUST DISABLE SYSTEM ANIMATIONS:
        //
        // adb shell settings put global window_animation_scale 0
        // adb shell settings put global transition_animation_scale 0
        // adb shell settings put global animator_duration_scale 0
        val animatorListener = object : Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator) {
                // You will notice in the logs that this is called twice
                Log.d("MainActivity", "onAnimationEnd ${counter++}")
            }

            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationStart(animation: Animator) {}
        }
        lottieAnimationView.addAnimatorListener(animatorListener)
        lottieAnimationView.playAnimation()
    }
}
