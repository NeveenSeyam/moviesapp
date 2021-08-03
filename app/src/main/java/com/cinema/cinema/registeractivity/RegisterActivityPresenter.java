package com.cinema.cinema.registeractivity;


import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivityPresenter implements RegisterActivityPresenterInterface {
  private RegisterActivityViewInterface view;

  public RegisterActivityPresenter(RegisterActivityViewInterface view) {
    this.view = view;
  }

  @Override
    public void createAccount(String email, String password, String name, String phone) {
    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                      view.onCreateSuccess(email, name, phone);
                    } else {
                      view.onCreateFailed(task.getException().getLocalizedMessage());
                    }
                    }
                    });
  }
}