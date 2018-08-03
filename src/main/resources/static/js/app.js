var App = {
		
	loadAjax: function (type, element) {
        if (type === 'unmask') {
            $('#screen-load').remove();
        } else if (typeof element !== 'undefined') {
            element.append('<div id="screen-load"><img src="../images/loader.gif" class="ajax-loader"/></div>');
        } else {
            $('body').append('<div id="screen-load"><img src="../images/loader.gif" class="ajax-loader"/></div>');
        }
    }
}