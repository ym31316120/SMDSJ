package com.ym.smdsj.support.manager;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ym
 * @date 2019/3/12
 **/
public class LogExeManager {

    private static final int LOG_DELAY_TIME = 10;

    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(20);

    private static LogExeManager logExeManager = new LogExeManager();
    private LogExeManager() {

    }

    public static LogExeManager getInstance() {
        return logExeManager;
    }

    public void executeLogTask(TimerTask timerTask) {
        executor.schedule(timerTask, LOG_DELAY_TIME, TimeUnit.MICROSECONDS);
    }
}