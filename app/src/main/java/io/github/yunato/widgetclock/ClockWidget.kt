package io.github.yunato.widgetclock

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Handler
import android.widget.RemoteViews
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class ClockWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val widgetText = DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Tokyo"))).toString("HH:mm:ss",Locale.JAPANESE)
            val views = RemoteViews(context.packageName, R.layout.clock_widget)
            views.setTextViewText(R.id.appwidget_text, widgetText)

//            val intent = ClockService.intent(context, appWidgetId)
//            val pendingIntent = PendingIntent.getService(context, appWidgetId, intent, 0)
//            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            alarmManager.cancel(pendingIntent)
//            val interval = 1000 * 60L
//            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), interval, pendingIntent)

            val handler = Handler()
            val r = Runnable {
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }
            handler.postDelayed(r, 1000)


            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

