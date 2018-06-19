package com.example.jersey.resource;

import com.example.jersey.database.OrderDatabase;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@Path("/order")
public class Order {

    @PUT
    @Consumes("application/json")
    public void addOrder(String x){
        JSONObject object = new JSONObject(x);
        OrderDatabase database = new OrderDatabase();
        database.addOrder(object);
    }
}
