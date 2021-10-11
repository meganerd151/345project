package com.example.antitime_wasting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import android.content.res.Resources
import android.view.View
import android.widget.*
import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.GridLabelRenderer
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Page to display statistics stored in the database
 *
 * @author Sam Fern
 * @author David Black
 * @author Amy Lloyd
 */
class StatsMenuActivity : AppCompatActivity() {
    private var graphView: GraphView? = null
    private var yMin: Double = 10.0



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
        typeSpinner.adapter = typeAdapter

        val scopeSpinner : Spinner = findViewById(R.id.scopeSelector)
        val scopeAdapter:SpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.ScopeTypes,
            R.layout.spinner_item
        )
        scopeSpinner.adapter = scopeAdapter

        graphView = findViewById(R.id.idGraphView)

        graphView?.gridLabelRenderer?.horizontalAxisTitleTextSize = 45f
        graphView?.gridLabelRenderer?.padding = 8

        graphView?.titleTextSize = 65f

        graphView?.gridLabelRenderer?.gridStyle = GridLabelRenderer.GridStyle.HORIZONTAL

        val dayLabels = StaticLabelsFormatter(graphView)

        val dayArray = arrayOfNulls<String>(31)

        dayArray[4] = "05"
        dayArray[9] = "10"
        dayArray[14] = "15"
        dayArray[19] = "20"
        dayArray[24] = "25"
        dayArray[29] = "30"
        dayLabels.setHorizontalLabels(dayArray)

        val res: Resources = resources
        val monthLabels = StaticLabelsFormatter(graphView)
        val monthArray = arrayOfNulls<String>(12)
        var i = 0
        for(month in res.getStringArray(R.array.months)) {
            monthArray[i] = month.substring(0, 3)
            i += 1
        }
        monthLabels.setHorizontalLabels(monthArray)

        val thisMonth: String = res.getStringArray(R.array.months)[SimpleDateFormat("MM", Locale.US).format((Date())).toInt()-1]
        val thisYear: String = SimpleDateFormat("yyyy", Locale.US).format((Date()))

        updateGraph("Study", Scope.BY_DAY, dayLabels, thisMonth)

        val spinnerSelected = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (scopeSpinner.selectedItem.toString()){
                    "Month View" -> updateGraph(typeSpinner.selectedItem.toString(), Scope.BY_DAY, dayLabels, thisMonth)
                    "Year View" -> updateGraph(typeSpinner.selectedItem.toString(), Scope.BY_MONTH, monthLabels, thisYear)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        typeSpinner.onItemSelectedListener = spinnerSelected
        scopeSpinner.onItemSelectedListener = spinnerSelected

    }

    /**
     * Redraws the graph with data from a given session type and a scope.
     *
     * @param sessionType the type of session the graph is to display.
     * @param scope the length of time the graph covers.
     */
    private fun updateGraph(sessionType: String, scope: Scope, YLabels: StaticLabelsFormatter, scopeLabel: String){
        graphView?.removeAllSeries()
        val points: ArrayList<Point> = DataPointFinder.findDataPoints(sessionType, scope, this)
        val dataPoints = arrayOfNulls<DataPoint>(points.size)
        var i = 0
        for (point in points) {
            dataPoints[i] = DataPoint((point.x).toDouble(), (point.y).toDouble() / (1000))
            i += 1
        }
        //val series = LineGraphSeries(dataPoints)
        val series = BarGraphSeries(dataPoints)

        when (scope){
            Scope.BY_DAY -> {
                graphView?.gridLabelRenderer?.horizontalAxisTitle = "Day"
                graphView?.gridLabelRenderer?.labelFormatter = YLabels
            }
            Scope.BY_MONTH -> {
                graphView?.gridLabelRenderer?.horizontalAxisTitle = "Month"
                graphView?.gridLabelRenderer?.labelFormatter = YLabels
            }
        }
        graphView?.title = sessionType
        graphView?.gridLabelRenderer?.horizontalAxisTitle = scopeLabel
        graphView?.gridLabelRenderer?.textSize = 19f

        val maxValue: Double = DataPointFinder.getMaxY(points)

        if (maxValue > yMin) {
            graphView?.viewport?.setMaxY(maxValue)
        } else {
            graphView?.viewport?.setMaxY(yMin)
        }
        series.spacing = 5
        graphView?.addSeries(series)

    }
}