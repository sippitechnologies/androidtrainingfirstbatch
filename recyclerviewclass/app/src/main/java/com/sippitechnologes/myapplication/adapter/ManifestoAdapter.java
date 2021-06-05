package com.sippitechnologes.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sippitechnologes.myapplication.R;
import com.sippitechnologes.myapplication.data.local.Icon;
import com.sippitechnologes.myapplication.data.local.Manifesto;
import com.sippitechnologes.myapplication.listener.OnRecyclerViewItemClick;

import java.util.List;

public class ManifestoAdapter  extends RecyclerView.Adapter<ManifestoAdapter.ManifestoViewHolder> {

    List<Manifesto> manifestoList;
    private OnRecyclerViewItemClick onRecyclerViewItemClick;
    public ManifestoAdapter(List<Manifesto>manifestoList)
    {
        this.manifestoList = manifestoList;

    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }
    @NonNull
    @Override
    public ManifestoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manifesto_layout,parent,false);
        return new ManifestoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManifestoViewHolder holder, int position) {

        Manifesto manifesto = manifestoList.get(position);
        holder.onBind(manifesto);
        holder.itemView.setTag(position);
        if(onRecyclerViewItemClick!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index= (Integer)v.getTag();
                    Manifesto selectedIcon= manifestoList.get(index);
                    onRecyclerViewItemClick.onItemClickListener(selectedIcon,index);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return manifestoList.size();
    }

    public static class ManifestoViewHolder extends RecyclerView.ViewHolder {
        TextView title,description;
        public ManifestoViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.txtTitle);
            description = itemView.findViewById(R.id.txtDescription);
        }

        public void onBind(Manifesto manifesto)
        {
            title.setText(manifesto.getTitle());
            description.setText(manifesto.getDescription());
        }
    }
}
