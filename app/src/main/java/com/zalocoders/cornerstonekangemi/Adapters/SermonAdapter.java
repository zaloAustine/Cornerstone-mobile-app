package com.zalocoders.cornerstonekangemi.Adapters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.teleclinic.bulent.smartimageview.SmartImageViewLayout;
import com.zalocoders.cornerstonekangemi.Models.News;
import com.zalocoders.cornerstonekangemi.Models.Sermon;
import com.zalocoders.cornerstonekangemi.R;
import com.zalocoders.cornerstonekangemi.activities.ListenSermonActivity;
import com.zalocoders.cornerstonekangemi.activities.ViewImageActivity;

import java.util.List;


public class SermonAdapter extends RecyclerView.Adapter<SermonAdapter.newsViewHolder> {
    private Context context;
    private List<Sermon> mNewsList;
    public boolean on_attach = true;


    public SermonAdapter(Context context, List<Sermon> newsList) {
        this.context = context;
        this.mNewsList = newsList;
    }

    @NonNull
    @Override
    public newsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View viewItem = inflater.inflate(R.layout.sermonitem, parent, false);
        return new newsViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull newsViewHolder holder, int position) {

        final Sermon sermon = mNewsList.get(position);

        holder.description.setText(sermon.getPastor());
        holder.topic.setText(sermon.getTopic());

       setAnimation(holder.itemView,position);



       holder.play.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(context, ListenSermonActivity.class);
               Gson g = new Gson();
               String news1 = g.toJson(sermon,Sermon.class);
               i.putExtra("News",news1);
               context.startActivity(i);
           }
       });
     //  RightLeft(holder.itemView,position);



    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);

    }


    @Override
    public int getItemCount() {
        return mNewsList.size() ;
    }

    public class newsViewHolder extends RecyclerView.ViewHolder{
        private TextView description,topic;
        ImageView play;



        public newsViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.description);
            topic = itemView.findViewById(R.id.topic);
            play = itemView.findViewById(R.id.play);






        }
    }



    public void setAnimation(View itemview, int i) {

        if (!on_attach) {
            i = -1;
        }


        boolean isNotFirst = i == -1;
        i++;

        itemview.setAlpha(0.f);
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemview, "alpha", 0.f, 0.5f, 1.0f);
        animator.setStartDelay(isNotFirst ? 500 / 2 : (i * 500 / 3));
        animator.setDuration(500);
        set.play(animator);
        animator.start();
    }


    public void RightLeft(View itemview, int i) {
        if (!on_attach) {
            i = -1;
        }


        boolean isNotFirst = i == -1;
        i = i + 1;
        itemview.setTranslationX(itemview.getX() + 400);
        itemview.setAlpha(0.f);
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(itemview, "translationX", itemview.getX(), +400,0);
        ObjectAnimator animatorx = ObjectAnimator.ofFloat(itemview, "alpha", 1.f);
        ObjectAnimator.ofFloat(itemview, "alpha", 0.f).start();
        animatorY.setStartDelay(isNotFirst ? 150 : (i * 150));
        animatorY.setDuration((isNotFirst ? 2 : 1) * 150);
        set.playTogether(animatorY, animatorx);
        set.start();
    }


    public void ViewImages(SmartImageViewLayout smartImageViewLayout, final News news){

        smartImageViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewImageActivity.class);
                Gson g = new Gson();
                String news1 = g.toJson(news,News.class);
                i.putExtra("News",news1);
            context.startActivity(i);
            }
        });
    }

}
