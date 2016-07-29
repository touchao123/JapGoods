package com.touchenjoy.japgoods.ui.adapter.RecyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.touchenjoy.japgoods.R;
import com.touchenjoy.japgoods.model.entities.CarsEntity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/18.
 */
public class CarsRecyclerViewAdapter extends RecyclerView.Adapter<CarsRecyclerViewAdapter.CarsViewHolder> {
    private final LayoutInflater mLayoutInflater;
    ArrayList<CarsEntity> carsLists;

//    public CarsRecyclerViewAdapter(Context context){
//        super();
//        mLayoutInflater = LayoutInflater.from(context);
//    }

    public  CarsRecyclerViewAdapter(Context context, ArrayList<CarsEntity> carsLists){
        super();
        mLayoutInflater=LayoutInflater.from(context);
        this.carsLists=carsLists;

    }

    @Override
    public CarsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CarsViewHolder(mLayoutInflater.inflate(R.layout.list_item_cars, parent, false));
    }

    @Override
    public void onBindViewHolder(CarsViewHolder holder, int position) {
//
//        holder.textViewPro.setText("Jap");
//        holder.textViewCom.setText("Sony");
//        holder.textViewDomain.setText("http://www.jap.com");
        CarsEntity ce = carsLists.get(position);
        holder.textViewCom.setText(ce.getName().toString());
        holder.textViewDomain.setText(ce.getUrl());
        holder.item_image_layout.setVisibility(View.GONE);
        String imageUrl = ce.getLogo_url();
        ImageLoader.getInstance().displayImage(imageUrl, holder.right_logo_image);


    }

    @Override
    public int getItemCount() {
        return carsLists.size();
    }

    public static class CarsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.textViewDomain)
        TextView textViewDomain;

        @Bind(R.id.textViewCom)
        TextView textViewCom;

        @Bind(R.id.right_logo_image)
        ImageView right_logo_image;

        @Bind(R.id.item_image_layout)
        LinearLayout item_image_layout;

        public CarsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void update(int position) {

        }
    }
}
