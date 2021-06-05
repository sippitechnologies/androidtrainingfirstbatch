package com.sippitechnologes.myapplication.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sippitechnologes.myapplication.R;
import com.sippitechnologes.myapplication.data.local.Icon;
import com.sippitechnologes.myapplication.data.local.Manifesto;
import com.sippitechnologes.myapplication.listener.OnRecyclerViewItemClick;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitViewHolder> {

    private List<Icon> iconList;
    private OnRecyclerViewItemClick onRecyclerViewItemClick;
    public FruitAdapter(List<Icon>iconList)
    {
        this.iconList = iconList;

    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruit,parent,false);
        return new FruitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder holder, int position) {

        holder.itemView.setTag(position);
        if(onRecyclerViewItemClick!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index= (Integer)v.getTag();
                            Icon selectedIcon= iconList.get(index);
                            onRecyclerViewItemClick.onItemClickListener(selectedIcon,index);
                }
            });

        }
        Icon icon = iconList.get(position);
        holder.onBind(icon);

    }

    @Override
    public int getItemCount() {
        return iconList.size();
    }

    public static class FruitViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView fruitIcon;
        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.txt_fruit_name);
            fruitIcon = itemView.findViewById(R.id.img_fruit);
        }

        public void onBind(Icon icon)
        {
            title.setText(icon.getName());
            fruitIcon.setImageResource(icon.getIcon());
        }


    }
}
