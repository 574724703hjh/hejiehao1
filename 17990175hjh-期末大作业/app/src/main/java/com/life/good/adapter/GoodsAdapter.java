package com.life.good.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.life.good.R;
import com.life.good.bean.Good;
import com.life.good.utils.Utils;

import java.util.ArrayList;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Good> merchantDatalist;
    public GoodsAdapter(Context context, ArrayList<Good> merchantDatalist) {
        this.context = context;
        this.merchantDatalist = merchantDatalist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_goods, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Good item = merchantDatalist.get(position);
        holder.tvName.setText(item.getName());
        holder.tv_des.setText(item.getDes());
        holder.tv_price.setText("价格:"+item.getPrice());
        if (position == merchantDatalist.size() - 1) {
            holder.vLine.setVisibility(View.GONE);
        } else {
            holder.vLine.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.setOnItemClickListener(position);
                }
            }
        });
      holder.ivPic.setImageDrawable(Utils.getDrawable(context,item.getImg()));
    }


    @Override
    public int getItemCount() {
        return merchantDatalist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_des;
        TextView tv_price;
        TextView tvName;
        ImageView ivPic;
        View vLine;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tv_des = itemView.findViewById(R.id.tv_des);
            tv_price = itemView.findViewById(R.id.tv_price);
            ivPic = itemView.findViewById(R.id.iv_pic);
            vLine = itemView.findViewById(R.id.v_line);
        }
    }

    private ItemClickListener listener;

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public interface ItemClickListener {
        void setOnItemClickListener(int position);
    }
}
