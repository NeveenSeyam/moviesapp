package com.cinema.cinema.registeractivity;

public interface RegisterActivityViewInterface {
  void onCreateSuccess(String email, String name, String phone);

  void onCreateFailed(String message);
}
