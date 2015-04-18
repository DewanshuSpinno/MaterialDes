package com.example.dewanshusharma.materialdes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by dewanshusharma on 08/04/15.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyviewHolder> {


    private LayoutInflater inflator;
    List<Dataitem> data= Collections.emptyList();

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved( position);
    }

    public DataAdapter(Context context,List<Dataitem> data){
        inflator= LayoutInflater.from(context);
        this.data=data;
    }
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =inflator.inflate(R.layout.rowitem,parent,false);
        MyviewHolder holder=new MyviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder,int position) {
        Dataitem info=data.get(position);
  holder.title.setText(info.title);
        holder.icon.setImageResource(info.img);
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView icon;

        public MyviewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.listtext);
            icon=(ImageView)itemView.findViewById(R.id.listicn);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
         delete(getPosition());
        }
    }
}
