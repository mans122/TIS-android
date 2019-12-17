<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	String url = "jdbc:oracle:thin:@localhost:1521:myoracle";
	String uid = "ora_user";
	String pass = "hong";
	String sql;
%>
<%
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url, uid, pass);
		String num = request.getParameter("num");

		sql = "select * from board where num=?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, num);
		rs = stmt.executeQuery();
		
		String result="";
		while(rs.next()){
			if(!result.equals("")){
				result+=",";
			}
			result+=" {\"num\":\""+rs.getString("num")+"\"," +  
					"\"pass\":\""+rs.getString("pass")+"\","+
					"\"name\":\""+rs.getString("name")+"\","+
					"\"email\":\""+rs.getString("email")+"\","+
					"\"title\":\""+rs.getString("title")+"\","+
				 	"\"content\":\""+rs.getString("content")+"\","+
				 	"\"readcount\":\""+rs.getString("readcount")+"\","+
					"\"writedate\":\""+rs.getString("writedate")+"\"}";
		}
		result="{\"view\":["+result+"]}";
		out.print(result);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	} //finally 끝
%>