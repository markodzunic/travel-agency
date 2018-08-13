var Transports = {
		init: function () {
			$("#datatable-responsive").DataTable({
	        	"aoColumnDefs": [{ "bSortable": false, "aTargets": [1]}]
	        });
		},
		
		Refresh: function(el) {	
			App.loadAjax();
			
			$.ajax({
				type: "POST",
				async: true,
				url: '/transports/refresh',
				dataType: 'html',
				success:function(result){
					//$('#datatable-responsive_wrapper').remove();
					$('#transports-table').html(result);
					
					$("#datatable-responsive").DataTable({
			        	"aoColumnDefs": [{ "bSortable": false, "aTargets": [1]}]
			        });
					
					App.loadAjax('unmask');
				}
			});
		},
		
		Delete: function (el) {
			var id = $(el).attr("transportid");
			console.log(id);
			App.loadAjax();

			$.ajax({
				url: '/transports/delete',
				type: 'get',
				data: {
					id: id
				},
				success: function (result) {
					App.loadAjax('unmask');

					// calling dialog for calls
					$('body').append(result);
					$('#delete-transport').dialog({
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
										url: '/transports/delete',
										type: 'post',
										async: true,
										
										data: {
											id: id
										},
										success: function (result) {
											Transports.Refresh(el);
											App.loadAjax('unmask');
									   		$('#delete-transport').remove();
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
							   		$('#delete-transport').remove();
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
						   	$('#delete-transport').remove();
						}
					});
				}
			});
		},
		
		ShowCities: function (el) {
			var id = $(el).attr("transportid");

			App.loadAjax();

			$.ajax({
				url: '/transports/showTransports',
				type: 'get',
				data: {
					id: id
				},
				success: function (result) {
					App.loadAjax('unmask');

					// calling dialog for calls
					$('body').append(result);
					$('#show-transports').dialog({
						dialogClass: 'dialog-position',
						width: 600,
						show: 'blind',
						hide: 'fold',
						modal: true,
						buttons: {
							Close: {
								text: 'Close',
								class: 'btn btn-info',
								click: function() {
									$(this).dialog( "close" );
								   	$('#show-transports').remove();
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
						   	$('#show-transports').remove();
						}
					});
				}
			});
		}
}