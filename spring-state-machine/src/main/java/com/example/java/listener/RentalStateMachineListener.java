package com.example.java.listener;

import com.example.java.config.BookEvent;
import com.example.java.config.BookState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

@Slf4j
public class RentalStateMachineListener implements StateMachineListener<BookState, BookEvent> {

    @Override
    public void stateChanged(State<BookState, BookEvent> from, State<BookState, BookEvent> to) {
        log.info("State changed from {} to {}", getStateInfo(from), getStateInfo(to));
    }

    @Override
    public void stateEntered(State<BookState, BookEvent> state) {
        log.info("Entered state {}", getStateInfo(state));
    }

    @Override
    public void stateExited(State<BookState, BookEvent> state) {
        log.info("Exited state {}", getStateInfo(state));
    }

    @Override
    public void eventNotAccepted(Message<BookEvent> event) {
        log.error("Event not accepted: {}", event.getPayload());
    }

    @Override
    public void transition(Transition<BookState, BookEvent> transition) {
        log.info("Transaction {}", getTransitionInfo(transition));
    }

    @Override
    public void transitionStarted(Transition<BookState, BookEvent> transition) {
        log.info("TransitionStarted {}", getTransitionInfo(transition));
    }

    @Override
    public void transitionEnded(Transition<BookState, BookEvent>transition) {
        log.info("transitionEnded {}", getTransitionInfo(transition));
    }

    @Override
    public void stateMachineStarted(StateMachine<BookState, BookEvent> stateMachine) {
        log.info("Machine started: {}", stateMachine);
    }

    @Override
    public void stateMachineStopped(StateMachine<BookState, BookEvent> stateMachine) {
        log.info("Machine stopped: {}", stateMachine);
    }

    @Override
    public void stateMachineError(StateMachine<BookState, BookEvent> stateMachine, Exception exception) {
        log.error("Machine error: {}", stateMachine);
    }

    @Override
    public void extendedStateChanged(Object key, Object value) {
        log.info("Extended state changed: [{}: {}]", key, value);
    }

    @Override
    public void stateContext(StateContext<BookState, BookEvent> stateContext) {
        log.info("State Context: {}", stateContext);
    }


    public static String getStateInfo(State<BookState, BookEvent> state) {
        return state != null ? state.getId().name() : "EMPTY STATE";
    }

    public static String getTransitionInfo(Transition<BookState, BookEvent> t) {
        return String.format("[%s: %s]",
                t.getSource() != null ? t.getSource().getId() : "EMPTY",
                t.getTarget() != null ? t.getTarget().getId() : "EMPTY"
        );
    }
}
