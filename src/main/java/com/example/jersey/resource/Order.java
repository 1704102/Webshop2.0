package com.example.jersey.resource;

import com.example.jersey.controller.Controller;
import com.example.jersey.database.OrderDatabase;
import com.sun.jersey.api.NotFoundException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
public class Order {

    @POST
    @Consumes("application/json")
    public void addOrder(String x){
        JSONObject object = new JSONObject(x);
        OrderDatabase database = new OrderDatabase();
        database.addOrder(object);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        OrderDatabase database = new OrderDatabase();
        JSONObject order = database.getOrder(id);
        if (order == null){
            return javax.ws.rs.core.Response.status(403).build();
        }else {
            return Response.ok(order.toString()).build();
        }

    }


}
