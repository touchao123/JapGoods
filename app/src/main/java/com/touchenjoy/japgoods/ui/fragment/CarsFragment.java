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
import com.touchenjoy.japgoods.model.entities.BaseEntity;
import com.touchenjoy.japgoods.model.entities.CarsEntity;
import com.touchenjoy.japgoods.model.entities.Trademark;
import com.touchenjoy.japgoods.ui.adapter.RecyclerViewAdapter.BaseRecyclerViewAdapter;
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
                ArrayList<BaseEntity> carsLists= new ArrayList<BaseEntity>();
                for (Trademark tm:list){
                    BaseEntity be = new BaseEntity();
                    be.setName(tm.getName());
                    be.setCategory(tm.getCategory());
                    be.setLogo(tm.getLogo());
                    be.setShort_name(tm.getShort_name());
                    be.setUrl(tm.getUrl());
                    be.setInfo(tm.getInfo());
                    if(tm.getLogo_img()!=null) {
                        Log.d(TAG, "Url:" + tm.getLogo_img().getUrl());
                        be.setLogo_url(tm.getLogo_img().getUrl());
                    }
                    carsLists.add(be);

                }

                baseRecyclerViewAdapter= new BaseRecyclerViewAdapter(getActivity(),carsLists);
                recyclerView.setAdapter(baseRecyclerViewAdapter);

            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }
}
