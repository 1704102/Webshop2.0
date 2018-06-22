function getCall(rest, dataType) {
    var output = "";
    $.ajax({
        type: 'GET',
        url: rest,
        async: false,
        contentType: 'application/json',
        dataType: dataType,
        processData: true,
        success: function(data) {
            console.log(data);
            output = data;
        }
    });
    return output;
}

function postCall(input, rest, dataType) {
    var output = "";
    console.log(JSON.stringify(input));
    $.ajax({
        type: "POST",
        async: false,
        url: rest,
        data: JSON.stringify(input),
        contentType: "application/json",
        dataType: dataType,
        success: function(data) {
            output = data;
            console.log(data);
        }
    });
    return output;
}

function putCall(input, rest) {
    var output = JSON.parse("{}");
    $.ajax({
        type: 'PUT',
        url: rest,
        async: false,
        processData: true,
        data: JSON.stringify(input),
        contentType: 'application/json',
        dataType: 'json',
        success: function(data) {
            output = data;
            console.log(output);
        }
    });
    return output;
}

function deleteCall(input, rest) {
    $.ajax({
        type: 'DELETE',
        url: rest,
        data: JSON.stringify(input),
        contentType: 'application/json'
    });
}


