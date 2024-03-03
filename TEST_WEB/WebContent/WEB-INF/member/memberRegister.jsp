<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  	function add2() {
  		if($("#file").val()!=''){
  			var formData=new FormData(); //바이너리 데이터가 넘어감
  			formData.append("file",$("input[name=file]")[0].files[0]);
  			$.ajax({
  				url : "<c:url value='/fileAdd.do'/>",
  				type : "post",
  				data : formData,
  				processData : false, //file 속성
  				contentType : false, //file 속성
  				success : function(data) { // 업로드된 실제파일 이름을 전달받기
  					$('#filename').val(data);
  					alert(data);
  					document.form1.action="<c:url value='memberInsert.do'/>?mode=fadd";
  					// text데이터를 저장하는 부분
  					document.form1.submit(); //id,pass,name,age,email,phone,filename
  					
  				},
  				error : function() {alert("errror");}
  					
  			});
  		}else{ // 파일이 첨부 되지 않았을 때
				document.form1.action="<c:url value='memberInsert.do'/>?mode=add";
					// text데이터를 저장하는 부분
					document.form1.submit(); //id,pass,name,age,email,phone
  		}
  	}
  	
  	function add() {
  		// form의 데이터 유효성 체크
  		document.form1.action="<c:url value='/memberInsert.do'/>"
  		document.form1.submit();
  	}
    function formreset(){
    	document.form1.reset();
    }
    function doublecheck(){
    	if($("#id").val()==''){
    		alert("아이디를 입력하세요.");
    		$("#id").focus();
    	}
    	var id =$('#id').val();
    	$.ajax({
    		
    		url : "<c:url value='/memberDbcheck.do'/>",
    		type :  "POST",
    		data : {"id" : id}, // post로 넘기고 이후 다시 결과값을 가져온다.
    		success : dbCheck, //함수(callback)
    		error : function() {alert("error"); }
    	});
    }
    function dbCheck(data){
    	if(data != "null"){
    		alert("유효한 아이디입니다.")
    		$("#id").focus();
    	}else{
        	alert("중복된 아이디입니다.")
        	$("#id").focus();
    	}
    }
  </script>
</head>
<body>
<div class="container">
  <h2>[회원가입]</h2>
  <div class="panel panel-default">
  </div>
    <div class="panel-heading">
    <c:if test="${sessionScope.userId!=null && sessionScope.userId!='' }">
    	<label>
	
		  		${sessionScope.userName}님이 로그인 하셨습니다.
    	</label>
    	
    	
    </c:if>
    <c:if test="${sessionScope.userId==null && sessionScope.userId=='' }">
    	<label>${sessionScope.userName}안녕하세요.</label>
    </c:if>
   	</div>
    <div class="panel-body">

		<form id= "form1" name = "form1" class="form-horizontal" method="post">
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="id">아이디:</label>
		    <div class="col-sm-10">
		      <table>
		      	<tr>
		      	<td><input type="text" class="form-control" id="id" name="id" placeholder="id를 입력하세요.">
		      </td>
		      	<td><input type="button"  value="중복체크" onclick="doublecheck()" class="btn btn-warning">
		      </td>
		      	</tr>
		      </table>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="pass">패스워드:</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="pass" name="pass" placeholder=pass를 입력하세요.">
		    </div>
		  </div>		 
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="name">이름:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="name" name="name" placeholder="name를 입력하세요.">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="age">나이:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="age" name="age" placeholder="나이를 입력하세요.">
		    </div>
		  </div>		
  		  <div class="form-group"> 
		    <label class="control-label col-sm-2" for="email">이메일:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="email" name="email" placeholder="email를 입력하세요.">
		    </div>
		  </div>		 
  		  <div class="form-group">
		    <label class="control-label col-sm-2" for="phone">전화번호:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="phone" name="phone" placeholder="phone를 입력하세요.">
		    </div>	 
		   </div>		 		  
  		  <div class="form-group">
		    <label class="control-label col-sm-2" for="file">첨부파일:</label>
		    <div class="col-sm-10">
		      <input type="file" class="controler-label" id="file" name="file">
		   </div>	
		      <input type="hidden" class="filename" id="filename" name="filename" value="" >
		   		
		   </div>
 		</form>
	</div>
  <div class="panel-footer" style="text-algin: center;">
	  <c:if test="${sessionScope.userId==null || sessionScope.userId==''}">
	  	<input type="button" value="등록" class='btn btn-primary' onclick= "add2()"/>
	  </c:if>
	  <c:if test="${sessionScope.userId!=null && sessionScope.userId!=''}">
	  	<input type="button" value="등록" class='btn btn-primary' onclick= "add()" disabled="disabled"/>
	  </c:if>
	  	<input type="button" value="취소" class='btn btn-primary' onclick= "formreset()">
	  
	  	<input type="button" value="리스트" class='btn btn-primary' onclick="location.href='${ctx}/memberlist.do'"/>			
  
  </div>
  
  </div>

</body>
</html>