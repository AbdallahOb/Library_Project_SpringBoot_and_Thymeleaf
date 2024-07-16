package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    // define a private field for the dependency
    private Coach myCoach;


    //Constructor injection
    public DemoController(@Qualifier("cricketCoach") Coach theCoach){
        System.out.println("In constructor: +"+ getClass().getSimpleName());
        this.myCoach= theCoach;

    }


    // Expose an endpoint for dailyworkout
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return "This is your coaches tasks: \n"+myCoach.getDailyWorkout();
    }

}

/*
*   Bean Lifecycles Methods:
*   2 special type of annotations used for adding some code or logic after starting the spring container
*   and after turning it off.
*
*   VIP Note: For "prototype" scoped beans, Spring does not call the destroy method.
* */

