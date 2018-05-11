# TabListDemo
## 一个RecyclerView 展示多种样式  和  ScrollView 中包含多个RecyclerView

### 页面一 ： 一个RecyclerView 展示多种样式
![](https://github.com/QQzs/Image/blob/master/TabListDemo/show_time_art.gif) <br>
整个是一个RecyclerView 通过Bean 的type属性才控制，如果bean的标题类型，显示一行占满，如果是标签，占一个位置。
<br>
先看数据部分定义了三种类型
```Java
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

```
Activity部分：
```Java
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
```
Adapter部分:<br>
两个ViewHolder 根据bean中的数据来判断显示哪个，点击后刷新数据，可以采用局部刷新 和 全局刷新。
```Java
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
```
### 页面二 ： ScrollView 中包含多个RecyclerView
![](https://github.com/QQzs/Image/blob/master/TabListDemo/show_setting_art.gif)
<br>
RecyclerView套嵌在ScrollView 中时，由于两个控件都有上下滑动事件，所有会有冲突，解决办法是，把RecyclerView的滑动事件完全交给ScrollView来处理，
要用NestedScrollView，用普通的ScrollView 当数据满屏时显示会有问题，点击事件通过接口回调给activity去处理。
<br>
Activity部分：

```Java
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

```

布局：多个RecyclerView 这样会使Activity页面数据处理稍微复杂一点，但是布局的逻辑会清晰点

```Java
<android.support.v4.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scrollbars="none">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <RelativeLayout
                android:layout_width="match_parent"
                android:layout_height="50dp">
                <View
                    android:layout_width="match_parent"
                    android:layout_height="1px"
                    android:background="@color/font_gray">
                </View>
                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="50dp"
                    android:text="设置可预约时间"
                    android:gravity="center_vertical"
                    android:paddingLeft="14dp">
                </TextView>

                <View
                    android:layout_width="match_parent"
                    android:layout_height="1px"
                    android:background="@color/font_gray"
                    android:layout_alignParentBottom="true">
                </View>

            </RelativeLayout>

            <android.support.v7.widget.RecyclerView
                android:id="@+id/recycler_subscribe"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                >

            </android.support.v7.widget.RecyclerView>

            <RelativeLayout
                android:layout_width="match_parent"
                android:layout_height="50dp">
                <View
                    android:layout_width="match_parent"
                    android:layout_height="1px"
                    android:background="@color/font_gray">
                </View>
                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="50dp"
                    android:text="设置工作时间"
                    android:gravity="center_vertical"
                    android:paddingLeft="14dp">
                </TextView>

                <View
                    android:layout_width="match_parent"
                    android:layout_height="1px"
                    android:background="@color/font_gray"
                    android:layout_alignParentBottom="true">
                </View>

            </RelativeLayout>

            <android.support.v7.widget.RecyclerView
                android:id="@+id/recycler_work"
                android:layout_width="match_parent"
                android:layout_height="match_parent">

            </android.support.v7.widget.RecyclerView>

            <RelativeLayout
                android:layout_width="match_parent"
                android:layout_height="50dp">
                <View
                    android:layout_width="match_parent"
                    android:layout_height="1px"
                    android:background="@color/font_gray">
                </View>
                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="50dp"
                    android:text="装车台编号"
                    android:gravity="center_vertical"
                    android:paddingLeft="14dp">
                </TextView>

                <View
                    android:layout_width="match_parent"
                    android:layout_height="1px"
                    android:background="@color/font_gray"
                    android:layout_alignParentBottom="true">
                </View>

            </RelativeLayout>

            <android.support.v7.widget.RecyclerView
                android:id="@+id/recycler_platform"
                android:layout_width="match_parent"
                android:layout_height="match_parent">

            </android.support.v7.widget.RecyclerView>

            <View
                android:layout_width="match_parent"
                android:layout_height="50dp"
                >
            </View>

        </LinearLayout>
    </android.support.v4.widget.NestedScrollView>
```

Adapter中只有一种布局，里面的逻辑很简单，只有显示数据和添加点击事件。
