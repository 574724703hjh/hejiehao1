package com.life.good.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.life.good.R;
import com.life.good.bean.FOrder;

import java.util.List;
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    public Context context;
    public List<FOrder> shopList;
    public HistoryAdapter(Context context, List<FOrder> shopList) {
        this.context = context;
        this.shopList = shopList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        FOrder FOrder = shopList.get(position);
        holder.tv_content.setText(FOrder.getContent());
        holder.tv_time.setText("联系方式:"+ FOrder.getPhone());
        holder.tv_price.setText("总金额:"+ FOrder.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(view,position);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_shopname ;
        private TextView tv_content ;
        private TextView tv_time ;
        private TextView tv_price ;

        public ViewHolder(View view) {
            super(view);
            tv_content = (TextView) view.findViewById(R.id.tv_content);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
        }
    }
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
