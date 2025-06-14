package org.itmo.manageBean;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.concurrent.atomic.AtomicInteger;


public class PointCounterBean extends NotificationBroadcasterSupport {

    private final AtomicInteger totalShots = new AtomicInteger(0);
    private final AtomicInteger hitPoints = new AtomicInteger(0);
    private int consecutiveMisses = 0;
    private long sequenceNumber = 1;

    /**
     * Обрабатывает результат проверки точки.
     * @param isHit true, если точка попала в область
     */
    public synchronized void registerShot(boolean isHit) {
        totalShots.incrementAndGet();
        
        if (isHit) {
            hitPoints.incrementAndGet();
            consecutiveMisses=0;
        } else {
            consecutiveMisses++;

            if (consecutiveMisses >= 3) {
                Notification notif = new Notification(
                        "consecutive.misses",
                        this,
                        sequenceNumber++,
                        System.currentTimeMillis(),
                        "User made 3 consecutive misses."
                );
                sendNotification(notif);
                consecutiveMisses = 0;
            }
        }
    }


    public int getTotalShots() {
        return totalShots.get();
    }

    public int getMisses() {
        return totalShots.get() - hitPoints.get();
    }


    public int getHits() {
        return hitPoints.get();
    }
}