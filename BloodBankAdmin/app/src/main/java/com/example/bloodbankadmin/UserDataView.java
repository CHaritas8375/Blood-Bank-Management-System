package com.example.bloodbankadmin;

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

public class UserDataView extends AppCompatActivity {
    private RecyclerView recyclerView;
    DatabaseReference reference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_view);

        recyclerView = findViewById(R.id.recycler_view_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("User DB");

        ArrayList<UserInfo> userInfo = new ArrayList<>();
        UserDataAdapter adapter = new UserDataAdapter(getApplicationContext(),userInfo);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot d:snapshot.getChildren()){
                    UserInfo user =d.getValue(UserInfo.class);
                    userInfo.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.w("UserDataView.this", "Failed to read value.", error.toException());
            }
        });
        recyclerView.setAdapter(adapter);
    }
}