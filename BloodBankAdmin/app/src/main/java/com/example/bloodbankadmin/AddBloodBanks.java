package com.example.bloodbankadmin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBloodBanks extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private TextInputEditText et_bbName;
    private TextInputEditText et_bbAddress;
    private TextInputEditText et_bbContact;
    private TextInputEditText et_bbEmail;
    private Button btn_Add;
    private String bbKey;
    private String bbName;
    private String bbAddress;
    private String bbContact;
    private String bbEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blood_banks);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Blood Bank").child("Blood Bank Details");

        et_bbName = findViewById(R.id.edit_text_blood_bank_name);
        et_bbContact = findViewById(R.id.edit_text_blood_bank_contact);
        et_bbEmail = findViewById(R.id.edit_text_blood_bank_email);
        et_bbAddress = findViewById(R.id.edit_text_blood_bank_address);
        btn_Add =findViewById(R.id.btn_add);

        onclickOnAdd();
    }

    public void getInputs(){
        bbKey = reference.push().getKey();
        bbName = et_bbName.getText().toString();
        bbContact = et_bbContact.getText().toString();
        bbEmail = et_bbEmail.getText().toString();
        bbAddress = et_bbAddress.getText().toString();
    }

    public void onclickOnAdd() {
        btn_Add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getInputs();

                if(TextUtils.isEmpty(bbName) || TextUtils.isEmpty(bbContact) || TextUtils.isEmpty(bbAddress) || TextUtils.isEmpty(bbEmail) || TextUtils.isEmpty(bbKey)){
                    Toast.makeText(getBaseContext(),"Please Enter All Data...",Toast.LENGTH_SHORT ).show();
                }
                else {
                    BuildingData data = new BuildingData(bbKey,bbName,bbContact,bbEmail,bbAddress);
                    reference.child("bb" + bbKey).setValue(data);

                    Toast.makeText(getBaseContext(),"Added Successful...",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddBloodBanks.this,BloodBankDataViewer.class));
                }
            }
        });
    }
}