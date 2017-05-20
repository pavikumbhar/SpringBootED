<html>

<head>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<link href="resources/bootstrap.css" rel="stylesheet" type="text/css"/>

    
		<title><tiles:getAsString name="title" /></title>
	</head>
	<body>
		<!-- Header -->
		<tiles:insertAttribute name="header" />
		<!-- Body -->
		<tiles:insertAttribute name="body" />
		<!-- Footer -->
		<tiles:insertAttribute name="footer" />
	</body>
</html>