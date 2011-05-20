package contador

import grails.test.*
import org.junit.*

class CuentaServiceTests extends GroovyTestCase {

    def cuentaService

    @Before
    public void setUp() {
        super.setUp()
    }

    @After
    public void tearDown() {
        super.tearDown()
    }

    @Test
    void deberiaCrearCuenta() {
        def cuenta = new Cuenta(
            nombre:'T-E-S-T-1'
            ,descripcion:'T-E-S-T-1'
        )

        cuenta = cuentaService.crea(cuenta)
        assert cuenta
        assert cuenta.id
    }
    
    @Test
    void debieraObtenerCuenta() {
        def cuenta = new Cuenta(
            nombre:'T-E-S-T-1'
            ,descripcion:'T-E-S-T-1'
        )
        cuenta = cuentaService.crea(cuenta)
        assert cuenta
        assert cuenta.id

        def prueba = cuentaService.obtiene(cuenta.id)
        assert prueba
        assertEquals cuenta.id, prueba.id
        assertEquals 'T-E-S-T-1', prueba.nombre
    }

    @Test
    void debieraCambiarValorDeNombre() {
        def cuenta = new Cuenta(
            nombre:'T-E-S-T-1'
            ,descripcion:'T-E-S-T-1'
        )
        cuenta = cuentaService.crea(cuenta)
        assert cuenta
        assert cuenta.id

        def prueba = cuentaService.obtiene(cuenta.id)
        assert prueba

        prueba.nombre = 'D-E-M-O'
        def otro = cuentaService.actualiza(prueba)
        assert otro
        assert 'D-E-M-O',otro.nombre

        def demo = cuentaService.obtiene(cuenta.id)
        assert demo
        assert 'D-E-M-O', demo.nombre
    }

    @Test
    void debieraEliminarCuenta() {
        def cuenta = new Cuenta(
            nombre:'T-E-S-T-1'
            ,descripcion:'T-E-S-T-1'
        )
        cuenta = cuentaService.crea(cuenta)
        assert cuenta
        assert cuenta.id

        cuentaService.elimina(cuenta)

        def prueba = cuentaService.obtiene(cuenta.id)
        assertNull prueba
    }

    @Test(expected=grails.validation.ValidationException.class)
    void noDebieraCrearCuenta() {
        def cuenta = new Cuenta(
            nombre : ''
            , descripcion : null
        )

        cuenta = cuentaService.crea(cuenta)
        assertNull cuenta
        assert cuenta.hasErrors()
    }

    @Test
    void noDebieraObtenerCuenta() {
        def cuenta = cuentaService.obtiene(-1)
        assertNull cuenta
    }

    @Test(expected=grails.validation.ValidationException.class)
    void noDebieraCambiarNombreDeCuenta() {
        def cuenta = new Cuenta(
            nombre:'T-E-S-T-1'
            ,descripcion:'T-E-S-T-1'
        )
        cuenta = cuentaService.crea(cuenta)
        assert cuenta
        assert cuenta.id

        def prueba = cuentaService.obtiene(cuenta.id)
        assert prueba

        prueba.nombre = 'D-E-M-O'
        prueba.descripcion = ''
        def otro = cuentaService.actualiza(prueba)
        assertNull otro
        assertFalse 'D-E-M-O'.equals(otro.nombre)
    }
}
