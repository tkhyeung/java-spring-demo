package com.example.java.listener;

import com.example.java.config.BookEvent;
import com.example.java.config.BookState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Slf4j
public class RentalStateMachineListenerV2 extends StateMachineListenerAdapter<BookState, BookEvent> {

    @Override
    public void stateChanged(State<BookState, BookEvent> from, State<BookState, BookEvent> to) {
        if(from != null) {
            log.info("State changed from {} to {}", from.getId(), to.getId());
        }else {
            log.info("State changed from {} to {}", "null state", to.getId());
        }
    }

    @Override
    public void eventNotAccepted(Message<BookEvent> event) {
        log.info("Event not accepted: {}", event.getPayload());
    }

}
