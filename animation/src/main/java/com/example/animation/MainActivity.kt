package com.example.animation

import android.app.Activity
import android.os.Bundle
import android.widget.Button

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.animation.R.id.button


class MainActivity : Activity() {
    var scaleAnimation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.start);
//        button.setOnClickListener {
//            button.startAnimation(scaleAnimation)
//        }
    }
}



