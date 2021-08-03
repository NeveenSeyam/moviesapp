package com.cinema.cinema.registeractivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cinema.cinema.R;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends
        AppCompatActivity implements ForgetPasswordActivityViewInterface {
  ForgetPasswordActivityPresenter presenter;
  EditText inputEmail;
  TextView linkLogin;
  Button btnResetPassword;
  String email;
  ImageView backbtn;
  FirebaseAuth auth = FirebaseAuth.getInstance();

  @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forget_password);

    presenter = new ForgetPasswordActivityPresenter(this);

    inputEmail = findViewById(R.id.email);

    btnResetPassword = findViewById(R.id.btn_reset_password);
    btnResetPassword.setOnClickListener(v -> {
      email = inputEmail.getText().toString().trim();
      presenter.forget(email);
    });


    backbtn = findViewById(R.id.backbtn);
    backbtn.setOnClickListener(v -> onBackPressed());

    linkLogin = findViewById(R.id.link_login);
    linkLogin.setOnClickListener(v -> onBackPressed());
  }

  @Override
    public void onForgetSuccess() {
    Toast.makeText(getApplicationContext(), "Reset email sent successfully",
                Toast.LENGTH_SHORT).show();
  }

  @Override
    public void onForgetFailed(String message) {
    Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
  }
}