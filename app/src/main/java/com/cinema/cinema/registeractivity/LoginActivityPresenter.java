package com.cinema.cinema.registeractivity;


import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivityPresenter implements LoginActivityPresenterInterface {
  private LoginActivityViewInterface view;

  public LoginActivityPresenter(LoginActivityViewInterface view) {
    this.view = view;
  }

  @Override
    public void login(String email, String password) {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, log in
                        view.onLoginSuccess();
                    } else {
                      // If sign in fails, display a message to the user.
                      view.onLoginFailed(task.getException().getLocalizedMessage());
                    }
                    }
                    });
  }
}