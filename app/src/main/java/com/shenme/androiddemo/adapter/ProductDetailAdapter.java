package com.shenme.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.data.shoppingcart.Entry;
import com.shenme.androiddemo.utils.Utils;

import java.util.List;

/**
 * Created by CANC on 2016/11/9.
 * 结算界面商品信息
 */
public class ProductDetailAdapter extends CommonAdapter<Entry> {

    public final static int TYPE_NORMAL = 0;

    protected int layoutId;

    public ProductDetailAdapter(Context context, List<Entry> datas) {
        super(context, datas);
        this.layoutId = R.layout.activity_order_product_item;
    }

    @Override
    public void convert(ViewHolder holder, Entry entry) {

        SimpleDraweeView simpleDraweeView = holder.getView(R.id.product_icon_iv);
        TextView nameTv = holder.getView(R.id.name_tv);
        TextView goodsOrignalPriceTv = holder.getView(R.id.goods_orignal_price_tv);
        TextView goodsNoTv = holder.getView(R.id.goods_no_tv);
        TextView priceTotal = holder.getView(R.id.price_total);
        //设置第一个图片
        simpleDraweeView.setImageURI(entry.product.getThumbnail());
        //设置商品名称
        nameTv.setText(entry.product.getProductName());
        goodsOrignalPriceTv.setText(Utils.PriceFormat(entry.getBasePrice()));
        //设置商品数量
        goodsNoTv.setText(entry.getQuantity() + "");
        //合計
        priceTotal.setText(Utils.PriceFormat(entry.getTotalPrice()));

    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_NORMAL;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Entry getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder holder = ViewHolder.get(context, convertView, parent, layoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    public void setData(List<Entry> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }


}