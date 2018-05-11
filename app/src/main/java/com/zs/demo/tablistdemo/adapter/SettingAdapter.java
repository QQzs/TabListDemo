package com.zs.demo.tablistdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zs.demo.tablistdemo.R;
import com.zs.demo.tablistdemo.listener.RecyclerItemListener;
import com.zs.demo.tablistdemo.view.BorderTextView;

import java.util.ArrayList;

/**
 * Created by zs
 * Date：2018年 05月 09日
 * Time：9:50
 * —————————————————————————————————————
 * About:
 * —————————————————————————————————————
 */
public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.TagTimeHolder> {


    public static final int TPYE_1 = 1;
    public static final int TPYE_2 = 2;
    public static final int TPYE_3 = 3;

    private Context mContext;
    private int mType;
    private ArrayList<Integer> mData;
    private RecyclerItemListener mListener;

    public SettingAdapter(Context mContext , int type , ArrayList<Integer> mData) {
        this.mContext = mContext;
        this.mType = type;
        this.mData = mData;
        mListener = (RecyclerItemListener) mContext;
    }

    @NonNull
    @Override
    public SettingAdapter.TagTimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View timeView = View.inflate(parent.getContext(),R.layout.setting_time_layout,null);
        return new TagTimeHolder(timeView);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingAdapter.TagTimeHolder holder, final int position) {

        if (getItemViewType(position) == 0){
            holder.tv_tag_time.setVisibility(View.VISIBLE);
            holder.iv_delete_tag.setVisibility(View.VISIBLE);
            holder.tv_tag_add.setVisibility(View.GONE);

            holder.iv_delete_tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(position,mType,view);
                }
            });

        }else{
            holder.tv_tag_time.setVisibility(View.GONE);
            holder.iv_delete_tag.setVisibility(View.GONE);
            holder.tv_tag_add.setVisibility(View.VISIBLE);

            holder.tv_tag_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(position,mType,view);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position);
    }

    class TagTimeHolder extends RecyclerView.ViewHolder{

        private BorderTextView tv_tag_time , tv_tag_add;
        private ImageView iv_delete_tag;

        public TagTimeHolder(View itemView) {
            super(itemView);
            tv_tag_time = itemView.findViewById(R.id.tv_tag_time);
            tv_tag_add = itemView.findViewById(R.id.tv_tag_add);
            iv_delete_tag = itemView.findViewById(R.id.iv_delete_tag);

        }
    }


}
