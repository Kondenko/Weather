package com.aleksandersh.weather.service;


/**
 * Created by AleksanderSh on 18.07.2017.
 * <p>
 * Интерфейс планировщика сервиса.
 */

public interface ServiceScheduler {

    /**
     * Планирует выполнение задачи с заданным интервалом.
     *
     * @param interval Интервал.
     */
    void startService(int interval);

    /**
     * Останавливает задачу.
     */
    void stopService();
}
