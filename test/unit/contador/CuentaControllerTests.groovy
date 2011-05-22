package contador

import grails.test.*
import org.junit.*

class CuentaControllerTests extends ControllerUnitTestCase {

    @Before
    public void setUp() {
        super.setUp()
    }

    @After
    public void tearDown() {
        super.tearDown()
    }

    @Test
    void debieraRedireccionarAListaDeIndex() {
        controller.index()
        assert "lista", redirectArgs.action
    }

    @Test
    void debieraMostrarLista() {
        def cuentas = []
        for(i in 1..10) {
            cuentas << new Cuenta(nombre:"T-E-S-T-${i}",descripcion:"T-E-S-T-${i}")
        }

        def cuentaMock = mockFor(CuentaService)
        cuentaMock.demand.lista(1..1){Map params ->
            return [lista:cuentas, cantidad:20]
        }
        controller.cuentaService = cuentaMock.createMock()
        def model = controller.lista()
        assert model
        assertEquals 20, model.totalDeCuentas
        assertEquals 10, model.cuentas.size()
    }

    @Test
    void debieraMostrarCapturaDeNuevaCuenta() {
        mockDomain(Cuenta)
        def model = controller.nueva()
        assert model
        assert model.cuenta
    }

    @Test
    void debieraCrearNuevaCuenta() {
        mockDomain(Cuenta)
        def cuentaMock = mockFor(CuentaService)
        cuentaMock.demand.crea(1..1) { Cuenta cuenta ->
            return new Cuenta(id:1,version:1,nombre:"T-E-S-T-1",descripcion:"T-E-S-T-1")
        }
        controller.cuentaService = cuentaMock.createMock()
        controller.metaClass.message = { args -> return 'foo' }

        controller.params.nombre = 'T-E-S-T-1'
        controller.params.descripcion = 'T-E-S-T-1'
        controller.crea()
        assertEquals "ver", redirectArgs.action
    }

    @Test
    void debieraMostrarCuenta() {
        def cuentaMock = mockFor(CuentaService)
        cuentaMock.demand.obtiene(1..1){Integer id ->
            return new Cuenta(id:1,version:1,nombre:"T-E-S-T-1",descripcion:"T-E-S-T-1")
        }
        controller.cuentaService = cuentaMock.createMock()
        controller.params.id = 1
        def model = controller.ver()
        assert model.cuenta
        assertEquals "T-E-S-T-1", model.cuenta.nombre
    }

    @Test
    void debieraMostrarEditar() {
        def cuentaMock = mockFor(CuentaService)
        cuentaMock.demand.obtiene(1..1){Integer id ->
            return new Cuenta(id:1,version:1,nombre:"T-E-S-T-1",descripcion:"T-E-S-T-1")
        }
        controller.cuentaService = cuentaMock.createMock()
        controller.params.id = 1
        def model = controller.edita()
        assert model
        assertEquals 1, model.cuenta.id
    }

    @Test
    void debieraActualizarCuenta() {
        mockDomain(Cuenta)
        def cuentaMock = mockFor(CuentaService)
        cuentaMock.demand.obtiene(1..1){Integer id ->
            return new Cuenta(id:1,version:1,nombre:"T-E-S-T-1",descripcion:"T-E-S-T-1")
        }
        cuentaMock.demand.actualiza(1..1){Cuenta cuenta ->
            return new Cuenta(id:1,version:1,nombre:"T-E-S-T-2",descripcion:"T-E-S-T-2")
        }
        controller.cuentaService = cuentaMock.createMock()
        controller.metaClass.message = { args -> return 'foo' }

        controller.params.id = 1
        controller.params.nombre = 'T-E-S-T-2'
        controller.params.descripcion = 'T-E-S-T-2'
        controller.actualiza()
        assertEquals 'ver', redirectArgs.action
    }

    @Test
    void debieraEliminarCuenta() {
        mockDomain(Cuenta)
        def cuentaMock = mockFor(CuentaService)
        cuentaMock.demand.elimina(1..1) { Cuenta cuenta ->
            return null
        }
        controller.cuentaService = cuentaMock.createMock()
        controller.metaClass.message = { args -> return 'foo' }

        controller.params.id = 1
        controller.elimina()
        assertEquals 'lista', redirectArgs.action
    }
}
