function login(){
		var member_id = $('[name=member_id]').val();
		var password = $('[name=password]').val();
		
		if(member_id == ''){
			alert('아이디를 입력하여주세요.');
			return;
		}else if(password == ''){
			alert('비밀번호를 입력하여주세요.');
			return;
		}else{
			
			$.ajax({
			
				type : 'POST',
				url : '/view/login.do',
				data : ({
					member_id : member_id,
					password : password
				}),
				success : function (data , status , xhr){
					console.log(data);
					
					if(data.indexOf('true') > -1){
						
						console.log(data.indexOf('true'));
						
						alert('로그인 성공');
						location.href='/index.do';
					}else if(data.indexOf('false') > -1){
						
						if(data.indexOf('0') > 0){
						
							console.log(data.indexOf('-1'));
							
							alert('완전불일치');
							
						}else if(data.indexOf('2') > -1){
							
							console.log(data.indexOf('2'));
							
							alert('비밀번호가 틀렸습니다.');
							
						}else if(data.indexOf('3') > -1){
							
							console.log(data.indexOf('3'));
							
							alert('로그인 오류 error - 사이트 관리자에게 문의 부탁드립니다.');
							
						}else if(data.indexOf('4') > -1){
							
							console.log(data.indexOf('4'));
							
							alert('삭제처리된 회원입니다. - 사이트 관리자에게 문의 부탁드립니다.');
							
						}
						
					}
				},
				error : function (error , status , xhr){
					
				}
				
			})
			
			
		}
	}
	
	function typeChange(type){
		console.log(type);
		$('input[name=password]').prop('type',type);
	}