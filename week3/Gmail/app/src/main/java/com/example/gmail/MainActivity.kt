package com.example.gmail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail.adapters.EmailAdapter
import com.example.gmail.models.EmailModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailList = listOf(
            EmailModel(
                resources.getIdentifier("thumb_e", "mipmap", packageName),
                "Edurila.com",
                "12:34 PM",
                "$19 Only (First 10 spots)",
                "Are you looking to learn web design?"),
            EmailModel(
                resources.getIdentifier("thumb_c", "mipmap", packageName),
                "Chris Abad",
                "11:22 AM",
                "Help make Campaign Monitor better",
                "Let us know your thoughts! No Images "),
            EmailModel(
                resources.getIdentifier("thumb_t", "mipmap", packageName),
                "Tuto.com",
                "11:04 AM",
                "8h de formation gratuite et les nouvea...",
                "Photoshop, SEO, Blender, CSS, WordPress"),
            EmailModel(
                resources.getIdentifier("thumb_s", "mipmap", packageName),
                "support",
                "10:26 AM",
                "Société Ovh : suivi de vos services - hp...",
                "SAS OVH - http://www.ovh.com 2 rue K..."),
            EmailModel(
                resources.getIdentifier("thumb_m", "mipmap", packageName),
                "Matt from Ionic",
                "10:00 AM",
                "The New Ionic Creator Is Here!",
                "Announcing the all-new Creator, build..."),
            EmailModel(
                resources.getIdentifier("thumb_e", "mipmap", packageName),
                "Edurila.com",
                "12:34 PM",
                "$19 Only (First 10 spots)",
                "Are you looking to learn web design?"),
            EmailModel(
                resources.getIdentifier("thumb_c", "mipmap", packageName),
                "Chris Abad",
                "11:22 AM",
                "Help make Campaign Monitor better",
                "Let us know your thoughts! No Images "),
            EmailModel(
                resources.getIdentifier("thumb_t", "mipmap", packageName),
                "Tuto.com",
                "11:04 AM",
                "8h de formation gratuite et les nouvea...",
                "Photoshop, SEO, Blender, CSS, WordPress"),
            EmailModel(
                resources.getIdentifier("thumb_s", "mipmap", packageName),
                "support",
                "10:26 AM",
                "Société Ovh : suivi de vos services - hp...",
                "SAS OVH - http://www.ovh.com 2 rue K..."),
            EmailModel(
                resources.getIdentifier("thumb_m", "mipmap", packageName),
                "Matt from Ionic",
                "10:00 AM",
                "The New Ionic Creator Is Here!",
                "Announcing the all-new Creator, build..."),
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EmailAdapter(emailList)
    }
}