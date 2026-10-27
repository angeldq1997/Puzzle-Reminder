package com.example.puzzlereminder

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.puzzlereminder.databinding.ActivityMapBinding

import org.maplibre.android.MapLibre
import org.maplibre.android.annotations.BaseMarkerOptions
import org.maplibre.android.annotations.Marker
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap

class MapActivity : AppCompatActivity() , SensorEventListener{
    private lateinit var binding : ActivityMapBinding
    private lateinit var sensorManager : SensorManager
    private var step : Int = 0
    private var sensor : Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapLibre.getInstance(this)
        binding = ActivityMapBinding.inflate(layoutInflater)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        setContentView(binding.root)
        setMap()
        setListeners()
    }

    private fun setListeners() {


        binding.enterLocation.setOnClickListener {
            var latitude = 0
            var longitude = 0
            when (step){
                0 -> {
                    binding.mHelp.visibility = View.GONE
                    binding.mv1.visibility = View.VISIBLE
                    binding.enterLocation.setText(R.string.m_b_enter_marker)
                    binding.mQuestion.text = R.string.m_question1.toString()
                    this.step++
                }
                1 -> {
                    //TODO QUESTION 1,2,3,4 with data taken from marker position
                    if (longitude < 50 && longitude > 40 && latitude < 20 && latitude > 30){

                        binding.mQuestion.text = R.string.m_question2.toString()
                        this.step++
                    } else {
                        Toast.makeText(this, R.string.m_incorrect.toString(), Toast.LENGTH_LONG).show()
                    }
                }
                2 -> {
                    if (longitude < 40 && longitude > 30 && latitude < 60 && latitude > 40){

                        binding.mQuestion.text = R.string.m_question3.toString()
                        this.step++
                    } else {
                        Toast.makeText(this, R.string.m_incorrect.toString(), Toast.LENGTH_LONG).show()
                    }
                }
                3 -> {
                    if (longitude < 90 && longitude > 80 && latitude < 10 && latitude > 0){

                        binding.mQuestion.text = R.string.m_question4.toString()
                        this.step++
                    } else {
                        Toast.makeText(this, R.string.m_incorrect.toString(), Toast.LENGTH_LONG).show()
                    }
                }
                4 -> {
                    if (longitude < 90 && longitude > 80 && latitude < 10 && latitude > 0){

                        binding.mQuestion.text = R.string.m_all_correct.toString()
                        binding.enterLocation.text = R.string.m_exit.toString()
                        this.step++
                        /*
                        //RESET
                        binding.mQuestion.text = R.string.m_help.toString()
                        binding.enterLocation.text = R.string.m_b_start.toString()
                        binding.mHelp.visibility = View.VISIBLE
                        binding.mv1.visibility = View.GONE
                         */
                    } else {
                        Toast.makeText(this, R.string.m_incorrect.toString(), Toast.LENGTH_LONG).show()
                    }
                }

                5 -> {

                }
            }
        }
    }

    private fun setMap() {
        binding.mv1.getMapAsync { map ->
            map.setStyle("https://tiles.openfreemap.org/styles/liberty") {
                val madrid = LatLng(40.4165, -3.70256)
                map.cameraPosition = CameraPosition.Builder()
                    .target(madrid)
                    .zoom(15.0)
                    .build()
                map.addMarker(MarkerOptions().position(madrid).title("Madrid"))
            }
        }
    }

    override fun onStart()   { super.onStart();   binding.mv1.onStart() }
    override fun onStop()    { binding.mv1.onStop();    super.onStop() }
    override fun onLowMemory() { super.onLowMemory(); binding.mv1.onLowMemory() }
    override fun onDestroy() { binding.mv1.onDestroy(); super.onDestroy() }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState); binding.mv1.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        binding.mv1.onResume()
        sensor?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        binding.mv1.onPause()
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }

    override fun onSensorChanged(event: SensorEvent) {
        val lumos = event.values[0]
        if (lumos < 10000){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            setTextDarkTheme()
            binding.mapMain.setBackgroundResource(R.drawable.dark_theme)
        } else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            setTextLightTheme()
            binding.mapMain.setBackgroundResource(R.drawable.background)
        }
    }

    private fun setTextDarkTheme() {
        //binding.tvTitle.setTextColor(Color.WHITE)
    }

    private fun setTextLightTheme() {
        //binding.tvTitle.setTextColor(Color.BLACK)
    }
}