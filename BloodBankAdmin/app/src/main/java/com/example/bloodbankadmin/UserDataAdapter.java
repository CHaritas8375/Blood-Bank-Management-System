package com.example.bloodbankadmin;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserDataViewHolder> {
    Context context;
    ArrayList<UserInfo> userInfoList;

    public UserDataAdapter(Context context, ArrayList<UserInfo> userInfoList) {
        this.context = context;
        this.userInfoList = userInfoList;
    }

    @NonNull
    @Override
    public UserDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_data_viewer,parent,false);
        return new UserDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDataViewHolder holder, int position) {
        UserInfo userInfo = userInfoList.get(position);
        String name = userInfo.getFirstName()+" "+ userInfo.getLastName();
        String contact ="+91 "+userInfo.getContactNumber();
        String email =userInfo.getEmailAddress();
        String dob = userInfo.getDateOfBirth();
        String bloodGroup = userInfo.getBloodGroup();
        holder.tv_name.setText(name);
        holder.tv_email.setText(email);
        holder.tv_contact.setText(contact);
        holder.tv_dob.setText(dob);
        holder.tv_bloodGroup.setText(bloodGroup);
    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }

    public class UserDataViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv_name,tv_contact,tv_dob,tv_email,tv_bloodGroup;
        
        public UserDataViewHolder(@NonNull View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.img);
            tv_name =itemView.findViewById(R.id.text_view_name);
            tv_contact =itemView.findViewById(R.id.text_view_contact);
            tv_email =itemView.findViewById(R.id.text_view_email);
            tv_dob =itemView.findViewById(R.id.text_view_dob);
            tv_bloodGroup =itemView.findViewById(R.id.text_view_blood_group);
        }
    }
}
