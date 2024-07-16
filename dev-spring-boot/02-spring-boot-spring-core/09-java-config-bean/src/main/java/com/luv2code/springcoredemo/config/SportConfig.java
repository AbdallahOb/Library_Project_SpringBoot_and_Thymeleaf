package com.luv2code.springcoredemo.config;

import com.luv2code.springcoredemo.common.Coach;
import com.luv2code.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    //The BeanId that will be used in Qualifier(ID) is the name of this method !! be careful
    @Bean
    public Coach swimCoach(){
        return new SwimCoach();
    }
}


/*
*   This approach used with Third-party classes since you cannot modify those classes to add @Component annotation
*   like previous approach
*   Example:
*   If you need to use AWS service using dependency injection you should follow this approach
* */

/*
*   How to implement this approach?
*   1- Create your logic class
*   2- Create a configuration class with the above implementation [@Configuration & @Bean] annotations
*   3- Inject the service using @Qualifier(Id) annotation
*   4- remember the Id is same as the name of the @Bean method by default
*   5- Or you can give the @Bean a custom Id using @Bean("Your Custom Id")
* */