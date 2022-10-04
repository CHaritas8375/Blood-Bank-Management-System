package com.example.bloodbankadmin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddHospitals extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private TextInputEditText et_hName;
    private TextInputEditText et_hAddress;
    private TextInputEditText et_hContact;
    private TextInputEditText et_hEmail;
    private Button btn_Add;

    private String hKey;
    private String hName;
    private String hAddress;
    private String hContact;
    private String hEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospitals);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Blood Bank").child("Hospital Details");

        et_hName = findViewById(R.id.edit_text_hospital_name);
        et_hAddress = findViewById(R.id.edit_text_hospital_address);
        et_hEmail = findViewById(R.id.edit_text_hospital_email);
        et_hContact = findViewById(R.id.edit_text_hospital_contact);
        btn_Add = findViewById(R.id.btn_add);

        onclickOnAdd();
    }

    public void getInputs(){
        hKey = reference.push().getKey();
        hName = et_hName.getText().toString();
        hContact = et_hContact.getText().toString();
        hEmail = et_hEmail.getText().toString();
        hAddress = et_hAddress.getText().toString();
    }

    public void onclickOnAdd() {
        btn_Add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getInputs();
                if(TextUtils.isEmpty(hName) || TextUtils.isEmpty(hContact) || TextUtils.isEmpty(hAddress) || TextUtils.isEmpty(hEmail) || TextUtils.isEmpty(hKey)){
                    Toast.makeText(getBaseContext(),"Please Enter All Data...",Toast.LENGTH_SHORT ).show();
                }
                else {
                    BuildingData data = new BuildingData(hKey,hName,hContact,hEmail,hAddress);
                    reference.child("hos" + hKey).setValue(data);

                    Toast.makeText(getBaseContext(),"Added Successful...",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddHospitals.this,BloodBankDataViewer.class));
                }
            }
        });
    }
}