package com.touchenjoy.japgoods.ui.fragment;

import android.support.v4.app.Fragment;


/**
 * Created by ctou on 16/6/21.
 */
public abstract class BaseFragment extends Fragment {
//    protected Subscription subscription;

//    @OnClick(R.id.tipBt)
//    void tip() {
//        new AlertDialog.Builder(getActivity())
//                .setTitle(getTitleRes())
//                .setView(getActivity().getLayoutInflater().inflate(getDialogRes(), null))
//                .show();
//    }

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

}
