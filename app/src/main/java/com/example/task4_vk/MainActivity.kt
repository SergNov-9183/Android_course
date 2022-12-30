package com.example.task4_vk

import android.os.Bundle
import android.content.Intent
import android.widget.EditText
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val get_albums_btn: Button = findViewById(R.id.btnGetAlbums)
        val text_user_id: EditText = findViewById(R.id.inputUserIdText)
        get_albums_btn.setOnClickListener {
            val userId = text_user_id.text.toString()
            if (userId.isNotEmpty()) {
                val intent = Intent(this@MainActivity, AlbumsActivity::class.java)
                intent.putExtra("user_id", userId)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Поле ID пропущено", Toast.LENGTH_SHORT).show()
            }
        }
    }
}