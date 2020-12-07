package com.example.aplicacaojavatopi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.aplicacaojavatopi.R;
import com.example.aplicacaojavatopi.models.Items;
import com.example.aplicacaojavatopi.models.Owner;

import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.MyViewHolder> {

    private Context context;
    private List<Items> itemsList;
    private Owner owner;

    public AdapterItems(Context context, List<Items> itemsList){
        this.context = context;
        this.itemsList = itemsList;
    }

    public void setItemsList(List<Items> itemsList){
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterItems.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItems.MyViewHolder holder, int position) {
        holder.descRepositorie.setText(this.itemsList.get(position).getNode_id());
        holder.fullNameUser.setText(this.itemsList.get(position).getFull_name());
        holder.nameUser.setText(this.itemsList.get(position).getName());
        holder.nameRepositorie.setText(this.itemsList.get(position).getName());
        Glide.with(context).load(this.itemsList.get(position).getOwner().getAvatar_url()).apply(RequestOptions.centerCropTransform()).into(holder.photoUser);
        holder.numbLiked.setText(this.itemsList.get(position).getStargazers_count());
        holder.numbShared.setText(this.itemsList.get(position).getWatchers_count());
    }

    @Override
    public int getItemCount() {
        if (this.itemsList != null){
            return itemsList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameRepositorie;
        TextView descRepositorie;
        TextView numbShared;
        TextView numbLiked;
        ImageView photoUser;
        TextView nameUser;
        TextView fullNameUser;

        public MyViewHolder(View itemView){
            super(itemView);
            nameRepositorie = itemView.findViewById(R.id.lblRepositorie);
            descRepositorie = itemView.findViewById(R.id.lblDescription);
            numbShared = itemView.findViewById(R.id.lblNShared);
            numbLiked = itemView.findViewById(R.id.lblNLiked);
            photoUser = itemView.findViewById(R.id.imgPerfilUser);
            nameUser = itemView.findViewById(R.id.lblNameUser);
            fullNameUser = itemView.findViewById(R.id.lblFullNameUser);
        }
    }
}
