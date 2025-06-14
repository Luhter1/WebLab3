package org.itmo.manageBean;

public interface PointCounterMBean {
    int getTotalShots();
    int getMisses();
    int getHits();
}