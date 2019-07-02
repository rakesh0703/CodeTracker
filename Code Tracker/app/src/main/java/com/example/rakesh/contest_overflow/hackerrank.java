package com.example.rakesh.contest_overflow;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class hackerrank extends android.support.v4.app.Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<contest_items> mlist;
    private ProgressDialog progress;
    private String s =  "hackerrank";
    public hackerrank() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = new ProgressDialog(getActivity());
        progress.setMessage("Loading Data");
        progress.show();
        new grab().execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.hackerrank,container,false);
        mlist = new ArrayList<>();
        myrecyclerview=(RecyclerView) v.findViewById(R.id.hackerrank_recycler);
        RecyclerViewAdapter recycleradapter = new RecyclerViewAdapter(getContext(),mlist);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recycleradapter);

        //onCreate(savedInstanceState);
        return v;
    }
    public class grab extends AsyncTask<Void ,Void,Void> {
        String hatessst,hatime,hatime2,hatitle;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String linkk="https://www.hackerrank.com/contests";
                Document doc2 = Jsoup.connect("https://www.hackerrank.com/contests").get();
                hatessst = doc2.select("div .active_contests .psB").first().text();
                System.out.println("############################  HACKERRANK #####################################" + "\n");
                if (hatessst.equals("Active Contests")) {
                    for (Element row : doc2.getElementsByAttributeValue("data-contest-state", "Active")) {
                        String timee[] = {" ", " "};
                        hatime = row.select("div .contest-status").text();
                        hatime2 = row.getElementsByTag("meta").attr("itemprop", "startDate").attr("content");
                        for (int i = 0; i < hatime2.length(); i++) {
                            timee = hatime2.split("T");
                        }
                        hatitle = row.select(".contest-name").text();
                        if (timee[0] != " ") {
                            timee[0]="End : "+timee[0];
                            hatime=timee[0];
                            timee[0]="";
                        }
                        //System.out.println(hatitle + "  ANDDD " + hatime + timee[0] + "\n");
                        mlist.add(new contest_items(hatitle,hatime,timee[0],s,linkk));
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progress.dismiss();
            RecyclerViewAdapter recycleradapter = new RecyclerViewAdapter(getContext(),mlist);
            myrecyclerview.setAdapter(recycleradapter);
        }
    }
}
