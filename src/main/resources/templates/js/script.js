function searchPatientParam() {
    var option = $("#searchPatientParam").val();
    $(".patient").hide();
    $(".patient").removeAttr("required","false");
    $("#p" + option).show();
    $("#p" + option).attr("required", "true");

}
function searchStaffParam() {
    var option = $("#searchStaffParam").val();
    $(".staff").hide();
    $(".staff").removeAttr("required","false");
    $("#s" + option).show();
    $("#s" + option).attr("required", "true");

}
$(document).ready(function() {
   $( ".dashboard-menu > h3" ).click(function() {
       $("i",this).toggleClass("fa fa-caret-down fa fa-caret-up")
       $("form",$(this).parent()).toggle();
   }); 
});



