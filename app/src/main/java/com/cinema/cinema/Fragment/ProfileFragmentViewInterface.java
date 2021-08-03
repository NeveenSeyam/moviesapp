package com.cinema.cinema.Fragment;

import com.cinema.cinema.Model.User;

public interface ProfileFragmentViewInterface {

  void onUploadSuccess(String url);

  void onUploadFailed(String message);

  void onGetProfileSuccess(User user);

  void onProfileDataFailed(String message);

}
