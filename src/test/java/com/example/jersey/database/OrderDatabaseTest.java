package com.example.jersey.database;

import org.junit.Assert;
import org.junit.Test;;
public class OrderDatabaseTest {

    @Test
    public void getOrder() {
        String testValue = "{\"klant\":{\"name\":\"Martijn\"},\"orderLines\":[{\"amount\":12,\"id\":4,\"productName\":\"iphone\"},{\"amount\":9,\"id\":5,\"productName\":\"kauwgom\"},{\"amount\":7,\"id\":6,\"productName\":\"taco\"},{\"amount\":5,\"id\":7,\"productName\":\"usb stick\"}],\"id\":1,\"adres\":{\"zipCode\":\"4007NH\",\"city\":\"Tiel\",\"street\":\"Ruisvoorn 38 \"}}";
        OrderDatabase database = new OrderDatabase();
        Assert.assertEquals(testValue, database.getOrder(1).toString());
    }

    @Test
    public void getOrder2() {
        String testValue = "{\"klant\":{\"name\":\"Martijn2\"},\"orderLines\":[{\"amount\":12,\"id\":4,\"productName\":\"iphone\"},{\"amount\":9,\"id\":5,\"productName\":\"kauwgom\"},{\"amount\":7,\"id\":6,\"productName\":\"taco\"},{\"amount\":5,\"id\":7,\"productName\":\"usb stick\"}],\"id\":1,\"adres\":{\"zipCode\":\"4007NH\",\"city\":\"Tiel\",\"street\":\"Ruisvoorn 38 \"}}";
        OrderDatabase database = new OrderDatabase();
        Assert.assertEquals(testValue, database.getOrder(1).toString());
    }
}
