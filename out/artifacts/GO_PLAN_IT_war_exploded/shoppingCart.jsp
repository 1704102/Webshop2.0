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
        var cart = JSON.parse(sessionStorage.getItem("shoppingCart"));
        for(data in cart){
            var input = postCall(cart[data], 'rest/product', 'json');
            $("#shoppingCart").append("<tr>" +
                "<td>" + input.name + "</td>" +
                "<td>" + cart[data].amount + "</td>" +
                "<td>" + input.price + "</td>" +
                "<td>" + (input.price * cart[data].amount).toFixed(2) + "</td>" +
                "</tr>");
        }
        $("#overView").append($("#shoppingCart").clone());
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

        var data = JSON.parse("{}");
        data["street"] = street;
        data["city"] = city;
        data["zipCode"] = zipCode;
        data["shoppingCart"] = JSON.parse(sessionStorage.getItem('shoppingCart'));
        data["token"] = sessionStorage.getItem("token");
        putCall(data, "rest/order");
        console.log(confirmation);
    }

</script>
</body>
</html>
