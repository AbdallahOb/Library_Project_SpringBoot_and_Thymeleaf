package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    // define a private field for the dependency
    private Coach cricketCoach;

    /*
    *   If you have multiple implementation of beans also you can use @Primary annotation in the top of only one of the implementation classes
    *   VIP Note: You can use both @Primary & @Qualifier at the same time and the @Qualifier has the higher priority
    * */


    //Constructor injection
    public DemoController(Coach cricketCoach){
        //I have updated the CricketCoach class by adding @Primary annotation
        //So, when ever you request a coach from DI it inject the primary implementation class which is CricketCoach
        this.cricketCoach= cricketCoach;


    }


    // Expose an endpoint for dailyworkout
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return "This is your coaches tasks: \n"+cricketCoach.getDailyWorkout();
    }


}
