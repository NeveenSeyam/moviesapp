package com.cinema.cinema.registeractivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(MockitoJUnitRunner.class)
public class LoginActivityPresenterTest {
    CountDownLatch latch = new CountDownLatch(1);
    LoginActivityPresenter presenter;

    @Mock
    LoginActivityViewInterface view;

    @Before
    public void setup(){
        presenter = new LoginActivityPresenter(view);
    }

    @Test
    public void testLoginSuccess() throws InterruptedException {
        // given
        String email = "mmooddrr22@gmail.com";
        String password = "q1q2q1q2";
        // when
        presenter.login(email, password);
        // then
        latch.await(5, TimeUnit.SECONDS);
        Mockito.verify(view).onLoginSuccess();
    }

    @Test
    public void testLoginFailed() throws InterruptedException {
        // given
        String email = "mmooddrr22@gmail.com";
        String password = "123456";
        // when
        presenter.login(email, password);
        // then
        latch.await(5, TimeUnit.SECONDS);
        Mockito.verify(view).onLoginFailed("The password is invalid or the user does not have a password.");
    }
}