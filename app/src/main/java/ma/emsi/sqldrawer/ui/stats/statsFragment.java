package ma.emsi.sqldrawer.ui.stats;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.emsi.sqldrawer.R;
import ma.emsi.sqldrawer.beans.Machine;
import ma.emsi.sqldrawer.beans.Salle;
import ma.emsi.sqldrawer.service.MachineService;
import ma.emsi.sqldrawer.service.SalleService;

/////
public class statsFragment extends Fragment {

/////
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public statsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment statsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static statsFragment newInstance(String param1, String param2) {
        statsFragment fragment = new statsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stats, container, false);

        BarChart barChart = v.findViewById(R.id.nbM);
        SalleService ss = new SalleService(getContext());
        MachineService ms = new MachineService(getContext());
        int i=0, nbr=0;

        ArrayList<BarEntry> machines = new ArrayList<>();
        ArrayList<String> labelnames = new ArrayList<>();

        for(Salle salle : ss.findAll()){
            for(Machine machine : ms.findMachines(salle.getId())){

                nbr++;


            }
            machines.add(new BarEntry(i,nbr));
            labelnames.add(salle.getCode());

            i++;
            nbr=0;
        }

        BarDataSet barDataSet = new BarDataSet(machines,"Machine");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelnames));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(270);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Nombre Machine par salle");
        barChart.animateY(2000);
        barChart.invalidate();
        return v;

    }

}