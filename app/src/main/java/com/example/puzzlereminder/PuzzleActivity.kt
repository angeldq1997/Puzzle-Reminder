package com.example.puzzlereminder

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.puzzlereminder.databinding.ActivityPuzzleBinding

class PuzzleActivity : AppCompatActivity() , SensorEventListener{
    private lateinit var binding : ActivityPuzzleBinding
    private lateinit var sensorManager : SensorManager
    private var sensor : Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuzzleBinding.inflate(layoutInflater)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        setContentView(binding.root)

        val puzzles = listOf(
            Puzzle(getString(R.string.p_name_1), R.drawable.puzzle1, getString(R.string.p1_hint1), getString(R.string.p1_hint2), getString(R.string.p1_hint3)),
            Puzzle(getString(R.string.p_name_2), R.drawable.puzzle2, getString(R.string.p2_hint1), getString(R.string.p2_hint2), getString(R.string.p2_hint3)),
            Puzzle(getString(R.string.p_name_3), R.drawable.puzzle3, getString(R.string.p3_hint1), getString(R.string.p3_hint2), getString(R.string.p3_hint3)),
            Puzzle(getString(R.string.p_name_4), R.drawable.puzzle4, getString(R.string.p4_hint1), getString(R.string.p4_hint2), getString(R.string.p4_hint3)),
        )

        binding.pRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.pRecyclerView.adapter = PuzzleAdapter(puzzles)
        initComponents()
        setListeners()
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
        if ( lumos < 10000){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            setTextDarkTheme()
            binding.apMain.setBackgroundResource(R.drawable.dark_theme)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            setTextLightTheme()
            binding.apMain.setBackgroundResource(R.drawable.background)
        }
    }

    private fun initComponents() {

    }

    private fun setListeners() {

    }

    private fun setTextDarkTheme() {

    }

    private fun setTextLightTheme() {

    }
}