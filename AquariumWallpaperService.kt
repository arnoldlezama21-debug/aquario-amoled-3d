package com.acuario.wallpaper
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import android.webkit.WebView
import android.webkit.WebSettings
import android.content.Context
import android.os.Handler
import android.os.Looper

class AquariumWallpaperService : WallpaperService() {
  override fun onCreateEngine(): Engine = AquariumEngine()
  inner class AquariumEngine : Engine() {
    private var webView: WebView? = null
    private var visible = false
    override fun onCreate(surfaceHolder: SurfaceHolder) {
      super.onCreate(surfaceHolder)
      setTouchEventsEnabled(true)
    }
    override fun onSurfaceCreated(holder: SurfaceHolder) {
      super.onSurfaceCreated(holder)
      // WebView renderiza el acuario 3D (Three.js) dentro del fondo
      val ctx: Context = this@AquariumWallpaperService
      webView = WebView(ctx).apply {
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.mediaPlaybackRequiresUserGesture = false
        setBackgroundColor(android.graphics.Color.BLACK)
        // Carga desde assets/www/index.html - no necesita internet
        loadUrl("file:///android_asset/www/index.html")
      }
    }
    override fun onVisibilityChanged(visible: Boolean) {
      super.onVisibilityChanged(visible)
      this.visible = visible
      if (visible) webView?.onResume() else webView?.onPause()
    }
    override fun onTouchEvent(event: android.view.MotionEvent) {
      super.onTouchEvent(event)
      // Reenvía el toque al WebView para alimentar / ondas
      webView?.dispatchTouchEvent(event)
    }
    override fun onDestroy() {
      super.onDestroy()
      webView?.destroy(); webView = null
    }
  }
}
