var App = {
		
	loadAjax: function (type, element) {
        if (type === 'unmask') {
            $('#screen-load').remove();
            $('.ajax-loader').remove();
        } else if (typeof element !== 'undefined') {
            element.append('<div id="screen-load"></div><img src="../images/loader.gif" class="ajax-loader"/>');
        } else {
            $('body').append('<div id="screen-load"></div><img src="../images/loader.gif" class="ajax-loader"/>');
        }
    }
}