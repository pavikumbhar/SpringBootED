<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>PGC </title>
        <c:set var="baseURL" value="${pageContext.servletContext.contextPath}" />  
        
        <%-- JQuery --%>
        
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.8.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>
        
        <%-- JTable --%>
        <script src="${pageContext.request.contextPath}/resources/jtable/jquery.jtable.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/resources/jtable/metro/jtable.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/resources/css/code.jquery.com_ui_1.10.4_jquery-ui.js" type="text/javascript"></script>
        
          <%-- customized javascript code to manage JTable --%>
        <script src="${pageContext.request.contextPath}/resources/shedulerJTable.js" type="text/javascript"></script>
    </head>
    <body>
        <div> 
           
             <div id="jobSchedulerList" style="width:99%;"></div>
        </div>
    </body>
</html>