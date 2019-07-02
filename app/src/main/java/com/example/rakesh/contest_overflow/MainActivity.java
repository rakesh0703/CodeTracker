package com.example.rakesh.contest_overflow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private Button getbtn;
    String formattedDate;
    private FloatingActionButton fab;
    private FloatingActionButton fab2;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private String username;
    public static String URL_NAME;
    private List<todo_items> mmlist1;
    private List<todo_items> mmlist2;
    private ArrayList<String> arrli = new ArrayList<>();
    private GraphView graph;
    private GraphView graph2;
    private LineGraphSeries<DataPoint> series;
    private LineGraphSeries<DataPoint> series2;
    private ArrayList<Integer> solvemap=new ArrayList<>();
        private ArrayList<Integer> pendmap=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        username = intent.getStringExtra("USERNAME");
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new hackerrank(), "Hackerrank");
        adapter.AddFragment(new codeforces(), "Codeforces");
        adapter.AddFragment(new codechef(), "Codechef");
        adapter.AddFragment(new hackerearth(), "Hackerearth");
        adapter.AddFragment(new fragmentspoj(), "SPOJ");
        URL_NAME = "http://10.10.144.243/data_fetch.php?user=" + "abc@gmail.com";
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        viewPager.setOffscreenPageLimit(5);
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);



//
mmlist1 = new ArrayList<>();
                StringRequest stringrequest = new StringRequest(Request.Method.GET,
                        URL_NAME,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    JSONArray obb = obj.getJSONArray("links");
                                    String t="";int solve=0,pend=0;
                                    for (int i = 0; i < obb.length(); i++) {
                                        String s = obb.getString(i);
                                        JSONObject ob = obj.getJSONObject(s);
                                        String status = ob.getString("status");
                                        String tags = ob.getString("tags");
                                        String timeSolved = ob.getString("time_solved");
                                        System.out.println("&&&&&&&&&&&&&&&&&&&& " + status + " ############ " + tags + " $$$$$$$$$$$$ " + timeSolved + "  " + s);
                                            mmlist1.add(new todo_items(s,tags, timeSolved, status));
//                                            if(timeSolved.equals(t)){
//                                                if(status.equals("SOLVED")){
//                                                    solve++;
//                                                }else{
//                                                    pend++;
//                                                }
//                                            }else{
//                                                solvemap.add(solve);
//                                                pendmap.add(pend);
//                                                solve=0;pend=0;
//                                            }
//                                        t=timeSolved;
                                    }
                                } catch (JSONException e) {
                                    Log.wtf("json", e);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Please connect to INTERNET", Toast.LENGTH_SHORT).show();
                            }
                        });
                RequestQueue reqque = Volley.newRequestQueue(MainActivity.this);
                reqque.add(stringrequest);
//        System.out.println("###################################"+solvemap.size());
//        System.out.println("###################################"+pendmap.size());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog2 = new Dialog(MainActivity.this);
                dialog2.setTitle("Details");
                dialog2.setContentView(R.layout.todo);
                RecyclerView detailsRecyclerView2 = (RecyclerView) dialog2.findViewById(R.id.todo_recycler);
                //Button detailsCloseButton = (Button) dialog2.findViewById(R.id.cls);
                RecyclerViewAdapter2 detailsAdapter = new RecyclerViewAdapter2(MainActivity.this, mmlist1);
                detailsRecyclerView2.setAdapter(detailsAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(dialog2.getContext());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                detailsRecyclerView2.setLayoutManager(layoutManager);
//                detailsCloseButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog2.dismiss();
//                    }
//                });
                dialog2.show();
            }
        });
////
////
////
//
////
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle(username);
                dialog.setContentView(R.layout.graph_plot);
                graph = (GraphView) dialog.findViewById(R.id.graph);
                graph2 = (GraphView) dialog.findViewById(R.id.graph2);
//                    graph.getViewport().setXAxisBoundsManual(true);
//                        graph.getViewport().setMinX(1);
//                        graph.getViewport().setMaxX(10);
////
////                        // set manual Y bounds
//                        graph.getViewport().setYAxisBoundsManual(true);
//                        graph.getViewport().setMinY(1);
//                        graph.getViewport().setMaxY(12);
//                graph2.getViewport().setXAxisBoundsManual(true);
//                graph2.getViewport().setMinX(1);
//                graph2.getViewport().setMaxX(40);
//                // set manual Y bounds
//                graph2.getViewport().setYAxisBoundsManual(true);
//                graph2.getViewport().setMinY(1);
//                graph2.getViewport().setMaxY(40);
//                DataPoint[] dp = new DataPoint[solvemap.size()];
//                for(int i=1;i<=solvemap.size();i++){
//                    dp[i] = new DataPoint(i, solvemap.get(i));
//                }
//                System.out.println("###################################3"+solvemap.size());
//                DataPoint[] dp2 = new DataPoint[pendmap.size()];
//                for(int i=1;i<=pendmap.size();i++){
//                    dp2[i] = new DataPoint(i, pendmap.get(i));
//                }
//                System.out.println("###################################3"+pendmap.size());
//                series = new LineGraphSeries<>(dp);
//                series2 = new LineGraphSeries<>(dp2);
                Calendar calendar = Calendar.getInstance();
                Date d1 = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                Date d2 = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                Date d3 = calendar.getTime();

// you can directly pass Date objects to DataPoint-Constructor
// this will convert the Date to double via Date#getTime()
               series = new LineGraphSeries<>(new DataPoint[] {
                        new DataPoint(d1, 1),
                        new DataPoint(d2, 5),
                        new DataPoint(d3, 3)
                });
                series.setColor(Color.RED);
                graph.addSeries(series);

// set date label formatter
                graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(MainActivity.this));
                graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

// set manual x bounds to have nice steps
                graph.getViewport().setMinX(d1.getTime());
                graph.getViewport().setMaxX(d3.getTime());
                graph.getViewport().setXAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
                graph.getGridLabelRenderer().setHumanRounding(false);

               series2 = new LineGraphSeries<>(new DataPoint[] {
                        new DataPoint(d1, 4),
                        new DataPoint(d2, 7),
                        new DataPoint(d3, 2)
                });
                series2.setColor(Color.GREEN);
                graph2.addSeries(series2);

// set date label formatter
                graph2.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(MainActivity.this));
                graph2.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

// set manual x bounds to have nice steps
                graph2.getViewport().setMinX(d1.getTime());
                graph2.getViewport().setMaxX(d3.getTime());
                graph2.getViewport().setXAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
                graph2.getGridLabelRenderer().setHumanRounding(false);
                dialog.show();
            }
        });

    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.about):
                Toast.makeText(this, "Developed By TRP", Toast.LENGTH_SHORT).show();
                return true;
            case (R.id.exit): {
                finish();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

//    public void todo(View view) {
//        final Dialog dialog2 = new Dialog(MainActivity.this);
//        dialog2.setTitle("Details");
//        dialog2.setContentView(R.layout.todo);
//        RecyclerView detailsRecyclerView2 = (RecyclerView) dialog2.findViewById(R.id.todo_recycler);
//        // Button detailsCloseButton = (Button) dialog2.findViewById(R.id.cls);
//        mmlist1 = new ArrayList<>();
//        StringRequest stringrequest = new StringRequest(Request.Method.GET,
//                URL_NAME,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            JSONArray obb = obj.getJSONArray("links");
//                            for (int i = 0; i < obb.length(); i++) {
//                                String s = obb.getString(i);
//                                JSONObject ob = obj.getJSONObject(s);
//                                String status = ob.getString("status");
//                                String tags = ob.getString("tags");
//                                String timeSolved = ob.getString("time_solved");
//                                System.out.println("&&&&&&&&&&&&&&&&&&&& "+status+" ############ "+tags+" $$$$$$$$$$$$ "+timeSolved+"  "+s);
//                                if(status.equals("PENDING")){
//                                    mmlist1.add(new todo_items(tags,timeSolved,status,s));
//                                }
//                            }
//                        } catch (JSONException e) {
//                            Log.wtf("json", e);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, "Please connect to INTERNET", Toast.LENGTH_SHORT).show();
//                    }
//                });
//        RequestQueue reqque = Volley.newRequestQueue(MainActivity.this);
//        reqque.add(stringrequest);
//        RecyclerViewAdapter2 detailsAdapter = new RecyclerViewAdapter2(MainActivity.this, mmlist1);
//        detailsRecyclerView2.setAdapter(detailsAdapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(dialog2.getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        detailsRecyclerView2.setLayoutManager(layoutManager);
////                detailsCloseButton.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        dialog2.dismiss();
////                    }
////                });
//        dialog2.show();
//    }
//
//    public void done(View view) {
//        final Dialog dialog = new Dialog(MainActivity.this);
//        dialog.setTitle("Details");
//        dialog.setContentView(R.layout.done);
//        RecyclerView detailsRecyclerView = (RecyclerView) dialog.findViewById(R.id.done_recycler);
//        //Button detailsCloseButton = (Button) dialog.findViewById(R.id.cls);
//        mmlist2 = new ArrayList<>();
//        StringRequest stringrequest2 = new StringRequest(Request.Method.GET,
//                URL_NAME,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            JSONArray obb = obj.getJSONArray("links");
//                            for (int i = 0; i < obb.length(); i++) {
//                                String s = obb.getString(i);
//                                JSONObject ob = obj.getJSONObject(s);
//                                String status = ob.getString("status");
//                                String tags = ob.getString("tags");
//                                String timeSolved = ob.getString("time_solved");
//                                System.out.println("&&&&&&&&&&&&&&&&&&&& " + status + " ############ " + tags + " $$$$$$$$$$$$ " + timeSolved + "  " + s);
//                                if (status.equals("SOLVED")) {
//                                    mmlist2.add(new todo_items(tags, timeSolved, status, s));
//                                }
//                            }
//                        } catch (JSONException e) {
//                            Log.wtf("json", e);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, "Please connect to INTERNET", Toast.LENGTH_SHORT).show();
//                    }
//                });
//        RequestQueue reqque2 = Volley.newRequestQueue(MainActivity.this);
//        reqque2.add(stringrequest2);
////feedbackResponseSubList contains n items
//        RecyclerViewAdapter2 detailsAdapter = new RecyclerViewAdapter2(MainActivity.this, mmlist2);
//        detailsRecyclerView.setAdapter(detailsAdapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(dialog.getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        detailsRecyclerView.setLayoutManager(layoutManager);
////                detailsCloseButton.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        dialog.dismiss();
////                    }
////                });
//        dialog.show();
//    }
//}










