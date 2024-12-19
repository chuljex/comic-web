$(document).ready(function () {
    var div_width = $('#div_suggest .scroll').width();
    if($('#list_suggest li').length * 190 >= div_width){
        setInterval(function(){ scroll_div('right') }, 5000);
        $('#div_suggest .scroll').show();
    }
    var device = 0;
    if(device == 0){
            $(".qtip").hover(function () {
                var e = $($(this).data("qtip"));
                if (!e.length) return !1;
                $('<div id="info_tip"></div>').html(e.html()).appendTo("body").fadeIn("300")
            }, function () {
                $("#info_tip").remove()
            }).mousemove(function (e) {
                var t = e.pageX + 20, i = e.pageY + 10;
                var width = $(document).width()/2;
                var height = $(window).height()/2;

                if(e.pageX > width && width < $(document).width()){
                    t = t - $("#info_tip").width() - 50;
                }
                if(e.clientY > height && height < $(window).height()){
                    i = i - $("#info_tip").height() - 40;
                }
                $("#info_tip").css({top: i, left: t});
            })
        }
});
div_suggest_margin = 0;
function scroll_div(target){
    var div_width = $('#div_suggest .scroll').width();
    var num_block = $('#list_suggest li').length;
    var max_block = Math.floor(div_width/190);
    if(target == 'right'){
        if(div_suggest_margin == -10){
            div_suggest_margin = div_width - (((max_block+1)*190) - 10);
        }else{
            div_suggest_margin = div_suggest_margin - 190;
        }
        if(div_suggest_margin <= -10 - ((num_block*190) - div_width)){
            div_suggest_margin = -10;
        }
    }else if(target == 'left'){
        div_suggest_margin = -10;
    }
    $('#list_suggest').css('margin-left',div_suggest_margin+'px');
}
function show_hidden_div(target){
    if($(".hidden_div[target="+target+"]").css("display") == "none"){
        $(".hidden_div").hide();
        $(".hidden_div[target="+target+"]").show();
    }else{
        $(".hidden_div").hide();
    }
}