package com.example.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.BuildingDataViewHolder> {
    Context context;
    ArrayList<BuildingData> buildingData;
    int res;

    public BuildingAdapter(Context context, ArrayList<BuildingData> buildingData, int res) {
        this.context = context;
        this.buildingData = buildingData;
        this.res = res;
    }

    @NonNull
    @Override
    public BuildingDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.building_data_list,parent,false);
        return new BuildingDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuildingDataViewHolder holder, int position) {
        BuildingData data = buildingData.get(position);
        String name = data.getName();
        String contact = data.getContact();
        String address = data.getAddress();

        holder.img.setImageResource(res);
        holder.tv_name.setText(name);
        holder.tv_contact.setText(contact);
        holder.tv_address.setText(address);
    }

    @Override
    public int getItemCount() {
        return buildingData.size();
    }

    public class BuildingDataViewHolder extends RecyclerView.ViewHolder{
        ImageView img,img_call;
        TextView tv_name;
        TextView tv_contact;
        TextView tv_address;
        public BuildingDataViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            img_call = itemView.findViewById(R.id.img_call);
            tv_name = itemView.findViewById(R.id.text_view_name);
            tv_contact = itemView.findViewById(R.id.text_view_contact);
            tv_address = itemView.findViewById(R.id.text_view_address);

            img_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+tv_contact.getText().toString()));

                    context.startActivity(intent);
                }
            });
        }
    }
}
