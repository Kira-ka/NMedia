package ru.netology.nmedia.activity


import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

import ru.netology.nmedia.R
import ru.netology.nmedia.activity.NewPostFragment.Companion.texArg


class AppActivity : AppCompatActivity(R.layout.activity_app) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.let {
            if (it.action != Intent.ACTION_SEND){
                return@let
            }

            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            if (text?.isNotBlank() != true){
                return@let
            }
            intent.removeExtra(Intent.EXTRA_TEXT)
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_feedActivity_to_newPostFragment, Bundle().apply {
                texArg = text
            })
        }
    }

}
