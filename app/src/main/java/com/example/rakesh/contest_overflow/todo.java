package com.example.rakesh.contest_overflow;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.rakesh.contest_overflow.MainActivity.URL_NAME;

public class todo extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<contest_items> mlist;
    private ProgressDialog progress;
    private String s = "todo";
    String linkk="";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = new ProgressDialog(getActivity());
        progress.setMessage("Loading Data");
        progress.show();
        load();
        //new grab().execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.todo,container,false);
        mlist = new ArrayList<>();
        myrecyclerview=(RecyclerView) v.findViewById(R.id.todo_recycler);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        //onCreate(savedInstanceState);
        return v;
    }

    public todo() {
    }
    public void load(){
        StringRequest stringrequest = new StringRequest(Request.Method.GET,
                URL_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray obb = obj.getJSONArray("links");
                            for (int i = 0; i < obb.length(); i++) {
                                String s = obb.getString(i);
                                JSONObject ob = obj.getJSONObject(s);
                                String status = ob.getString("status");
                                String tags = ob.getString("tags");
                                String timeSolved = ob.getString("time_solved");
                                System.out.println("&&&&&&&&&&&&&&&&&&&& "+status+" ############ "+tags+" $$$$$$$$$$$$ "+timeSolved+"  "+s);
                                mlist.add(new contest_items(status, tags, timeSolved, s, linkk));
                            }
                            RecyclerViewAdapter recycleradapter = new RecyclerViewAdapter(getContext(),mlist);
                            myrecyclerview.setAdapter(recycleradapter);
                        } catch (JSONException e) {
                            Log.wtf("json", e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Please connect to INTERNET", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue reqque = Volley.newRequestQueue(getContext());
        reqque.add(stringrequest);
    }
//    public class grab extends AsyncTask<Void ,Void,Void> {
//        @Override
//        protected Void doInBackground(Void... voids) {
//            StringRequest stringrequest = new StringRequest(Request.Method.GET,
//                    URL_NAME,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                JSONObject obj = new JSONObject(response);
//                                JSONArray obb = new JSONArray("added_links");
//                                for (int i = 0; i < obb.length(); i++) {
//                                    String s = obb.getString(i);
//                                    JSONObject ob = obj.getJSONObject(s);
//                                    String status = ob.getString("status");
//                                    String tags = ob.getString("tags");
//                                    String timeSolved = ob.getString("time_solved");
//                                    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+URL_NAME+status+s);
//                                    mlist.add(new contest_items(status, tags, timeSolved, s, linkk));
//                                }
//                                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+URL_NAME+s);
//
//                            } catch (JSONException e) {
//                                Log.wtf("json", e);
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(getContext(), "Please connect to INTERNET", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//            RequestQueue reqque = Volley.newRequestQueue(getContext());
//            reqque.add(stringrequest);
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            progress.dismiss();
//            RecyclerViewAdapter recycleradapter = new RecyclerViewAdapter(getContext(),mlist);
//            myrecyclerview.setAdapter(recycleradapter);
//        }
//    }
}
