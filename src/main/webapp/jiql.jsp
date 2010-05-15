<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.util.*" %> 
<% 





ResultSet result = null;

Connection Conn = null;


	String status = "";
String url = "jdbc:jiql://local";
String host = "";
String password = "";
String user = "";
String dsName = "";
String sql = "";

	if (request.getParameter("query") != null){
try{
	sql = request.getParameter("sql");
	if (sql == null || sql.length() < 1)
	throw new SQLException("Please Enter a valid SQL Statement!");
	

	Properties props = new Properties();
	props.put("user",user);
	props.put("password",password);
	
	Class clazz = Class.forName("org.jiql.jdbc.Driver");
	Driver driver = (Driver) clazz.newInstance();
	Conn = driver.connect(url,props);
	

Statement Stmt = Conn.createStatement();
Stmt.execute(sql);
result = Stmt.getResultSet();
status = sql + "<br/>SQL COMPLETED SUCESSFULLY";
}catch (Exception ex){
status = "<font color=\"red\">" + sql + "<br/>SQL FAILED " + ex.toString() + "</font>";
ex.printStackTrace(response.getWriter());

}
//Conn.close();
}

%>

<html>
<head>
  </head>
  <body>

    <form action="jiql.jsp" method="post">
      <div>host:<input type="text" size="50" name="host" value="<%=host%>"/></div>
      <div>user:<input type="text" name="user" value="<%=user%>"/></div>
 <div>password:<input type="text" name="password" value="<%=password%>"/></div>
 <div>DataSource Name:<input type="text" name="dsName" value="<%=dsName%>"/></div>
       <div>SQL:<input type="text" name="sql" size="100"/></div>
      <div><input type="submit" name="query" value="query">
      </div>
    </form>
    
    <b>USER</b>: <%=user%><br/>
    <b>HOST</b>: <%=host%><br/>
    <%=status%>
    
    <% if (result != null) {
    		ResultSetMetaData mres = result.getMetaData(); 
    		
    		int cc = mres.getColumnCount();

    %>
    <div>Result INFO: Fetch Size:<%=result.getFetchSize()%></div>

    		<table border="1">
    	<tr>
    		 <% for (int c = 0;c < cc;c++){
    		 %>
    		
		 <th><%=mres.getColumnName(c + 1)%></th>
    <%}%>
    </tr>
    
    <% while (result.next()){%>
    <tr>
       		 <% for (int c = 0;c < cc;c++){%>
   		
    		 <td><%=result.getObject(mres.getColumnName(c + 1))%></td>
    <%}%>
    </tr>
    
    
    <%}%>
    </table>
    
    <%}%>
    
    <pre>
    Follow the examples below and preferable in the order shown:
    
    
    1)Enter the following SQL statement to create a table:
    <b><i>create  table  testable  ( name  varchar(18) ,countf int,yesno  varchar(90) )</b></i>
    
    2)Enter the following SQL statements to populate the table:
    <b><i>INSERT into testable (name,countf,yesno) values ('counter',1,'yes');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter',2,'no');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter',3,'yes');</b></i>
    
    3)Enter the following SQL statement to select all from the table:
    <b><i>select * from testable</b></i>
 
    4)Enter the following SQL statements to select with filter from the table:
    <b><i>select countf from testable where yesno='yes';</b></i>
    <b><i>select countf,yesno from testable where yesno='yes' AND countf=3;</b></i>
    <b><i>select * from testable where countf=2 OR countf=3;</b></i>
    
    5)Enter the following SQL statements to update values in the table:
    <b><i>UPDATE testable SET  countf=4 where  countf = 3</b></i>
    <b><i>UPDATE testable SET  countf=4 where  countf = 2</b></i>
    <b><i>UPDATE testable SET  countf=6 where  countf = 4 AND yesno='no';</b></i>
 
    6)Enter the following SQL statements to delete entries from the table:
    <b><i>delete from testable where countf=1</b></i>
    <b><i>delete from testable where yesno='no'</b></i>
    <b><i>delete from testable</b></i>

    7)Enter the following SQL statements to create tables with PRIMARY KEYS:
    <b><i>CREATE TABLE realm_user (realm_username varchar(120),realm_passphrase varchar(120),PRIMARY KEY  (realm_username)) ;</b></i>
    <b><i>CREATE TABLE realm_userrole (  realm_username varchar(120), realm_rolename varchar(120), PRIMARY KEY  (realm_username,realm_rolename)) ;</b></i>

    8)Enter the following SQL statements to populate the tables:
    <b><i>INSERT into realm_user (realm_username,realm_passphrase) values ('ruser1','tigres');</b></i>
    <b><i>INSERT into realm_user (realm_username,realm_passphrase) values ('ruser1','tigres');</b></i>
    <b><i>INSERT into realm_userrole (realm_username,realm_rolename) values ('ruser1','role1');</b></i>
    <b><i>INSERT into realm_userrole (realm_username,realm_rolename) values ('ruser1','role1');</b></i>
    <b><i>INSERT into realm_userrole (realm_username,realm_rolename) values ('ruser1','role2');</b></i>
 
    9)Enter the following SQL statements to ALTER with UNIQUE CONSTRAINT tables:
    <b><i>drop table realm_user</b></i>
    <b><i>CREATE TABLE realm_user (realm_username varchar(120),realm_passphrase varchar(120)) ;</b></i>
    <b><i>alter table realm_user add constraint realm_user_uq unique ( realm_username );</b></i>
    <b><i>INSERT into realm_user (realm_username,realm_passphrase) values ('ruser1','tigres');</b></i>
    <b><i>INSERT into realm_user (realm_username,realm_passphrase) values ('ruser1','tigres');</b></i>
 
    10)Enter the following SQL statements to ALTER with FOREIGN KEYS tables:
    <b><i>drop table realm_user</b></i>
    <b><i>drop table realm_userrole</b></i>
    <b><i>CREATE TABLE realm_user (realm_username varchar(120) primary key,realm_passphrase varchar(120))</b></i>
    <b><i>CREATE TABLE realm_userrole (  realm_user varchar(120), realm_rolename varchar(120), PRIMARY KEY  (realm_user,realm_rolename))</b></i>
    <b><i>INSERT into realm_user (realm_username,realm_passphrase) values ('ruser1','tigres');</b></i>
    <b><i>alter table realm_user add constraint ws_userid_fk foreign key ( realm_username ) references realm_userrole ( realm_user )</b></i>
    <b><i>INSERT into realm_userrole (realm_user,realm_rolename) values ('ruser1','role2')</b></i>
    <b><i>alter table realm_user add constraint ws_userid_fk foreign key ( realm_username ) references realm_userrole ( realm_user )</b></i>
    <b><i>INSERT into realm_user (realm_username,realm_passphrase) values ('ruser2','tigres');</b></i>
    <b><i>INSERT into realm_userrole (realm_user,realm_rolename) values ('ruser2','role2');</b></i>
    <b><i>INSERT into realm_user (realm_username,realm_passphrase) values ('ruser2','tigres');</b></i>


    11)Enter the following SQL statements with not null primary key tables:
    <b><i>drop table testable</b></i>
    <b><i>create  table  testable  ( name  varchar(18) not null primary key,countf int,yesno  varchar(90) )</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter',1,'yes');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter',1,'yes');</b></i>

    12)Enter the following SQL statements without not null tables:
    <b><i>drop table testable</b></i>
    <b><i>create  table  testable  ( name  varchar(18),countf int,yesno  varchar(90) )</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter',1,'yes');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values (null,2,'yes');</b></i>
    <b><i>INSERT into testable (countf,yesno) values (42,'yes');</b></i>
    <b><i>select * from testable;</b></i>

    13)Enter the following SQL statements with not null tables:
    <b><i>drop table testable
    <b><i>create  table  testable  ( name  varchar(18) not null,countf int,yesno  varchar(90) )</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter',1,'yes');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values (null,2,'yes');</b></i>

    14)Enter the following SQL statements with DISTINCT and IN tables:
    <b><i>drop table testable</b></i>
    <b><i>create  table  testable  ( name  varchar(18),countf int,yesno  varchar(90) )</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter',1,'yes');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values (null,2,'yes');</b></i>
    <b><i>INSERT into testable (countf,yesno) values (3,'yes');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter',1,'no');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values (null,2,'yes');</b></i>
    <b><i>INSERT into testable (countf,yesno) values (3,'no');</b></i>
    <b><i>select * from testable;</b></i>
    <b><i>select distinct * from testable</b></i>
    <b><i>SELECT DISTINCT countf FROM testable WHERE countf IN (2,3);</b></i>
    <b><i>SELECT DISTINCT t.countf FROM testable t WHERE t.countf IN (2,3);</b></i>

    15)Enter the following SQL statements with default value tables:
    <b><i>drop table testable</b></i>
    <b><i>create  table  testable  ( name  varchar(18),countf int default 22,yesno  varchar(90) )</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter',1,'yes');</b></i>
    <b><i>INSERT into testable (name,yesno) values ('counter2','yes');</b></i>
    <b><i>select * from testable;</b></i>

    16)Enter the following SQL statements with 'space , commas () or where and not null primary key' tables:
    <b><i>drop table testable</b></i>
    <b><i>create  table  testable  ( name  varchar(18),countf int default 22,yesno  varchar(100) default 'Yes and :) (:where or, No not null primary key' not null )</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter7',7,'NO AND  (: not null primary key:) WHERE OR, YES');</b></i>
    <b><i>INSERT into testable (name) values ('counter8')</b></i>
    <b><i>select * from testable;</b></i>
    <b><i>select * from testable where yesno = 'Yes and :) (:where or, No not null primary key';</b></i>

    17)Enter the following SQL statements with 'max column data length' tables:
    <b><i>drop table testable</b></i>
    <b><i>create  table  testable  ( name  varchar(18),countf int,yesno  varchar(3) )</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter4',1,'yesnono');</b></i>
 
    18)Enter the following SQL statements to test ORDER BY:
    <b><i>drop table testable</b></i>
    <b><i>create  table  testable  ( name  varchar(18),countf int,yesno  varchar(30) )</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter4',1,'a');</b></i>
    <b><i>INSERT into testable (name,yesno) values ('counter2','c');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values (null,3,'b');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter4',5,'e');</b></i>
    <b><i>INSERT into testable (countf,yesno) values (4,'g');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter6',6,'d');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter6b',6,'g');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter7',7,'f');</b></i>
    <b><i>INSERT into testable (countf,yesno) values (9,'h');</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values (null,8,'i');</b></i>
    <b><i>select * from testable order by countf,yesno DESC</b></i>

    19)Enter the following SQL statements to test IS NULL:
    <b><i>drop table testable</b></i>
    <b><i>create  table  testable  ( name  varchar(18),countf int,yesno  varchar(30) )</b></i>
    <b><i>INSERT into testable (name,countf) values ('counter4',1);</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter6',6,'d');</b></i>
    <b><i>INSERT into testable (name,countf) values ('counter4b',5);</b></i>
    <b><i>INSERT into testable (name,countf,yesno) values ('counter7',7,'f');</b></i>
    <b><i>select count(*) from testable where yesno is null</b></i>
    <b><i>UPDATE testable SET  yesno='yep' where  yesno is null;</b></i>
    <b><i>select * from testable where yesno is null;</b></i>

    20)Enter the following SQL statement to drop the tables:
    <b><i>drop table testable</b></i>
    <b><i>drop table realm_user</b></i>
    <b><i>drop table realm_userrole</b></i>
    </pre>
  </body>
</html>

<%
if (Conn != null)
Conn.close();
%>