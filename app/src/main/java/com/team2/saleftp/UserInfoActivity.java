package com.team2.saleftp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import dao.UserDAO;
import model.User;

public class UserInfoActivity extends AppCompatActivity {

    EditText edType, edAdr, edUserName, edMail, edName, edPhone;
    Button btnUpdate, btnChangePass, btnLogout;
    ImageView imvAvatar;
    UserDAO userDAO;
    public static User updateUSER = null;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thông tin cá nhân");
        setContentView(R.layout.activity_user_info);

        edType = (EditText) findViewById(R.id.edType);
        edAdr = (EditText) findViewById(R.id.edAdr);
        edUserName = (EditText) findViewById(R.id.edUsername);
        edName = (EditText) findViewById(R.id.edName);
        edMail = (EditText) findViewById(R.id.edMail);
        edPhone = (EditText) findViewById(R.id.edPhone);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnChangePass = findViewById(R.id.btnChangePass);
        btnLogout = findViewById(R.id.btnLogout);
        imvAvatar = (ImageView) findViewById(R.id.imvAvatar);

        userDAO = new UserDAO(this);
        edUserName.setText(LoginActivity.USER.getUsername());
        edName.setText(LoginActivity.USER.getName());
        edMail.setText(LoginActivity.USER.getEmail());
        edAdr.setText(LoginActivity.USER.getAddr());
        edPhone.setText(LoginActivity.USER.getPhone());
        edType.setText("Member");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userDAO.updateUser(edUserName.getText().toString(),
                        edName.getText().toString(),
                        edMail.getText().toString(),
                        edAdr.getText().toString(),
                        edPhone.getText().toString()) > 0) {
                    Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ChangePassActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
//                SharedPreferences.Editor edit = pref.edit();
//                //xoa tinh trang luu tru truoc do
//                edit.clear();
//                edit.apply();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUSER = userDAO.getUserByUsername(LoginActivity.USER.getUsername());
        edUserName.setText(LoginActivity.USER.getUsername());
        edName.setText(updateUSER.getName());
        edMail.setText(updateUSER.getEmail());
        edAdr.setText(updateUSER.getAddr());
        edPhone.setText(updateUSER.getPhone());
        edType.setText("Member");
    }
}
