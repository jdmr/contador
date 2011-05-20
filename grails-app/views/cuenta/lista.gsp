
<%@ page import="contador.Cuenta" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cuenta.label', default: 'Cuenta')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table id="cuentas">
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="nombre" title="${message(code: 'cuenta.nombre.label', default: 'Nombre')}" />
                        
                            <g:sortableColumn property="descripcion" title="${message(code: 'cuenta.descripcion.label', default: 'Descripcion')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${cuentas}" status="i" var="cuenta">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="ver" id="${cuenta.id}">${fieldValue(bean: cuenta, field: "nombre")}</g:link></td>
                        
                            <td>${fieldValue(bean: cuenta, field: "descripcion")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${totalDeCuentas}" />
            </div>
        </div>
        <g:javascript>
            $(document).ready(function() {
                    highlightTableRows('cuentas')
            });
        </g:javascript>
    </body>
</html>
