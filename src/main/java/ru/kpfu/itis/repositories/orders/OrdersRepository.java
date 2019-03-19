package ru.kpfu.itis.repositories.orders;

import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.repositories.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrdersRepository extends CrudRepository<Order>{
    void addOrder(Long clientId, Long cityId);
    void makeChanges(Long employeeId,Boolean isAccepted, Long orderId);
    Long addOrderForCoop(Long clientId, Long cityId);
    List<Order> findOrdersOfClientById(Long id);
}
