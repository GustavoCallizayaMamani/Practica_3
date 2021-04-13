<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practica  3 </title>
    </head>
    <body>
        <h1><c:if test="${producto.id == 0}">Nuevo</c:if>
            <c:if test="${producto.id != 0}">Editar</c:if>
            producto
        </h1>
            <form action="inicio" method="post">
                <input type="hidden" name="id" value="${producto.id}"/>
            <table>
                <tr>
                    <td>Producto:</td>
                    <td><input type="text" name="nombre_producto" value="${producto.nombre_producto}"/></td>
                </tr>
                <tr>
                    <td>Precio:</td>
                    <td><input type="text" name="precio" value="${producto.precio}"/></td>
                </tr>
                <tr>
                    <td>Cantidad:</td>
                    <td><input type="text" name="stock" value="${producto.stock}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit"/></td>
                </tr>
            </table>
            </form>
    </body>
</html>

