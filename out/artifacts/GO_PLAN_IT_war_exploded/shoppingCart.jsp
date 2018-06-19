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

    <form id="regForm" action="" style="display: none;">

        <h1>Register:</h1>

        <div class="tab">Name:
            <p><input placeholder="First name..." oninput="this.className = ''"></p>
            <p><input placeholder="Last name..." oninput="this.className = ''"></p>
        </div>

        <div class="tab">Contact Info:
            <p><input placeholder="E-mail..." oninput="this.className = ''"></p>
            <p><input placeholder="Phone..." oninput="this.className = ''"></p>
        </div>

        <div class="tab">Address:
            <p><input placeholder="City" oninput="this.className = ''"></p>
            <p><input placeholder="Street + number" oninput="this.className = ''"></p>
            <p><input placeholder="ZipCode" oninput="this.className = ''"></p>
        </div>

        <div style="overflow:auto;">
            <div style="float:right;">
                <button type="button" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
                <button type="button" id="nextBtn" onclick="nextPrev(1)">Next</button>
            </div>
        </div>
        
        <div style="text-align:center;margin-top:40px;">
            <span class="step"></span>
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
    }

    function buy() {
        $("#regForm").css("display", "block");
    }

    var currentTab = 0; // Current tab is set to be the first tab (0)
    showTab(currentTab); // Display the current tab

    function showTab(number) {
        // This function will display the specified tab of the form ...
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
            $("#nextBtn").append('Submit');
        }else{
            $("#nextBtn").empty();
            $("#nextBtn").append('Next');
        }
        fixStepIndicator(number);
    }

    function nextPrev(n) {
        // This function will figure out which tab to display
        var x = document.getElementsByClassName("tab");
        // Exit the function if any field in the current tab is invalid:
        //if (n == 1 && !validateForm()) return false;
        // Hide the current tab:
        x[currentTab].style.display = "none";
        // Increase or decrease the current tab by 1:
        currentTab = currentTab + n;
        // if you have reached the end of the form... :
        if (currentTab >= x.length) {
            //...the form gets submitted:
            document.getElementById("regForm").submit();
            return false;
        }
        // Otherwise, display the correct tab:
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
</script>
</body>
</html>
