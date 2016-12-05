package com.shenme.androiddemo.activity.shopping;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.adapter.ProductAdapter;
import com.shenme.androiddemo.adapter.ProductDetailAdapter;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.data.shoppingcart.ResultData;
import com.shenme.androiddemo.utils.DateUtils;
import com.shenme.androiddemo.utils.Utils;
import com.shenme.androiddemo.widget.AutoHeightListView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by CANC on 2016/11/10.
 */

public class ResultActivity extends BaseActivity {
    public static final String RESULT_DATA = "resultData";
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.order_title_tv)
    TextView orderTitleTv;
    @BindView(R.id.order_status)
    TextView orderStatus;
    @BindView(R.id.order_no)
    TextView orderNo;
    @BindView(R.id.order_no_tv)
    TextView orderNoTv;
    @BindView(R.id.order_time_or_reject_code)
    TextView orderTimeOrRejectCode;
    @BindView(R.id.order_time_tv)
    TextView orderTimeTv;
    @BindView(R.id.pay_way_or_apply_time_tv)
    TextView payWayOrApplyTimeTv;
    @BindView(R.id.pay_way_value)
    TextView payWayValue;
    @BindView(R.id.productContainer)
    AutoHeightListView productContainer;
    @BindView(R.id.consignee_or_reject_way_tv)
    TextView consigneeOrRejectWayTv;
    @BindView(R.id.consignee_tv)
    TextView consigneeTv;
    @BindView(R.id.cell_no)
    TextView cellNo;
    @BindView(R.id.cell_no_tv)
    TextView cellNoTv;
    @BindView(R.id.cell_no_rl)
    RelativeLayout cellNoRl;
    @BindView(R.id.order_address_or_reject_title_tv)
    TextView orderAddressOrRejectTitleTv;
    @BindView(R.id.order_address_tv)
    TextView orderAddressTv;
    @BindView(R.id.take_their_value)
    LinearLayout takeTheirValue;
    @BindView(R.id.notes)
    TextView notes;
    @BindView(R.id.invoice)
    TextView invoice;
    @BindView(R.id.product_price_title)
    TextView productPriceTitle;
    @BindView(R.id.product_price)
    TextView productPrice;
    @BindView(R.id.carriage)
    TextView carriage;
    @BindView(R.id.carriage_tv)
    TextView carriageTv;
    @BindView(R.id.sub_total_title)
    TextView subTotalTitle;
    @BindView(R.id.sub_total)
    TextView subTotal;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    @BindView(R.id.btn_back)
    Button btnBack;

    private ResultData result;
    private ProductDetailAdapter adapter;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        result = gson.fromJson(getIntent().getStringExtra(RESULT_DATA), ResultData.class);
        setData();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData() {
        if (result != null) { //设置商品数据
            if (result.orderEntries != null && result.orderEntries.size() > 0) {
                adapter = new ProductDetailAdapter(mContext, result.orderEntries);
                productContainer.setAdapter(adapter);
            }

            orderStatus.setText(result.getOrderStatus());
            orderNoTv.setText(result.getOrderCode());
            orderTimeTv.setText(DateUtils.getDateFromMillisecond(Long.parseLong(result.getCreateDate())));
            payWayValue.setText(result.getPaymentMode());
            //收货信息
            if (result.user != null) {
                consigneeTv.setText(result.user.getUserName());
                cellNoTv.setText(result.user.getMobileNumber());
            }
            orderAddressTv.setText(result.getDeliveryAddress());
            //beizhu
            notes.setText(TextUtils.isEmpty(result.getNotes()) ? "无" : result.getNotes());
            //fapiao
            invoice.setText(TextUtils.isEmpty(result.getInvoice()) ? "无" : result.getInvoice());
            //金额
            productPrice.setText(Utils.PriceFormat(result.getTotalPrice()));
            subTotal.setText(Utils.PriceFormat(result.getSubTotalPrice()));
            carriageTv.setText(Utils.PriceFormat(result.getDeliverycost()));
        }
    }
}
