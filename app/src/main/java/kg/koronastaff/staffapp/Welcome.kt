package kg.koronastaff.staffapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        Thread(Runnable {
            try { // TODO remove after network checking
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            // TODO runs network checking

            // run permission checking
            if (ContextCompat.checkSelfPermission(activity,
                            Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                Manifest.permission.READ_CONTACTS)) {
                } else {
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_CONTACTS),
                            1)
                }
            }
            startActivity(Intent(activity, MainActivity::class.java))
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            finish()
        }).start()
    }

    val activity: Welcome
        get() = this
}