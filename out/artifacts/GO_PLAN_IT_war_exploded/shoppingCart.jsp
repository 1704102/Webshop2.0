<%--
  Created by IntelliJ IDEA.
  User: marti
  Date: 19-6-2018
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/rest.js"></script>
    <link rel="stylesheet" type="text/css" href="css/wizard.css">
    <style>
        .amount *{display: inline-block}
        .amount input{width: 41px;
            height: 41px;}
        #shoppingCart{width:800px;}
        #shoppingCart *{text-align: center}
    </style>
</head>
<body>
<div>
    <table id="shoppingCart">
        <tr>
            <th>name</th>
            <th>amount</th>
            <th>price</th>
            <th>total</th>
        </tr>
    </table>

    <input type="button" value="buy" onclick="buy()">

    <form id="regForm" style="display: none;">

        <h1>Afrekenen:</h1>

        <div class="tab">Adres:
            <p><input id="city" placeholder="Stad" oninput="this.className = ''"></p>
            <p><input id="street" placeholder="Straat + nummer" oninput="this.className = ''"></p>
            <p><input id="zipCode" placeholder="Postcode" oninput="this.className = ''"></p>
        </div>

        <div class="tab">Overzicht:
            <div id="overView"></div>
            <p> Mijn bestelling klopt: <input id="confirmation" type="checkbox" oninput="this.className = ''"></p>
        </div>

        <div style="overflow:auto;">
            <div style="float:right;">
                <button type="button" id="prevBtn" onclick="nextPrev(-1)">Terug</button>
                <button type="button" id="nextBtn" onclick="nextPrev(1)">Volgende</button>
            </div>
        </div>

        <div style="text-align:center;margin-top:40px;">
            <span class="step"></span>
            <span class="step"></span>
        </div>

    </form>
</div>

<script>
    initialiseShoppingCart();
    function initialiseShoppingCart() {
        $("#shoppingCart").empty();
        $("#shoppingCart").append("<tr>\n" +
            "            <th>name</th>\n" +
            "            <th>amount</th>\n" +
            "            <th>price</th>\n" +
            "            <th>total</th>\n" +
            "        </tr>");
        var cart = JSON.parse(sessionStorage.getItem("shoppingCart"));
        for(data in cart){
            var input = postCall(cart[data], 'rest/product', 'json');
            $("#shoppingCart").append("<tr>" +
                "<td>" + input.name + "</td>" +
                "<td class='amount'> <input type='button' value='-' onclick='alterAmount(" + input.id + " , -1)'>" +  cart[data].amount + " <input type='button' value='+' onclick='alterAmount(" + input.id + " , 1)'></td>" +
                "<td>" + input.price + "</td>" +
                "<td>" + (input.price * cart[data].amount).toFixed(2) + "</td>" +
                "<td><input type='button' value='x' onclick='deleteProduct(" + input.id + ")'></td>" +
                "</tr>");
        }
        $("#overView").empty();
        $("#overView").append($("#shoppingCart").clone());
    }

    function deleteProduct(number) {
        var cart = JSON.parse(sessionStorage.getItem("shoppingCart"));
        for(var i = 0; i < cart.length; i++) {
            if (cart[i].id == number){
                delete cart[i];
            }

        }
        var output = JSON.stringify(cart);
        sessionStorage.setItem('shoppingCart',JSON.stringify(cart).replace(",null", ""));
        initialiseShoppingCart();
    }

    function buy() {
        $("#regForm").css("display", "block");
    }

    var currentTab = 0;
    showTab(currentTab);

    function showTab(number) {
        var tab = $(".tab");
        for (var i = 0; i < tab.length; i++){
            if (i == number){
                $(tab[i]).css("display", "block");
            }else {
                $(tab[i]).css("display", "none");
            }
        }
        if (number == tab.length - 1){
            console.log($("#nextBtn"));
            $("#nextBtn").empty();
            $("#nextBtn").append('Opsturen');
        }else{
            $("#nextBtn").empty();
            $("#nextBtn").append('Volgende');
        }
        fixStepIndicator(number);
    }

    function nextPrev(number) {
        var tab = $(".tab");
        $(tab[number]).css("display", "none");
        currentTab = currentTab + number;
        if (currentTab >= tab.length) {
            submitOrder();
            return false;
        }
        showTab(currentTab);
    }

    function fixStepIndicator(n) {

        var steps = $('span[class*=step]');
        for (var i = 0; i < steps.length; i++){
            if( i < n){
                steps[i].className = "step finish";
            }else {
                steps[i].className = steps[i].className.replace(" active", "");
            }
        }
        steps[n].className += " active finish";
    }

    function submitOrder() {
        var city = $("#city").val();
        var street = $("#street").val();
        var zipCode = $("#zipCode").val();

        if ($("#confirmation").is(':checked')){
            confirmation = true;
        }else{
            confirmation = false;
        }

        if (validate()){
            var data = JSON.parse("{}");
            data["street"] = street;
            data["city"] = city;
            data["zipCode"] = zipCode;
            data["shoppingCart"] = JSON.parse(sessionStorage.getItem('shoppingCart'));
            data["token"] = sessionStorage.getItem("token");
            putCall(data, "rest/order");
            console.log(confirmation);
            alert("order verwerkt!");
            window.location.href = "home.jsp";
        }

    }

    function alterAmount(id, number) {
        console.log(id);
        console.log(number);
        var cart = JSON.parse(sessionStorage.getItem("shoppingCart"));
        for (item in cart){
            if (cart[item].id == id){
                if (cart[item].amount + number > 0){
                    cart[item].amount = cart[item].amount + number;
                }
            }
        }
        sessionStorage.setItem("shoppingCart", JSON.stringify(cart));
        initialiseShoppingCart();
    }

    function validate() {
        if ($("#city").val() != "" && $("#city").val() != "" && $("#city").val() != ""){
            if (!$("#confirmation").is(':checked')){
            alert("klik op het vinkje");
            nextPrev(-1)
            return false;
        }

            return true;
        }else{
            nextPrev(-1)
            alert("validatie mislukt");
            return false;
        }

    }
</script>
</body>
</html>
