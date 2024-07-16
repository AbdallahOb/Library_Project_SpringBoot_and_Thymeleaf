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
    private Coach baseballCoach;
    private Coach tennisCoach;
    private Coach trackCoach;

    /*
    *   If you have multiple beans you need to use @Qualifier annotation to specify which bean you need to inject
    *   VIP Note: The qualifier value must the same name of the implementation class except the first letter should be in lowercase
    * */


    //Constructor injection
    public DemoController(@Qualifier("cricketCoach") Coach cricketCoach,
                          @Qualifier("baseballCoach") Coach baseballCoach,
                          @Qualifier("tennisCoach") Coach tennisCoach,
                          @Qualifier("trackCoach") Coach traackCoach){

        this.cricketCoach= cricketCoach;
        this.baseballCoach= baseballCoach;
        this.tennisCoach= tennisCoach;
        this.trackCoach= traackCoach;

    }


    // Expose an endpoint for dailyworkout
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return "This is your coaches tasks: \n"+cricketCoach.getDailyWorkout()+"\n"+baseballCoach.getDailyWorkout()+"\n"+tennisCoach.getDailyWorkout()+"\n"+trackCoach.getDailyWorkout();
    }


}
