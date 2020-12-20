package com.life.good.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.life.good.R;
import com.life.good.db.Car;
import com.life.good.utils.Utils;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.OrderItemViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Car> mDatas;

    public CarAdapter(Context context, List<Car> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public OrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_car, parent, false);
        return new OrderItemViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(OrderItemViewHolder holder, int position) {

        Car food = mDatas.get(position);
        holder.mIvImage.setImageDrawable(Utils.getDrawable(mContext,food.getGoodimg()));
        holder.mTvName.setText(food.getGoodsname());
        holder.mTvPrice.setText(food.getPrice() + "元/份");
        holder.mTvCount.setText("" + food.getGoodsNum());
        if (food.getChoosed().equals("1")){
            holder.ck_select.setChecked(true);
        }else {
            holder.ck_select.setChecked(false);
        }
    }

    public interface OnFoodListener {
        void onFoodAdd(int food);
        void onFoodSub(int food);
        void onFoodDel(int food);
        void onFoodCheck(int food, boolean ischeck);
    }

    private OnFoodListener mOnFoodListener;

    public void setOnFoodListener(OnFoodListener listener) {
        mOnFoodListener = listener;
    }
    class OrderItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIvImage;
        public TextView mTvName;
        public TextView mTvLabel;
        public TextView mTvPrice;
        public ImageView mIvAdd;
        public ImageView mIvSub;
        public ImageView iv_del;
        public TextView mTvCount;
        public CheckBox ck_select;
        public OrderItemViewHolder(View itemView) {
            super(itemView);
            mIvImage = (ImageView) itemView.findViewById(R.id.id_iv_image);
            mTvName = (TextView) itemView.findViewById(R.id.id_tv_name);
            mTvLabel = (TextView) itemView.findViewById(R.id.id_tv_label);
            mTvPrice = (TextView) itemView.findViewById(R.id.id_tv_price);
            mIvAdd = (ImageView) itemView.findViewById(R.id.id_iv_add);
            mIvSub = (ImageView) itemView.findViewById(R.id.id_iv_sub);
            iv_del = (ImageView) itemView.findViewById(R.id.iv_del);
            mTvCount = (TextView) itemView.findViewById(R.id.id_tv_count);
            ck_select = (CheckBox) itemView.findViewById(R.id.ck_select);
            ck_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (mOnFoodListener != null&&buttonView.isPressed()) {
                        int position = getLayoutPosition();
                        mOnFoodListener.onFoodCheck(position,isChecked);
                    }
                }
            });
            mIvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    // 回调
                    if (mOnFoodListener != null) {
                        mOnFoodListener.onFoodAdd(position);
                    }
                }
            });
            iv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    // 修改数据集
                    if (mOnFoodListener != null) {
                        mOnFoodListener.onFoodDel(position);
                    }
                }
            });
            mIvSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    if (mOnFoodListener != null) {
                        mOnFoodListener.onFoodSub(position);
                    }
                }
            });
        }


    }

}
