function login(){
	
	var member_id = $('[name=member_id]').val();
	var password = $('[name=password]').val();
	
	$.ajax({
		
		type : 'POST',
		url : '/view/login.do',
		cache : false,
		data : ({
			member_id : member_id,
			password : password
		}),
		success : function(result , status , xhr){
		
			console.log(result);
			var comfirm = result.indexOf('true');
			if(comfirm > -1){
				location.href='/index.do';
				return;
			}else if(result.indexOf('false:0')){
				
				console.log('전체 불일치');
				alert('아이디가 존재하지 않습니다.');
				return;
				
			}else if(result.indexOf('false:2')){
				
				console.log('아이디만 성공.');
				alert('비밀번호가 일치하지 않습니다.')
				return;
			}else if(result.indexOf('false:3')){
				
				console.log('로그인 이상')
				alert('로그인 오류! 오류가 반복될시 관리자에게 문의 부탁드립니다.');
				return;
				
			}
			
		},
		error : function(error , status , xhr){
			
		}
		
	})
	
}