package tn.tunisair.workfow.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import tn.tunisair.workfow.Entities.EvenementIncident;
import tn.tunisair.workfow.Entities.StatutIncident;

import java.util.EnumSet;

@Configuration
@EnableStateMachine(name = "incidentStateMachine")
public class IncidentStateMachineConfig extends EnumStateMachineConfigurerAdapter<StatutIncident, EvenementIncident> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<StatutIncident, EvenementIncident> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<StatutIncident, EvenementIncident> states)
            throws Exception {
        states
                .withStates()
                .initial(StatutIncident.SIGNALE)
                .states(EnumSet.allOf(StatutIncident.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<StatutIncident, EvenementIncident> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(StatutIncident.SIGNALE).target(StatutIncident.EN_COURS).event(EvenementIncident.COMMENCER_TRAVAIL)
                .and()
                .withExternal()
                .source(StatutIncident.EN_COURS).target(StatutIncident.RESOLU).event(EvenementIncident.RESOUDRE);
    }

    @Bean
    public StateMachineListener<StatutIncident, EvenementIncident> listener() {
        return new StateMachineListenerAdapter<StatutIncident, EvenementIncident>() {
            @Override
            public void stateChanged(State<StatutIncident, EvenementIncident> from, State<StatutIncident, EvenementIncident> to) {
                System.out.println("Incident state changed to " + to.getId());
            }
        };
    }
}
