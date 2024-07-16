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

    //Constructor injection
    public DemoController(@Qualifier("cricketCoach") Coach theCoach){
        //The TrackCoach is @Lazy to show difference
        System.out.println("In constructor: +"+ getClass().getSimpleName());
        this.myCoach= theCoach;



        //After trying @Lazy at each component the default behavior is all beans injected
        //You can change the default to be Lazy initialization

        //Check application.properties
        //To show the differences remove the lazy=true from application.properties then check the Log
    }


    // Expose an endpoint for dailyworkout
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return "This is your coaches tasks: \n"+myCoach.getDailyWorkout();
    }


}
