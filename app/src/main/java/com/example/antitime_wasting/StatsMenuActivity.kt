package com.example.antitime_wasting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.SpinnerAdapter
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

        val typeSpinner : Spinner = findViewById(R.id.sessionTypeSelector)
        val typeAdapter: SpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.SessionTypes,
            R.layout.spinner_item
        )
        typeSpinner.setAdapter(typeAdapter)

        val scopeSpinner : Spinner = findViewById(R.id.scopeSelector)
        val scopeAdapter:SpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.ScopeTypes,
            R.layout.spinner_item
        )
        scopeSpinner.setAdapter(scopeAdapter)

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




        val applybtn = findViewById<Button>(R.id.applyBtn)
        applybtn.setOnClickListener {
            when (scopeSpinner.selectedItem.toString()){
                "Month View" -> update_graph(typeSpinner.selectedItem.toString(), Scope.BY_DAY)
                "Year View" -> update_graph(typeSpinner.selectedItem.toString(), Scope.BY_MONTH)
            }

        }

        /* TESTING */
        val data: ArrayList<Point> = DataPointFinder.findDataPoints("Study", Scope.BY_DAY, this)
        for (point in data) {
            Log.i("datapoints", "x: ${point.x}  y: ${point.y}")
        }

    }

    fun update_graph(sessionType: String, scope: Scope){
        graphView?.removeAllSeries()
        val points: ArrayList<Point> = DataPointFinder.findDataPoints(sessionType, scope, this)
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
        graphView?.getViewport()?.setMinX(0.0)
        graphView?.getViewport()?.setMaxX(numPoints)
        graphView?.getViewport()?.setMinY(0.0)
        graphView?.getViewport()?.setMaxY(maxValue)

        graphView?.addSeries(series)
    }
}