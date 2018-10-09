package com.example.shaochengyang.deltaapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Bullet;
import com.anychart.charts.Pie;
import com.anychart.charts.Venn;
import com.example.shaochengyang.deltaapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChartActivity extends AppCompatActivity {


    @BindView(R.id.any_chart_view)
    AnyChartView anyChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);

        Pie piechart = AnyChart.pie();
        List<DataEntry> dataEntryList = new ArrayList<>();
        dataEntryList.add(new ValueDataEntry("DELTA", 100));
        dataEntryList.add(new ValueDataEntry("Sprint", 200));
        dataEntryList.add(new ValueDataEntry("American Airline", 150));

        /*piechart.data(dataEntryList);

        Bullet bullet = AnyChart.bullet();
        bullet.title("A title");

        bullet.data(dataEntryList);*/
        Venn venn = AnyChart.venn();
        venn.data(dataEntryList);

        anyChartView.setChart(venn);
        //anyChartView.setChart(piechart);

    }
}
