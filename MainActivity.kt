package com.acuario.wallpaper
import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val btn = Button(this).apply { text = "Establecer como Fondo de Pantalla" }
    btn.setOnClickListener {
      val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
      intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, ComponentName(this, AquariumWallpaperService::class.java))
      startActivity(intent)
    }
    setContentView(btn)
  }
}
