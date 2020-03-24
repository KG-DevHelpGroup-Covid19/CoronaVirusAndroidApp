package kg.koronastaff.staffapp.ui.contacts

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.ui.FragmentWithStat
import kotlinx.android.synthetic.main.conatcs_detail.view.*
import pub.devrel.easypermissions.EasyPermissions

class ContactsFragment : FragmentWithStat() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_cotacts, container, false)
        val phone = "+996 312 620319"

        rootView.call.setOnClickListener {

            if(context?.let { it1 -> EasyPermissions.hasPermissions(it1,android.Manifest.permission.CALL_PHONE) }!!) {

                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone")))
            }else{
                EasyPermissions.requestPermissions(this,
                        "This application need your permission to access photo gallery.",
                        981,
                        android.Manifest.permission.CALL_PHONE)
            }
        }
        rootView.call_ky.setOnClickListener {

            if(context?.let { it1 -> EasyPermissions.hasPermissions(it1,android.Manifest.permission.CALL_PHONE) }!!) {

                startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone")))
            }else{
                EasyPermissions.requestPermissions(this,
                        "This application need your permission to access photo gallery.",
                        981,
                        android.Manifest.permission.CALL_PHONE)
            }
        }



        rootView.email.setOnClickListener {
            // sendEmail()
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:"+ "info@mineconom.gov.kg" )
            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "There is no email client installed.", Toast.LENGTH_SHORT).show()
            }
        }
        rootView.email_ky.setOnClickListener {
            // sendEmail()
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:"+ "info@mineconom.gov.kg" )
            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "There is no email client installed.", Toast.LENGTH_SHORT).show()
            }
        }
        rootView.whatsapp.setOnClickListener {
            openWhatsApp("+996555774405")
        }
        rootView.whatsapp_ky.setOnClickListener {
            openWhatsApp("+996555774405")
        }

        return rootView
    }
    private fun openWhatsApp(number: String) {
        try {
            val number2 = number.replace(" ", "").replace("+", "")
            val sendIntent = Intent("android.intent.action.MAIN")
            sendIntent.component = ComponentName("com.whatsapp", "com.whatsapp.Conversation")
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(number2) + "@s.whatsapp.net")
            context?.startActivity(sendIntent)
        } catch (e: Exception) {
            Log.e("OpenWhatsapfunction", "ERROR_OPEN_MESSANGER$e")
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.updateStats(cache.getStat())
    }
}