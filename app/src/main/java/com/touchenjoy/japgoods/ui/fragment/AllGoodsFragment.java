package com.touchenjoy.japgoods.ui.fragment;

import android.util.Log;

import com.touchenjoy.japgoods.model.entities.BaseEntity;
import com.touchenjoy.japgoods.model.entities.Trademark;
import com.touchenjoy.japgoods.ui.adapter.RecyclerViewAdapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/7/30.
 */
public class AllGoodsFragment extends BaseFragment {
    public void onFresh(){

        BmobQuery<Trademark> bmobQuery = new BmobQuery<Trademark>();
        bmobQuery.setLimit(50);
        bmobQuery.findObjects(getActivity(), new FindListener<Trademark>() {
            @Override
            public void onSuccess(List<Trademark> list) {
                ArrayList<BaseEntity> allLists= new ArrayList<BaseEntity>();
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
                    allLists.add(be);

                }

                baseRecyclerViewAdapter= new BaseRecyclerViewAdapter(getActivity(),allLists);
                recyclerView.setAdapter(baseRecyclerViewAdapter);

            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }
}
