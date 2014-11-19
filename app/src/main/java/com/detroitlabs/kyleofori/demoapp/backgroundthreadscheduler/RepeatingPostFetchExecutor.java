package com.detroitlabs.kyleofori.demoapp.backgroundthreadscheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

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
