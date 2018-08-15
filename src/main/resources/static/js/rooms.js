var Rooms = {
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
				url: '/rooms/refresh',
				dataType: 'html',
				success:function(result){
					//$('#datatable-responsive_wrapper').remove();
					$('#rooms-table').html(result);
					
					$("#datatable-responsive").DataTable({
			        	"aoColumnDefs": [{ "bSortable": false, "aTargets": [1]}]
			        });
					
					App.loadAjax('unmask');
				}
			});
		},
		
		Delete: function (el) {
			var id = $(el).attr("roomid");
			console.log(id);
			App.loadAjax();

			$.ajax({
				url: '/rooms/delete',
				type: 'get',
				data: {
					id: id
				},
				success: function (result) {
					App.loadAjax('unmask');

					// calling dialog for calls
					$('body').append(result);
					$('#delete-room').dialog({
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
										url: '/rooms/delete',
										type: 'post',
										async: true,
										
										data: {
											id: id
										},
										success: function (result) {
											Rooms.Refresh(el);
											App.loadAjax('unmask');
									   		$('#delete-room').remove();
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
							   		$('#delete-room').remove();
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
						   	$('#delete-room').remove();
						}
					});
				}
			});
		}
}