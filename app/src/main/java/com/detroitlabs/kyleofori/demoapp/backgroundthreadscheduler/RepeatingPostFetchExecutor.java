package com.detroitlabs.kyleofori.demoapp.backgroundthreadscheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by BFineRocks on 11/19/14.
 */
public class RepeatingPostFetchExecutor {
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    private String subredditName;

    public RepeatingPostFetchExecutor(String subredditName){
        this.subredditName = subredditName;
        scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor)
                Executors.newScheduledThreadPool(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(periodicTask, 5, 5, TimeUnit.SECONDS);
    }



    Runnable periodicTask = new Runnable(){
        @Override
        public void run() {
            try{
                System.out.println("I'm a repeating task, or something like that");

            }catch (Exception e){

            }
        }
    };
}
