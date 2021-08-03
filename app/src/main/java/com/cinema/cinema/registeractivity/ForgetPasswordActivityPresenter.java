package com.cinema.cinema.registeractivity;


import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgetPasswordActivityPresenter implements ForgetPasswordActivityPresenterInterface {
  private ForgetPasswordActivityViewInterface view;

  public ForgetPasswordActivityPresenter(ForgetPasswordActivityViewInterface view) {
    this.view = view;
  }

  @Override
    public void forget(String email) {
    FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    view.onForgetSuccess();
                } else {
                    view.onForgetFailed(task.getException().getLocalizedMessage());
                }
              }
            });
  }
}