package com.yyc.mydemo

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    var mediaPlayer : MediaPlayer?=null
    var voicePath = "http://file.fh21.com.cn/fhfile1/M00/00/1F/oYYBAF3ChzuAWkTmAAKgeGbqOWo395.mp3"
//    var voicePath = "http://downsc.chinaz.net/Files/DownLoad/sound1/201804/9945.mp3"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btn1.setOnClickListener {
            var aa0 = System.currentTimeMillis()
            var uri = URI(voicePath)
            var aa = System.currentTimeMillis()
            log("00--" + (aa0-aa))
            mediaPlayer = MediaPlayer()
            var aa1 = System.currentTimeMillis()
            log("11--" + (aa1-aa))
            mediaPlayer?.setDataSource(voicePath)
            var aa2 = System.currentTimeMillis()
            log("22--" + (aa2-aa1))
            mediaPlayer?.prepare()
            var aa3 = System.currentTimeMillis()
            log("33--" + (aa3-aa2))
        }

        btn2.setOnClickListener {
            Thread(Runnable {
                log("是否可达：" + checkUrlEffectivity(voicePath,20*1000))
            }).start()
        }
    }

    private fun log(str:String) {
        Log.e("aa","str:" + str)

    }

    /**
     * 判读url是否有效
     */
    fun checkUrlEffectivity(address: String, waitMilliSecond: Int): Boolean {
        try {
            val url = URL(address)
            val conn = url.openConnection() as HttpURLConnection
            conn.setUseCaches(false)
            conn.setInstanceFollowRedirects(true)
            conn.setConnectTimeout(waitMilliSecond)
            conn.setReadTimeout(waitMilliSecond)

            //HTTP connect
            try {
                conn.connect()
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }

            val code = conn.getResponseCode()
            return if (code >= 100 && code < 400) {
                true
            } else false

        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }

}
