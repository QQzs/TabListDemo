package com.zs.demo.tablistdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zs.demo.tablistdemo.adapter.TagAdapter;
import com.zs.demo.tablistdemo.util.RecyclerViewUtil;

import java.util.ArrayList;

public class TimeActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private TagAdapter mAdapter;
    private ArrayList<Integer> mData = new ArrayList<>();


    private int mSpanCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_view = findViewById(R.id.recycler_view);

        mData.add(0);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(1);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(1);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(0);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(1);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(1);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);
        mData.add(2);

        mAdapter = new TagAdapter(this,mData);
        RecyclerViewUtil.initGrid(this,recycler_view,mAdapter,mSpanCount);
        GridLayoutManager layoutManager = (GridLayoutManager) recycler_view.getLayoutManager();
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mAdapter.getItemViewType(position);
                if (type == 0 || type == 1){
                    return mSpanCount;
                }else{
                    return 1;
                }
            }
        });
        recycler_view.setAdapter(mAdapter);

    }
}
