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
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlin.collections.ArrayList

/**
 * Page to display statistics stored in the database
 *
 * TODO: change to displaying in either minutes or hours
 * TODO: Add labels to spinners
 *
 * @author David Black
 * @author Sam Fern
 */
class StatsMenuActivity : AppCompatActivity() {
    var graphView: GraphView? = null

    /**
     * Creates the basis of a graph, two spinners for selecting the type of data to display, along
     * with a button to apply the changes from the spinners
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_menu)

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

        graphView?.getGridLabelRenderer()?.setVerticalAxisTitle("Time spent")
        graphView?.getGridLabelRenderer()?.setHorizontalAxisTitle("Day")
        graphView?.getGridLabelRenderer()?.setPadding(32)

        graphView?.setTitleColor(R.color.purple_200)

        graphView?.setTitleTextSize(70f)

        graphView?.getViewport()?.setYAxisBoundsManual(true)
        graphView?.getViewport()?.setXAxisBoundsManual(true)

        graphView?.getViewport()?.setMinX(0.0)
        graphView?.getViewport()?.setMaxX(31.0)
        graphView?.getViewport()?.setMinY(0.0)
        graphView?.getViewport()?.setMaxY(10.0)

        //graphView?.addSeries(series)

        val applybtn = findViewById<Button>(R.id.applyBtn)
        applybtn.setOnClickListener {
            when (scopeSpinner.selectedItem.toString()){
                "Month View" -> update_graph(typeSpinner.selectedItem.toString(), Scope.BY_DAY)
                "Year View" -> update_graph(typeSpinner.selectedItem.toString(), Scope.BY_MONTH)
            }

        }

        /* TESTING
        val data: ArrayList<Point> = DataPointFinder.findDataPoints("Study", Scope.BY_DAY, this)
        for (point in data) {
            Log.i("datapoints", "x: ${point.x}  y: ${point.y}")
        }
        */
    }

    /**
     * Redraws the graph with data from a given session type and a scope.
     *
     * @param sessionType the type of session the graph is to display.
     * @param scope the length of time the graph covers.
     */
    fun update_graph(sessionType: String, scope: Scope){
        graphView?.removeAllSeries()
        val points: ArrayList<Point> = DataPointFinder.findDataPoints(sessionType, scope, this)
        var dataPoints = arrayOfNulls<DataPoint>(points.size)
        for (point in points)
            dataPoints[point.x - 1] = DataPoint((point.x).toDouble(), (point.y).toDouble()/(1000))

        //val series = LineGraphSeries(dataPoints)
        val series = BarGraphSeries(dataPoints)


        graphView?.getGridLabelRenderer()?.setVerticalAxisTitle("Time spent")
        when (scope){
            Scope.BY_DAY -> graphView?.getGridLabelRenderer()?.setHorizontalAxisTitle("Day")
            Scope.BY_MONTH -> graphView?.getGridLabelRenderer()?.setHorizontalAxisTitle("Month")
        }


        val numPoints: Double = points.size.toDouble()
        val maxValue: Double = DataPointFinder.getMaxY(points)

        //graphView?.getViewport()?.setMinX(0.0)
        graphView?.getViewport()?.setMaxX(numPoints)
        //graphView?.getViewport()?.setMinY(0.0)
        if (maxValue > 10.0) {
            graphView?.getViewport()?.setMaxY(maxValue)
        } else {
            graphView?.getViewport()?.setMaxY(10.0)
        }

        graphView?.addSeries(series)
    }
}