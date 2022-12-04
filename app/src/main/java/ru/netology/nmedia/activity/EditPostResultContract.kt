package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.netology.nmedia.dto.Post

class EditPostResultContract : ActivityResultContract<Post, Post?>() {
    override fun createIntent(context: Context, input: Post): Intent {
        return Intent(context, EditPostActivity::class.java)
       .putExtra("data", Json.encodeToString(Post))
    }


    override fun parseResult(resultCode: Int, intent: Intent?): Post? =
        if (resultCode == Activity.RESULT_OK) {
            intent?.getStringExtra("data")?.let { Json.decodeFromString<Post>(it) }
        } else {
            null
        }
}
