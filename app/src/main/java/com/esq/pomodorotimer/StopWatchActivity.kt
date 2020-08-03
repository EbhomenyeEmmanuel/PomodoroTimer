package com.esq.pomodorotimer

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.esq.pomodorotimer.databinding.ActivityStopWatchBinding
import kotlinx.android.synthetic.main.activity_stop_watch.*

class StopWatchActivity : AppCompatActivity() {
    lateinit var bind: ActivityStopWatchBinding
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                Toast.makeText(this, "Rate", Toast.LENGTH_SHORT).show()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_stop_watch)
        timerProgramCountdown.base = 25L
        timerProgramCountdown.setCountDown(true)
        bind.buttonStart.setOnClickListener {
            bind.timerProgramCountdown.base = SystemClock.elapsedRealtime()
            bind.timerProgramCountdown.start()
            buttonStart.visibility = View.GONE
            buttonStop.visibility = View.VISIBLE
        }
        bind.buttonStop.setOnClickListener {
            buttonStart.visibility = View.VISIBLE
            buttonStop.visibility = View.GONE
            bind.timerProgramCountdown.stop()
        }
        bind.buttonReset.setOnClickListener { bind.timerProgramCountdown.stop() }
    }
}
