package com.example.smartiptv.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartiptv.Activity.PlayingLive;
import com.example.smartiptv.Fragment.PlayLiveFragment;
import com.example.smartiptv.R;
import com.example.smartiptv.model.Live;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class livevideoscreenAdapter1 extends RecyclerView.Adapter<livevideoscreenAdapter1.MyViewHolder> {

    private List<Live> dataSet;
    List<Live> dataSet1 = new ArrayList<Live>();
    Context ctx;
    int value;
    String name,id,streamcheck;
  //  Fragment fragment;
    String tmpString="1111";
    StringBuffer finalString;
    private ArrayList<Live> arraylist;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName,textViewName1;
        ImageView img,img1;
        CardView gridlayout;
        View line;

        RelativeLayout rel;

        public MyViewHolder(View itemView) {
            super(itemView);

             this.rel=(RelativeLayout)itemView.findViewById(R.id.relative);
            this.textViewName1 = (TextView) itemView.findViewById(R.id.streamname1);
            this.img1=(ImageView)itemView.findViewById(R.id.img1);
            this.textViewName=(TextView)itemView.findViewById(R.id.streamidtext);
           // this.line=(View)itemView.findViewById(R.id.line);



        }
    }

    public livevideoscreenAdapter1(List<Live> data, Context ctx, List<Live>dataSet1, String name, String id, String streamcheck ){
        this.dataSet = data;
        this.ctx=ctx;
        this.dataSet1=dataSet1;
        this.name=name;
        this.id=id;
       // this.fragment=fragment;
        this.arraylist = new ArrayList<Live>();
        this.arraylist.addAll(dataSet);
        this.streamcheck=streamcheck;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listscreen, parent, false);
        MyViewHolder myViewHolder   = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        String namepass[] = dataSet.get(listPosition).getStreamname().split(":");
        String namei8 = namepass[0];
        final String pass1 = namepass[1];
            holder.textViewName1.setText(pass1);



        String input = dataSet.get(listPosition).getStream_id();     //input string
        String lastFourDigits = "";
        String  firstFourChars="";

        if (input.length() > 4)
        {
            lastFourDigits = input.substring(input.length() - 1);
            firstFourChars = input.substring(0, 4);

            holder.textViewName.setText(firstFourChars+"\n"+lastFourDigits);
        }
        else
        {
            lastFourDigits = input;
            holder.textViewName.setText(lastFourDigits);
        }


        if(!isNullOrEmpty(dataSet.get(listPosition).getStream_icon()))

            {
                Picasso.get()
                        .load(dataSet.get(listPosition).getStream_icon())
                        .placeholder(R.drawable.logo1)
                        .error(R.drawable.logo1)
                        .into(holder.img1);
            }


            holder.rel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.rel.setBackgroundColor(Color.parseColor("#050505"));
                  //  ((PlayLiveFragment) fragment).checkplayer(dataSet.get(listPosition).getStream_id(),dataSet.get(listPosition).getStreamname());

                   ((PlayingLive)ctx).checkplayer(dataSet.get(listPosition).getStream_id(),dataSet.get(listPosition).getStreamname());


                }
            });




    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

}
