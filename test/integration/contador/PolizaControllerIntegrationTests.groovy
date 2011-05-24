package contador

import grails.test.*
import org.junit.*

class PolizaControllerIntegrationTests extends GroovyTestCase {
    def polizaService

    @Test
    void debieraMostrarlista() {
        for(i in 1..20) {
            new Poliza(folio:"T-E-S-T-%i",descripcion:"T-E-S-T-%i").save()
        }

        def controller = new PolizaController()
        controller.index()
        assertEquals "/poliza/lista", controller.response.redirectedUrl
        def resultado = controller.lista()
        assertEquals 10, resultado.polizas.size()
        assert resultado.totalDePolizas >= 20
    }

    @Test
    void debieraCrearNuevaPoliza() {
        def controller = new PolizaController()
        def resultado = controller.nueva()
        assert resultado

        controller.params.descripcion = 'T-E-S-T-1'
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/poliza/ver')
    }

    @Test
    void debieraMostrarLaPoliza() {
        def poliza = new Poliza(folio:"T-E-S-T-1",descripcion:"T-E-S-T-1").save()

        def controller = new PolizaController()
        controller.params.id = poliza.id
        def resultado = controller.ver()
        assert resultado
        assertEquals poliza.id, resultado.poliza.id
        assertEquals poliza.descripcion, resultado.poliza.descripcion
    }

    @Test
    void debieraActualizaPoliza() {
        def poliza = new Poliza(folio:"T-E-S-T-1",descripcion:"T-E-S-T-1").save()

        def controller = new PolizaController()
        controller.params.id = poliza.id
        def resultado = controller.edita()
        assert resultado
        assertEquals poliza.id, resultado.poliza.id
        assertEquals poliza.folio, resultado.poliza.folio

        controller.params.id = poliza.id
        controller.params.version = poliza.version
        controller.params.descripcion = 'T-E-S-T-2'
        controller.actualiza()
        assertEquals "/poliza/ver/${poliza.id}", controller.response.redirectedUrl
    }

    @Test
    void debieraEliminarPoliza() {
        def poliza = new Poliza(folio:"T-E-S-T-1",descripcion:"T-E-S-T-1").save()
        def controller = new PolizaController()
        controller.params.id = poliza.id
        controller.elimina()
        assertEquals "/poliza/lista", controller.response.redirectedUrl
    }

    @Test
    void debieraCerrarPoliza() {
        def poliza = new Poliza(folio:"T-E-S-T-1",descripcion:"T-E-S-T-1").save()
        def controller = new PolizaController()
        controller.params.id = poliza.id
        def resultado = controller.cierra()
        assertEquals poliza.id, poliza.id
        assertEquals 'CERRADA', poliza.estatus
    }

    @Test
    void debieraCancelarPoliza() {
        def poliza = new Poliza(folio:"T-E-S-T-1",descripcion:"T-E-S-T-1",estatus:'CERRADA').save()
        def controller = new PolizaController()
        controller.params.id = poliza.id
        def resultado = controller.cancela()
        assertEquals poliza.id, poliza.id
        assertEquals 'CANCELADA', poliza.estatus
    }
}
