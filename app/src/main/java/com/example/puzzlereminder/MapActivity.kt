package com.example.puzzlereminder

import android.graphics.Color
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
import java.sql.Time
import java.time.LocalTime

class MapActivity : AppCompatActivity() , SensorEventListener{
    private lateinit var binding : ActivityMapBinding
    private lateinit var sensorManager : SensorManager
    private var sensor : Sensor? = null
    private var step : Int = 0
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
                    binding.mapView1.visibility = View.VISIBLE
                    binding.enterLocation.setText(R.string.m_b_enter_marker)
                    binding.mQuestion.text = R.string.m_question1.toString()
                    this.step++
                }
                1 -> {
                    //TODO: change the if conditions until I learn how to create a marker and make it draggable
                    if (LocalTime.now().second % 2 == 0){
                    //if (longitude < 50 && longitude > 40 && latitude < 20 && latitude > 30){

                        binding.mQuestion.text = getText(R.string.m_question2)
                        this.step++
                    } else {
                        Toast.makeText(this, getText(R.string.m_incorrect), Toast.LENGTH_LONG).show()
                    }
                }
                2 -> {
                    if (LocalTime.now().second % 2 == 0){
                    //if (longitude < 40 && longitude > 30 && latitude < 60 && latitude > 40){

                        binding.mQuestion.setText(R.string.m_question3)
                        this.step++
                    } else {
                        Toast.makeText(this, getText(R.string.m_incorrect), Toast.LENGTH_LONG).show()
                    }
                }
                3 -> {
                    if (LocalTime.now().second % 2 == 0){
                    //if (longitude < 90 && longitude > 80 && latitude < 10 && latitude > 0){

                        binding.mQuestion.setText(R.string.m_question4)
                        this.step++
                    } else {
                        Toast.makeText(this, getText(R.string.m_incorrect), Toast.LENGTH_LONG).show()
                    }
                }
                4 -> {
                    if (LocalTime.now().second % 2 == 0){
                    //if (longitude < 90 && longitude > 80 && latitude < 10 && latitude > 0){

                        binding.mQuestion.setText(R.string.m_all_correct)
                        binding.enterLocation.setText(R.string.m_exit)
                        this.step++
                    } else {
                        Toast.makeText(this,  getText(R.string.m_incorrect), Toast.LENGTH_LONG).show()
                    }
                }

                5 -> {
                    finish()
                }
            }
        }
    }

    private fun setMap() {
        binding.mapView1.getMapAsync { map ->
            map.setStyle("https://tiles.openfreemap.org/styles/liberty") {
                val madrid = LatLng(40.4165, -3.70256)
                map.cameraPosition = CameraPosition.Builder()
                    .target(madrid)
                    .zoom(10.0)
                    .build()
                map.addMarker(MarkerOptions().position(madrid).title("Madrid"))
            }
        }
    }

    override fun onStart()   { super.onStart();   binding.mapView1.onStart() }
    override fun onStop()    { binding.mapView1.onStop();    super.onStop() }
    override fun onLowMemory() { super.onLowMemory(); binding.mapView1.onLowMemory() }
    override fun onDestroy() { binding.mapView1.onDestroy(); super.onDestroy() }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState); binding.mapView1.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        binding.mapView1.onResume()
        sensor?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        binding.mapView1.onPause()
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
        binding.mQuestion.setTextColor(Color.WHITE)
        binding.mHelp.setTextColor(Color.WHITE)
        binding.enterLocation.setTextColor(Color.WHITE)
    }

    private fun setTextLightTheme() {
        binding.mQuestion.setTextColor(Color.BLACK)
        binding.mHelp.setTextColor(Color.BLACK)
        binding.enterLocation.setTextColor(Color.BLACK)
    }
}