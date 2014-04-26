$(document).ready(function($) {
	
	$ = jQuery;
		
		// Nav nesting
		$('#desktop-nav > ul > li > ul')
		.parent('li').addClass('nesting');
		
		
        //Sliders
		
		$( ".slider" ).cycle({
			fx: 'scrollHorz',
			next:   '.next', 
    		prev:   '.per',
			timeout: 0,
		});
		$( "#slider" ).cycle({
			fx: 'scrollHorz',
			next:   '#next', 
    		prev:   '#per',
			timeout: 0,
			pager: '.slide-opetion',
		});
		$( "#slider-c" ).cycle({
			fx: 'fade',
			next:   '#forward', 
    		prev:   '#prev',
			timeout: 0
		});
		$( ".block" ).cycle({
			fx:'scrollHorz',
			pager: '.quote-option'
		});
		$( "#accordion" ).accordion();
		
		$("#desktop-nav > ul > li").hover(function(){
			$(this).children("ul").stop(true,true).slideDown(500);
		},function(){
			$(this).children("ul").stop(true,true).slideUp(500);
		});
		$("#desktop-nav > ul > li > ul li").hover(function(){
			$(this).children("ul").stop(true,true).slideDown(500);
		},function(){
			$(this).children("ul").stop(true,true).slideUp(500);
		});
		
		// Hover Effect
		$(".product").hover(function(){
			$(this).children('.p-overlay').stop(true, true).fadeIn(500);
		}, function(){
			$(this).children('.p-overlay').stop(true, true).fadeOut(500);
		});
		$("figure").hover(function(){
			$(this).children('.overlay').stop(true, true).fadeIn(500);
		}, function(){
			$(this).children('.overlay').stop(true, true).fadeOut(500);
		});
		//submenu overlay
		$(".showroom-pic").hover(function(){
			$(this).children('.submenu-overlay').stop(true, true).fadeIn(500);
		}, function(){
			$(this).children('.submenu-overlay').stop(true, true).fadeOut(500);
		});
		//Time Line Hover
		$(".timeline-image").hover(function(){
			$(this).children('.time-overlay').stop(true, true).fadeIn(500);
		}, function(){
			$(this).children('.time-overlay').stop(true, true).fadeOut(500);
		});
		//Tabs
		$('ul.tabs').each(function(){
			var $active, $content, $links = $(this).find('a');
		
			$active = $($links.filter('[href="'+location.hash+'"]')[0] || $links[0]);
			$active.addClass('active');
			$content = $($active.attr('href'));

			$links.not($active).each(function () {
				$($(this).attr('href')).hide();
			});
			
			$(this).on('click', 'a', function(e){
				$active.removeClass('active');
				$content.hide();

				$active = $(this);
				$content = $($(this).attr('href'));

				$active.addClass('active');
				$content.show();

				e.preventDefault();
			});
		});		
		//Login
		$('#login-link').click(function(){
			$('.login-popup-wrapper').fadeIn('slow',function(){
				$(this).children('#login-popup').fadeIn('fast');			
			});
		});
		$('.close').click(function(){
			$(this).parents('.login-popup-wrapper').fadeOut('slow');
			$(this).parent('#login-popup').fadeOut('slow');
		});
		
		//Chat
		$('#chat').click(function(){
			$('.chat-popup-wrapper').fadeIn('slow',function(){
				$(this).children('#chat-popup').fadeIn('fast');			
			});
		});
		$('.close').click(function(){
			$(this).parents('.chat-popup-wrapper').fadeOut('slow');
			$(this).parent('#chat-popup').fadeOut('slow');
		});
		
		
		$('.responsive-timeline').rTimeline({theme: 'light', url: 'load_more_post.html'});
				
		// Select Boxes
		$(" form SELECT").selectBox();
		
		// Datepicker
		$('input.data-range').datepicker();
		
		//Responsive Menu
		$('.responsive-nav').on('change', function() {
			window.location = $(this).val();
		});
		
		// Range
		$( "#slider-range" ).slider({
				range: true,
				min: 0,
				max: 500,
				values: [ 75, 300 ],
				slide: function( event, ui ) {
				$( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
			}
		});
		$( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
			" - $" + $( "#slider-range" ).slider( "values", 1 ) );
		
			$( "#mileage-range" ).slider({
				range: true,
				min: 0,
				max: 500,
				values: [ 75, 300 ],
				slide: function( event, ui ) {
				$( "#amount2" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
			}
		});
		$( "#amount2" ).val( "$" + $( "#mileage-range" ).slider( "values", 0 ) +
			" - $" + $( "#mileage-range" ).slider( "values", 1 ) );
			
		// Filteration
		$(function(){
	  
				var $container = $('#project-eliment');
				
				$container.isotope({
					itemSelector : '.item'
				});
				
				var $optionSets = $('.my-selector'),
				  $optionLinks = $optionSets.find('a');
				
				$optionLinks.click(function(){
				var $this = $(this);
				if ( $this.hasClass('selected') ) {
				  return false;
				}
				var $optionSet = $this.parents('.my-selector');
				$optionSet.find('.selected').removeClass('selected');
				$this.addClass('selected');
				var options = {},
					key = $optionSet.attr('data-option-key'),
					value = $this.attr('data-option-value');
				value = value === 'false' ? false : value;
				options[ key ] = value;
				if ( key === 'layoutMode' && typeof changeLayoutMode === 'function' ) {
				  changeLayoutMode( $this, options )
				} else {
				  $container.isotope( options );
				}
				
				return false;
				});
		
		  
		});
		
});


var tpj=jQuery;               // MAKE JQUERY PLUGIN CONFLICTFREE
      tpj.noConflict();
                
      tpj(document).ready(function() {
                
                   if (tpj.fn.cssOriginal!=undefined)   // CHECK IF fn.css already extended
                   tpj.fn.css = tpj.fn.cssOriginal;
 
                    tpj('.fullwidthbanner').revolution(
						{	
							delay:6000,												
							startwidth:960,
							startheight:300,
							
							onHoverStop:"on",						// Stop Banner Timet at Hover on Slide on/off
							
							thumbWidth:100,							// Thumb With and Height and Amount (only if navigation Tyope set to thumb !)
							thumbHeight:50,
							thumbAmount:4,
							
							hideThumbs:200,
							navigationType:"none",					//bullet, thumb, none, both	 (No Shadow in Fullwidth Version !)
							navigationArrows:"verticalcentered",		//nexttobullets, verticalcentered, none
							navigationStyle:"round",				//round,square,navbar
							
							touchenabled:"on",						// Enable Swipe Function : on/off
							
							navOffsetHorizontal:0,
							navOffsetVertical:20,
							
							fullWidth:"on",
							
							shadow:0								//0 = no Shadow, 1,2,3 = 3 Different Art of Shadows -  (No Shadow in Fullwidth Version !)
														
						});	
						});