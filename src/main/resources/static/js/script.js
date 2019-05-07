$(document).ready(function() {
    $( ".dashboard-menu > h3" ).click(function() {
        $("i",this).toggleClass("fa fa-caret-down fa fa-caret-up");
        $("form",$(this).parent()).toggle();
    });
});

function upPatient() {
    var option = $("#selectParamPatient").val();
    $(".updatePatient").hide();
    $(".updatePatient").removeAttr("required","false");
    if (option === "birthday") {
        var id = "#dateP"
    } else if (option === "gender") {
        var id = "#genderP"
    } else if (option === "phoneNumber") {
        var id = "#numP"
    } else if (option === "name" || option === "surname" || option === "homeAddress") {
        var id = "#textP"
    }
    $(id).show();
    $(id).attr("required","true")
}
function upStaff() {
    var option = $("#selectParamStaff").val();
    $(".updateStaff").hide();
    $(".updateStaff").removeAttr("required","false");
    if (option === "birthday") {
        var id = "#dateS"
    } else if (option === "gender") {
        var id = "#genderS"
    } else if (option === "phoneNumber") {
        var id = "#numS"
    } else if (option === "role") {
        var id = "#roleS"
    } else if (option === "name" || option === "surname" || option === "homeAddress" || option === "email") {
        var id = "#textS"
    }
    $(id).show();
    $(id).attr("required","true")
}

function addDepartment() {
    var option = $("#departmentType").val();
    if (option === "out") {
        $("#capacity").hide();
        $("#capacity").removeAttr("required","false")
    } else {
        $("#capacity").show();
        $("#capacity").attr("required","true")
    }
}
function roleStaff() {
    var option = $("#role").val();
    if (option === "Doctor") {
        $("#speciality").show();
        $("#speciality").attr("required","true")
    } else {
        $("#speciality").hide();
        $("#speciality").removeAttr("required","true")
    }
}