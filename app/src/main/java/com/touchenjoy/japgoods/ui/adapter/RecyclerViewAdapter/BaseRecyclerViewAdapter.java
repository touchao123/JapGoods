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
import com.touchenjoy.japgoods.model.entities.BaseEntity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/29.
 */
public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder> {
    private final LayoutInflater mLayoutInflater;
    ArrayList<BaseEntity> itemLists;

    public BaseRecyclerViewAdapter(Context context, ArrayList<BaseEntity> itemLists) {
        mLayoutInflater=LayoutInflater.from(context);
        this.itemLists=itemLists;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(mLayoutInflater.inflate(R.layout.list_item_base, parent, false));
    }

    @Override
    public int getItemCount() {
        return itemLists.size();
    }
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        BaseEntity be = itemLists.get(position);
        holder.textViewCom.setText(be.getName().toString());
        holder.textViewDomain.setText(be.getUrl());
        holder.item_image_layout.setVisibility(View.GONE);
        String imageUrl = be.getLogo_url();
        ImageLoader.getInstance().displayImage(imageUrl, holder.right_logo_image);
        holder.item_info.setText(be.getInfo());


    }
    public static class BaseViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.textViewDomain)
        TextView textViewDomain;

        @Bind(R.id.textViewCom)
        TextView textViewCom;

        @Bind(R.id.right_logo_image)
        ImageView right_logo_image;

        @Bind(R.id.item_image_layout)
        LinearLayout item_image_layout;

        @Bind(R.id.item_info)
        TextView item_info;

        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void update(int position) {

        }
    }
}
