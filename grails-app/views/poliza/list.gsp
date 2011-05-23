
<%@ page import="contador.Poliza" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'poliza.label', default: 'Poliza')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'poliza.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="folio" title="${message(code: 'poliza.folio.label', default: 'Folio')}" />
                        
                            <g:sortableColumn property="descripcion" title="${message(code: 'poliza.descripcion.label', default: 'Descripcion')}" />
                        
                            <g:sortableColumn property="estatus" title="${message(code: 'poliza.estatus.label', default: 'Estatus')}" />
                        
                            <g:sortableColumn property="clasificacion" title="${message(code: 'poliza.clasificacion.label', default: 'Clasificacion')}" />
                        
                            <g:sortableColumn property="cerrada" title="${message(code: 'poliza.cerrada.label', default: 'Cerrada')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${polizaInstanceList}" status="i" var="polizaInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${polizaInstance.id}">${fieldValue(bean: polizaInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: polizaInstance, field: "folio")}</td>
                        
                            <td>${fieldValue(bean: polizaInstance, field: "descripcion")}</td>
                        
                            <td>${fieldValue(bean: polizaInstance, field: "estatus")}</td>
                        
                            <td>${fieldValue(bean: polizaInstance, field: "clasificacion")}</td>
                        
                            <td><g:formatBoolean boolean="${polizaInstance.cerrada}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${polizaInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
