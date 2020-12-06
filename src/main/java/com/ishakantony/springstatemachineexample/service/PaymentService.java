package com.ishakantony.springstatemachineexample.service;

import com.ishakantony.springstatemachineexample.domain.Payment;
import com.ishakantony.springstatemachineexample.domain.PaymentEvent;
import com.ishakantony.springstatemachineexample.domain.PaymentState;
import org.springframework.statemachine.StateMachine;

public interface PaymentService {

    Payment newPayment(Payment payment);

    StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);

}
