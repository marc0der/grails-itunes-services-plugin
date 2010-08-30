<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 30-Aug-2010
  Time: 13:57:47
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Simple Artist Search Demo Page using Grails iTunes Service</title>
  </head>
  
  <body>
  
    <g:form action="search">
      <label>Search Artists: </label>
      <g:textField name="search" value="${searchTerm}"/>
      <g:submitButton name="submit" value="Search!"/> 
    </g:form>

    <div class="results">
      <h2>Albums by ${searchTerm}</h2>
      <g:each var="album" in="${searchResults}">
        <p><a href="${album.link}"><img src="${album.image}" alt=""/></a></p>
        <p>${album.name} by ${album.artist} at a mere £${album.price}</p>
      </g:each>
    </div>
  
  </body>
</html>