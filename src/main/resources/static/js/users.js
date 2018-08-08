var Users = {
		init: function () {
			$("#datatable-responsive").DataTable({
	        	"aoColumnDefs": [{ "bSortable": false, "aTargets": [9]}]
	        });
		},
		
		Refresh: function(el) {	
			App.loadAjax();
			
			$.ajax({
				type: "POST",
				async: true,
				url: '/users/refresh',
				dataType: 'html',
				success:function(result){
					//$('#datatable-responsive_wrapper').remove();
					$('#users-table').html(result);
					
					$("#datatable-responsive").DataTable({
			        	"aoColumnDefs": [{ "bSortable": false, "aTargets": [9]}]
			        });
					
					App.loadAjax('unmask');
				}
			});
		},
		
		Delete: function (el) {
			var id = $(el).attr("userid");
			console.log(id);
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
						width: 350,
						show: 'blind',
						hide: 'fold',
						modal: true,
						buttons: {
							Yes: {
								text: 'Delete',
								class: 'btn btn-danger',
								click: function() {
									App.loadAjax();
									
									$.ajax({
										url: '/users/delete',
										type: 'post',
										async: true,
										dataType: 'json',
										data: {
											id: id
										},
										success: function (result) {
											Users.Refresh(el);
											App.loadAjax('unmask');
									   		$('#delete-user').remove();
										}
									});
								}
							},
							No: {
								text: 'Cancel',
								class: 'btn btn-default',
								click: function() {
									// making call dialog init
									$(this).dialog( "close" );
							   		$('#delete-user').remove();
								}
							}
						},
						create: function() {
							var buttons = $('.ui-dialog-buttonset').children('button');
		                    buttons.removeClass("ui-button ui-widget ui-state-default ui-state-active ui-state-focus ui-corner-all");
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