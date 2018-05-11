package com.zs.demo.tablistdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zs.demo.tablistdemo.adapter.SettingAdapter;
import com.zs.demo.tablistdemo.listener.RecyclerItemListener;
import com.zs.demo.tablistdemo.util.RecyclerViewUtil;

import java.util.ArrayList;

/**
 * Created by zs
 * Date：2018年 05月 10日
 * Time：16:25
 * —————————————————————————————————————
 * About:
 * —————————————————————————————————————
 */
public class SettingActivity extends AppCompatActivity implements RecyclerItemListener {

    private RecyclerView recycler_subscribe;
    private RecyclerView recycler_work;
    private RecyclerView recycler_platform;

    private SettingAdapter mAdapter1;
    private SettingAdapter mAdapter2;
    private SettingAdapter mAdapter3;

    private ArrayList<Integer> mData1 = new ArrayList<>();
    private ArrayList<Integer> mData2 = new ArrayList<>();
    private ArrayList<Integer> mData3 = new ArrayList<>();

    private int mSpanCount = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        recycler_subscribe = findViewById(R.id.recycler_subscribe);
        recycler_work = findViewById(R.id.recycler_work);
        recycler_platform = findViewById(R.id.recycler_platform);

        mData1.add(0);
        mData1.add(0);
        mData1.add(1);

        mData2.add(0);
        mData2.add(0);
        mData2.add(1);

        mData3.add(1);

        mAdapter1 = new SettingAdapter(this,SettingAdapter.TPYE_1,mData1);
        RecyclerViewUtil.initGridScroll(this,recycler_subscribe,mAdapter1,mSpanCount);
        recycler_subscribe.setAdapter(mAdapter1);

        mAdapter2 = new SettingAdapter(this,SettingAdapter.TPYE_2,mData2);
        RecyclerViewUtil.initGridScroll(this,recycler_work,mAdapter2,mSpanCount);
        recycler_work.setAdapter(mAdapter2);

        mAdapter3 = new SettingAdapter(this,SettingAdapter.TPYE_3,mData3);
        RecyclerViewUtil.initGridScroll(this,recycler_platform,mAdapter3,mSpanCount);
        recycler_platform.setAdapter(mAdapter3);

    }

    @Override
    public void onItemClick(int position, int type , View view) {

        switch (type){
            case SettingAdapter.TPYE_1:

                switch (view.getId()){
                    case R.id.iv_delete_tag:
                        mData1.remove(position);
                        mAdapter1.notifyDataSetChanged();
                        break;

                    case R.id.tv_tag_add:

                        if (mData1.size() > 1){
                            mData1.add(mData1.size() - 2,0);
                        }else{
                            mData1.add(0,0);
                        }
                        mAdapter1.notifyDataSetChanged();
                        break;
                }

                break;

            case SettingAdapter.TPYE_2:

                switch (view.getId()){
                    case R.id.iv_delete_tag:
                        mData2.remove(position);
                        mAdapter2.notifyDataSetChanged();
                        break;

                    case R.id.tv_tag_add:

                        if (mData2.size() > 1){
                            mData2.add(mData2.size() - 2,0);
                        }else{
                            mData2.add(0,0);
                        }
                        mAdapter2.notifyDataSetChanged();
                        break;
                }

                break;

            case SettingAdapter.TPYE_3:

                switch (view.getId()){
                    case R.id.iv_delete_tag:
                        mData3.remove(position);
                        mAdapter3.notifyDataSetChanged();
                        break;

                    case R.id.tv_tag_add:

                        if (mData3.size() > 1){
                            mData3.add(mData3.size() - 2,0);
                        }else{
                            mData3.add(0,0);
                        }
                        mAdapter3.notifyDataSetChanged();
                        break;
                }
                break;

        }



    }
}
