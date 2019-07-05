function changeElement(url, action, countryId, countryName, cityId, cityName) {
    var place = {
        "cityId": cityId,
        "cityName": cityName,
        "country" : {"countryId": countryId, "countryName": countryName}
    };
    $.ajax({
        type: "POST",
        url: url + action,
        data: JSON.stringify(place),
        async: false,
        contentType: "application/json",
        success: window.location = url
    });
}

function validateInput(elementName) {
    var element = document.getElementById(elementName);
    var rsl = false;
    if (element.value === '') {
        alert(element.placeholder);
    } else {
        rsl = true;
    }
    return rsl;
}

function validate() {
    var rsl = false;
    var first_name = $("#first_name");
    var last_name = $("#last_name");
    var description = $("#description");
    var sex = $('#male').prop("checked") || $('#female').prop('checked');
    if (first_name.val() === '') {
        alert(first_name.prop('placeholder'));
    } else if (last_name.val() === '') {
        alert(last_name.prop('placeholder'));
    } else if (description.val() === '') {
        alert(description.prop('placeholder'));
    } else if (!sex) {
        alert('select your gender ');
    } else {
        rsl = true;
    }
    return rsl;
}

function createRow(first_name, last_name, description, gender) {
    $('#table > tbody:last-child').append('<tr>'
        + '<td>' + first_name + '</td>'
        + '<td>' + last_name + '</td>'
        + '<td>' + description + '</td>'
        + '<td>' + gender + '</td>'
        + '</tr>'
    );
}

function addRow() {
    var first_name = $("#first_name").val();
    var last_name = $("#last_name").val();
    var description = $("#description").val();
    var gender = $('#male').prop("checked") ? 'male' : 'female';
    createRow(first_name, last_name, description, gender);
    $('#user_form')[0].reset();
}

function sendPerson() {
    if (validate()) {
        var person = {
            "FirstName": $("#first_name").val(),
            "LastName": $("#last_name").val(),
            "Description": $("#description").val(),
            "Gender": $("input[name=sex]:checked").val()
        };
        $.ajax({
            type: "POST",
            url: "././persons",
            data: JSON.stringify(person),
            contentType: "application/json",
            success: addRow()
        });
    }
}

function removeRows() {
    var table = document.getElementById("table");
    for (i = table.tBodies[0].rows.length - 1; i >= 0; i--) {
        table.tBodies[0].deleteRow(i)
    }
}

function getPersons() {
    $.ajax({
        type: "GET",
        url: "././persons",
        success: function(data, textStatus, request){
            removeRows();
            var list = JSON.parse(request.getResponseHeader('persons'));
            for (i = list.length - 1; i >= 0; i--) {
                createRow(list[i].FirstName, list[i].LastName, list[i].Description, list[i].Gender);
            }
        }
    });
}

function deleteElement(url, id) {
    $.ajax({
        type: "POST",
        url: url,
        data: "action=delete&id=" + id,
        success: window.location = url
    })
}