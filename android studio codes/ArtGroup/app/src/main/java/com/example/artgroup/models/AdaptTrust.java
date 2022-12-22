package com.example.artgroup.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.artgroup.R;

import java.util.List;

public class AdaptTrust extends PagerAdapter {
    private List<Trust> trusts;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdaptTrust(List<Trust> trusts, Context context) {
        this.trusts = trusts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return trusts.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_layer, container, false);
        ImageView imageView = view.findViewById(R.id.myImage);
        TextView textView = view.findViewById(R.id.txtAll);
        imageView.setImageResource(trusts.get(position).getImage());
        textView.setText(trusts.get(position).getTester());
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
