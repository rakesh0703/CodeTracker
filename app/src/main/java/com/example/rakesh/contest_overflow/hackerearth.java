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
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class hackerearth extends android.support.v4.app.Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<contest_items> mlist;
    private ProgressDialog progress;
    private String s =  "hackerearth";
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
        v=inflater.inflate(R.layout.hackerearth,container,false);

        mlist = new ArrayList<>();
        myrecyclerview=(RecyclerView) v.findViewById(R.id.hackerearth_recycler);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        //onCreate(savedInstanceState);
        return v;
    }

    public hackerearth() {
    }
    public class grab extends AsyncTask<Void ,Void,Void> {
        String eatitle="",eatime,eatime2="",temp="";
        @Override
        protected Void doInBackground(Void... voids) {
            String linkk="https://www.hackerearth.com/challenges/";
            eatime="Please Refer to website";
            try {
                Document doc1 = Jsoup.connect("https://clist.by/").get();
                Elements divs= doc1.getElementsByAttributeValueStarting("href","https://www.hackerearth.com");
                for (Element div : divs){
                    eatitle = div.attr("title");
                    if(!temp.equals(eatitle)){
                       // System.out.println("#############"+eatitle+"##################");
                        mlist.add(new contest_items(eatitle,eatime,eatime2,s,linkk));
                        temp=eatitle;
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
