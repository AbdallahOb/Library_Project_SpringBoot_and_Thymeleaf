package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component //This annotation marks the class as a spring bean, make it available for dependency injection
public class CricketCoach implements Coach{

    public CricketCoach(){
        System.out.println("In constructor: +"+ getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes --!.";
    }
}
