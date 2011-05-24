
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
            <span class="menuButton"><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table id="polizas">
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="folio" title="${message(code: 'poliza.folio.label', default: 'Folio')}" />
                        
                            <g:sortableColumn property="descripcion" title="${message(code: 'poliza.descripcion.label', default: 'Descripcion')}" />
                        
                            <g:sortableColumn property="estatus" title="${message(code: 'poliza.estatus.label', default: 'Estatus')}" />
                        
                            <g:sortableColumn property="clasificacion" title="${message(code: 'poliza.clasificacion.label', default: 'Clasificacion')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${polizas}" status="i" var="poliza">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="ver" id="${poliza.id}">${fieldValue(bean: poliza, field: "folio")}</g:link></td>
                        
                            <td>${fieldValue(bean: poliza, field: "descripcion")}</td>
                        
                            <td>${fieldValue(bean: poliza, field: "estatus")}</td>
                        
                            <td>${fieldValue(bean: poliza, field: "clasificacion")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${totalDePolizas}" />
            </div>
        </div>
        <g:javascript>
            $(document).ready(function() {
                    highlightTableRows('polizas')
            });
        </g:javascript>
    </body>
</html>
