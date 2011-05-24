

<%@ page import="contador.Poliza" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'poliza.label', default: 'Poliza')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${poliza}">
            <div class="errors">
                <g:renderErrors bean="${poliza}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${poliza?.id}" />
                <g:hiddenField name="version" value="${poliza?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="folio"><g:message code="poliza.folio.label" default="Folio" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: poliza, field: 'folio', 'errors')}">
                                    <g:textField name="folio" maxlength="64" value="${poliza?.folio}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="descripcion"><g:message code="poliza.descripcion.label" default="Descripcion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: poliza, field: 'descripcion', 'errors')}">
                                    <g:textField name="descripcion" value="${poliza?.descripcion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="estatus"><g:message code="poliza.estatus.label" default="Estatus" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: poliza, field: 'estatus', 'errors')}">
                                    <g:select name="estatus" from="${poliza.constraints.estatus.inList}" value="${poliza?.estatus}" valueMessagePrefix="poliza.estatus"  />
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
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="transacciones"><g:message code="poliza.transacciones.label" default="Transacciones" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: poliza, field: 'transacciones', 'errors')}">
                                    <g:select name="transacciones" from="${contador.Transaccion.list()}" multiple="yes" optionKey="id" size="5" value="${poliza?.transacciones*.id}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="actualiza" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
