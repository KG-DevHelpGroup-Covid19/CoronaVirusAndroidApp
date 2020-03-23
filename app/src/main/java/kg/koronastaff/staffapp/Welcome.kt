package kg.koronastaff.staffapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kg.koronastaff.staffapp.database.Base
import kg.koronastaff.staffapp.network.NetworkService

class Welcome : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        Thread(Runnable {

            // run permission checking
            if (ContextCompat.checkSelfPermission(activity,
                            Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                Manifest.permission.INTERNET)) {
                } else {
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.INTERNET),
                            1)
                }
            }
            startActivity(Intent(activity, MainActivity::class.java))
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            finish()
        }).start()
    }

    val activity: Welcome
        get() = this
}