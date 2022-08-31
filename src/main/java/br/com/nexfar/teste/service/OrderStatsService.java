package br.com.nexfar.teste.service;

import br.com.nexfar.teste.domain.Order;
import br.com.nexfar.teste.domain.ProductOrder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static br.com.nexfar.teste.domain.Order.OrderStatus.CANCELED;
import static br.com.nexfar.teste.domain.Order.OrderStatus.DELIVERED;

@Service
public class OrderStatsService {

    /**
     * Tutorial CRUD MongoDB:Ø
     * https://www.mongodb.com/compatibility/spring-boot
     */

    private final MongoTemplate mongoTemplate;

    public OrderStatsService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void exemplo() {
        /*
        Exemplo de uma consulta sem filtros. Nos casos especificos abaixo, para melhorar a performance deverao ser utilizados filtros.
         */
        Query query = new Query();
        mongoTemplate.find(query, Order.class).forEach(product1 -> {
            System.out.println(product1);
        });
    }

    /**
     * Implementar um metodo que imprima os TOP 10 produtos com maior quantidade vendida.
     * So deverao ser considerados itens de pedidos (order) com status = DELIVERED
     */



    public void printTop10Products() {
    List<Order> list = mongoTemplate.findAll(Order.class);
    var count = list.stream()
            .filter(order -> DELIVERED.equals(order.getStatus()))
            .flatMap(order -> order.getProducts().stream())
            .sorted(Comparator.comparing(ProductOrder::getQuantity))
            .limit(10)
            .toList();
        System.out.println(count);
           }


    /**
     * Implementar um metodo que imprima o valor total de pedidos cancelados (status = CANCELED).
     * IMPORTANTE: cada item tem um preco e uma quantidade. No valor total, isso precisa ser multiplicado.
     */
    public void printTotalCanceled() {
        List<Order> list = mongoTemplate.findAll(Order.class);
        Double count = list.stream()
                .filter(order -> CANCELED.equals(order.getStatus()))
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
        System.out.println(count);

    }

    /**
     * Implementar um metodo que imprima o valor total de pedidos entregues (status = DELIVERED).
     * IMPORTANTE: cada item tem um preco e uma quantidade. No valor total, isso precisa ser multiplicado.
     */
    public void printTotalDelivered() {
        List<Order> list = mongoTemplate.findAll(Order.class);
        double count = list.stream()
                .filter(order -> DELIVERED.equals(order.getStatus()))
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
        System.out.println(count);

    }

    /**
     * Implementar um metodo que imprima a media de itens por pedido.
     */
    public void printAverageOrderItems() {
        List<Order> list = mongoTemplate.findAll(Order.class);
        Double avg = list.stream()
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(ProductOrder::getQuantity)
                .average()
                .orElse(0);
        System.out.println(avg);
    }

    /**
     * Implementar um metodo que imprima a media de valor total por pedido.
     */
    public void printAverageOrderValue() {
        List<Order> list = mongoTemplate.findAll(Order.class);
        Double avg2 = list.stream()
                .flatMap(order -> order.getProducts().stream())
                        .mapToDouble(ProductOrder::getPrice)
                .average()
                .orElse(0);
        System.out.println(avg2);

    }}


 
