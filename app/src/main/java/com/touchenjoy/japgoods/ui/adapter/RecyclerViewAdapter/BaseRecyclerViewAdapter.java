package com.touchenjoy.japgoods.ui.adapter.RecyclerViewAdapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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

    private PopupWindow popupWindow;

    public BaseRecyclerViewAdapter(Context context, ArrayList<BaseEntity> itemLists) {
        mLayoutInflater=LayoutInflater.from(context);
        this.itemLists=itemLists;
        initPopWindow(mLayoutInflater);
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

        //setting popitem
        holder.popicon.setOnClickListener(new popAction(position));

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


        //
        @Bind(R.id.popicon)
        ImageView popicon;

        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void update(int position) {

        }
    }

    private ImageView btn_pop_close;

    private void initPopWindow(LayoutInflater inflater) {
        View popView = inflater.inflate(R.layout.list_item_pop, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置popwindow出现和消失动画
        popupWindow.setAnimationStyle(R.style.PopMenuAnimation);
        btn_pop_close = (ImageView) popView.findViewById(R.id.btn_pop_close);
    }


    public class popAction implements View.OnClickListener {
        int position;
        public popAction(int position){
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            int[] arrayOfInt = new int[2];
            //获取点击按钮的坐标
            v.getLocationOnScreen(arrayOfInt);
            int x = arrayOfInt[0];
            int y = arrayOfInt[1];
            showPop(v, x , y, position);
        }
    }

    public void showPop(View parent, int x, int y,int postion) {
        //设置popwindow显示位置
        popupWindow.showAtLocation(parent, 0, x, y);
        //获取popwindow焦点
        popupWindow.setFocusable(true);
        //设置popwindow如果点击外面区域，便关闭。
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        if (popupWindow.isShowing()) {

        }
        btn_pop_close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
                popupWindow.dismiss();
            }
        });
    }
}
