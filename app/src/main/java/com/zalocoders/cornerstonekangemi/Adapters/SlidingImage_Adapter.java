package com.zalocoders.cornerstonekangemi.Adapters;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;
import com.zalocoders.cornerstonekangemi.R;


import java.util.ArrayList;

public class SlidingImage_Adapter extends PagerAdapter {


    private ArrayList<String> IMAGES;
    private LayoutInflater inflater;
    private Context context;
    String descriptionText;



    public SlidingImage_Adapter(ArrayList<String> IMAGES, Context context,String descriptionText) {
        this.IMAGES = IMAGES;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.descriptionText = descriptionText;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.view_images, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);

        Picasso.get().load(IMAGES.get(position)).into(imageView);


        TextView description = imageLayout.findViewById(R.id.description);
        description.setText(descriptionText);

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


    public void setDescriptionText(String descriptionText){
        this.descriptionText = descriptionText;
    }

}
