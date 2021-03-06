package com.shenme.androiddemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.data.Welfare;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CANC on 2016/10/18.
 */

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Welfare> datas;
    private LayoutInflater inflater;
    private ImageDetailListener imageDetailListener;

    public DataAdapter(Context context, ImageDetailListener listener) {
        this(context, null, listener);

    }

    public DataAdapter(Context context, List<Welfare> data, ImageDetailListener listener) {
        this.mContext = context;
        this.datas = data;
        this.imageDetailListener = listener;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<Welfare> data, Context context) {
        this.datas = data;
        this.mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_welfare, parent, false);
        RecyclerView.ViewHolder viewHolder = new WelfareHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((WelfareHolder) holder).simpleDraweeView.setImageURI(datas.get(position).getUrl());
        ((WelfareHolder) holder).simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageDetailListener != null) {
                    imageDetailListener.ImageDetail(((WelfareHolder) holder).simpleDraweeView, datas.get(position).getUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class WelfareHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        SimpleDraweeView simpleDraweeView;

        public WelfareHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ImageDetailListener {
        void ImageDetail(View view, String imageUrl);
    }

}
