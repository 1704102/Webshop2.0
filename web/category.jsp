<%--
  Created by IntelliJ IDEA.
  User: marti
  Date: 21-6-2018
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/rest.js"></script>
    <style>
        .id{display: none   }
        .old{
            display: inline-block;
            text-decoration: line-through;
        }
    </style>
</head>
<body>
<div id="products"></div>
<script>
    var input = JSON.parse("{}");
    var category = location.search.substring(1).split("=")[1];
    input["category"] = category;
    addProducts(postCall(input, 'rest/category', 'json'));

    function addProducts(data) {
        for (dat in data){
            var input = data[dat];
            if (input.aanbieding != undefined){
                $("#products").append(  "<div class ='product'>" +
                    "<div class='id'>" + input.id + "</div>"+
                    "<div class='name'> name : " + input.name + "</div>"+
                    "<div class='price'> price : " + "<div class='old'> "+input.price + "</div> " + " " + input.aanbieding.price + "</div>"+
                    "</div>");
                $("#products").append("<div style='display: block; width: 10px;'>.</div>");
            }else{
                $("#products").append(  "<div class ='product'>" +
                    "<div class='id'>" + input.id + "</div>"+
                    "<div class='name'> name : " + input.name + "</div>"+
                    "<div class='price'> price : " + " " + input.price + "</div>"+
                    "</div>");
                $("#products").append("<div style='display: block; width: 10px;'>.</div>");
            }

        }
    }

    $(".product").click(function (data) {

        var id = $(data.currentTarget).find(".id").html();
        window.location.href = 'product.jsp?id='+ id;
    })
</script>
</body>
</html>
