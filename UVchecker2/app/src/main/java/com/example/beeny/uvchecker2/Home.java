package com.example.beeny.uvchecker2;


import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements View.OnClickListener {
    TextView homeDate;
    ImageView photoRegist;
    ImageButton setting;
    View view;
    Intent intent;
    PieChart pieChart;
    LineChart lineChart;
    public Home() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        /*photoRegist = (ImageView) view.findViewById(R.id.PhotoRegist);
        uvExposure = (ImageButton)view.findViewById(R.id.UVexposure);
        uvGraph = (ImageView)view.findViewById(R.id.uvGraph);

        uvExposure.setOnClickListener(this);*/
        photoRegist =(ImageView)view.findViewById(R.id.photoRegist);
        //photoRegist.setOnClickListener(this);

        //photoRegist.setBackground(new ShapeDrawable(new OvalShape()));

        //GradientDrawable drawable = (GradientDrawable)getContext().getDrawable(R.drawable.circle_image);
        //photoRegist.setBackground(drawable);
        //photoRegist.setClipToOutline(true);

        setting = (ImageButton)view.findViewById(R.id.settingButton);
        setting.setOnClickListener(this);

        //pieChart = (PieChart)view.findViewById(R.id.pieChart);
        //pieChart.setUsePercentValues(true);

        ArrayList<PieEntry> pieEntries = new ArrayList<PieEntry>();
        PieEntry pieEntry = new PieEntry(10,"80") ;
        pieEntries.add(pieEntry);
        PieDataSet dataSet = new PieDataSet(pieEntries,"잡티");

        //PieData data = new PieData(dataSet);

        //pieChart.setData(data);
        lineChart = (LineChart)view.findViewById(R.id.lineChart);
        homeDate = (TextView)view.findViewById(R.id.mainDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String dateForNow =dateFormat.format(date);
        homeDate.setText(dateForNow);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == setting){
            intent = new Intent(getActivity(),DeviceSetting.class);
            startActivity(intent);
        }
        /*else if (view == uvExposure){
            Toast toast = Toast.makeText(getActivity(),"눌렸습니다",Toast.LENGTH_SHORT);
            toast.show();
        }*/
    }
}
