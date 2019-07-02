package com.example.rakesh.contest_overflow;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class codechef extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<contest_items> mlist;
    private ProgressDialog progress;
    private String s =  "codechef";
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.codechef,container,false);

        mlist = new ArrayList<>();
        myrecyclerview=(RecyclerView) v.findViewById(R.id.codechef_recycler);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        //onCreate(savedInstanceState);
        return v;
    }

    public codechef() {
    }
    public class grab extends AsyncTask<Void ,Void,Void> {
        String chtessst,chtime,chtime2,chtitle;
        @Override
        protected Void doInBackground(Void... voids) {
            String linkk="https://www.codechef.com/contests";

            try {
                Document doc1 = Jsoup.connect("https://www.codechef.com/contests").get();
                chtessst = doc1.select("h3").first().text();
                System.out.println("############################  CODECHEF  #####################################" + "\n");
                if (chtessst.equals("Present Contests")) {
                    int i = 12;
                    for (Element row : doc1.select("table.dataTable tr")) {
                        i--;
                        if (i == 0) {
                            break;
                        }
                        chtime = row.select("td.start_date").text();
                        chtime2 = row.select("td.end_date").text();
                        chtitle = row.select("td a").text();
                        if (chtitle != "") {
                            chtime="Start : "+chtime;
                            chtime2="End : "+chtime2;
                           // System.out.println(chtitle + "  ANDDD  " + chtime + " and " + chtime2 + "\n");
                            mlist.add(new contest_items(chtitle,chtime,chtime2,s,linkk));
                        }

                    }
                }

//
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




//    Document doc1 = Jsoup.connect("https://www.hackerearth.com/challenges/competitive/").get();
//    Elements ele = doc1.select("div .date less-margin dark");
//                for( Element row : ele){
//                        chtitle=row.text();
//                        //chtime=row.select("div .date less-margin dark").text();
//                        System.out.println("#############"+chtitle+"ANDDDDDDDDDDD"+row);
//                        }
