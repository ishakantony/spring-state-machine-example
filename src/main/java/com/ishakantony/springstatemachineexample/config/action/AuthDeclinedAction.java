package com.ishakantony.springstatemachineexample.config.action;

import com.ishakantony.springstatemachineexample.domain.PaymentEvent;
import com.ishakantony.springstatemachineexample.domain.PaymentState;
import com.ishakantony.springstatemachineexample.service.PaymentServiceImpl;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AuthDeclinedAction implements Action<PaymentState, PaymentEvent> {
    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> stateContext) {
        System.out.println("Auth was declined, sending notification. This could be used for lots of things, like calling other microservices");
    }
}
