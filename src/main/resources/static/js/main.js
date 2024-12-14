$(document).ready(function () {
    var div_width = $('#div_suggest .scroll').width();
    if($('#list_suggest li').length * 190 >= div_width){
        setInterval(function(){ scroll_div('right') }, 5000);
        $('#div_suggest .scroll').show();
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