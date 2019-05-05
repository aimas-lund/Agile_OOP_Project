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

$(document).ready(function() {
   $( ".dashboard-menu > h3" ).click(function() {
       $("i",this).toggleClass("fa fa-caret-down fa fa-caret-up")
       $("form",$(this).parent()).toggle();
   }); 
});



