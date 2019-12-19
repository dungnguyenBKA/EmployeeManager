package com.example.qunlnhns.activities.loginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qunlnhns.activities.mainAcitivity.MainActivity;
import com.example.qunlnhns.R;
import com.example.qunlnhns.db.DbUsers;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    DbUsers dbUsers;
    EditText edUsername, edPassword;

    private String USERNAME, PASSWORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        // create username
        dbUsers = new DbUsers(this);
        dbUsers.createDatabase();

        // Share Preference, if true (logged) then go to main screen
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        boolean logged = sp.getBoolean("log", false);
        if(logged) gotoMainScreen();



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edPassword.getText().toString().trim().isEmpty() || edUsername.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "TK & MK là admin", Toast.LENGTH_SHORT).show();
                    return;
                }

                DbUsers dbUsers = new DbUsers(getApplicationContext());

                USERNAME = edUsername.getText().toString().trim();
                PASSWORD = edPassword.getText().toString().trim();

                boolean check = dbUsers.checkLoginSuccess(USERNAME, PASSWORD);

                if(check){
                    SharedPreferences sp = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();

                    editor.putBoolean("log", true);
                    editor.apply();

                    gotoMainScreen();

                    return;
                }

                Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        btnLogin = findViewById(R.id.btn);
        edPassword = findViewById(R.id.password);
        edUsername = findViewById(R.id.username);
    }

    void gotoMainScreen(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
