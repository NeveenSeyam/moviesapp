package com.cinema.cinema.registeractivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cinema.cinema.MainActivity;
import com.cinema.cinema.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements LoginActivityViewInterface {

  private LoginActivityPresenter presenter;

  private EditText emailInput;
  private EditText inputPassword;
  private Button btnLogin;
  private TextView creatAccountInLogin;
  private TextView linkForgotpassword;
  private ImageView backBtn;
  private FirebaseAuth fireAuth;
  private String email;
  private String password;
  boolean doubleBackToExitPressedOnce = false;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    presenter = new LoginActivityPresenter(this);


    // firebase instance
    fireAuth = FirebaseAuth.getInstance();
    //inputs from user
    emailInput = findViewById(R.id.input_email);
    inputPassword = findViewById(R.id.input_password);
    // button
    btnLogin = findViewById(R.id.btn_login);
    creatAccountInLogin = findViewById(R.id.Creat_Account_in_login);
    backBtn = findViewById(R.id.backbtn);
    linkForgotpassword = findViewById(R.id.link_forgotpassword);

    if (fireAuth.getCurrentUser() != null) {
      startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    btnLogin.setOnClickListener(v -> {
      email = emailInput.getText().toString().trim();
      password = inputPassword.getText().toString();

      if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
        Toast.makeText(LoginActivity.this,
                        "One of the fields is missing", Toast.LENGTH_SHORT).show();
      } else if (password.length() < 6) {
        Toast.makeText(LoginActivity.this,
                        "Password can't be less than 6 characters", Toast.LENGTH_SHORT).show();
      } else {
        presenter.login(email, password);
      }
    });

    creatAccountInLogin.setOnClickListener(v -> {
      startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    });

    backBtn.setOnClickListener(v -> onBackPressed());

    linkForgotpassword.setOnClickListener(v -> {
      startActivity(new Intent(getApplicationContext(), ForgetPasswordActivity.class));

    });

  }

  @Override
    public void onLoginSuccess() {
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
    finish();
  }

  @Override
    public void onLoginFailed(String message) {
    Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
  }

  @Override
    public void onBackPressed() {
    if (doubleBackToExitPressedOnce) {
      this.finishAffinity();
      System.exit(0);
      return;
    }

    this.doubleBackToExitPressedOnce = true;
    Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

    new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
  }

}