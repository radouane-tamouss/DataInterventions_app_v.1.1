package com.haerul.interventions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements Filterable {

    List<Interventions> interventions, interventionsFilter;
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter filter;

    public Adapter(List<Interventions> interventions, Context context, RecyclerViewClickListener listener) {
        this.interventions = interventions;
        this.interventionsFilter = interventions;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.mRequest_info.setText(interventions.get(position).getRequest_info());
        holder.mType.setText(interventions.get(position).getRequester_name()  + " / "
                + interventions.get(position).getMobile());
        holder.mDate.setText(interventions.get(position).getAssign_date());
        holder.mRequester_add1.setText(interventions.get(position).getRequester_add1());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);



        final Boolean love = interventions.get(position).getLove();

        if (love){
            holder.mLove.setImageResource(R.drawable.likeon);
        } else {
            holder.mLove.setImageResource(R.drawable.likeof);
        }

    }

    @Override
    public int getItemCount() {
        return interventions.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilter((ArrayList<Interventions>) interventionsFilter,this);

        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;

        private ImageView mLove;
        private TextView mRequest_info,mRequest_desc,mRequest_id, mType, mDate,mMobile,mRequester_city,mRequester_add1,mRequester_zip,mRequester_email;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mMobile = itemView.findViewById(R.id.mobile);
            mRequest_id = itemView.findViewById(R.id.request_id);
            mRequest_info = itemView.findViewById(R.id.request_info);
            mRequest_desc = itemView.findViewById(R.id.request_desc);
            mType = itemView.findViewById(R.id.type);
            mLove = itemView.findViewById(R.id.love);
            mDate = itemView.findViewById(R.id.date);
            mRequester_city = itemView.findViewById(R.id.requester_city);
            mRequester_add1 = itemView.findViewById(R.id.requester_add1);
            mRequester_zip = itemView.findViewById(R.id.requester_zip);
            mRequester_email = itemView.findViewById(R.id.requester_email);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
            mLove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                case R.id.love:
                    mListener.onLoveClick(mLove, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
        void onLoveClick(View view, int position);
    }

}
