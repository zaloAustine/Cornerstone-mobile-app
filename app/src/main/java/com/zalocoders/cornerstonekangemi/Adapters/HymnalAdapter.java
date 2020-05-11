package com.zalocoders.cornerstonekangemi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.zalocoders.cornerstonekangemi.R;
import com.zalocoders.cornerstonekangemi.activities.ViewSongActivity;
import com.zalocoders.cornerstonekangemi.models.Hymnal;

import java.util.ArrayList;
import java.util.List;

public class HymnalAdapter extends RecyclerView.Adapter<HymnalAdapter.ViewHolder> implements Filterable {
    private final Context mcontext;
    private final List<Hymnal> mBlogs;
    private List<Hymnal> Filtered;
    private OnItemClickListener mlistener;

    public HymnalAdapter(Context mcontext, List<Hymnal> mBlogs) {
        this.mcontext = mcontext;
        this.mBlogs = mBlogs;
        this.Filtered = mBlogs;

    }


    @NonNull
    @Override
    public HymnalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(mcontext).inflate(R.layout.hymnal_item,viewGroup,false);

        return new HymnalAdapter.ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull HymnalAdapter.ViewHolder Holder, int i) {

        final Hymnal uploadCurrent = Filtered.get(i);

        Holder.title.setText(uploadCurrent.getTitle());
        Holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext, ViewSongActivity.class);
                i.putExtra("title",uploadCurrent.getTitle());
                i.putExtra("description",uploadCurrent.getDescription());
                mcontext.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return Filtered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if(key.isEmpty()){
                    Filtered = mBlogs;
                }else{
                    List<Hymnal> lstFiltered = new ArrayList<>();
                    for(Hymnal row:mBlogs){

                        if(row.getTitle().toLowerCase().contains(key.toLowerCase())){
                            lstFiltered.add(row);

                        }
                    }

                    Filtered = lstFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = Filtered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                Filtered = (List<Hymnal>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        private final TextView title;
        private final CardView mCardView;




        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            mCardView = itemView.findViewById(R.id.mCardView);




            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);




        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            menu.setHeaderTitle("Select Action");
            MenuItem delete = menu.add(Menu.NONE,1,1,"Delete");
            MenuItem edit = menu.add(Menu.NONE,2,2,"Edit");

            delete.setOnMenuItemClickListener(this);
            edit.setOnMenuItemClickListener(this);


        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(mlistener!=null){
                int Position = getAdapterPosition();
                if(Position!= RecyclerView.NO_POSITION){
                    switch (item.getItemId()){
                        case 2:
                            mlistener.OnEditClick(Position);
                            return true;
                        case 1:
                            mlistener.OnDeleteClick(Position);
                            return  true;
                    }

                }
            }
            return true;
        }


        @Override
        public void onClick(View v) {
            if(mlistener!=null){
                int Position = getAdapterPosition();
                if(Position!= RecyclerView.NO_POSITION){
                    switch (v.getId()){


                    }


                }
            }


        }
    }

    public interface OnItemClickListener{

        void OnEditClick(int Position);
        void OnDeleteClick(int Position);

    }
    public void setOnItemClickListener(OnItemClickListener listener){

        mlistener = listener;

    }
}
