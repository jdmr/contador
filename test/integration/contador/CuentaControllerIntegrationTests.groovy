package contador

import grails.test.*
import org.junit.*

class CuentaControllerIntegrationTests extends GroovyTestCase {
    def cuentaService

    @Test
    void debieraMostrarLista() {
        for(i in 1..20) {
            new Cuenta(nombre:"T-E-S-T-$i",descripcion:"T-E-S-T-$i").save()
        }
        
        def controller = new CuentaController()
        controller.index()
        assertEquals "/cuenta/lista", controller.response.redirectedUrl
        def resultado = controller.lista()
        assertEquals 10, resultado.cuentas.size()
        assert resultado.totalDeCuentas >= 20
    }

    @Test
    void debieraCrearNuevaCuenta() {
        def controller = new CuentaController()
        def resultado = controller.nueva()
        assert resultado

        controller.params.nombre = 'T-E-S-T-1'
        controller.params.descripcion = 'T-E-S-T-1'
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/cuenta/ver')
    }

    @Test
    void debieraMostrarLaCuenta() {
        def cuenta = new Cuenta(nombre:'T-E-S-T-1',descripcion:'T-E-S-T-1').save()

        def controller = new CuentaController()
        controller.params.id = cuenta.id
        def resultado = controller.ver()
        assert resultado
        assertEquals cuenta.id, resultado.cuenta.id
        assertEquals cuenta.nombre, resultado.cuenta.nombre
    }

    @Test
    void debieraActualizaCuenta() {
        def cuenta = new Cuenta(nombre:'T-E-S-T-1',descripcion:'T-E-S-T-1').save()
        def controller = new CuentaController()
        controller.params.id = cuenta.id
        def resultado = controller.edita()
        assert resultado
        assertEquals cuenta.id, resultado.cuenta.id
        assertEquals cuenta.nombre, resultado.cuenta.nombre

        controller.params.id = cuenta.id
        controller.params.version = cuenta.version
        controller.params.nombre = 'T-E-S-T-2'
        controller.params.descripcion = cuenta.descripcion
        controller.actualiza()
        assertEquals "/cuenta/ver/${cuenta.id}", controller.response.redirectedUrl
    }

    @Test
    void debieraEliminarCuenta() {
        def cuenta = new Cuenta(nombre:'T-E-S-T-1',descripcion:'T-E-S-T-1').save()
        def controller = new CuentaController()
        controller.params.id = cuenta.id
        controller.elimina()
        assertEquals "/cuenta/lista", controller.response.redirectedUrl
    }
}
