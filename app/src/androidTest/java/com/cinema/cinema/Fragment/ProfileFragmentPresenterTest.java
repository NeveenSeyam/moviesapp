package com.cinema.cinema.Fragment;

import android.net.Uri;

import com.cinema.cinema.Model.User;
import com.cinema.cinema.registeractivity.LoginActivityPresenter;
import com.cinema.cinema.registeractivity.LoginActivityViewInterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;



@RunWith(MockitoJUnitRunner.class)
public class ProfileFragmentPresenterTest {
    CountDownLatch latch = new CountDownLatch(1);

    ProfileFragmentPresenter presenter;

    @Mock
    ProfileFragmentViewInterface view;

    @Mock
    LoginActivityViewInterface loginView;

    @Mock
    LoginActivityPresenter loginPresenter;


    @Before
    public void setup(){
        presenter = new ProfileFragmentPresenter(view);
        loginPresenter= new LoginActivityPresenter(loginView);

        loginPresenter.login("mmooddrr22@gmail.com", "q1q2q1q2");
        try {
            latch.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateImageFileSuccess() throws IOException, InterruptedException {
        assertNotNull(presenter.createImageFile());
        assertThat(presenter.createImageFile(), instanceOf(File.class));
    }



    @Test
    public void testUploadImageSuccess() throws IOException, InterruptedException {
        // given
        Uri uri = Uri.fromFile(File.createTempFile("image", ".jpg"));
        // when
        presenter.uploadImage(uri);
        // then
        latch.await(10, TimeUnit.SECONDS);
        Mockito.verify(view).onUploadSuccess(Mockito.anyString());
    }

    @Test
    public void testGetProfileDataSuccess() throws InterruptedException {
        // given
        // when
        presenter.getUserData();
        // then
        latch.await(10, TimeUnit.SECONDS);
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(view).onGetProfileSuccess(captor.capture());
    }
}