package com.example.rakesh.contest_overflow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.myviewholder2>{
    Context mmContext;
    List<todo_items> mmdata;

    public RecyclerViewAdapter2(Context mmContext, List<todo_items> mmdata) {
        this.mmContext = mmContext;
        this.mmdata = mmdata;
    }


    @NonNull
    @Override
    public myviewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mmContext).inflate(R.layout.todo_view,parent,false);
        final myviewholder2 vholder = new myviewholder2(v);
        return vholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder2 holder, int position) {
        holder.name.setText(mmdata.get(position).getContestname());
        holder.tag.setText(mmdata.get(position).getTags());
        holder.time.setText(mmdata.get(position).getDate());
        holder.cls.setText(mmdata.get(position).getStatus());

        if(mmdata.get(position).getStatus().toString().equals("SOLVED")){
            holder.cls.setBackgroundResource(R.drawable.greencircle);
        }else{
            holder.cls.setBackgroundResource(R.drawable.circle);
        }
    }

    @Override
    public int getItemCount() {
        return mmdata.size();
    }

    public static class myviewholder2 extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView tag;
        public TextView time;
        public TextView status;
        public Button cls;
        public myviewholder2(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.txt1);
            tag=(TextView) itemView.findViewById(R.id.txt2);
            time=(TextView) itemView.findViewById(R.id.txt3);
            cls = (Button) itemView.findViewById(R.id.cls);
        }
    }
}
