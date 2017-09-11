package com.example.joelin.atm;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText text_userID;
    EditText text_pass;
    Button btn_login;
    Button btn_cancel;

    private void login(){
        String uid = text_userID.getText().toString();
        String pw = text_pass.getText().toString();
        if (uid.equals("jack") && pw.equals("1234")){ //登入成功
            SharedPreferences setting = getSharedPreferences("atm", MODE_PRIVATE);
            setting.edit().putString("PREF_USERID", uid).commit();
            Toast.makeText(this, getString(R.string.msg_login_success), Toast.LENGTH_LONG).show();
            finish();
        }else{ //登入失敗
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.app_name))
                    .setMessage(getString(R.string.msg_login_failed))
                    .setPositiveButton("OK", null)
                    .show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        text_userID = (EditText) findViewById(R.id.text_userID);
        text_pass = (EditText) findViewById(R.id.text_pass);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        btn_login.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        SharedPreferences setting =
                getSharedPreferences("atm", MODE_PRIVATE);
        text_userID.setText(setting.getString("PREF_USERID", ""));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_cancel:
                break;
        }
    }
}
