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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class codeforces extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<contest_items> mlist;
    private ProgressDialog progress;
    private String s =  "codeforces";
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
        v=inflater.inflate(R.layout.codeforces,container,false);
        mlist = new ArrayList<>();
        myrecyclerview=(RecyclerView) v.findViewById(R.id.codeforces_recycler);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        //onCreate(savedInstanceState);
        return v;
    }

    public codeforces() {
    }
    public class grab extends AsyncTask<Void ,Void,Void> {
        String fotitle="",fotime,fotime2,temp="";
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String linkk="http://codeforces.com/contests?complete=true";
                boolean hi;
                Document doc3 = Jsoup.connect("http://codeforces.com/contests?complete=true").get();
                System.out.println("############################ CODEFORCES #####################################" + "\n");
                for (Element row : doc3.select("table tr")) {
                    hi = row.select("td").text().contains("Final");
                    if (!hi) {
                        int i = 0;
                        for (Element tom : row.select("td")) {
                            if (i == 0) {
                                fotitle = tom.text();
                            } else if (i == 2) {
                                fotime = tom.select("a").text();
                            } else if (i == 3) {
                                fotime2 = tom.text();
                            }
                            i++;
                        }
                    } else {
                        fotitle = "";
                    }
                    if (fotitle.length() != 0) {
                        fotime2="Duration: "+fotime2+" hr";
                       // System.out.println(fotitle + "  ANDDD  " + fotime + "  " + fotime2+"\n");
                        mlist.add(new contest_items(fotitle,fotime,fotime2,s,linkk));
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
