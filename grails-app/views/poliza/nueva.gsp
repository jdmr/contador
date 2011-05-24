

<%@ page import="contador.Poliza" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'poliza.label', default: 'Poliza')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${poliza}">
            <div class="errors">
                <g:renderErrors bean="${poliza}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="crea" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="descripcion"><g:message code="poliza.descripcion.label" default="Descripcion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: poliza, field: 'descripcion', 'errors')}">
                                    <g:textArea name="descripcion" value="${poliza?.descripcion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="clasificacion"><g:message code="poliza.clasificacion.label" default="Clasificacion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: poliza, field: 'clasificacion', 'errors')}">
                                    <g:select name="clasificacion" from="${poliza.constraints.clasificacion.inList}" value="${poliza?.clasificacion}" valueMessagePrefix="poliza.clasificacion"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
        <g:javascript>
            $(document).ready(function() {
                $('#descripcion').focus();
            });
        </g:javascript>
    </body>
</html>
