<%--
  Created by IntelliJ IDEA.
  User: marti
  Date: 17-6-2018
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/rest.js"></script>
</head>
<body>
    <div>
        <div>name <input id="name" type="text"></div>
        <div>price <input id="price" type="number" step=".01"></div>
        <div>picture <input id="picture" type="text"></div>
        <div>description <input id="description" type="text"></div>
        <input type="button" value="addProduct" onclick="addProduct()">
    </div>

<script>
    function addProduct() {
        var input = JSON.parse("{}");
        input["name"] = $("#name").val();
        input["price"] = parseFloat($("#price").val());
        input["picture"] = $("#picture").val();
        input["description"] = $("#description").val();
        console.log(input);
        putCall(input, "rest/product");
    }
</script>
</body>
</html>
