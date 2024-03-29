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

            val handler = Handler()
            val r = Runnable {
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }

            val now = DateTime(DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Tokyo")))
            val next = DateTime(now.year,now.monthOfYear,now.dayOfMonth,now.hourOfDay,now.minuteOfHour,now.secondOfMinute,0).plusSeconds(1)
            handler.postDelayed(r, (next.millisOfSecond - next.millisOfSecond).toLong())

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

