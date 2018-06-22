<%--
  Created by IntelliJ IDEA.
  User: marti
  Date: 17-6-2018
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/rest.js"></script>
    <style>
        #price * {display: inline-block   }
        .old{
            display: inline-block;
            text-decoration: line-through;
        }
    </style>
</head>
<body>
<div>
    <div id="name"> name: </div>
    <div id="price"> price: </div>
    <div id="description"> description: </div>
    <div id="product"></div>
    <input type="button" value="buy" onclick="buyProduct()">
</div>


<script>
    function buyProduct() {
        var id = location.search.substring(1).split("=")[1];
        if(sessionStorage.getItem("shoppingCart") == null){
            sessionStorage.setItem("shoppingCart", "[]");
        }
            var cart = JSON.parse(sessionStorage.getItem("shoppingCart"));
            for (var data in cart){
                if (cart[data].id == id){
                    cart[data].amount++;
                    sessionStorage.setItem("shoppingCart", JSON.stringify(cart));
                    return;
                }
            }
            cart.push({"id" : id, "amount" : 1});
            sessionStorage.setItem("shoppingCart", JSON.stringify(cart));
    }
</script>
<script>
    var id = location.search.substring(1).split("=")[1];
    var input = JSON.parse("{}");
    input["id"] = id;
    var product = postCall(input, 'rest/product', 'json');
    $("#name").append(product.name);
    if (product.aanbieding == undefined){
        $("#price").append(product.price);
    }else{
        $("#price").append("<div class='old'>" +product.price + "</div> " + " " + "<div>" + product.aanbieding.price + "</div>");
        $("#product").append("<div>" + " aanbiedingsperiode : "+ product.aanbieding.begin + " - " + product.aanbieding.end + "</div>");
        $("#product").append("aanbieding omschrijving: " +product.aanbieding.text);
    }

    $("#description").append(product.description);
</script>
</body>
</html>
