package com.example.drawingoncanvas

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val miCanvitas = MiCanvas(this)

//        fun Activity.showSystemUI() {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                window.setDecorFitsSystemWindows(false)
//                miCanvitas.windowInsetsController?.show(WindowInsets.Type.systemBars())
//            } else {
//                @Suppress("DEPRECATION")
//                window.decorView.systemUiVisibility =
//                    (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN)
//            }
            //WindowCompat.setDecorFitsSystemWindows(window, false)
            //window.setDecorFitsSystemWindows(false)

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
//            window.setDecorFitsSystemWindows(false)
//        }else{
//            miCanvitas.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
//            //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        }

            miCanvitas.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
            setContentView(miCanvitas)
        }
    }
//}

