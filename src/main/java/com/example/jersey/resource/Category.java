package com.example.jersey.resource;

import com.example.jersey.controller.Controller;
import com.example.jersey.model.Product;
import com.mysql.cj.xdevapi.JsonArray;
import com.sun.jersey.api.NotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/categories")
public class Category {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(String x){
        JSONObject object = new JSONObject(x);
        ArrayList<Product> array = Controller.getProductInCategory(object.getString("category"));
        if (array.size() == 0){
            return javax.ws.rs.core.Response.status(403).build();
        }else {
            return Response.ok(new JSONArray(array).toString()).build();
        }
    }

}
