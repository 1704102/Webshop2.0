package com.example.jersey.resource;
import com.example.jersey.controller.Controller;
import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.api.core.ParentRef;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("/products")
public class Product {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") int id){
        com.example.jersey.model.Product product = Controller.getProduct(id);
        if (product == null){
            return javax.ws.rs.core.Response.status(403).build();
        }else{
            return Response.ok(new JSONObject(product).toString()).build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(){
        JSONArray array = new JSONArray(Controller.getProducts());
        if (array.length() == 0){
            return javax.ws.rs.core.Response.status(403).build();
        }else {
            return Response.ok(array.toString()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/aanbieding")
    public Response getAanbieding(){
        JSONArray array = new JSONArray(Controller.getAanbiedingen());
        if (array.length() == 0){
            return javax.ws.rs.core.Response.status(403).build();
        }else {
            return Response.ok(array.toString()).build();
        }
    }

    @POST
    @Consumes("application/json")
    public void addProduct(String x){
        Controller.addProduct(new JSONObject(x));
    }

    @PUT
    public void alterProduct(String x){
        JSONObject input = new JSONObject(x);
        Controller.alterProduct(input);
    }

    @DELETE
    public void deleteProduct(String x){
        JSONObject object = new JSONObject(x);
        Controller.deleteProduct((object.getInt("id")));
    }





}
