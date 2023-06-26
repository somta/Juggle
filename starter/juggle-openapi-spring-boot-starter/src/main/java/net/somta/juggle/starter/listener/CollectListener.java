package net.somta.juggle.starter.listener;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationListener;

/**
 * @author Gavin
 */
public class CollectListener implements ApplicationListener<AvailabilityChangeEvent> {
    @Override
    public void onApplicationEvent(AvailabilityChangeEvent event) {
        System.out.println("监听到事件：" + event);
        if (ReadinessState.ACCEPTING_TRAFFIC == event.getState()) {
            System.out.println("应用启动完成，可以请求了……");
        }
    }
}