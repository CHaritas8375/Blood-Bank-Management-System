package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileView extends AppCompatActivity {

    DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    private TextInputEditText et_fullName,et_email,et_contact;
    private TextView tv_profileFullName;
    private Button btn_editProfile,btn_signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        btn_editProfile= findViewById(R.id.btn_edit_profile);
        btn_signOut = findViewById(R.id.btn_sign_out);
        tv_profileFullName = findViewById(R.id.text_view_full_name);
        et_fullName = findViewById(R.id.edit_text_full_name);
        et_email = findViewById(R.id.edit_text_email_id);
        et_contact = findViewById(R.id.edit_text_contact_no);

        reference = FirebaseDatabase.getInstance().getReference().child("User DB");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String user = mUser.getUid();
                tv_profileFullName.setText(snapshot.child(user).child("firstName").getValue() +" "+String.valueOf(snapshot.child(user).child("lastName").getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        /**
         * Click on Edit profile button
         **/
        btn_editProfile.setOnClickListener(v->{
            checkStatus();
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void checkStatus(){
        if(et_fullName.isEnabled() && et_contact.isEnabled() && et_email.isEnabled()){
            unableEdit();
        }
        else{
            enableEdit();
        }
    }


    /**
     * Enable edit option in profile
     */
    public void enableEdit(){
        et_email.setEnabled(true);
        et_fullName.setEnabled(true);
        et_contact.setEnabled(true);
        btn_editProfile.setText("Save Profile");
    }

    /**
     * Disable edit option in profile and save id to database for future references
     */
    public void unableEdit(){
        et_email.setEnabled(false);
        et_fullName.setEnabled(false);
        et_contact.setEnabled(false);
        btn_editProfile.setText("Edit Profile");
    }
}