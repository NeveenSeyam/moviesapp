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
public class ForgetPasswordActivityPresenterTest {
    CountDownLatch latch = new CountDownLatch(1);
    ForgetPasswordActivityPresenter presenter;

    @Mock
    ForgetPasswordActivityViewInterface view;

    @Before
    public void setup(){
        presenter = new ForgetPasswordActivityPresenter(view);
    }

    @Test
    public void testForgetPasswordSuccess() throws InterruptedException {
        // given
        String email = "mmooddrr22@gmail.com";
        // when
        presenter.forget(email);
        // then
        latch.await(5, TimeUnit.SECONDS);
        Mockito.verify(view).onForgetSuccess();
    }

    @Test
    public void testLoginFailed() throws InterruptedException {
        // given
        String email = "asdsad";
        // when
        presenter.forget(email);
        // then
        latch.await(5, TimeUnit.SECONDS);
        Mockito.verify(view).onForgetFailed("The email address is badly formatted.");
    }
}