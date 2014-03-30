/*-----------------------------------------------------------------------------------

 	Custom JS - All front-end jQuery
 
-----------------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------------*/
/*	Remove JavaScript fallback class
/*-----------------------------------------------------------------------------------*/
 
jQuery('#container').removeClass('js-disabled');
 
/*-----------------------------------------------------------------------------------*/
/*	Let's get ready!
/*-----------------------------------------------------------------------------------*/

jQuery(document).ready(function($) {


/*-----------------------------------------------------------------------------------*/
/*	Widget Overlay Stuff
/*-----------------------------------------------------------------------------------*/
	
    function zillaWidgetOverlay() {
    	var $widgetOverlay = $('#widget-overlay-container'),
    	    $widgetTrigger = $('#overlay-open a'),
    	    widgetOverlayHeight = $widgetOverlay.height() + 3;

    	$widgetOverlay.css({
    		marginTop : -widgetOverlayHeight,
    		display : 'block'
    	});

    	$widgetTrigger.toggle(
    	    function() {
                $widgetOverlay.animate({
                    marginTop : 0
                }, 800, 'easeOutBounce');
                $widgetTrigger.addClass('close');
            }, function() {
                $widgetOverlay.animate({
                    marginTop : -widgetOverlayHeight
                }, 800, 'easeOutBounce');
                $widgetTrigger.removeClass('close');
    	});
    }
    setTimeout(zillaWidgetOverlay, 500);


});