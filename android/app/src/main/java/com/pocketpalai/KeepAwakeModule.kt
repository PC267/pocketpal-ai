package com.pocketpal

import android.content.Context
import android.net.wifi.WifiManager
import android.os.PowerManager
import android.view.WindowManager
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import com.pocketpal.specs.NativeKeepAwakeSpec

@ReactModule(name = NativeKeepAwakeSpec.NAME)
class KeepAwakeModule(reactContext: ReactApplicationContext) :
    NativeKeepAwakeSpec(reactContext) {

  private var wakeLock: PowerManager.WakeLock? = null
  private var wifiLock: WifiManager.WifiLock? = null

  override fun activate() {
    val activity = reactApplicationContext.currentActivity
    // 1. 保持屏幕常亮
    if (activity != null) {
      activity.runOnUiThread {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
      }
    }

    val context = reactApplicationContext
    // 2. 获取 CPU 唤醒锁（允许息屏后 CPU 继续运行）
    if (wakeLock == null) {
      val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
      wakeLock = powerManager.newWakeLock(
          PowerManager.PARTIAL_WAKE_LOCK,
          "PocketPal::WakeLock"
      )
      wakeLock?.setReferenceCounted(false)
      wakeLock?.acquire()
    }

    // 3. 获取 WiFi 锁（允许息屏后网络继续通信）
    if (wifiLock == null) {
      val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
      wifiLock = wifiManager.createWifiLock(
          WifiManager.WIFI_MODE_FULL_HIGH_PERF,
          "PocketPal::WifiLock"
      )
      wifiLock?.setReferenceCounted(false)
      wifiLock?.acquire()
    }
  }

  override fun deactivate() {
    val activity = reactApplicationContext.currentActivity
    // 1. 取消屏幕常亮
    if (activity != null) {
      activity.runOnUiThread {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
      }
    }

    // 2. 释放 CPU 锁
    if (wakeLock != null) {
      if (wakeLock?.isHeld == true) {
        wakeLock?.release()
      }
      wakeLock = null
    }

    // 3. 释放 WiFi 锁
    if (wifiLock != null) {
      if (wifiLock?.isHeld == true) {
        wifiLock?.release()
      }
      wifiLock = null
    }
  }
}
