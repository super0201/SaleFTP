package com.team2.saleftp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dao.UserDAO;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    UserDAO userDAO;

    EditText _nameText, _addressText, _emailText, _userText;
    EditText _mobileText, _passwordText, _reEnterPasswordText;
    Button _signupButton, _reLoginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userDAO = new UserDAO(this);

        _nameText = (EditText)findViewById(R.id.input_name);
        _addressText = (EditText)findViewById(R.id.input_address);
        _emailText = (EditText)findViewById(R.id.input_email);
        _mobileText = (EditText)findViewById(R.id.input_mobile);
        _userText = (EditText)findViewById(R.id.input_user);
        _passwordText = (EditText)findViewById(R.id.input_password);
        _reEnterPasswordText = (EditText)findViewById(R.id.input_reEnterPassword);

        _signupButton = (Button)findViewById(R.id.btn_signup);

        _reLoginButton = (Button)findViewById(R.id.btnReLogin);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _reLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }


    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog dialog = ProgressDialog.show(SignupActivity.this, "Đang Tạo Account!",
                "Vui Lòng Chờ...", true);
        dialog.show();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        dialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String user = _userText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        userDAO.insertUser(user, reEnterPassword, name, email, address, mobile);

        Toast.makeText(getBaseContext(), "Đăng Kí Thành Công!", Toast.LENGTH_SHORT).show();
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);

    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Đăng Ký Thất Bại!", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String phone = _mobileText.getText().toString();
        String user = _userText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("Tên Phải Trên 3 Kí Tự!");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (address.isEmpty()) {
            _addressText.setError("Địa Chỉ Không Được Để Trống!");
            valid = false;
        } else {
            _addressText.setError(null);
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _emailText.setError("Email Không Hợp Lệ!");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if(userDAO.checkUser(user) > 0){
            _emailText.setError("Đã Có Người Sử Dụng Email Này!");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (email.isEmpty()) {
            _emailText.setError("Email Không Được Để Trống!");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 10) {
            _mobileText.setError("Không Được Để Trống Và Phải Trên 9 Kí Tự!");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if(userDAO.checkUser(user) > 0){
            _mobileText.setError("SĐT Này Đã Được Sử Dụng!");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Password Phải Từ 4 Hoặc 8 Kí Tự");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Không Giống!");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }
        return valid;
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
    }
}