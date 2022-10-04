package com.example.bloodbankadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class HospitalDataViewer extends AppCompatActivity {
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private Button btn_add;
    private Button btn_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_data_viewer);

        reference = FirebaseDatabase.getInstance().getReference().child("Blood Bank").child("Hospital Details");

        recyclerView = findViewById(R.id.recycler_view_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn_add = findViewById(R.id.btn_add);
        btn_remove = findViewById(R.id.btn_delete);

        ArrayList<BuildingData> list = new ArrayList<>();
        BuildingDataAdapter adapter = new BuildingDataAdapter(getApplicationContext(),list, R.drawable.ic_baseline_hospital);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data:snapshot.getChildren()){
                    BuildingData bloodBankData = data.getValue(BuildingData.class);
                    list.add(bloodBankData);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        recyclerView.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HospitalDataViewer.this,AddHospitals.class));
            }
        });
    }
}