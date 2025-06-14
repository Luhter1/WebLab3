package org.itmo.manageBean;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.concurrent.atomic.AtomicInteger;

public class MissPercentage implements MissPercentageMBean {

    private final PointCounter shotStats;

    public MissPercentage(PointCounter stats) {
        this.shotStats = stats;
    }

    public double getMissRatio() {
        int total = shotStats.getTotalShots();
        if (total == 0) return 0.0;
        return (double) shotStats.getMisses() / total * 100;
    }
}