

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
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${polizaInstance}">
            <div class="errors">
                <g:renderErrors bean="${polizaInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${polizaInstance?.id}" />
                <g:hiddenField name="version" value="${polizaInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="folio"><g:message code="poliza.folio.label" default="Folio" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: polizaInstance, field: 'folio', 'errors')}">
                                    <g:textField name="folio" maxlength="64" value="${polizaInstance?.folio}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="descripcion"><g:message code="poliza.descripcion.label" default="Descripcion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: polizaInstance, field: 'descripcion', 'errors')}">
                                    <g:textField name="descripcion" value="${polizaInstance?.descripcion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="estatus"><g:message code="poliza.estatus.label" default="Estatus" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: polizaInstance, field: 'estatus', 'errors')}">
                                    <g:select name="estatus" from="${polizaInstance.constraints.estatus.inList}" value="${polizaInstance?.estatus}" valueMessagePrefix="poliza.estatus"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="clasificacion"><g:message code="poliza.clasificacion.label" default="Clasificacion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: polizaInstance, field: 'clasificacion', 'errors')}">
                                    <g:select name="clasificacion" from="${polizaInstance.constraints.clasificacion.inList}" value="${polizaInstance?.clasificacion}" valueMessagePrefix="poliza.clasificacion"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="cerrada"><g:message code="poliza.cerrada.label" default="Cerrada" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: polizaInstance, field: 'cerrada', 'errors')}">
                                    <g:checkBox name="cerrada" value="${polizaInstance?.cerrada}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="transacciones"><g:message code="poliza.transacciones.label" default="Transacciones" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: polizaInstance, field: 'transacciones', 'errors')}">
                                    <g:select name="transacciones" from="${contador.Transaccion.list()}" multiple="yes" optionKey="id" size="5" value="${polizaInstance?.transacciones*.id}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
