package com.sportfest.grundschul.datenbanktest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText txtUserName, txtPassword;
    TextView txtView1;
    Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        txtUserName = (EditText) findViewById(R.id.txtUsername);
        //txtUserName.setText("Nehemia");
        txtPassword = (EditText)   findViewById(R.id.txtPassword);
        //txtPassword.setText("1234");
        txtView1 = (TextView) findViewById(R.id.txtView1);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogIn(txtUserName.getText().toString(), txtPassword.getText().toString());
            }
        });
        SharedPreferences mySPR = getSharedPreferences("MySprFile",0);
        txtUserName.setText(mySPR.getString("username", ""));
        txtPassword.setText(mySPR.getString("password", ""));

    }

    private void validateLogIn (String txtUserName, String txtPassword){
        if((txtUserName.equals("Nehemia")) && (txtPassword.equals("1234"))){
            Intent goToHome = new Intent(getApplicationContext(), Home.class);
            startActivity(goToHome);
            Toast.makeText(getApplicationContext(),"Erfolgreich Eingeloggt",Toast.LENGTH_SHORT).show();
        }
        else{
            txtView1.setText("Username oder Password falsch");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences mySPR = getSharedPreferences("MySprFile",0);
        SharedPreferences.Editor editor = mySPR.edit();
        editor.putString("username",txtUserName.getText().toString());
        editor.putString("password",txtPassword.getText().toString());
        editor.commit();
    }
}
