package io.github.yunato.widgetclock

import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ClockService : Service() {
    internal var mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val extras = intent?.extras
        mAppWidgetId = extras?.getInt(EXTRA_ID) ?: AppWidgetManager.INVALID_APPWIDGET_ID
        Log.d("TEST", "SERVICE")
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            stopSelf()
        }
        ClockWidget.updateAppWidget(applicationContext, AppWidgetManager.getInstance(applicationContext), mAppWidgetId)
        stopSelf()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    companion object {
        @JvmStatic val EXTRA_ID = "io.github.yunato.widgetclock.EXTRA_ID"

        fun intent(context: Context, appWidgetId: Int): Intent =
            Intent(context, ClockService::class.java).putExtra(EXTRA_ID, appWidgetId)
    }
}
