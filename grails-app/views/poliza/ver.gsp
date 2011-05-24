
<%@ page import="contador.Poliza" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'poliza.label', default: 'Poliza')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="lista"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="nueva"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="poliza.folio.label" default="Folio" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: poliza, field: "folio")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="poliza.descripcion.label" default="Descripcion" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: poliza, field: "descripcion")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="poliza.estatus.label" default="Estatus" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: poliza, field: "estatus")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="poliza.clasificacion.label" default="Clasificacion" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: poliza, field: "clasificacion")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="poliza.transacciones.label" default="Transacciones" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${poliza.transacciones}" var="t">
                                    <li><g:link controller="transaccion" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <g:if test="${poliza.estatus != 'CANCELADA'}">
                <div class="buttons">
                    <g:form>
                        <g:hiddenField name="id" value="${poliza?.id}" />
                        <g:if test="${poliza.estatus == 'ABIERTA'}">
                            <span class="button"><g:actionSubmit class="edit" action="edita" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                            <span class="button"><g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                            <span class="button"><g:actionSubmit class="edit" action="cierra" value="${message(code: 'poliza.cierra.label', default: 'Cierra')}" onclick="return confirm('${message(code: 'poliza.cierra.confirm.message', default: 'Are you sure?')}');" /></span>
                        </g:if>
                        <g:if test="${poliza.estatus == 'CERRADA'}">
                            <span class="button"><g:actionSubmit class="delete" action="cancela" value="${message(code: 'poliza.cancela.label', default: 'Cancela')}" onclick="return confirm('${message(code: 'poliza.cancela.confirm.message', default: 'Are you sure?')}');" /></span>
                        </g:if>
                    </g:form>
                </div>
            </g:if>
        </div>
    </body>
</html>
