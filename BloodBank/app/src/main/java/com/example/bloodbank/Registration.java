package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class Registration extends AppCompatActivity {
    private Spinner spn_gender,spn_bloodGroup;
    private TextInputEditText et_first_name,et_last_name,et_contact_no,et_email,et_password,et_c_password;
    private LinearLayout ll_dob;
    private TextView tv_dob;
    private Button btn_signUp;
   
    private String firstName,lastName,emailId,password,c_password,contact_no,dateOfBirth,gender,bloodGroup;
    
    int mDay,mMonth,mYear;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference userDB,bloodDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /**
         * Binding View Objects with XML Views
         */
        et_first_name = findViewById(R.id.edittext_first_name);
        et_last_name = findViewById(R.id.edittext_last_name);
        et_contact_no =findViewById(R.id.edittext_contact);
        et_email = findViewById(R.id.edittext_email);
        et_password = findViewById(R.id.edittext_password);
        et_c_password = findViewById(R.id.edittext_confirm_password);
        spn_gender = findViewById(R.id.spinner_gender);
        spn_bloodGroup = findViewById(R.id.spinner_blood_type);
        ll_dob = findViewById(R.id.layout_date_of_birth);
        tv_dob = findViewById(R.id.text_view_date_of_birth);
        btn_signUp = findViewById(R.id.btn_sign_up);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userDB = database.getReference("User DB");

        getGender();        /** get Gender function**/
        getBloodGroup();    /** get blood group function**/
        getDateOfBirth();   /** get date of birth function**/
        onSubmit();         /** registration form submit function**/
    }


    /**
     * Date Picker Dialog to get Date Of Birth of user store in a variable at the same time set on xml view
     */
    public void getDateOfBirth(){
        ll_dob.setOnClickListener(v->{
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mMonth = month+1;
                    mDay=dayOfMonth;
                    mYear = year;
                dateOfBirth = mDay+"/"+mMonth+"/"+mYear;
                tv_dob.setTextColor(Color.WHITE);
                tv_dob.setText(dateOfBirth);
                }
            },mYear,mMonth,mDay);
            datePickerDialog.show();
        });
    }

    /**
     * Dropdown Menu to Choose Blood Group Spinner In XML and attaching it with string-array in string.xml file
     */
    public void getBloodGroup(){
        ArrayAdapter<CharSequence> bloodTypeAdapter = ArrayAdapter.createFromResource(this,R.array.blood_types,android.R.layout.simple_spinner_dropdown_item);
        bloodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_bloodGroup.setAdapter(bloodTypeAdapter);

        spn_bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0)
                    bloodGroup = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /**
     * Dropdown Menu to Choose Gender Spinner In XML and attaching it with string-array in string.xml file
     */
    public void getGender(){
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_dropdown_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_gender.setAdapter(genderAdapter);

        spn_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0)
                    gender = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /**
     * get all input from Registration filled by User
     */
    public void getAllInputs(){
        firstName = et_first_name.getText().toString();
        lastName = et_last_name.getText().toString();
        emailId = et_email.getText().toString();
        password = et_password.getText().toString();
        c_password = et_c_password.getText().toString();
        contact_no = et_contact_no.getText().toString();
    }

    @Override
    public String toString() {
        return "Registration{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", c_password='" + c_password + '\'' +
                '}';
    }

    /**
     * On Submit of Registration filled by User
     */
    public void onSubmit(){
        btn_signUp.setOnClickListener(v->{
            getAllInputs();
            registerUSer();
        });
    }


    /**
     * Registration Of user Using Email Method
     */
    public void registerUSer(){
        if(password.length()<9){
            Toast.makeText(getBaseContext(),"Password must have 8 or More Characters....",Toast.LENGTH_SHORT).show();
        }

        if((TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||
                TextUtils.isEmpty(emailId) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(c_password) || TextUtils.isEmpty(gender) ||
                TextUtils.isEmpty(bloodGroup) || TextUtils.isEmpty(contact_no) || TextUtils.isEmpty(dateOfBirth))){
            Toast.makeText(getBaseContext(), "Please Enter All Details "+toString(), Toast.LENGTH_LONG).show();
        }
        else{
            if(!password.equals(c_password) ){
                Toast.makeText(getBaseContext(), "Password and Confirm Password are not Matched...", Toast.LENGTH_SHORT).show();
            }
           /* else if(checkDuplicate(emailId)){
                    Toast.makeText(getBaseContext(),"This Email Address is already Registered",Toast.LENGTH_SHORT).show();
            }*/
            else{
                mAuth.createUserWithEmailAndPassword(emailId,password).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isComplete() ){
                            bloodDB = database.getReference("Blood Bank").child("Blood Group").child(bloodGroup);
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userID = user.getUid();
                            UserInfo userInfo = new UserInfo(userID, firstName, lastName, contact_no, gender, bloodGroup, dateOfBirth, emailId, password);
                            userDB.child(userID+" "+firstName+" "+lastName).setValue(userInfo);
                            database.getReference("User DB").child(userID+" "+firstName+" "+lastName).setValue(userInfo);
                            bloodDB.child(userID+" "+firstName+" "+lastName).setValue(userInfo);
                            Toast.makeText(getBaseContext(), "Successfully Done...", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getBaseContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    private boolean checkDuplicate(String emailId) {
        boolean duplicate = true;
        ArrayList<UserInfo> list = new ArrayList<>();

        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot d : snapshot.getChildren()){
                    UserInfo user = d.getValue(UserInfo.class);
                    list.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        for(int i=0;i<list.size();i++){
            UserInfo u = list.get(i);
            if(emailId.equals(u.getEmailAddress())){
                duplicate = true;
                break;
            }
            else{
                duplicate = false;
            }
        }
        return duplicate;
    }
}