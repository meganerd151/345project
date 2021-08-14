package com.example.antitime_wasting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlin.collections.ArrayList

class StatsMenuActivity : AppCompatActivity() {
    var graphView: GraphView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_menu)
        // on below line we are initializing our graph view.

        fun DP(a: Int, b: Int): DataPoint {
            return DataPoint(a.toDouble(), b.toDouble())
        }
        /*
        // on below line we are adding data to our graph view.
        val series = LineGraphSeries(
            arrayOf<DataPoint>( // on below line we are adding
                // each point on our x and y axis.
                DP(0, 1),
                DP(1, 2),
                DP(2, 3),
                DP(3, 4),
                DP(4, 5),
                DP(5, 6),
                DP(6, 7),
                DP(7, 8),
                DP(8, 9)
            )
        )
        */
        val points: ArrayList<Point> = DataPointFinder.findDataPoints("Study", Scope.BY_DAY, this)
        var dataPoints = arrayOfNulls<DataPoint>(points.size)
        for (point in points)
            dataPoints[point.x - 1] = DataPoint((point.x).toDouble(), (point.y/1000).toDouble())
        /* -----TESTING-----
        for (dataPoint in dataPoints){
            if (dataPoint != null){
                Log.i("TEST", "not null")
            } else {
                Log.i("TEST", "is null")
            }
        } */
        val series = LineGraphSeries(dataPoints)

        val numPoints: Double = points.size.toDouble()
        val maxValue: Double = DataPointFinder.getMaxY(points)
        // after adding data to our line graph series.
        // on below line we are setting
        // title for our graph view.
        graphView = findViewById(R.id.idGraphView)
        graphView?.setTitle("My Graph View")

        // on below line we are setting
        // text color to our graph view.
        graphView?.setTitleColor(R.color.purple_200)

        // on below line we are setting
        // our title text size.
        graphView?.setTitleTextSize(18f)

        graphView?.getViewport()?.setYAxisBoundsManual(true)
        graphView?.getViewport()?.setXAxisBoundsManual(true)
        // on below line we are adding
        // data series to our graph view.

        graphView?.getViewport()?.setMinX(0.0)
        graphView?.getViewport()?.setMaxX(numPoints)
        graphView?.getViewport()?.setMinY(0.0)
        graphView?.getViewport()?.setMaxY(maxValue)

        graphView?.addSeries(series)

        /* TESTING */
        val data: ArrayList<Point> = DataPointFinder.findDataPoints("Study", Scope.BY_DAY, this)
        for (point in data) {
            Log.i("datapoints", "x: ${point.x}  y: ${point.y}")
        }

    }
}