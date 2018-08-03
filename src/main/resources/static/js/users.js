var Users = {
		init: function () {
			$("#datatable-responsive").DataTable({
	        	"aoColumnDefs": [{ "bSortable": false, "aTargets": [9]}]
	        });
		},
		
		Delete: function (el) {
			var id = $(el).attr("user-id");
			
			App.loadAjax();

			$.ajax({
				url: '/users/delete',
				type: 'get',
				data: {
					id: id
				},
				success: function (result) {
					App.loadAjax('unmask');

					// calling dialog for calls
					$('body').append(result);
					$('#delete-user').dialog({
						dialogClass: 'dialog-position',
						width: 1000,
						modal: true,
						buttons: {
							Yes: {
								text: 'Yes',
								class: 'btn btn-custom assign-btn pull-right',
								click: function() {
									// making call dialog init
									$(this).dialog( "close" );
							   		$('#delete-user').remove();
								}
							},
						},
						open: function() {
						},
						close: function() {
						   	$(this).dialog( "close" );
						   	$('#delete-user').remove();
						}
					});
				}
			});
		}
}