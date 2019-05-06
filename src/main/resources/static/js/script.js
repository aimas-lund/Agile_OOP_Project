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
    $(".update").hide();
    $(".update").removeAttr("required","false");
    switch(option) {
        case "birthday":
            $("#date").show()
            $("#date").attr("required","true")
            break
        case "gender":
            $("#gender").show()
            $("#gender").attr("required","true")
            break;
        case "phoneNumber":
            $("#num").show()
            $("#num").attr("required","true")
            break;
        case "name":
        case "surname":
        case "homeAddress":
            $("#text").show()
            $("#text").attr("required","true")
            break
        default:
            alert("error");
    }
}

$(document).ready(function() {
    $( ".dashboard-menu > h3" ).click(function() {
        $("i",this).toggleClass("fa fa-caret-down fa fa-caret-up")
        $("form",$(this).parent()).toggle();
    });
});



