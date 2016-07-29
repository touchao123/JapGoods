package com.touchenjoy.japgoods.ui.fragment;

import android.util.Log;

import com.touchenjoy.japgoods.model.entities.BaseEntity;
import com.touchenjoy.japgoods.model.entities.CarsEntity;
import com.touchenjoy.japgoods.model.entities.Trademark;
import com.touchenjoy.japgoods.ui.adapter.RecyclerViewAdapter.BaseRecyclerViewAdapter;
import com.touchenjoy.japgoods.ui.adapter.RecyclerViewAdapter.CarsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/7/18.
 */
public class ResideFragment extends BaseFragment {

    public void onFresh(){
        Log.i("tc","enter count...");
        BmobQuery<Trademark> bmobQuery = new BmobQuery<Trademark>();
        bmobQuery.addWhereEqualTo("category","电器");
        bmobQuery.setLimit(50);
        bmobQuery.findObjects(getActivity(), new FindListener<Trademark>() {
            @Override
            public void onSuccess(List<Trademark> list) {
//                Log.d(TAG,"category=汽车 "+list.size());
//                Log.d(TAG, "onSuccess: first one: "+list.get(0).getName());
                ArrayList<BaseEntity> resideLists= new ArrayList<BaseEntity>();
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
                    resideLists.add(be);

                }

                baseRecyclerViewAdapter= new BaseRecyclerViewAdapter(getActivity(),resideLists);
                recyclerView.setAdapter(baseRecyclerViewAdapter);


            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }
}
