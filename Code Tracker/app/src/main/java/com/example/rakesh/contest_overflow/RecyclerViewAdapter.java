package com.example.rakesh.contest_overflow;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myviewholder> {
    Context mContext;
    List<contest_items> mdata;

    public RecyclerViewAdapter(Context mContext, List<contest_items> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
    }

    @Override
    public myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.contest_view,parent,false);
        final myviewholder vholder = new myviewholder(v);
        return vholder;
    }

    @Override
    public void onBindViewHolder(myviewholder holder, final int position) {
        holder.name.setText(mdata.get(position).getContestname());
        holder.st.setText(mdata.get(position).getStartdate());
        holder.et.setText(mdata.get(position).getEnddate());
       String s =(mdata.get(position).getContestlogo());
       final String linkk=mdata.get(position).getLink();
        if(s.equals("hackerrank")){
            holder.imgview.setImageResource(R.drawable.hackerrank2);

        }
        else if(s.equals("codeforces")){
            holder.imgview.setImageResource(R.drawable.codeforceslogo);
            holder.imgview.getLayoutParams().width=700;
        }
        else if(s.equals("codechef")){
            holder.imgview.setImageResource(R.drawable.codechef);
           // holder.imgview.getLayoutParams().width=600;
            holder.imgview.getLayoutParams().height=120;
        }
        else if(s.equals("spoj")){
            holder.imgview.setImageResource(R.drawable.spoj);
            holder.imgview.getLayoutParams().width=700;
           // holder.imgview.getLayoutParams().height=60;
        }
        else if(s.equals("hackerearth")){
            holder.imgview.setImageResource(R.drawable.hackerearth);
            holder.imgview.getLayoutParams().width=500;
        }else{

        }

        holder.btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("Loading...");
                WebView wv = new WebView(mContext);
                wv.loadUrl(linkk);
                //wv.getSettings().setJavaScriptEnabled(true);
                wv.getSettings().setBuiltInZoomControls(true);
               // wv.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);

                        return true;
                    }
                });

                alert.setView(wv);
                alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });
        holder.btnn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setData(CalendarContract.Events.CONTENT_URI);
                calIntent.setType("vnd.android.cursor.item/event");
                calIntent.putExtra(CalendarContract.Events.TITLE, mdata.get(position).getContestname());
                calIntent.putExtra(CalendarContract.Reminders.MINUTES,30);
                calIntent.putExtra(CalendarContract.Events.HAS_ALARM, true);
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Contest Name : "+mdata.get(position).getContestname()+"\n"+mdata.get(position).getStartdate()+"\n"+mdata.get(position).getEnddate()+"\n"+"Online judge = "+mdata.get(position).getContestlogo().toUpperCase());
                mContext.startActivity(calIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class myviewholder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView st;
        public TextView et;
        public ImageView imgview;
        public Button btnn;
        public Button btnn2;
        public RelativeLayout relativeLayout;
        public myviewholder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.contest_name);
            st=(TextView) itemView.findViewById(R.id.start_time);
            et=(TextView) itemView.findViewById(R.id.end_time);
            imgview=(ImageView) itemView.findViewById(R.id.contest_logo);
            btnn=(Button) itemView.findViewById(R.id.btn);
            btnn2=(Button) itemView.findViewById(R.id.btn2);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_view);
        }
    }
}
