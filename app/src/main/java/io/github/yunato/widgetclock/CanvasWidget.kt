package io.github.yunato.widgetclock

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.graphics.*
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class CanvasWidget : AppWidgetProvider() {

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
//        private const val offset: Float = 5F
        private const val radius: Float = 750F
        private const val cWidth: Int = 1600
        private const val cHeight: Int = 1600
        private const val wOffsetFromCenter = 00F
        private const val hOffsetFromCenter = 0F

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.canvas_widget)

            val mPaint = Paint()
            mPaint.color = Color.RED

            val bitmap = Bitmap.createBitmap(cWidth, cHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawColor(Color.WHITE)

            val rectF = RectF(
                cWidth / 2.0F - radius + wOffsetFromCenter,
                cHeight / 2.0F - radius + hOffsetFromCenter,
                cWidth / 2.0F + radius + wOffsetFromCenter,
                cHeight / 2.0F + radius + hOffsetFromCenter)
            canvas.drawArc(rectF, 0F, 360F, false, mPaint)

            views.setImageViewBitmap(R.id.appwidget_canvas, bitmap)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

