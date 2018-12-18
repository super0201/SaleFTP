package com.team2.saleftp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;

import dao.UserDAO;
import model.User;
import session.SessionManager;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText etUser, etPass;
    Button btnLogin, btnSignUp;
    CheckBox ckbRemember;
    UserDAO userDAO;
    SessionManager session;
    public static User USER = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        ckbRemember = (CheckBox) findViewById(R.id.ckbRemember);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        //get access in DAO
        userDAO = new UserDAO(getBaseContext());
        session = new SessionManager(this);

        //button clickListener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }

        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void login(){
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);

        final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "Đang Xác Thực...",
                "Vui Lòng Chờ!", true);
        dialog.show();

//        String mail = etEmail.getText().toString();
//        String pass = etPass.getText().toString();

        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        try {
                            onLoginSuccess();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        // onLoginFailed();
                        dialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onLoginSuccess() throws ParseException {
        String user = etUser.getText().toString().toLowerCase();
        String pass = etPass.getText().toString();

        if(userDAO.checkLoginStat(user, pass) > 0){
//            if(ckbRemember.isChecked()){
                session.createLoginSession(pass, user);
//            }
            btnLogin.setEnabled(false);
            USER = userDAO.getUserByUsername(user);
            Intent intent = new Intent(getBaseContext(), UserInfoActivity.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(), "Đăng Nhập Thành Công!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initial();
    }

    private void initial() {
        session = new SessionManager(getBaseContext());
        if (session.isLoggedIn()){
            etUser.setText(session.getSharedUsername());
            etPass.setText(session.getSharedPass());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnLogin.performClick();
                }
            },0);
        }

    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Username Hoặc Password Không Đúng!", Toast.LENGTH_SHORT).show();
        btnLogin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String user = etUser.getText().toString();
        String password = etPass.getText().toString();
        if (user.isEmpty()) {
            etUser.setError("Username không được để trống!");
            valid = false;
        }
        if (password.isEmpty() || password.length() < 8) {
            etPass.setError("Password Phải Từ 8 Kí Tự Trở Lên!");
            valid = false;
        }
        if (userDAO.checkLoginStat(user, password) < 0) {
            etUser.setError("Username Không Đúng!");
            etPass.setError("Mật khẩu Không Đúng!");
            valid = false;
        }
        else {
            etUser.setError(null);
            etPass.setError(null);
        }
        return valid;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}