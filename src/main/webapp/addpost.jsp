<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@page import="com.example.dao.BoardDAO"%>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="u" class="com.example.bean.BoardVO" />
<jsp:setProperty property="*" name="u"/>

<%
	BoardDAO boardDAO = new BoardDAO();
	int i = boardDAO.insertBoard(u);
	String msg = "게시글 추가 성공 !";
	if(i == 0) msg = "[에러] 게시글 추가 실패 ";
%>

<script>
	alert('<%=msg%>');
	location.href='posts.jsp';
</script>