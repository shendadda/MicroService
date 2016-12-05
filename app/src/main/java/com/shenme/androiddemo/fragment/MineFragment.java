package com.shenme.androiddemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.general.SysSettingActivity;
import com.shenme.androiddemo.activity.mine.MineInformationActivity;
import com.shenme.androiddemo.base.BaseFragment;
import com.shenme.androiddemo.data.user.Customer;
import com.shenme.androiddemo.utils.SharedPre;
import com.shenme.androiddemo.utils.SharedPreUtils;
import com.shenme.androiddemo.utils.Utils;
import com.shenme.androiddemo.widget.CircleImageView;
import com.shenme.androiddemo.widget.WaveView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CANC on 2016/9/26.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.text_title_tv)
    TextView textTitleTv;
    @BindView(R.id.head_icon)
    CircleImageView headIcon;
    @BindView(R.id.head_bg)
    RelativeLayout headBg;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_mobile)
    TextView userMobile;
    @BindView(R.id.update)
    ImageButton update;
    @BindView(R.id.iv_system_setting)
    ImageView ivSysTemSetting;

    @BindView(R.id.wave_view)
    WaveView waveView;
    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        headIcon.setOnClickListener(this);
        headBg.setOnClickListener(this);
        update.setOnClickListener(this);
        ivSysTemSetting.setOnClickListener(this);
        initWidget();
        updateInfor();
        return view;
    }

    //初始化各控件大小位置
    private void initWidget() {


        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2, -2);
        lp.gravity =  Gravity.CENTER;
        waveView.setOnWaveAnimationListener(new WaveView.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                lp.setMargins(0, 0, (int) y + 2, (int) y + 2);
                llUserInfo.setLayoutParams(lp);
            }
        });
        //容器大小设置
        llUserInfo.getLayoutParams().height = (int) (346.0f / 640 * Utils.getScreenWidth(getActivity()));
        //头像背景大小及位置
        int size = (int) (100f / 640 * Utils.getScreenWidth(getActivity()));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        layoutParams.width = size;
        layoutParams.height = size;
        headBg.setLayoutParams(layoutParams);
        //头像大小及位置
        RelativeLayout.LayoutParams llhead = (RelativeLayout.LayoutParams) headIcon.getLayoutParams();
        llhead.height = (int) (78f / 640 * Utils.getScreenWidth(getActivity()));
        llhead.width = (int) (78f / 640 * Utils.getScreenWidth(getActivity()));
        headIcon.setLayoutParams(llhead);

    }


    //更新信息
    public void updateInfor() {
        String mobile = "";
        Customer customer = Utils.getUserInfo(getActivity());
        if (customer != null && customer.getMobileNumber() != null) {
            mobile = customer.getMobileNumber().substring(0, 3) + "******" +
                    customer.getMobileNumber().substring(9);
        } else {
            mobile = "";
        }
        userName.setText((customer != null && customer.getNickName() != null) ? customer.getNickName() : "");
        userMobile.setText(mobile);
//        RetrofitUtil.getHttpService().getUserInfo(Utils.getToken(mContext))
//                .compose(new RetrofitUtil.CommonOptions<LoginResult>())
//                .subscribe(new CodeHandledSubscriber<LoginResult>() {
//                    @Override
//                    protected void onError(ApiException apiException) {
//
//                    }
//
//                    @Override
//                    protected void onBusinessNext(LoginResult data) {
////                        SharedPreUtils.putString(mContext, SharedPre.User.AVATAR_URL, data.customer.);
//
//                    }
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//                });
//        //
        //获取用户头像
        String avatarUrl = SharedPreUtils.getString(getActivity(), SharedPre.User.AVATAR_URL);
        if (!TextUtils.isEmpty(avatarUrl)) {
            headIcon.setImageURI(avatarUrl);
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.update:
            case R.id.head_icon:
            case R.id.head_bg:
                intent = new Intent(mContext, MineInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_system_setting:
                intent = new Intent(mContext, SysSettingActivity.class);
                startActivity(intent);
                break;
        }

    }
}
