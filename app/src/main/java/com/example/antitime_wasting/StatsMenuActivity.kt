package com.example.antitime_wasting

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import android.content.res.Resources
import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GridLabelRenderer
import com.jjoe64.graphview.GridLabelRenderer.GridStyle
import java.text.SimpleDateFormat
import java.util.*
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
    var Ymin: Double = 10.0



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

        graphView?.getGridLabelRenderer()?.setHorizontalAxisTitleTextSize(45f)
        graphView?.getGridLabelRenderer()?.setPadding(8)

        graphView?.setTitleTextSize(50f)

        graphView?.getGridLabelRenderer()?.setGridStyle(GridLabelRenderer.GridStyle.HORIZONTAL);

        //graphView?.getViewport()?.setYAxisBoundsManual(true)
        //graphView?.getViewport()?.setXAxisBoundsManual(true)


        //graphView?.getViewport()?.setMinX(0.0)

        val dayLabels = StaticLabelsFormatter(graphView)
        var dayArray = arrayOf("", "05 ", "10 ", "15 ", "20 ", "25 ", "30 ")
        var i: Int = 0
        dayLabels.setHorizontalLabels(dayArray)

        val res: Resources = resources
        val monthLabels = StaticLabelsFormatter(graphView)
        val monthArray = arrayOfNulls<String>(12)
        i = 0
        for(month in res.getStringArray(R.array.months)) {
            monthArray[i] = month.substring(0, 3) + "  "
            i += 1
        }
        monthLabels.setHorizontalLabels(monthArray)

        var thisMonth: String = res.getStringArray(R.array.months)[SimpleDateFormat("MM", Locale.US).format((Date())).toInt()-1]
        var thisYear: String = SimpleDateFormat("yyyy", Locale.US).format((Date()))

        update_graph("Study", Scope.BY_DAY, dayLabels, thisMonth)

        val applybtn = findViewById<Button>(R.id.applyBtn)
        applybtn.setOnClickListener {
            when (scopeSpinner.selectedItem.toString()){
                "Month View" -> update_graph(typeSpinner.selectedItem.toString(), Scope.BY_DAY, dayLabels, thisMonth)
                "Year View" -> update_graph(typeSpinner.selectedItem.toString(), Scope.BY_MONTH, monthLabels, thisYear)
            }

        }
    }

    /**
     * Redraws the graph with data from a given session type and a scope.
     *
     * @param sessionType the type of session the graph is to display.
     * @param scope the length of time the graph covers.
     */
    fun update_graph(sessionType: String, scope: Scope, YLabels: StaticLabelsFormatter, scopeLabel: String){
        graphView?.removeAllSeries()
        val points: ArrayList<Point> = DataPointFinder.findDataPoints(sessionType, scope, this)
        var dataPoints = arrayOfNulls<DataPoint>(points.size)
        var i: Int = 0
        for (point in points) {
            dataPoints[i] = DataPoint((point.x).toDouble(), (point.y).toDouble() / (1000))
            i += 1
        }
        //val series = LineGraphSeries(dataPoints)
        val series = BarGraphSeries(dataPoints)

        when (scope){
            Scope.BY_DAY -> {
                graphView?.setTitle("$sessionType This Month")
                graphView?.getGridLabelRenderer()?.setHorizontalAxisTitle("Day")
                graphView?.getGridLabelRenderer()?.setLabelFormatter(YLabels)
                graphView?.getGridLabelRenderer()?.setHorizontalLabelsAngle(135)
            }
            Scope.BY_MONTH -> {
                graphView?.setTitle("$sessionType This Year")
                graphView?.getGridLabelRenderer()?.setHorizontalAxisTitle("Month")
                graphView?.getGridLabelRenderer()?.setLabelFormatter(YLabels)
                graphView?.getGridLabelRenderer()?.setHorizontalLabelsAngle(135)
            }
        }
        graphView?.getGridLabelRenderer()?.setHorizontalAxisTitle(scopeLabel)

        val numPoints: Double = points.size.toDouble()
        val maxValue: Double = DataPointFinder.getMaxY(points)

        graphView?.getViewport()?.setMaxX(numPoints)
        if (maxValue > Ymin) {
            graphView?.getViewport()?.setMaxY(maxValue + 1)
        } else {
            graphView?.getViewport()?.setMaxY(Ymin)
        }

        graphView?.addSeries(series)

    }
}