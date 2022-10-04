package com.example.bloodbankadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class BloodQuantity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;

    private TextView tv_ap;
    private TextView tv_an;
    private TextView tv_bp;
    private TextView tv_bn;
    private TextView tv_abp;
    private TextView tv_abn;
    private TextView tv_op;
    private TextView tv_on;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_quantity);

        tv_abn=findViewById(R.id.text_view_ab_negative);
        tv_abp=findViewById(R.id.text_view_ab_positive);
        tv_an=findViewById(R.id.text_view_a_negative);
        tv_ap=findViewById(R.id.text_view_a_positive);
        tv_bn=findViewById(R.id.text_view_b_negative);
        tv_bp=findViewById(R.id.text_view_b_positive);
        tv_on=findViewById(R.id.text_view_o_negative);
        tv_op=findViewById(R.id.text_view_o_positive);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Blood Bank").child("Blood Group");

        ArrayList<UserInfo> bloodType = new ArrayList<>();

            reference.child("A+").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    bloodType.clear();
                    for (DataSnapshot data : snapshot.getChildren()){
                        UserInfo user = data.getValue(UserInfo.class);
                        bloodType.add(user);
                    }
                    tv_ap.setText(""+bloodType.size()+" Units");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        reference.child("A-").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bloodType.clear();
                for (DataSnapshot data : snapshot.getChildren()){
                    UserInfo user = data.getValue(UserInfo.class);
                    bloodType.add(user);
                }
                tv_an.setText(""+bloodType.size()+" Units");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child("B+").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bloodType.clear();
                for (DataSnapshot data : snapshot.getChildren()){
                    UserInfo user = data.getValue(UserInfo.class);
                    bloodType.add(user);
                }
                tv_bp.setText(""+bloodType.size()+" Units");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child("B-").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bloodType.clear();
                for (DataSnapshot data : snapshot.getChildren()){
                    UserInfo user = data.getValue(UserInfo.class);
                    bloodType.add(user);
                }
                tv_bn.setText(""+bloodType.size()+" Units");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child("O+").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bloodType.clear();
                for (DataSnapshot data : snapshot.getChildren()){
                    UserInfo user = data.getValue(UserInfo.class);
                    bloodType.add(user);
                }
                tv_op.setText(""+bloodType.size()+" Units");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child("O-").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bloodType.clear();
                for (DataSnapshot data : snapshot.getChildren()){
                    UserInfo user = data.getValue(UserInfo.class);
                    bloodType.add(user);
                }
                tv_on.setText(""+bloodType.size()+" Units");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child("AB+").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bloodType.clear();
                for (DataSnapshot data : snapshot.getChildren()){
                    UserInfo user = data.getValue(UserInfo.class);
                    bloodType.add(user);
                }
                tv_abp.setText(""+bloodType.size()+" Units");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child("AB-").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bloodType.clear();
                for (DataSnapshot data : snapshot.getChildren()){
                    UserInfo user = data.getValue(UserInfo.class);
                    bloodType.add(user);
                }
                tv_abn.setText(""+bloodType.size()+" Units");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}