package com.example.jersey.resource;
import com.example.jersey.controller.Controller;
import org.json.JSONObject;

import javax.ws.rs.*;
import java.util.ArrayList;


@Path("/product")
public class Product {
    @POST
    @Consumes("application/json")
    public String getProduct(String input){
        JSONObject object = new JSONObject(input);
        return  new JSONObject(Controller.getProduct(object.getInt("id"))).toString();
    }

    @PUT
    @Consumes("application/json")
    public void addProduct(String x){
        Controller.addProduct(new JSONObject(x));
    }

}
