package br.com.nexfar.teste.schedule;

import br.com.nexfar.teste.service.OrderStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrecosSchedule {

    @Autowired
    OrderStatsService service;

    @Scheduled(fixedDelay = 10000)
    public void exemplo() {
        service.exemplo();
    }

    /**
     * Implementar as chamadas por intervalo de tempo para todos os metodos da classe OrderStatsService.java
     */

    @Scheduled(fixedDelay = 10000)
    public void printTop10Products() {
        service.printTop10Products();
    }

}
