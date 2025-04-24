package com.wzh.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val hell =  Hell()
    private val PREFS_NAME: String = "ThemePrefs"
    private val KEY_THEME: String = "current_theme"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentTheme =
            getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getInt(KEY_THEME, R.style.AppTheme_Blue)
        setTheme(currentTheme)

        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSwitchTheme = findViewById<Button>(R.id.button)
        btnSwitchTheme.setOnClickListener { switchTheme() }
    }

    private fun switchTheme() {
        val newTheme =
            if (getCurrentTheme() == R.style.AppTheme_Blue) R.style.AppTheme_Yellow else R.style.AppTheme_Blue
        getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit().putInt(KEY_THEME, newTheme).apply()
        recreate()
    }

    private fun getCurrentTheme(): Int {
        return getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getInt(
            KEY_THEME,
            R.style.AppTheme_Blue
        )
    }
}