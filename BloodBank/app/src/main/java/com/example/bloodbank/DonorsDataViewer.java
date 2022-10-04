package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Objects;

public class DonorsDataViewer extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private RecyclerView receiverList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors_data_viewer);

        receiverList = findViewById(R.id.dataList);
        receiverList.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("User DB");

        ArrayList<UserInfo> list = new ArrayList<>();
        MyAdapter adapter = new MyAdapter(getApplicationContext(),list);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    UserInfo data = ds.getValue(UserInfo.class);
                    list.add(data);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        receiverList.setAdapter(adapter);
    }
}