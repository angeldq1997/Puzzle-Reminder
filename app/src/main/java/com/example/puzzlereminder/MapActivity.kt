package com.example.puzzlereminder

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.puzzlereminder.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() , SensorEventListener{
    private lateinit var binding : ActivityMapBinding
    private lateinit var sensorManager : SensorManager
    private var sensor : Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutinflater)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        sensor?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }

    override fun onSensorChanged(event: SensorEvent) {
        val lumos = event.values[0]
        if (lumos < 10000){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            setTextDarkTheme()
            binding.main.setBackgroundResource(R.drawable.dark_theme)
        } else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            setTextLightTheme()
            binding.main.setBackgroundResource(R.drawable.background)
        }
    }

    private fun setTextDarkTheme() {
        //binding.tvTitle.setTextColor(Color.WHITE)

    }

    private fun setTextLightTheme() {
        //binding.tvTitle.setTextColor(Color.BLACK)
    }
}