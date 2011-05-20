package contador

class CuentaService {

    static transactional = true

    def lista(params) {
        log.debug("Buscando lista de Cuentas")
        def cuentas = Cuenta.list(params)
        def cantidad = Cuenta.count()
        return [lista:cuentas,cantidad:cantidad]
    }
}
