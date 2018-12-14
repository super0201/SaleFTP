package com.team2.saleftp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dao.UserDAO;
import model.User;

public class ChangePassActivity extends AppCompatActivity {

    EditText edPass,edRePass, edOldPass;
    Button btnUpdate;
    UserDAO userDAO;
    TextView tvUser;
    String strUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Đổi mật khẩu");
        setContentView(R.layout.activity_change_pass);
        edPass = (EditText) findViewById(R.id.edNewPass);
        edRePass = (EditText) findViewById(R.id.edRePass);
        edOldPass = (EditText) findViewById(R.id.edOldPass);
        tvUser = (TextView) findViewById(R.id.tvUser);
        btnUpdate = findViewById(R.id.btnUpdate);

        userDAO = new UserDAO(this);
        strUserName = LoginActivity.USER.getUsername();

        tvUser.setText("Username: " +strUserName);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//    @Override
//    public boolean onSupportNavigateUp(){
//        finish();
//        return true;
//    }

    public int validateForm(){
        int check = 1;
        if (edPass.getText().length()==0 || edRePass.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if(edPass.getText().length()<6 || edPass.getText().length()>16) {
            Toast.makeText(getApplicationContext(), "Mật khẩu phải lớn hơn 6 và nhỏ hơn 16 kí tự", Toast.LENGTH_SHORT).show();
            check = -1;
        } else{
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    public void changePassword() {

        strUserName = LoginActivity.USER.getUsername();
        try {
            if (validateForm() > 0) {
                if (userDAO.checkLoginStat(strUserName, edOldPass.getText().toString()) > 0) {
                    User user = new User(strUserName, edPass.getText().toString());
                    if (userDAO.changePasswordUser(user) > 0) {
                        Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Sai mật khẩu cũ", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
}