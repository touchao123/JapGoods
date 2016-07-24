package com.touchenjoy.japgoods.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.touchenjoy.japgoods.R;
import com.touchenjoy.japgoods.model.entities.Trademark;
import com.touchenjoy.japgoods.ui.adapter.RecyclerViewAdapter.CarsRecyclerViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;

/**
 * Created by Administrator on 2016/7/18.
 */
public class CarsFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    CarsRecyclerViewAdapter carsRecyclerViewAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cars, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(new StockListViewAdapter(getActivity()));
        swipeRefreshLayout.setEnabled(false);

        initViews(view );

        setAdpater();

        return view;
    }

    private void initViews(View view){

        countObjects();
    }

    private void setAdpater(){

        carsRecyclerViewAdapter= new CarsRecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(carsRecyclerViewAdapter);
    }

    public void countObjects(){
        BmobQuery<Trademark> bmobQuery = new BmobQuery<Trademark>();
        bmobQuery.count(getActivity(),Trademark.class, new CountListener(){

            @Override
            public void onSuccess(int i) {

                Log.d("tc","counts:"+i);
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("tc",s);
            }
        });
    }
}
