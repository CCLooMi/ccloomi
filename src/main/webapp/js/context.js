/**
 * Created by Chenxj on 2015/8/4.
 */
context = (function () {

	var options = {
		fadeSpeed: 100,
		filter: function ($obj) {
			//编辑$obj，切勿返回值
		},
		targetClickEvent:function(e){
			//处理菜单点击事件
		},
		preventDoubleContext: true
	};
	var currentContextSelector;

	function initialize(opts) {

		options = $.extend({}, options, opts);

		$(document).on('click', function (e) {
			if(e.which!=3){//解决firefox中鼠标点击事件不分的问题(菜单闪退问题)
				$('.dropdown-context').fadeOut(options.fadeSpeed, function(){
					$('.dropdown-context').css({display:''}).find('.drop-left').removeClass('drop-left');
				});
			}
		});
		if(options.preventDoubleContext){
			$(document).on('contextmenu', '.dropdown-context', function (e) {
				e.preventDefault();
			});
		}
		$(document).on('mouseenter', '.dropdown-submenu', function(){
			var $sub = $(this).find('.dropdown-context-sub:first'),
				subWidth = $sub.width(),
				subHeight = $sub.height(),
				subTop = $sub.offset().top,
				subLeft = $sub.offset().left,
				subBottom = window.innerHeight-(subTop+subHeight);
				collision_x = (subWidth+subLeft) > window.innerWidth,
				collision_y = (subHeight+subTop) > window.innerHeight;
			if(collision_x){
				$sub.addClass('drop-left');
			}
			if(collision_y){
				$sub.css({
					top:-subHeight+26
				});
			}else if(subBottom>subHeight){
				$sub.css({
					top:0
				});
			}
		});

	}

	function updateOptions(opts){
		options = $.extend({}, options, opts);
	}

	function buildMenu(data, id, subMenu) {
		var subClass = (subMenu) ? ' dropdown-context-sub' : '',
			$menu = $('<ul class="dropdown-menu dropdown-context' + subClass +'" id="dropdown-' + id + '"></ul>');
        
        return buildMenuItems($menu, data, id, subMenu);
	}

    function buildMenuItems($menu, data, id, subMenu, addDynamicTag) {
	    var linkTarget = '';
        for(var i = 0; i<data.length; i++) {
        	if(data[i]){
				if (data[i].divider) {
					var divider = '<li class="divider';
					divider += (addDynamicTag) ? ' dynamic-menu-item' : '';
					divider += '"></li>';
					$menu.append(divider);
				} else if (data[i].header) {
					var header = '<li class="nav-header';
					header += (addDynamicTag) ? ' dynamic-menu-item' : '';
					header += '">' + data[i].header + '</li>';
					$menu.append(header);
				} else if (data[i].menu_item_src) {
					var funcName;
					if (typeof data[i].menu_item_src === 'function') {
						if (data[i].menu_item_src.name === "") { // The function is declared like "foo = function() {}"
							for (var globalVar in window) {
								if (data[i].menu_item_src == window[globalVar]) {
									funcName = globalVar;
									break;
								}
							}
						} else {
							funcName = data[i].menu_item_src.name;
						}
					} else {
						funcName = data[i].menu_item_src;
					}
					$menu.append('<li class="dynamic-menu-src" data-src="' + funcName + '"></li>');
				} else {
					if (!data[i].href) {
						data[i].href = '#';
					}
					if (data[i].target) {
						linkTarget = ' target="'+data[i].target+'"';
					}
					if (data[i].subMenu) {
						var sub_menu = '<li class="dropdown-submenu';
						sub_menu += (addDynamicTag) ? ' dynamic-menu-item' : '';
						sub_menu += '"><a tabindex="-1" href="' + data[i].href + '">';
						if (typeof data[i].icon !== 'undefined')
							sub_menu += '<span class="' + data[i].icon + '"></span> ';
						sub_menu += data[i].text + '</a></li>';
						$sub = (sub_menu);
					} else {
						var element = '<li';
						element += (addDynamicTag) ? ' class="dynamic-menu-item"' : '';
						element += '><a tabindex="-1" href="' + data[i].href + '"'+linkTarget+'>';
						if (data[i].icon)
							element += '<span class="' + data[i].icon + '"></span> ';
						element += data[i].text + '</a></li>';
						$sub = $(element);
					}
					if (data[i].action) {
						$action = data[i].action;
						$sub
							.find('a')
							.addClass('context-event')
							.on('click', createCallback($action));
					}
					$menu.append($sub);
					if (typeof data[i].subMenu != 'undefined') {
						//递归调用
						var subMenuData = buildMenu(data[i].subMenu, id, true);
						$menu.find('li:last').append(subMenuData);
					}
				}
			}
			//每完成一个li菜单项就调用filter方法一次并将菜单项作为参数传给filter函数
			if (options.filter&&typeof options.filter == 'function') {
				options.filter($menu.find('li:last'));
			}
		}
        return $menu;
    }

	function addContext(selector, data) {
		$(selector).on('contextmenu', function (e) {
			e.preventDefault();
			e.stopPropagation();
			currentContextSelector = $(this);
			showContext(e,data);
		});
	}
	function showContext(e,data){
		var container=options.container||$('html');
		var offset=container.offset();
		var x= e.pageX|| e.pointer.DOM.x;
		x+=offset.left;
		var y= e.pageY|| e.pointer.DOM.y;
		y+=offset.top;
		if(data){
			if (data.id&&data.data) {
				options.id = data.id;
				if (!$menu) {
					$menu = buildMenu(data.data, options.id);
					//$('body').append($menu);
					container.append($menu);
					//绑定click事件
					$menu.on('click',options.targetClickEvent);
				}
			} else if(!options.id){
				var d = new Date();
				options.id = d.getTime();
				$menu = buildMenu(data, options.id);
				//$('body').append($menu);
				container.append($menu);
				//绑定click事件
				$menu.on('click',options.targetClickEvent);
			}else if(options.id){
				//$menu = $('body').find('#dropdown-' + options.id)[0];
				$menu = container.find('#dropdown-' + options.id)[0];
				if(!$menu){
					$menu = buildMenu(data, options.id);
					//$('body').append($menu);
					container.append($menu);
					//绑定click事件
					$menu.on('click',options.targetClickEvent);
				}
			}
		}else{
			return false;
		}

		$('.dropdown-context:not(.dropdown-context-sub)').hide();

		$dd = $('#dropdown-' + options.id);

		$dd.find('.dynamic-menu-item').remove(); // Destroy any old dynamic menu items
		$dd.find('.dynamic-menu-src').each(function(idx, element) {
			//其中$(element).data('src')中指定的方法在buildMenu的时候从menu_item_src中取得
			//执行window中$(element).data('src')中指定的方法将currentContextSelector作为参数传进去，返回结果为动态生成的子菜单
			var menuItems = window[$(element).data('src')](currentContextSelector);
			$parentMenu = $(element).closest('.dropdown-menu.dropdown-context');
			$parentMenu = buildMenuItems($parentMenu, menuItems, options.id, undefined, true);
		});

		$dd.removeClass('dropdown-context-up').find('.drop-left').removeClass('drop-left');
		//此处进行判断菜单向上还是向下显示（没有考虑屏幕比菜单小的情况故这里是有bug的）
		var autoH = $dd.height() + 12;
		if ((y-offset.top + autoH) > container.height()) {
			$dd.addClass('dropdown-context-up').css({
				top: y - autoH,
				left: x
			}).fadeIn(options.fadeSpeed);
		} else {
			$dd.css({
				top: y,
				left: x
			}).fadeIn(options.fadeSpeed);
		}

		$dd.removeClass('dropdown-context-left');
		var autoL = $dd.width() - 12;
		if ((x-offset.left + autoL) > container.width()) {
			$dd.addClass('dropdown-context-left').css({
				left: x - $dd.width()
			});
		}
	}

	function destroyContext(selector) {
		$(document).off('contextmenu', selector).off('click', '.context-event');
	}
	var createCallback = function(func) {
		return function(event) { func(event, currentContextSelector) };
	}
	return {
		init: initialize,
		settings: updateOptions,
		attach: addContext,
		destroy: destroyContext,
		show:showContext
	};
})();
