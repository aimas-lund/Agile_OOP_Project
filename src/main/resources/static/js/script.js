function searchPatient() {
    var option = $("#searchPatientParam").val();
    $(".searchPatient").hide();
    $(".searchPatient").removeAttr("required","false");
    $("#p" + option).show();
    $("#p" + option).attr("required", "true");
}

function searchStaff() {
    var option = $("#searchStaffParam").val();
    $(".searchStaff").hide();
    $(".searchStaff").removeAttr("required","false");
    $("#s" + option).show();
    $("#s" + option).attr("required", "true");
}

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
    $(id).show()
    $(id).attr("required","true")
}
function upStaff() {
    var option = $("#selectParamStaff").val();
    $(".updateStaff").hide();
    $(".updateStaff","#updateStaff").removeAttr("required","false");
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
    $(id).show()
    $(id).attr("required","true")
}

$(document).ready(function() {
    $( ".dashboard-menu > h3" ).click(function() {
        $("i",this).toggleClass("fa fa-caret-down fa fa-caret-up")
        $("form",$(this).parent()).toggle();
    });
});
