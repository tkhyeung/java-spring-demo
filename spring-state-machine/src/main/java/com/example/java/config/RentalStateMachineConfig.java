package com.example.java.config;

import com.example.java.listener.RentalStateMachineListenerV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Slf4j
@Configuration
@EnableStateMachine
public class RentalStateMachineConfig extends EnumStateMachineConfigurerAdapter<BookState, BookEvent> {
    @Override
    public void configure(StateMachineStateConfigurer<BookState, BookEvent> state) throws Exception {
        state.withStates().initial(BookState.AVAILABLE)
                .initial(BookState.AVAILABLE)
                .states(EnumSet.allOf(BookState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<BookState, BookEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(BookState.AVAILABLE)
                .target(BookState.BORROWED)
                .event(BookEvent.BORROW)
                .and()
                .withExternal()
                .source(BookState.BORROWED)
                .target(BookState.AVAILABLE)
                .event(BookEvent.RETURN);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<BookState, BookEvent> config) throws Exception {
        config.withConfiguration()
                .autoStartup(false)
//                .listener(new RentalStateMachineListener())
                .listener(new RentalStateMachineListenerV2())
        ;
    }
}
