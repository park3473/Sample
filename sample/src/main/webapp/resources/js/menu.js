$.ajax({
			url : '/view/menu.do',
			type : 'POST',
			dataType : 'json',
			success : function(data , status , xhr){
				
				console.log('success : ' + status);
				console.log(data);
				var html = '';
				for(i =0; i < data.list.length; i ++){
					
					
					if(data.list[i].depth == 0){
						if(data.list[i].UNDER_CNT > 0){
							
							html += `<li><a href="#"><span>`+data.list[i].name+`</span></a>`;
							html += `<ul class="depth2">`;
							
							var j_cnt = data.list[i].UNDER_CNT;
							
							console.log(j_cnt);
							for(j=0; j < j_cnt; j++){
								
								
								i += 1;
								console.log(data.list[i]);
								if(data.list[i].link == null || data.list[i].link == ''){
								
									html +=`<li><a href="#"><span>`+data.list[i].name+`</span></a></li>`;
									
								}else{
									
									html +=`<li><a href="`+data.list[i].link+`"><span>`+data.list[i].name+`</span></a></li>`;
									
								}
								
							}
							
							html += `</ul>`;
							html += `</li>`;
							
							
						}else{
							
							html += `<li><a href="#"><span>`+data.list[i].name+`</span></a></li>`;
							
						}	
					}else{
								
					}
					
				}
				//메뉴 부분 구성
				console.log(html);
				$('#menu_list').append(html);
				
				//메뉴 부분 navi 설정
				var header = $("#header");
				var windowWidth = $(window).width();

				$(".btn-gnb-open").on("click",function () { // [p] 20190503 수정
					m_gnb_open ();
				});
				$(".btn-gnb-close").on("click",function () { // [p] 20190503 수정
					m_gnb_close ();
				});
				

				// PC GNB 2Depth
				$(".gnb-menu li").on("mouseover", function () {
					depth2_open(this);
				});

				// [p] 20190514 수정
				$(".nav-inner").mouseleave(function () { 
					depth2_close(this);
				});

				// Mobile GNB 2Depth
				$(".gnb-menu li > a").on("click",function () {
					m_depth2_open(this);
				});

				// 언어 선택
				$(".lang-list").children(".lang").on("click",function () {
					$(this).next(".select-lang").toggleClass("hide");
				});

				$(".select-lang").children("li").each(function () {
					$(this).find("a").click(function () {
						var selected = $(this).children("span").html();
						$(this).parent("li").addClass("on").siblings("li").removeClass("on")
						$(this).parent().parent(".select-lang").addClass("hide").siblings(".lang").children("span").html(selected);
					})
				});


				$(window).resize(function () {
					if($(window).width() >= 768 && $("#header").hasClass("m-gnb-open")) {
						m_gnb_close();
					}
					m_mode()

					// [p] 20190509 수정
					if (windowWidth >= 768) {
						$(".nav-inner").on("mouseleave", function () { 
							depth2_close(this);
						});
					}
				});

			// 스크롤
				$(window).on("scroll", function(){
			       goTop();
			       scroll_header (); // 스크롤시 header
			    });
				
			},
			error : function(status , xhr){
				
				console.log('error : ' + status);
				
			}
		})