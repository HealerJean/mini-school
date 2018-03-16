(function() {
	var blogname = 'write',
		address = window.location.host.split(".")[0], // 获取二级域名
		homeAdress = {
			feed: 'http://mp.blog.csdn.net/postlist',
			blog: 'http://write.blog.csdn.net/'
		},

		isFeed = false, // 是否feed首页
		popu_content = {
			popuNUm: 'popu_583',
			pid: 'write',
			push: 'get'
		}
	// 判断二级域名是否显示切换开关
	//if ((address == blogname)) return;
	// 判断是否是详情页
    if (address === 'mp') {

		isFeed = true;
		popu_content = {
			popuNUm: 'popu_584',
			pid: 'mp',
			push: 'get'
		}
	}
	// 使用onload 事件加载
    function addLoadEvent(func)
    {
		var oldonload = window.onload;
		if (typeof window.onload != 'function') {
			window.onload = func;
		} else {
			window.onload = function() {
				oldonload();
				func();
			}
        }
        console.log(window.onload)
	};

    function isSwitchAddress()
    {
		var switchButtonNew = '去新版';
        var text = '<span style="color:red;">严禁讨论涉及中国之军/政相关话题，违者会被禁言、封号!</span>',
			activationDom = '.switch-old';
        if (isFeed) text = '欢迎体验CSDN创作中心alpha版，有任何意见或建议请<a href="http://bbs.csdn.net/forums/BlogQuestion" style="color:#4093c6;" target="_blank">前往博客站务专区</a>';
		var mainLeft = 0;
		try {
			mainLeft = $('main').length > 0 ? $('main').offset().left : $('.blog_l').offset().left;
		} catch (e) {
			mainLeft = 150;
		}
		var switchDOMText = '<div class="switchDOM " data-poputype="feed" data-feed-show="false" style ="display:none;height:35px;background: #FAFAFA;box-shadow: 0 1px 2px 0 rgba(0,0,0,0.1);margin-bottom:1px;">\
                      <div class="switch-text" style="padding-left:' + mainLeft + 'px;padding-right:16px;"><span style="line-height:35px;font-size: 14px;color: #4F4F4F;">' + text + '</span>\
                        <div class="switch-bottom csdn-tracking-statistics" style="float:right">\
                          <a class="switch-old" href="javascript:void(0);" style="padding-bottom: 5px;padding-top: 5px;line-height:35px;font-size:12px;padding-left:10px;padding-right:10px;" target="_self">旧版</a>\
                          <a class="switch-new" href="javascript:void(0);" style="padding-bottom: 5px;padding-top: 5px;line-height:35px;font-size:12px;padding-left:10px;padding-right:10px;" target="_self">' + switchButtonNew + '</a>\
                        </div>\
                      </div>\
                      <style>\
                      .switch-old,.switch-new,.switch-new:link,.switch-new:visited,.switch-old:link,.switch-old:visited{color:#999;}\
                      body .switch-activation{border-radius: 16px;background-color: #5B5B5B;color:#fff !important}\
                      body .switch-old:hover,body .switch-new:hover{color: #4F4F4F;}\
                      body .switch-activation:hover{color:#999;} \
                      </style>\
                    </div>'
		var bodyDOM = $('body');
		bodyDOM.prepend(switchDOMText);
		csdn.trackingAd('.switch-bottom', {
			pid: popu_content.pid,
			mod: popu_content.popuNUm,
			dsm: popu_content.push,
			mtp: '1'
		});
		if (isFeed) activationDom = '.switch-new';
		$(activationDom).addClass('switch-activation');
		$('.switchDOM').show(500);
		$('.switch-old').on('click', function() {
            if (address === blogname) return
			window.location.href = homeAdress.blog + "?feed=0";
		})
		$('.switch-new').on('click', function() {
			if (address === 'mp') return
			localStorage.setItem('switchHome', 1);
			window.location.href = homeAdress.feed;
		})
	}
    
    addLoadEvent(isSwitchAddress);
})();
