package ru.bmstu.nastasia.difur.solve;


import android.graphics.Color;
import android.support.annotation.Nullable;

import android.view.View;
import com.androidplot.ui.HorizontalPositioning;
import com.androidplot.ui.VerticalPositioning;
import com.androidplot.util.PixelUtils;
import com.androidplot.xy.*;

import java.util.Arrays;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import io.github.kexanie.library.MathView;
import ru.bmstu.nastasia.difur.R;

public class Plot extends AppCompatActivity {

    public static class ParamNames {
        final public static String y = "y array";
        final public static String x = "x array";
        final public static String y2 = "y2 array";
        final public static String equation = "equation";
        final public static String user_solution = "user solution";
    }

    private XYPlot plot;

    private Double[] y;
    private Double[] x;

    private MathView tex_equation;
    private MathView tex_user_solution;

    void addSeries(Double[] x, Double[] y, @Nullable String label, int formatter) {
        XYSeries series = new SimpleXYSeries(
                Arrays.asList(x), Arrays.asList(y), label);
        LineAndPointFormatter seriesFormat =
                new LineAndPointFormatter(this, formatter);
        seriesFormat.setPointLabelFormatter(null);
        // (optional) add some smoothing to the lines: http://androidplot.com/smooth-curves-and-androidplot/
        seriesFormat.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));
        plot.addSeries(series, seriesFormat);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solve_plot);

        tex_equation = findViewById(R.id.tex_solution_1);
        tex_user_solution = findViewById(R.id.tex_solution_user_1);


        Bundle b = this.getIntent().getExtras();

        if (b == null || !b.containsKey(ParamNames.x) || ! b.containsKey(ParamNames.y)) {
            throw new Error("solve.Plot: Not x or y array are found");
        }

        if (b.containsKey(ParamNames.equation)) {
            tex_equation.setText("$$y' = " + b.getString(ParamNames.equation) + "$$");
        }

        if (b.containsKey(ParamNames.user_solution)) {
            tex_user_solution.setText("$$y(x) = " + b.getString(ParamNames.user_solution) + "$$");
            findViewById(R.id.user_solution_container).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.user_solution_container).setVisibility(View.GONE);
        }
        x = (Double[])b.get(ParamNames.x);
        y = (Double[])b.get(ParamNames.y);
        plot = (XYPlot)findViewById(R.id.XYPlot);

        // create a couple arrays of y-values to plot:
//        Number[] domainLabels = x;
        Number[] series1Numbers = y;

        Number[] domainLabels = new Number[x.length];

        double precision = 100.;

        for (int i = 0; i < x.length; ++i) {
            domainLabels[i] = Math.round(x[i].floatValue() * precision) / precision;
        }

        // turn the above arrays into XYSeries': (Y_VALS_ONLY means use the element index as the x value)
//        XYSeries series1 = new SimpleXYSeries(
//                Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, getBaseContext().getString(R.string.plot_series_solution));

        // create formatters to use for drawing a series using LineAndPointRenderer and configure them from xml:
        PixelUtils.init(getBaseContext());

        addSeries(x, y, getBaseContext().getString(R.string.plot_series_solution), R.xml.line_point_formatter_with_labels);

        if (b.containsKey(ParamNames.y2)) {
            addSeries(x, (Double[])b.get(ParamNames.y2), getBaseContext().getString(R.string.plot_series_user_solution),
                    R.xml.line_point_formatter_with_labels_2);
        }
        plot.setDomainBoundaries(x[0], x[x.length-1], BoundaryMode.FIXED);
        plot.getGraph().getDomainGridLinePaint().setColor(Color.TRANSPARENT);
        plot.getGraph().getDomainSubGridLinePaint().setColor(Color.TRANSPARENT);
        plot.getGraph().getGridBackgroundPaint().setColor(Color.WHITE);
        plot.getGraph().position(
                0, HorizontalPositioning.ABSOLUTE_FROM_LEFT,
                0, VerticalPositioning.ABSOLUTE_FROM_TOP);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
