<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*" %>
<%@ page import="java.util.*" %>     

<%

	MemberVO vo = (MemberVO)request.getAttribute("vo");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function update(){
		document.form1.action= "<c:url value='/memberUpdate.do'/>"
		document.form1.submit();
	}
    function formreset(){
    	document.form1.reset();
    }
    function getFile(filename){
    	location.href="<c:url value='/fileGet.do'/>?filename="+filename;
    }
</script>

<title>Insert title here</title>
</head>
<body>

 
 
 회원가화면
<div class="container">
 <h2>회원 관리 시스템 상세화면</h2>
  <div class="panel panel-default">
    <div class="panel-heading">
		  <c:if test = "${sessionScope.userId !=null && sessionScope.userId !=''  && sessionScope.userId == vo.id}">
		  	<label>
		  		<img src= "<c:out value='file_repo/${vo.filename}'/>" width="60px" height="60px"/>
		  		${sessionScope.userName}님이 로그인 하셨습니다.
		  	</label>
		  </c:if>
		  <c:if test = "${ssesionScope.userId==null || sessionScope.userId==''}">
		  	<h2>안녕하세요</h2>
		  </c:if>
    </div>
    <div class="panel-body">
    <form id="form1" name="form1" class ="form-horizontal"  method="post">
	<input type="hidden" name="num" value="${vo.num}"/>
    	<div class="form-group">
    		<label class="cotrol-lablel col-sm-2">번호:</label>
    		<div class ="col-sm-10">
    			<c:out value="${vo.num}"/>
    			
    		</div>
    	</div>

 

    	<div class="form-group">
    		<label class="cotrol-lablel col-sm-2">아이디:</label>
    		<div class ="col-sm-10">
    			<c:out value="${vo.id}"/>
    			
    		</div>
    	</div>

     	<div class="form-group">
    		<label class="cotrol-lablel col-sm-2">패스워드:</label>
    		<div class ="col-sm-10">
    			<c:out value="${vo.pass}"/>
    		</div>
    	</div>

       
    

    	<div class="form-group">
    		<label class="cotrol-lablel col-sm-2">이름:</label>
    		<div class ="col-sm-10">
    			<c:out value="${vo.name}"/>
    		</div>
    	</div>

    
    

    	<div class="form-group">
    		<label class="cotrol-lablel col-sm-2">나이:</label>
    		<div class ="col-sm-10">
    			<input type="text" class="form-control" id="age" name="age" value="${vo.age}" style="width: 10%" />
    		</div>
    	</div>
      
    

    	<div class="form-group">
    		<label class="cotrol-lablel col-sm-2">이메일:</label>
    		<div class ="col-sm-10">
    			<input type="text" class="form-control" id="email" name="email" value="${vo.email}" style="width: 30%"/>
    		</div>
    	</div>

    
 

    	<div class="form-group">
    		<label class="cotrol-lablel col-sm-2">전화번호:</label>
    		<div class ="col-sm-10">
    		<input type="text" class="form-control" id="phone" name="phone" value="${vo.phone}" style="width: 30%"/>    		</div>
    	</div>
    	
   	    	<div class="form-group">
    		<label class="cotrol-lablel col-sm-2">첨부파일:</label>
    		<div class ="col-sm-10">
    		<input type="file" class="form-control" id="file" name="file">
    		<c:if test="${vo.filename != null && vo.filename != ''}">
    				<a href="javascript:getFile('${vo.filename}')"><c:out value='${vo.filename}'/></a>	
    		</c:if>
    		<c:if test = "${sessionScope.userId != null && vo.filename != null && vo.filename != '' && sessionScope.userId == vo.id}">
    		 <sapn calss="glyphicon glyphicon-remove"> </sapn>
    		</c:if>	  	
    	</div>
    	
    </form>
    </div>  
    <div class= "panel-footer">
    	<c:if test="${!empty sessionScope.userId}">
   			<c:if test="${sessionScope.userId == vo.id}">
   			<input type="button" value="수정하기" class='btn btn-primary' onclick="update()"/>
			</c:if>
		    <c:if test="${sessionScope.userId != vo.id}">
   			<input type="button" value="수정하기" class='btn btn-primary' onclick="update()" disabled="disabled"/>
			</c:if>
		</c:if>
		
			<input type="button" value="취소" class='btn btn-primary' onclick= "formreset()"/>
			<input type="button" value="리스트" class='btn btn-primary' onclick="location.href='${ctx}/memberlist.do'"/>			
    </div>     
  </div>
</div>

</body>
</html>