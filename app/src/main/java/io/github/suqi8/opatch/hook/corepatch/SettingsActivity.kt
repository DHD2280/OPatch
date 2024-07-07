package io.github.suqi8.opatch.hook.corepatch

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.graphics.Insets
import android.os.Bundle
import android.preference.PreferenceFragment
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowInsets
import io.github.suqi8.opatch.R
import java.util.Objects

@Suppress("deprecation")
class SettingsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        checkXSharedPreferences()
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container, SettingsFragment()).commit()
        }
    }

    @SuppressLint("WorldReadableFiles")
    private fun checkXSharedPreferences() {
        try {
            // getSharedPreferences will hooked by LSPosed
            // will not throw SecurityException
            getSharedPreferences("conf", MODE_WORLD_READABLE)
        } catch (exception: SecurityException) {
            AlertDialog.Builder(this)
                .setTitle(R.string.config_error)
                .setMessage(R.string.not_supported)
                .setPositiveButton(
                    android.R.string.ok
                ) { dialog12: DialogInterface?, which: Int -> finish() }
                .setNegativeButton(R.string.ignore, null)
                .show()
        }
    }

    class SettingsFragment : PreferenceFragment(), OnSharedPreferenceChangeListener {
        @Deprecated("Deprecated in Java")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            preferenceManager.sharedPreferencesName = "conf"
            addPreferencesFromResource(R.xml.prefs)
        }

        @Deprecated("Deprecated in Java")
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            view.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            view.setOnApplyWindowInsetsListener { v: View, windowInsets: WindowInsets ->
                val insets: Insets?
                insets = windowInsets.getInsets(WindowInsets.Type.systemBars())
                val mlp = v.layoutParams as MarginLayoutParams
                mlp.leftMargin = insets.left
                mlp.bottomMargin = insets.bottom
                mlp.rightMargin = insets.right
                mlp.topMargin = insets.top
                v.layoutParams = mlp
                WindowInsets.CONSUMED
            }
            super.onViewCreated(view, savedInstanceState)
        }

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        }

        @Deprecated("Deprecated in Java")
        override fun onResume() {
            super.onResume()
            preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        }

        @Deprecated("Deprecated in Java")
        override fun onPause() {
            super.onPause()
            preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        }
    }
}
