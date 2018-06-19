package com.example.jersey.resource;

import com.example.jersey.controller.Controller;
import com.example.jersey.database.ProductDatabase;
import org.json.JSONArray;

import javax.annotation.Generated;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/products")
public class Products {

    @GET
    public String getProducts(){
        JSONArray array = new JSONArray(Controller.getProducts());
        return array.toString();
    }

    @GET
    @Path("/aanbieding")
    public String getAanbieding(){
        JSONArray array = new JSONArray(Controller.getAanbiedingen());
        return array.toString();
    }
}
