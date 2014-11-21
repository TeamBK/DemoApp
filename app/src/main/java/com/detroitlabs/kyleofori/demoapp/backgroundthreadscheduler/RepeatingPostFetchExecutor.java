package com.detroitlabs.kyleofori.demoapp.backgroundthreadscheduler;

import com.detroitlabs.kyleofori.demoapp.tasks.GetRedditPostTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by BFineRocks on 11/19/14.
 */
public class RepeatingPostFetchExecutor {
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    private String subredditName;
    GetRedditPostTask getRedditPostTask;

    public RepeatingPostFetchExecutor(String subredditName){
        this.subredditName = subredditName;

    }

    public void startRepeatingPostFetchExecutor(){
        getRedditPostTask = new GetRedditPostTask(subredditName);
        scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor)
                Executors.newScheduledThreadPool(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(getRedditPostTask, 0, 60, TimeUnit.SECONDS);
    }

    public void stopRepeatingPostFetchExecutor(){
        scheduledThreadPoolExecutor.shutdownNow();
    }


}
