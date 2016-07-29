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
import com.touchenjoy.japgoods.model.entities.CarsEntity;
import com.touchenjoy.japgoods.model.entities.Trademark;
import com.touchenjoy.japgoods.ui.adapter.RecyclerViewAdapter.CarsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/7/18.
 */
public class CarsFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    final String TAG="CarsFragment";
    CarsRecyclerViewAdapter carsRecyclerViewAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cars, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(new StockListViewAdapter(getActivity()));
        swipeRefreshLayout.setEnabled(false);

        initViews(view );

        onFresh();

        return view;
    }

    private void initViews(View view){

//        countObjects();
    }

    private void setAdpater(){

        ArrayList<CarsEntity> carsLists = new ArrayList<CarsEntity>();

        carsRecyclerViewAdapter= new CarsRecyclerViewAdapter(getActivity(),carsLists);
        recyclerView.setAdapter(carsRecyclerViewAdapter);
    }


    public void onFresh(){
        Log.i("tc","enter count...");
        BmobQuery<Trademark> bmobQuery = new BmobQuery<Trademark>();
        bmobQuery.addWhereEqualTo("category","汽车");
        bmobQuery.setLimit(50);
        bmobQuery.findObjects(getActivity(), new FindListener<Trademark>() {
            @Override
            public void onSuccess(List<Trademark> list) {
//                Log.d(TAG,"category=汽车 "+list.size());
//                Log.d(TAG, "onSuccess: first one: "+list.get(0).getName());
                ArrayList<CarsEntity> carsLists= new ArrayList<CarsEntity>();
                for (Trademark tm:list){
                    CarsEntity ce = new CarsEntity();
                    ce.setName(tm.getName());
                    ce.setCategory(tm.getCategory());
                    ce.setLogo(tm.getLogo());
                    ce.setShort_name(tm.getShort_name());
                    ce.setUrl(tm.getUrl());
                    if(tm.getLogo_img()!=null) {
                        Log.d(TAG, "Url:" + tm.getLogo_img().getUrl());
                        ce.setLogo_url(tm.getLogo_img().getUrl());
                    }
                    carsLists.add(ce);

                }

                carsRecyclerViewAdapter= new CarsRecyclerViewAdapter(getActivity(),carsLists);
                recyclerView.setAdapter(carsRecyclerViewAdapter);


            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

    public void countObjects(){
        Log.i("tc","enter count...");
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
