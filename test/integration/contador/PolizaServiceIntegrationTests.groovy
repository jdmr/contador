package contador

import grails.test.*
import org.junit.*

class PolizaServiceIntegrationTests extends GroovyTestCase {

    def polizaService

    @Test
    void debieraObtenerListaDePolizas() {
        for(i in 1..20) {
            new Poliza(folio:"TEST%i",descripcion:"TEST%i").save()
        }
        def params = [:]
        params.max = 10
        def resultado = polizaService.lista(params)
        assert resultado
        assertEquals 10, resultado.lista.size()
        assert resultado.cantidad >= 20
    }

    @Test
    void deberiaCrearPoliza() {
        def poliza = new Poliza (
            descripcion : 'TEST'
        )

        println "Mandando a crear la poliza ${poliza.descripcion}"
        poliza = polizaService.crea(poliza)

        assert poliza
        assert poliza.id
    }

    @Test
    void debieraObtenerPoliza() {
        def poliza = new Poliza (
            descripcion : 'TEST'
        )

        poliza = polizaService.crea(poliza)

        assert poliza
        assert poliza.id

        def prueba = polizaService.obtiene(poliza.id)
        assert prueba
        assertEquals poliza.id, prueba.id
    }

    @Test
    void deberaActualizarPoliza() {
        def poliza = new Poliza (
            descripcion : 'TEST'
        )

        poliza = polizaService.crea(poliza)

        assert poliza
        assert poliza.id
        assertEquals 'ABIERTA', poliza.estatus

        def prueba = polizaService.obtiene(poliza.id)
        assert prueba
        assertEquals poliza.id, prueba.id
        
        prueba.descripcion = 'DEMO'
        def otro = polizaService.actualiza(prueba)
        assert otro
        assert 'DEMO', otro.descripcion

        def demo = polizaService.obtiene(poliza.id)
        assert demo
        assertEquals 'DEMO', demo.descripcion
    }

    @Test
    void debieraEliminarCuenta() {
        def poliza = new Poliza (
            descripcion : 'TEST'
        )

        poliza = polizaService.crea(poliza)

        assert poliza
        assert poliza.id
        assertEquals 'ABIERTA', poliza.estatus

        polizaService.elimina(poliza)

        def prueba = polizaService.obtiene(poliza.id)
        assertNull prueba
    }

    @Test
    void debieraCerrarLaPoliza() {
        def poliza = new Poliza (
            descripcion : 'TEST'
        )

        poliza = polizaService.crea(poliza)

        assert poliza
        assert poliza.id
        assertEquals 'ABIERTA', poliza.estatus

        polizaService.cierra(poliza)

        def prueba = polizaService.obtiene(poliza.id)
        assert prueba
        assertEquals 'CERRADA', poliza.estatus
    }

    @Test
    void debieraCancelarLaPoliza() {
        def poliza = new Poliza (
            descripcion : 'TEST'
        )

        poliza = polizaService.crea(poliza)

        assert poliza
        assert poliza.id
        assertEquals 'ABIERTA', poliza.estatus

        polizaService.cancela(poliza)

        def prueba = polizaService.obtiene(poliza.id)
        assert prueba
        assertEquals 'CANCELADA', poliza.estatus
    }

    @Test(expected=RuntimeException)
    void noDebieraDeActualizarPolizaCerrada() {
        def poliza = new Poliza (
            descripcion : 'TEST'
        )

        poliza = polizaService.crea(poliza)

        assert poliza
        assert poliza.id
        assertEquals 'ABIERTA', poliza.estatus

        polizaService.cierra(poliza)

        def prueba = polizaService.obtiene(poliza.id)
        assert prueba
        assertEquals 'CERRADA', poliza.estatus

        prueba.descripcion = 'PRUEBA'
        polizaService.actualiza(poliza)
        fail("Debio lanzar una excepcion de que la poliza no puede ser actualizada si esta cerrada")
    }

    @Test(expected=RuntimeException)
    void noDebieraDeEliminarPolizaCerrada() {
        def poliza = new Poliza (
            descripcion : 'TEST'
        )

        poliza = polizaService.crea(poliza)

        assert poliza
        assert poliza.id
        assertEquals 'ABIERTA', poliza.estatus

        polizaService.cierra(poliza)

        def prueba = polizaService.obtiene(poliza.id)
        assert prueba
        assertEquals 'CERRADA', poliza.estatus

        prueba.descripcion = 'PRUEBA'
        polizaService.elimina(poliza)
        fail("Debio lanzar una excepcion de que la poliza no puede ser eliminada si esta cerrada")
    }

}
