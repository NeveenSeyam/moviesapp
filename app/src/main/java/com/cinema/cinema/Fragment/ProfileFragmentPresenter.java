package com.cinema.cinema.Fragment;



import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.cinema.cinema.Model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class ProfileFragmentPresenter implements ProfileFragmentPresenterInterface {
  private ProfileFragmentViewInterface view;

  public ProfileFragmentPresenter(ProfileFragmentViewInterface view) {
    this.view = view;
  }

  @Override
  public File createImageFile() throws IOException {
    String timeStamp =
            new SimpleDateFormat("yyyyMMdd_HHmmss",
                    Locale.getDefault()).format(new Date());
    String imageFileName = "IMG_" + timeStamp + "_";
    File externalPath = Environment.getExternalStorageDirectory();
    File storageDir = new File(externalPath.getAbsolutePath()
            +
            "/Android/data/com.cinema.cinema/files");

    File image = File.createTempFile(
              imageFileName,  /* prefix */
              ".jpg",         /* suffix */
              storageDir      /* directory */
      );

    return image;
  }

  @Override
    public void uploadImage(Uri imageFile) {
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    String timeStamp =
              new SimpleDateFormat("yyyyMMdd_HHmmss",
                      Locale.getDefault()).format(new Date());
    String imageFileName = "IMG_" + timeStamp + "_.jpg";
    final String fileName = UUID.randomUUID().toString() + imageFileName;
    final StorageReference ref = storageReference.child("uploads").child(fileName);
    UploadTask uploadTask = ref.putFile(imageFile);
    Task<Uri> task1 = uploadTask.continueWithTask(
            new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
        @Override
        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
          return ref.getDownloadUrl();
        }
      }).addOnCompleteListener(new OnCompleteListener<Uri>() {
        @Override
        public void onComplete(@NonNull Task<Uri> task) {
          if (task.isSuccessful()) {
            //get direct download link
            String url = task.getResult().toString();
            final String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            FirebaseFirestore.getInstance().collection("users")
                    .document(currentUserId).update("pictureLink", url);
            view.onUploadSuccess(url);
          } else {
            view.onUploadFailed(task.getException().getLocalizedMessage());
          }

        }
      }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
          view.onUploadFailed(e.getLocalizedMessage());
        }
      });
  }

  @Override
    public void getUserData() {
    // Firebase Instance
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = firebaseAuth.getCurrentUser();

    //get user data from firebase
    DocumentReference docRef = db.collection("users").document(currentUser.getUid());
    docRef.get().addOnCompleteListener(task -> {
      if (task.isSuccessful()) {
        DocumentSnapshot document = task.getResult();
        if (document.exists()) {
          // get current user data object and show it in profile fragment
          User user = document.toObject(User.class);

          view.onGetProfileSuccess(user);
        } else {
          view.onProfileDataFailed("No such document");
        }
      } else {
        view.onProfileDataFailed(task.getException().getLocalizedMessage());
      }
    });
  }
}