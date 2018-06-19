<%--
  Created by IntelliJ IDEA.
  User: marti
  Date: 17-6-2018
  Time: 08:49
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
        input["category"] = "nieuw";
        postCall(input,  'rest/category', 'json');

        addProducts(getCall('rest/products/aanbieding', 'json'));

        function addProducts(data) {
            for (dat in data){
                var input = data[dat];;
                $("#products").append(  "<div class ='product'>" +
                                        "<div class='id'>" + input.id + "</div>"+
                                        "<div class='name'> name : " + input.name + "</div>"+
                                        "<div class='price'> price : " + "<div class='old'> "+input.price + "</div> " + " " + input.aanbieding.price + "</div>"+
                                        "</div>");
            }
        }

        $(".product").click(function (data) {

            var id = $(data.currentTarget).find(".id").html();
            window.location.href = 'product.jsp?id='+ id;
        })
    </script>
</body>
</html>
