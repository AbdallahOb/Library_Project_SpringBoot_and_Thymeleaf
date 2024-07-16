package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    // define a private field for the dependency
    private Coach myCoach;


    @Autowired
    //Constructor injection
    public DemoController(@Qualifier("swimCoach") Coach theCoach){
        System.out.println("In constructor: +"+ getClass().getSimpleName());
        this.myCoach= theCoach;

    }


    // Expose an endpoint for dailyworkout
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return "This is your coaches tasks: \n"+myCoach.getDailyWorkout();
    }

}

