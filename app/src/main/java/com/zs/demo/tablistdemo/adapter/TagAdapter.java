package com.zs.demo.tablistdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zs.demo.tablistdemo.view.BorderTextView;
import com.zs.demo.tablistdemo.R;

import java.util.ArrayList;

/**
 * Created by zs
 * Date：2018年 05月 09日
 * Time：9:50
 * —————————————————————————————————————
 * About:
 * —————————————————————————————————————
 */
public class TagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<Integer> mData;
    private int mSelectTime = -1;

    public TagAdapter(Context mContext, ArrayList<Integer> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View titleView = View.inflate(parent.getContext(), R.layout.tag_title_layout,null);

        View timeView = View.inflate(parent.getContext(),R.layout.tag_time_layout,null);

        if (viewType == 0 || viewType == 1){
            return new TagTitleHolder(titleView);
        }else{
            return new TagTimeHolder(timeView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TagTitleHolder){
            TagTitleHolder titleHolder = (TagTitleHolder) holder;
            if (getItemViewType(position) == 0){
                titleHolder.rl_take_date.setVisibility(View.VISIBLE);
            }else{
                titleHolder.rl_take_date.setVisibility(View.GONE);
            }

        }else{
            TagTimeHolder timeHolder = (TagTimeHolder) holder;
            timeHolder.tv_tag_time.setText("00:00-22:22");

            if (position == mSelectTime){
                timeHolder.tv_tag_time.setTextColor(ContextCompat.getColor(mContext,R.color.white));
                timeHolder.tv_tag_time.setContentColorResource(R.color.tag_back);
            }else{
                timeHolder.tv_tag_time.setTextColor(ContextCompat.getColor(mContext,R.color.font_gray));
                timeHolder.tv_tag_time.setContentColorResource(R.color.white);
            }

            timeHolder.tv_tag_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // 局部刷新,只刷新变化的两个item ( 有header要注意是否有影响 )
                    if (mSelectTime != -1){
                        notifyItemChanged(mSelectTime);
                    }
                    mSelectTime = position;
                    notifyItemChanged(mSelectTime);

                    // 也可直接刷新全部
//                    mSelectTime = position;
//                    notifyDataSetChanged();
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

    class TagTitleHolder extends RecyclerView.ViewHolder{

        private RelativeLayout rl_take_date;
        private RelativeLayout rl_take_time;
        private TextView tv_take_date;
        private TextView tv_take_time;

        public TagTitleHolder(View itemView) {
            super(itemView);
            rl_take_date = itemView.findViewById(R.id.rl_take_date);
            rl_take_time = itemView.findViewById(R.id.rl_take_time);
            tv_take_date = itemView.findViewById(R.id.tv_take_date);
            tv_take_time = itemView.findViewById(R.id.tv_take_time);


        }
    }

    class TagTimeHolder extends RecyclerView.ViewHolder{

        private BorderTextView tv_tag_time;

        public TagTimeHolder(View itemView) {
            super(itemView);
            tv_tag_time = itemView.findViewById(R.id.tv_tag_time);

        }
    }


}
