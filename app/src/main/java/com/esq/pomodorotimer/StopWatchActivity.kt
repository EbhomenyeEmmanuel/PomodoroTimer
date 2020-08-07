package com.esq.pomodorotimer

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.esq.pomodorotimer.databinding.ActivityStopWatchBinding
import kotlinx.android.synthetic.main.activity_stop_watch.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import java.util.concurrent.TimeUnit

class StopWatchActivity : AppCompatActivity() {
    lateinit var bind: ActivityStopWatchBinding
    private val TAG: String? = this::class.simpleName
    private var isRunning: Boolean = false
    private lateinit var timer: CountDownTimer
    private val START_TIME_IN_MILIS = TimeUnit.MINUTES.toSeconds(25)
    private var mTimeLeftInMillis = START_TIME_IN_MILIS

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_stop_watch)
        setSupportActionBar(toolbar)

        buttonStart.setOnClickListener {
            startTimer()
        }
        if (isRunning) {
            animation_view.playAnimation()
        } else {
            animation_view.pauseAnimation()
        }
        buttonStop.setOnClickListener {
            timer.cancel()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            START_TIME_IN_MILIS,
            1000
        ) {
            override fun onTick(p0: Long) {
                mTimeLeftInMillis = p0
                upDateCountDownText()
            }

            override fun onFinish() {
                timerTextView.text = "Time Up"
            }
        }.start()
        isRunning = true
    }

    private fun upDateCountDownText() {
        val minutes = TimeUnit.SECONDS.toMinutes(mTimeLeftInMillis / 1000)
        val seconds = minutes % 60
        timerTextView.text = String.format("%02d:%02d", minutes, seconds)
    }

}

