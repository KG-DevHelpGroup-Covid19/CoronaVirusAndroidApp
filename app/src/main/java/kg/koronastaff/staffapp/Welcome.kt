package kg.koronastaff.staffapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kg.koronastaff.staffapp.database.Base
import kg.koronastaff.staffapp.network.NetworkService
import kg.koronastaff.staffapp.ui.tests.TestsViewModel

class Welcome : AppCompatActivity() {
    lateinit var testViewModel: TestsViewModel
    var disposable: Disposable? = null
    var disposables: ArrayList<Disposable?> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        testViewModel = ViewModelProvider(this).get(TestsViewModel::class.java)

        Thread(Runnable {
            Thread.sleep(400)
            // run permission checking

            val base = Base(application)
            val results = base.getResults()
            if (results != null) {
                disposable = testViewModel.postTestResult(results, base.getToken()!!)?.subscribe{
                        disposable?.dispose()
                }
            }

            val chices = base.getChoices()

            if (chices.size > 0){
                var i = 0
                chices.forEach { it ->
                    disposables.add(
                            testViewModel.postPollChoice(it)?.subscribe{
                                base.removePollChoice(it)
                                disposables[i]?.dispose()
                            }
                    )
                    i++
                }
            }

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