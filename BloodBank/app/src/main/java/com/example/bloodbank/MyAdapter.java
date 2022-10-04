package com.example.bloodbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserInfo> userList;

    public MyAdapter(Context context,ArrayList<UserInfo> userList){
        this.context=context;
        this.userList=userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.users_data_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserInfo userData = userList.get(position);
        String fullName = userData.getFirstName()+" "+userData.getLastName();
        String contact = "+91 - "+userData.getContactNumber();
        String bloodGroup = userData.getBloodGroup();
        String emailid = userData.getEmailAddress();

        holder.name.setText(fullName);
        holder.contact.setText(contact);
        holder.img.setImageResource(bloodGroupImage(bloodGroup));
        holder.email.setText(emailid);
    }

    public int bloodGroupImage(String bloodGroup){
        if(bloodGroup.equals("A+"))
            return R.drawable.a_positive;
        else if(bloodGroup.equals("A-"))
            return R.drawable.a_negative;
        else if(bloodGroup.equals("B+"))
            return R.drawable.b_positive;
        else if(bloodGroup.equals("B-"))
            return R.drawable.b_negative;
        else if(bloodGroup.equals("AB+"))
            return R.drawable.ab_positive;
        else if(bloodGroup.equals("AB-"))
            return R.drawable.ab_negative;
        else if(bloodGroup.equals("O-"))
            return R.drawable.o_positive;
        else
            return R.drawable.o_negative;
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,contact,email;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_blood_type);
            name = itemView.findViewById(R.id.text_view_full_name);
            contact = itemView.findViewById(R.id.text_view_contact);
            email = itemView.findViewById(R.id.text_view_email);
        }
    }
}
