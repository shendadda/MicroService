package com.shenme.androiddemo.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.apps.muzei.api.MuzeiArtSource;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.ImageDetailActivity;
import com.shenme.androiddemo.activity.general.SearchActivity;
import com.shenme.androiddemo.adapter.DataAdapter;
import com.shenme.androiddemo.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import immortalz.me.library.TransitionsHeleper;


/**
 * Created by CANC on 2016/9/26.
 */

public class HomePageFragment extends BaseFragment implements DataAdapter.ImageDetailListener {

//    //界面数据
//    private List<Welfare> datas = new ArrayList<>();
//    private DataAdapter adapter;
//    @BindView(R.id.recycler_view)
//    RecyclerView recyclerView;
//    @BindView(R.id.swipe_refresh)
//    SwipeRefreshLayout swipeRefresh;
//    @BindView(R.id.tv_page_info)
//    TextView tvPageInfo;

    @BindView(R.id.ib_search)
    ImageButton ibSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        ButterKnife.bind(this, view);
        mView = view;
        mContext = getActivity();

        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
            }
        });
//        setSwipeLayout();
//        initView();
//        initPageInfo();
//        getData(true);
        return view;

    }

//    private void initView() {
//        adapter = new DataAdapter(mContext, this);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapter);
//    }

    //    @Override
//    protected void getData(boolean isShowDialog) {
//        RetrofitUtil.getHttpService().getImage()
//                .compose(new RetrofitUtil.CommonOptions<WelfareResult>())
//                .subscribe(new CodeHandledSubscriber<WelfareResult>() {
//                    @Override
//                    protected void onError(ApiException apiException) {
//
//                    }
//
//                    @Override
//                    protected void onBusinessNext(WelfareResult data) {
//                        if (isRefresh) {
//                            datas.clear();
//                        }
//                        if (data != null && data.getWelfares() != null && data.getWelfares().size() > 0) {
//                            datas.addAll(data.getWelfares());
//                            if (adapter == null) {
//                                adapter = new DataAdapter(mContext, datas, (DataAdapter.ImageDetailListener) mContext);
//                            } else {
//                                adapter.setData(datas, mContext);
//                            }
//                        } else {
//                            adapter = new DataAdapter(mContext, null);
//                        }
//                        adapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        swipeRefresh.setRefreshing(false);
//                    }
//                });
//        super.getData(isShowDialog);
//    }
//
//    @Override
//    public void onRefresh(SwipyRefreshLayoutDirection direction) {
//        super.onRefresh(direction);
//        swipeLayout.setRefreshing(false);
//    }
//
    @Override
    public void ImageDetail(View view, String imageUrl) {
        TransitionsHeleper.startAcitivty((Activity) mContext, ImageDetailActivity.class, view, imageUrl);
    }
}
