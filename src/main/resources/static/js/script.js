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
    $(".update","#updatePatient").hide();
    $(".update","#updatePatient").removeAttr("required","false");
    switch(option) {
        case "birthday":
            $("#date","#updatePatient").show()
            $("#date","#updatePatient").attr("required","true")
            break
        case "gender":
            $("#gender","#updatePatient").show()
            $("#gender","#updatePatient").attr("required","true")
            break;
        case "phoneNumber":
            $("#num","#updatePatient").show()
            $("#num","#updatePatient").attr("required","true")
            break;
        case "name":
        case "surname":
        case "homeAddress":
            $("#text","#updatePatient").show()
            $("#text","#updatePatient").attr("required","true")
            break
        default:
            alert("error");
    }
}
function upStaff() {
    var option = $("#selectParamStaff").val();
    $(".update","#updateStaff").hide(); $(".update","#updateStaff").removeAttr("required","false");
    switch(option) {
        case "birthday":
            $("#date","#updateStaff").show()
            $("#date","#updateStaff").attr("required","true")
            break
        case "gender":
            $("#gender","#updateStaff").show()
            $("#gender","#updateStaff").attr("required","true")
            break;
        case "phoneNumber":
            $("#num","#updateStaff").show()
            $("#num","#updateStaff").attr("required","true")
            break;
        case "role":
            $("#role","#updateStaff").show()
            $("#role","#updateStaff").attr("required","true")
            break;
        case "name":
        case "surname":
        case "homeAddress":
        case "email":
            $("#text","#updateStaff").show()
            $("#text","#updateStaff").attr("required","true")
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
