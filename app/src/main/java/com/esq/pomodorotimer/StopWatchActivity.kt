package com.esq.pomodorotimer

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.esq.pomodorotimer.databinding.ActivityStopWatchBinding
import kotlinx.android.synthetic.main.activity_stop_watch.*

class StopWatchActivity : AppCompatActivity() {
    lateinit var bind: ActivityStopWatchBinding
    private var stopSpeed = 0F
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
        val initialSpeed = animation_view.speed
        bind.buttonStart.setOnClickListener {
            bind.timerProgramCountdown.base = SystemClock.elapsedRealtime()
            bind.timerProgramCountdown.start()
            animation_view.playAnimation()
            animation_view.speed = initialSpeed
        }
        bind.buttonStop.setOnClickListener {
            bind.timerProgramCountdown.stop()
            animation_view.speed = stopSpeed
        }
    }
}
