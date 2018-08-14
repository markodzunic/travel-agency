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
    },
    
    galleryMosaic: function (items) {
    	$("#nanogallery").nanogallery2({

            kind: 'nano_photos_provider2',
            dataProvider: 'https://nano.gallery/nano_photos_provider2/nano_photos_provider2.php',
            album: 'oceans',
            galleryDisplayTransition: 'rotateX',
            galleryDisplayTransitionDuration: 1500,
           
            ZgalleryMosaic : [
              { w: 4, h: 3, c: 1, r: 1 },
              { w: 2, h: 2, c: 5, r: 1 },
              { w: 2, h: 2, c: 7, r: 1 },
              { w: 4, h: 3, c: 5, r: 3 },
              { w: 2, h: 2, c: 1, r: 4 },
              { w: 2, h: 2, c: 3, r: 4 }
            ],
            galleryMosaic : [
              { w: 2, h: 2, c: 1, r: 1 },
              { w: 1, h: 1, c: 3, r: 1 },
              { w: 1, h: 1, c: 3, r: 2 },
              { w: 1, h: 2, c: 4, r: 1 },
              { w: 2, h: 1, c: 5, r: 1 },
              { w: 2, h: 2, c: 5, r: 2 },
              { w: 1, h: 1, c: 4, r: 3 },
              { w: 2, h: 1, c: 2, r: 3 },
              { w: 1, h: 2, c: 1, r: 3 },
              { w: 1, h: 1, c: 2, r: 4 },
              { w: 2, h: 1, c: 3, r: 4 },
              { w: 1, h: 1, c: 5, r: 4 },
              { w: 1, h: 1, c: 6, r: 4 }
            ],

            thumbnailAlignment: 'scaled',

            thumbnailHeight: '180', thumbnailWidth: '220',
            thumbnailBaseGridHeight: 80,
            
            thumbnailDisplayTransition: 'slideLeft',
            thumbnailDisplayTransitionDuration: 600,
            thumbnailDisplayInterval: 90,
            galleryMaxRows: 1,
            galleryDisplayMode: 'rows',

            thumbnailL1GutterWidth: 0,
            thumbnailL1GutterHeight: 0,
            thumbnailBorderHorizontal: 0,
            thumbnailBorderVertical: 0,

            thumbnailL1BuildInit2: '.nGY2GThumbnailAlbumTitle_border-left_5px solid #23CB99|.nGY2GThumbnailAlbumTitle_margin_20px|\
                  title_backgroundColor_rgba(200,200,200,0.8)|title_color_#000',

            thumbnailL1HoverEffect2: 'label_left_-100%_0%|thumbnail_scale_1.00_0.95',

            galleryBuildInit2 : 'perspective_900px|perspective-origin_50% 150%',

            thumbnailHoverEffect2: 'label_font-size_1em_1.5em|title_backgroundColor_rgba(255,255,255,0.34)_rgba(((35,203,153,0.8)|title_color_#000_#fff|image_scale_1.00_1.10_5000|image_rotateZ_0deg_4deg_5000',

            thumbnailToolbarImage : null,
            thumbnailToolbarAlbum: null,

            thumbnailLabel: { display: false, position:'onBottomOverImage', hideIcons: true, titleFontSize: '1em', align: 'left', titleMultiLine:true, displayDescription: false},
            
            gallerySorting: 'random',

            galleryTheme : { 
              thumbnail: { titleShadow : 'none', descriptionShadow : 'none', titleColor: '#fff' },
              navigationBreadcrumb: { background : '#3C4B5B' },
              navigationFilter: { background : '#2E7C7F', backgroundSelected: '#19676B', color: '#eee' }
            },
            touchAnimation: false,
            locationHash: true
          });
    }
}