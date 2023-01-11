package ru.netology.nmedia.activity


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import ru.netology.nmedia.R



class AppActivity : AppCompatActivity(R.layout.activity_app) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {  }

        checkGoogleApiAvailability()
    }

    private fun checkGoogleApiAvailability(){
        with(GoogleApiAvailability.getInstance()){
            val code = isGooglePlayServicesAvailable(this@AppActivity)
            if(code == ConnectionResult.SUCCESS) {
                return@with
            }
            if (isUserResolvableError(code)){
                getErrorDialog(this@AppActivity, code, 9000)?.show()
                return
            }
            Toast.makeText(this@AppActivity, "Google API Unavailable", Toast.LENGTH_LONG).show()
        }

    }
}
