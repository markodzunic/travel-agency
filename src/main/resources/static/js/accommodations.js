var Accommodations = {
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
				url: '/accommodations/refresh',
				dataType: 'html',
				success:function(result){
					//$('#datatable-responsive_wrapper').remove();
					$('#accommodations-table').html(result);
					
					$("#datatable-responsive").DataTable({
			        	"aoColumnDefs": [{ "bSortable": false, "aTargets": [9]}]
			        });
					
					App.loadAjax('unmask');
				}
			});
		},
		
		Delete: function (el) {
			var id = $(el).attr("accommodationid");
			console.log(id);
			App.loadAjax();

			$.ajax({
				url: '/accommodations/delete',
				type: 'get',
				data: {
					id: id
				},
				success: function (result) {
					App.loadAjax('unmask');

					// calling dialog for calls
					$('body').append(result);
					$('#delete-accommodation').dialog({
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
										url: '/accommodations/delete',
										type: 'post',
										async: true,
										dataType: 'json',
										data: {
											id: id
										},
										success: function (result) {
											Accommodations.Refresh(el);
											App.loadAjax('unmask');
									   		$('#delete-accommodation').remove();
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
							   		$('#delete-accommodations').remove();
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
						   	$('#delete-accommodation').remove();
						}
					});
				}
			});
		}
}