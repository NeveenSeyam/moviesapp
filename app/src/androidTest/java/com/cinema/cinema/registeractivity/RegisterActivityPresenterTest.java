package com.cinema.cinema.registeractivity;

import com.github.javafaker.Faker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(MockitoJUnitRunner.class)
public class RegisterActivityPresenterTest {
    CountDownLatch latch = new CountDownLatch(1);
    Faker faker = new Faker();
    RegisterActivityPresenter presenter;


    @Mock
    RegisterActivityViewInterface view;

    @Before
    public void setup(){
        presenter = new RegisterActivityPresenter(view);
    }

    @Test
    public void testCreateAccountSuccess() throws InterruptedException {
        // given
        String password = "q1q2q1q2";
        String name = faker.name().name();
        String email = faker.internet().safeEmailAddress();
        String phone = faker.phoneNumber().cellPhone();
        // when
        presenter.createAccount(email, password, name, phone);
        // then
        latch.await(5, TimeUnit.SECONDS);
        Mockito.verify(view).onCreateSuccess(email, name, phone);
    }

    @Test
    public void testCreateAccountFailed() throws InterruptedException {
        // given
        String password = "q1q2q1q2";
        String name = faker.name().name();
        String email = "mmooddrr22@gmail.com";
        String phone = faker.phoneNumber().cellPhone();
        // when
        presenter.createAccount(email, password, name, phone);
        // then
        latch.await(5, TimeUnit.SECONDS);
        Mockito.verify(view).onCreateFailed("The email address is already in use by another account.");
    }
}