import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

// Class requests data from DatabaseManager and uses it to generate charts
public class ChartGenerator {
	// DatabaseManager for pulling data
	private DatabaseManager dbm;
	// Single constructor takes a DatabaseManager
	public ChartGenerator(DatabaseManager dbm) {
		this.dbm = dbm;
	}
	
	// Chart Functions ----------------------------------------------------------------------------
	// Save BodyWeight
	public void saveChartBodyWeight() {
		// Pull data from dbm
		TwoArray<Date, Float> data = dbm.getBodyWeightData();

		// Create Chart
		XYChart chart = new XYChartBuilder().width(800).height(600).title("Body Weight").xAxisTitle("Date").yAxisTitle("Weight").build();

		// Customize Chart
		chart.getStyler().setDecimalPattern("#0");

		// Series
		XYSeries series = chart.addSeries("Body Weight", Arrays.asList(data.getX()), Arrays.asList(data.getY()));
		series.setMarker(SeriesMarkers.CIRCLE);
		series.setMarkerColor(java.awt.Color.RED);
		series.setLineColor(java.awt.Color.BLACK);
		series.setLineStyle(SeriesLines.SOLID);
		series.setLineWidth(1.5f);

		// Save chart
		try {
			BitmapEncoder.saveBitmap(chart, "./charts/BodyWeight", BitmapFormat.PNG);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
