package org.itmo.manageBean;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.concurrent.atomic.AtomicInteger;

public class MissPercentageBean extends NotificationBroadcasterSupport {

    private final PointCounterBean shotStats;

    public MissPercentageBean(PointCounterBean stats) {
        this.shotStats = stats;
    }

    @Override
    public double getMissRatio() {
        int total = shotStats.getTotalShots();
        if (total == 0) return 0.0;
        return (double) shotStats.getMisses() / total * 100;
    }
}