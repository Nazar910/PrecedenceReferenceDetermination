<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pyvov
  Date: 11.12.2016
  Time: 5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <style type="text/css">
        th,td{
            border-left: 1px solid black;
            border-top: 1px solid black;
            min-width: 50px;
            text-align: center;
        }
        table{
            border-spacing: 0;
            border-right: 1px solid black;
            border-bottom: 1px solid black;
        }
        .conflict{
            background-color: red;
        }
        .tableColumn{
            font-weight: bold;
        }
        input{
            font-size:16pt;
        }
    </style>

</head>
<body>
    <c:if test="${!right}">
        <a href="/right-grammar" target="_blank">To right grammar</a>
    </c:if>
    <table id="first" class="matr">
        <tr>
            <th></th>
            <c:forEach items="${tableColumns}" var="tableColumn">
                <th>${tableColumn}</th>
            </c:forEach>
        </tr>
        <c:forEach items="${matr}" var="row">
            <tr>
                <c:set var="i" value="0"/>
                <c:forEach items="${row}" var="cell">
                    <c:set var="style" value=""/>
                    <c:if test="${cell.equals(\"=<\") || cell.equals(\"=>\") || cell.equals(\"=<>\")|| cell.equals(\"<>\")|| cell.equals(\"><\")|| cell.equals(\"=><\")}">
                        <c:set var="style" value="conflict"/>
                    </c:if>
                    <c:if test="${cell.equals(row.get(0))}">
                        <c:set var="style" value="tableColumn"/>
                        <c:set var="i" value="${i=i-1}"/>
                    </c:if>
                    <c:choose>
                        <c:when test="${i!=-1}">
                            <td class="${style}" title="${row.get(0).concat(";".concat(tableColumns.get(i)))}">${cell}</td>
                        </c:when>
                        <c:otherwise>
                            <td class="${style}">${cell}</td>
                        </c:otherwise>
                    </c:choose>

                    <c:set var="i" value="${i=i+1}"/>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
