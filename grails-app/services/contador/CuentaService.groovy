package contador

import org.springframework.transaction.annotation.Transactional;

class CuentaService {

    static transactional = true

    @Transactional(readOnly = true)
    def lista(params) {
        log.debug("Buscando lista de Cuentas")
        def cuentas = Cuenta.list(params)
        def cantidad = Cuenta.count()
        return [lista:cuentas,cantidad:cantidad]
    }

    def crea(cuenta) {
        log.debug("Creando cuenta $cuenta")
        cuenta.save()
        return cuenta
    }

    @Transactional(readOnly = true)
    def obtiene(id) {
        log.debug("Obtiene cuenta $id")
        def cuenta = Cuenta.get(id)
        return cuenta
    }

    def actualiza(cuenta) {
        log.debug("Actualizando la cuenta $cuenta")
        cuenta.save()
        return cuenta
    }

    def elimina(cuenta) {
        log.debug("Eliminando la cuenta ${cuenta.id}")
        cuenta.delete()
    }

}
