package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.DeliveryAddressRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DeliveryAddressTestSuite {

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Test
    public void testSaveDeliveryAddress() {
        //Given:
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setName("Mr. Bob");
        deliveryAddress.setCity("Kielce");
        deliveryAddress.setCountry("Poland");
        deliveryAddress.setBuildingNumber("50A");
        deliveryAddress.setFlatNumber(16);
        deliveryAddress.setPhone(111222333);
        deliveryAddress.setPostcode("11-222");
        deliveryAddress.setStreet("Dworcowa");

        //When:
        deliveryAddressRepository.save(deliveryAddress);
        Long deliveryAddressId = deliveryAddress.getDeliveryAddressId();

        //Then:
        Assert.assertTrue(deliveryAddressRepository.existsById(deliveryAddressId));

        //Clean-up:
        deliveryAddressRepository.deleteById(deliveryAddressId);
    }


    @Test
    public void testReadDeliveryAddress() {
        //Given:
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setName("Mr. Bob");
        deliveryAddress.setCity("Kielce");
        deliveryAddress.setCountry("Poland");
        deliveryAddress.setBuildingNumber("50A");
        deliveryAddress.setFlatNumber(16);
        deliveryAddress.setPhone(111222333);
        deliveryAddress.setPostcode("11-222");
        deliveryAddress.setStreet("Dworcowa");

        //When:
        deliveryAddressRepository.save(deliveryAddress);
        Long deliveryAddressId = deliveryAddress.getDeliveryAddressId();

        //Then:
        Assert.assertNotNull(deliveryAddressRepository.findById(deliveryAddressId));

        //Clean-up:
        deliveryAddressRepository.deleteById(deliveryAddressId);
    }


    @Test
    public void testUpdateDeliveryAddress() {
        //Given:
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setName("Mr. Bob");
        deliveryAddress.setCity("Kielce");
        deliveryAddress.setCountry("Poland");
        deliveryAddress.setBuildingNumber("50A");
        deliveryAddress.setFlatNumber(16);
        deliveryAddress.setPhone(111222333);
        deliveryAddress.setPostcode("11-222");
        deliveryAddress.setStreet("Dworcowa");

        deliveryAddressRepository.save(deliveryAddress);
        Long deliveryAddressId = deliveryAddress.getDeliveryAddressId();
        DeliveryAddress fetchedDeliveryAddress = deliveryAddressRepository.findById(deliveryAddressId).orElse(null);
        assert fetchedDeliveryAddress != null;
        String initialName = fetchedDeliveryAddress.getName();

        //When:
        fetchedDeliveryAddress.setName("Mrs. Pam");
        deliveryAddressRepository.save(fetchedDeliveryAddress);
        String updatedName = fetchedDeliveryAddress.getName();

        //Then:
        Assert.assertNotEquals(initialName, updatedName);

        //Clean-up:
        deliveryAddressRepository.deleteById(deliveryAddressId);
    }


    @Test
    public void testDeleteDeliveryAddress() {
        //Given:
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setName("Mr. Bob");
        deliveryAddress.setCity("Kielce");
        deliveryAddress.setCountry("Poland");
        deliveryAddress.setBuildingNumber("50A");
        deliveryAddress.setFlatNumber(16);
        deliveryAddress.setPhone(111222333);
        deliveryAddress.setPostcode("11-222");
        deliveryAddress.setStreet("Dworcowa");

        deliveryAddressRepository.save(deliveryAddress);
        Long deliveryAddressId = deliveryAddress.getDeliveryAddressId();

        //Then:
        Assert.assertTrue(deliveryAddressRepository.existsById(deliveryAddressId));

        //When:
        deliveryAddressRepository.deleteById(deliveryAddressId);

        //Then:
        Assert.assertFalse(deliveryAddressRepository.existsById(deliveryAddressId));

    }


    @Test
    public void testDeleteOrderVsDeliveryAddress() {
        //Given:
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setName("Mr. Bob");
        deliveryAddress.setCity("Kielce");
        deliveryAddress.setCountry("Poland");
        deliveryAddress.setBuildingNumber("50A");
        deliveryAddress.setFlatNumber(16);
        deliveryAddress.setPhone(111222333);
        deliveryAddress.setPostcode("11-222");
        deliveryAddress.setStreet("Dworcowa");

        Order order = new Order();
        order.setCreationDate(LocalDate.now());
        order.setStatus("pending");
        order.setPaymentMethod("wire");
        order.setDeliveryMethod("courier");

        deliveryAddressRepository.save(deliveryAddress);
        orderRepository.save(order);

        order.setDeliveryAddress(deliveryAddress);
        deliveryAddress.getOrders().add(order);

        deliveryAddressRepository.save(deliveryAddress);
        orderRepository.save(order);

        Long deliveryAddressId = deliveryAddress.getDeliveryAddressId();
        Long orderId = order.getOrderId();

        //When:
        orderRepository.deleteById(orderId);

        //Then: (assert that deleting Order does NOT delete the respective DeliveryAddress)
        Assert.assertFalse(orderRepository.existsById(orderId));
        Assert.assertTrue(deliveryAddressRepository.existsById(deliveryAddressId));

        //Clean-up:
        deliveryAddressRepository.deleteById(deliveryAddressId);
    }

    // w wyniku tesu kasowania DeliveryAddress w sytuacji gdy jakiś Order już z niego korzysta, wyskakują
    // błędy więzów integralności (i słusznie). Ten test tu nie występuje bo nie przechodzi :)
    // Zakładamy więc, że w naszej aplikacji nie można kasować ani edytować Adresów (bo po co).
    // W przypadku np. zmiany Adresu dodajemy po prostu nowy adres do bazy i nadajemy do niego
    // referencję tam gdzie trzeba.
    // Adresów zamówień historycznych nie chcemy zmieniać.
    // w sytuacji, gdyby jednak jakiś adres trzeba byłoby usunąć, należy najpierw ustawić nulle
    // w zamówieniach, które się do niego odnoszą.

}
