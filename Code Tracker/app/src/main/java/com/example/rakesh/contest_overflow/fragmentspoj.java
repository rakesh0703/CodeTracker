package com.example.rakesh.contest_overflow;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class fragmentspoj extends android.support.v4.app.Fragment{
    View v;
    private RecyclerView myrecyclerview;
    private List<contest_items> mlist;
    private ProgressDialog progress;
    private String s =  "spoj";
    public fragmentspoj(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.spoj,container,false);
        mlist = new ArrayList<>();
        myrecyclerview=(RecyclerView) v.findViewById(R.id.spoj_recycler);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        //onCreate(savedInstanceState);
        return v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = new ProgressDialog(getActivity());
        progress.setMessage("Loading Data");
        progress.show();
        new grab().execute();
    }
//    getbtn.setOnClickListener(new View.OnClickListener() {
////            @Override
//            public void onClick(View view) {
//                new grab().execute();
//            }
//        });
//    }

    public class grab extends AsyncTask<Void ,Void,Void> {
    String sptessst,sptitle="";
    String sptime[] = {"", "", "", ""};
        @Override
        protected Void doInBackground(Void... voids) {
            String linkk="https://www.spoj.com/contests/";
            try {
                Document doc = Jsoup.connect("https://www.spoj.com/contests/").get();
                System.out.println("############################ SPOJ #####################################" + "\n");
                sptessst = doc.select("div .col-md-6 h2").first().text();
                if (sptessst.equalsIgnoreCase("Running contests")) {
                    for (Element row : doc.select("table tr")) {
                        int i = 0;
                        for (Element ro : row.select("td")) {
                            sptime[i] = ro.text();
                            i++;
                        }
                        if (sptime[3] == "" && sptime[0]!="" && !sptitle.equals(sptime[0])){
                            sptime[1]="Start : "+sptime[1];
                            sptime[2]="End : "+sptime[2];
                            //System.out.println(sptime[0] + "  ANDDD  " + sptime[1] + "     " + sptime[2] + "\n");
                            mlist.add(new contest_items(sptime[0],sptime[1],sptime[2],s,linkk));
                            sptitle=sptime[0];
                        }
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
