package com.example.jersey.resource;

import com.example.jersey.controller.Controller;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/category")
public class Category {

    @POST
    @Consumes("application/json")
    public String getProducts(String x){
        JSONObject object = new JSONObject(x);
        return new JSONArray(Controller.getProductInCategory(object.getString("category"))).toString();
    }

}
