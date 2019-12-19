$(document).ready(function () {
    $("form").animate({top: "+=1000"}, 2000);

    function addClass() {
        $("form").addClass('transform');
    }

    setTimeout(addClass, 1900);
});