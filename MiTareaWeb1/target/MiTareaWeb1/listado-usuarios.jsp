<%-- Archivo: MiTareaWeb1/src/main/webapp/listado-usuarios.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Listado de Usuarios (CRUD)</title>
</head>
<body>
    <h2>Usuarios Registrados (CRUD MVC con Servlets y JSP)</h2>
    <a href="Usuario?action=new">Agregar Nuevo Usuario</a>
    <br/><br/>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Username</th>
                <th>Email</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${listUsuario}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.lastname}"/></td>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td>
                        <a href="Usuario?action=edit&id=<c:out value='${user.id}'/>">Editar</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="Usuario?action=delete&id=<c:out value='${user.id}'/>">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>