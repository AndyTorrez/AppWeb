<%-- Archivo: MiTareaWeb1/src/main/webapp/formulario-usuario.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>
        <c:if test="${usuario != null}">Editar Usuario</c:if>
        <c:if test="${usuario == null}">Crear Nuevo Usuario</c:if>
    </title>
</head>
<body>
    <h2>
        <c:if test="${usuario != null}">Editar Usuario</c:if>
        <c:if test="${usuario == null}">Crear Nuevo Usuario</c:if>
    </h2>
    <p>La vista JSP es la interfaz de usuario.</p>

    <form action="Usuario" method="POST"> 
        <c:if test="${usuario != null}">
            <input type="hidden" name="action" value="update"/>
            <input type="hidden" name="id" value="<c:out value='${usuario.id}'/>"/>
        </c:if>
        <c:if test="${usuario == null}">
            <input type="hidden" name="action" value="insert"/>
        </c:if>

        <label>Nombre:</label>
        <input type="text" name="name" value="<c:out value='${usuario.name}'/>" required><br/><br/>
        
        <label>Apellidos:</label>
        <input type="text" name="lastname" value="<c:out value='${usuario.lastname}'/>" required><br/><br/>
        
        <label>Username:</label>
        <input type="text" name="username" value="<c:out value='${usuario.username}'/>" required><br/><br/>
        
        <label>Password:</label>
        <input type="password" name="password" value="<c:out value='${usuario.password}'/>" required><br/><br/>
        
        <label>Email:</label>
        <input type="email" name="email" value="<c:out value='${usuario.email}'/>" required><br/><br/>

        <input type="submit" value="Guardar">
    </form>
    <br/>
    <a href="Usuario?action=list">Volver al Listado</a>
</body>
</html>