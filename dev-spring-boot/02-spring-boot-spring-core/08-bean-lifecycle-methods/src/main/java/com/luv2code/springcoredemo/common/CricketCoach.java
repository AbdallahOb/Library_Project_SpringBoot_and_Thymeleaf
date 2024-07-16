package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component //This annotation marks the class as a spring bean, make it available for dependency injection
public class CricketCoach implements Coach{



    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes --!.";
    }

    // Define out init method:
        //This method will be executed [Container Started -> Bean Instantiated -> Dependencies Injected -> Internal Spring Processing -> HERE YOUR INIT METHOD]
    @PostConstruct
    public void doMyStartupStuff(){
        System.out.println("In doMyStartupStuff(): "+ getClass().getSimpleName());
    }


    // Define our destroy method:
        //This method will be executed after stopping the spring application to clean Db, Sockets, and files etc...
    @PreDestroy
    public void doMyCleanupStuff(){
        System.out.println("In doMyCleanupStuff(): "+ getClass().getSimpleName());
    }
}
