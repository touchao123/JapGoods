package com.touchenjoy.japgoods.ui.adapter.RecyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.touchenjoy.japgoods.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/18.
 */
public class CarsRecyclerViewAdapter extends RecyclerView.Adapter<CarsRecyclerViewAdapter.CarsViewHolder> {
    private final LayoutInflater mLayoutInflater;

    public CarsRecyclerViewAdapter(Context context){
        super();
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public CarsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CarsViewHolder(mLayoutInflater.inflate(R.layout.list_item_cars, parent, false));
    }

    @Override
    public void onBindViewHolder(CarsViewHolder holder, int position) {

        holder.textViewPro.setText("Jap");
        holder.textViewCom.setText("Sony");
        holder.textViewDomain.setText("http://www.jap.com");
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class CarsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textViewPro)
        TextView textViewPro;


        @Bind(R.id.textViewDomain)
        TextView textViewDomain;

        @Bind(R.id.textViewCom)
        TextView textViewCom;

        public CarsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void update(int position) {

        }
    }
}
