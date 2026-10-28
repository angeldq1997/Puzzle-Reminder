package com.example.puzzlereminder

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import com.example.puzzlereminder.databinding.ActivityWebBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class WebActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding : ActivityWebBinding
    private lateinit var sensorManager : SensorManager
    private var sensor : Sensor? = null
    private var sensor2 : Sensor? = null
    private var position : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        setContentView(binding.root)
        setComponents()
        initListeners()
    }

    private fun initListeners() {
        binding.wButtonNext.setOnClickListener {
            when (position){
                1 -> {
                    binding.wTvTitle.setText(R.string.p_name_2)
                    binding.wBrowser.loadUrl("https://www.guiasnintendo.com/0_NINTENDO_DS/profesor_layton_villa_misteriosa/profesor_layton_villa_misteriosa_sp/puzle_002.html")
                    position++
                }

                2 -> {
                    binding.wTvTitle.setText(R.string.p_name_3)
                    binding.wBrowser.loadUrl("https://www.guiasnintendo.com/0_NINTENDO_DS/profesor_layton_villa_misteriosa/profesor_layton_villa_misteriosa_sp/puzle_003.html")
                    position++
                }

                3 -> {
                    binding.wTvTitle.setText(R.string.p_name_4)
                    binding.wBrowser.loadUrl("https://www.guiasnintendo.com/0_NINTENDO_DS/profesor_layton_villa_misteriosa/profesor_layton_villa_misteriosa_sp/puzle_004.html")
                    position++
                }

                4 -> {
                    Toast.makeText(this, getText(R.string.w_last_page), Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.wButtonBack.setOnClickListener {
            when (position){
                1 -> {
                    Toast.makeText(this, getText(R.string.w_first_page), Toast.LENGTH_LONG).show()
                }

                2 -> {
                    binding.wTvTitle.setText(R.string.p_name_1)
                    binding.wBrowser.loadUrl("https://www.guiasnintendo.com/0_NINTENDO_DS/profesor_layton_villa_misteriosa/profesor_layton_villa_misteriosa_sp/puzle_001.html")
                    position--
                }

                3 -> {
                    binding.wTvTitle.setText(R.string.p_name_2)
                    binding.wBrowser.loadUrl("https://www.guiasnintendo.com/0_NINTENDO_DS/profesor_layton_villa_misteriosa/profesor_layton_villa_misteriosa_sp/puzle_002.html")
                    position--
                }

                4 -> {
                    binding.wTvTitle.setText(R.string.p_name_3)
                    binding.wBrowser.loadUrl("https://www.guiasnintendo.com/0_NINTENDO_DS/profesor_layton_villa_misteriosa/profesor_layton_villa_misteriosa_sp/puzle_003.html")
                    position--
                }
            }
        }
    }

    private fun setComponents() {
        binding.wBrowser.loadUrl("https://www.guiasnintendo.com/0_NINTENDO_DS/profesor_layton_villa_misteriosa/profesor_layton_villa_misteriosa_sp/puzle_001.html")
    }

    override fun onResume() {
        super.onResume()
        sensor?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
        sensor2?.also {
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
        val movement = event.values[0]
        if ( event.sensor.type == sensor?.type && lumos < 10000){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            setTextDarkTheme()
            binding.wMain.setBackgroundResource(R.drawable.dark_theme)
        } else if (event.sensor.type == sensor?.type) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            setTextLightTheme()
            binding.wMain.setBackgroundResource(R.drawable.background)
        }

        if (event.sensor.type == sensor2?.type && movement > 3){
            Toast.makeText(this, "This is sickening me :(", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setTextDarkTheme() {
        binding.wTvTitle.setTextColor(Color.WHITE)
        binding.wButtonNext.setTextColor(Color.WHITE)
        binding.wButtonBack.setTextColor(Color.WHITE)
    }

    private fun setTextLightTheme() {
        binding.wTvTitle.setTextColor(Color.BLACK)
        binding.wButtonNext.setTextColor(Color.BLACK)
        binding.wButtonBack.setTextColor(Color.BLACK)
    }
}