package com.yyz.flywith24_r_toast_demo

import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    fun normalToast(view: View) {
        showToastDelay {
            Toast.makeText(this, R.string.text_toast, Toast.LENGTH_SHORT).also {
                it.setGravity(Gravity.CENTER, 0, 0)
                it.setMargin(0.1f, 0.06f)
                
                it.addCallback(object : Toast.Callback() {
                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d(TAG, "onToastShown")
                    }

                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d(TAG, "onToastHidden")

                        // targetSdkVersion R 时正常，小于 R 崩溃
                        constructorToast()
                    }
                })
            }.show()
        }
    }

    private fun constructorToast() {
        Toast(this).also {
            it.setText(R.string.text_toast2)
            it.setGravity(Gravity.CENTER, 0, 0)
            it.setMargin(0.1f, 0.06f)
        }.show()
    }

    fun customToast(view: View) {
        showToastDelay {
            val customToast = Toast(this)
            customToast.view = LayoutInflater.from(this).inflate(R.layout.view_toast, null)
            customToast.setGravity(Gravity.CENTER, 0, 0)
            customToast.setMargin(0.1f, 0.06f)
            customToast.show()
        }
    }


    private fun showToastDelay(block: () -> Unit) {
        Handler().postDelayed({
            block.invoke()
        }, 2000)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}