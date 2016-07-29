package com.touchenjoy.japgoods.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.touchenjoy.japgoods.R;
import com.touchenjoy.japgoods.ui.adapter.RecyclerViewAdapter.BaseRecyclerViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by ctou on 16/6/21.
 */
public abstract class BaseFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    final String TAG="ClothesFragment";
    BaseRecyclerViewAdapter baseRecyclerViewAdapter;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
//        if (subscription != null && !subscription.isUnsubscribed()) {
//            subscription.unsubscribe();
//        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(new StockListViewAdapter(getActivity()));
        swipeRefreshLayout.setEnabled(false);

        onFresh();

        return view;
    }

     abstract void onFresh();

}
