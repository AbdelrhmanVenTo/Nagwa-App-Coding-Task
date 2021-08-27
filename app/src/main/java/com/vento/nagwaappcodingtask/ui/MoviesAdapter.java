package com.vento.nagwaappcodingtask.ui;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import androidx.databinding.DataBindingUtil;

import com.vento.nagwaappcodingtask.R;
import com.vento.nagwaappcodingtask.databinding.ItemMoviesListBinding;
import com.vento.nagwaappcodingtask.ui.model.VideoModelResponseItem;
import com.vento.nagwaappcodingtask.utils.Constants;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    List<VideoModelResponseItem> itemList;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public MoviesAdapter(List<VideoModelResponseItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMoviesListBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_movies_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        VideoModelResponseItem responseItem = itemList.get(position);
        viewHolder.binding.tvItemText.setText(responseItem.getName());
        if (responseItem.getType().equals("VIDEO")){
            viewHolder.binding.ivItemIcon.setImageResource(R.drawable.ic_mp4_icon);
        }else {
            viewHolder.binding.ivItemIcon.setImageResource(R.drawable.ic_pdf_icon);

        }

        if (onItemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(position,responseItem);
                    viewHolder.binding.progress.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            viewHolder.binding.progress.setVisibility(View.GONE);
                            viewHolder.binding.ivCheckRight.setVisibility(View.VISIBLE);

                        }
                    }, Constants.DISPLAY_TIME);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (itemList ==null)
        return 0;
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemMoviesListBinding binding;

        public ViewHolder(@NonNull ItemMoviesListBinding bindingView) {
            super(bindingView.getRoot());
            this.binding = bindingView;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos , VideoModelResponseItem responseItem);
    }


}
