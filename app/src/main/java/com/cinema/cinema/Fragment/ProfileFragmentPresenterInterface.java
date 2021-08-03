package com.cinema.cinema.Fragment;

import android.net.Uri;
import java.io.File;
import java.io.IOException;

public interface ProfileFragmentPresenterInterface {
  File createImageFile() throws IOException;

  void uploadImage(Uri imageFile);

  void getUserData();
}
