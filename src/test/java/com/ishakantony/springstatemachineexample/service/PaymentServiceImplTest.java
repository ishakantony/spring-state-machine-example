package com.ishakantony.springstatemachineexample.service;

import com.ishakantony.springstatemachineexample.domain.Payment;
import com.ishakantony.springstatemachineexample.domain.PaymentEvent;
import com.ishakantony.springstatemachineexample.domain.PaymentState;
import com.ishakantony.springstatemachineexample.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.builder().amount(new BigDecimal("12.99")).build();
    }

    @Transactional
    @RepeatedTest(5)
    void testPreAuth() {
        Payment savedPayment = paymentService.newPayment(payment);

        System.out.println("When we save a new Payment, state should be: NEW");
        System.out.println(savedPayment);

        paymentService.preAuth(savedPayment.getId());

        Payment preAuthPayment = paymentRepository.getOne(savedPayment.getId());

        System.out.println("After we sent PRE_AUTHORIZE event, state should be either PRE_AUTH or PRE_AUTH_ERROR");
        System.out.println(preAuthPayment);
    }

    @Transactional
    @RepeatedTest(10)
    void testAuthorizePayment() {
        Payment savedPayment = paymentService.newPayment(payment);

        StateMachine<PaymentState, PaymentEvent> preAuthSM = paymentService.preAuth(savedPayment.getId());

        if (preAuthSM.getState().getId() == PaymentState.PRE_AUTH) {

            System.out.println("Payment was pre-authorized");

            StateMachine<PaymentState, PaymentEvent> authSM = paymentService.authorizePayment(savedPayment.getId());

            System.out.println("Result of auth: " + authSM.getState().getId());
        } else {
            System.out.println("PreAuth was decline, cannot authorize payment");
        }
    }
}