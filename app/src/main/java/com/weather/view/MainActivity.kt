package com.weather.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weather.view.main.MainFragment
import com.weather.weather.R


class MainActivity : AppCompatActivity() {

    private val receiver = ConnectivityBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}