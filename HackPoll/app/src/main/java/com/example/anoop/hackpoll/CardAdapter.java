package com.example.anoop.hackpoll;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anoop on 27/7/15.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    List<String> quesList,authorList,timeList;
    int qId=10;
    View.OnClickListener onClickListener;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_cards,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.question.setText(quesList.get(position).toString());
        holder.author.setText(authorList.get(position).toString());
        holder.timestamp.setText(timeList.get(position).toString());
        holder.cardView.setTag(qId);
        holder.cardView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return quesList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView question,author,timestamp;
        CardView cardView;
        public ViewHolder(View v){
            super(v);
            question=(TextView)v.findViewById(R.id.ques);
            author=(TextView)v.findViewById(R.id.author);
            timestamp=(TextView)v.findViewById(R.id.timestamp);
            cardView=(CardView)v.findViewById(R.id.card_view);

        }
    }

    public CardAdapter(List<String> quesList,List<String> authorList,List<String> timeList,View.OnClickListener onClickListener){
        this.quesList=quesList;
        this.authorList=authorList;
        this.timeList=timeList;
        this.onClickListener=onClickListener;
    }
}
