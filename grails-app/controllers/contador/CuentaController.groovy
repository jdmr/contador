package contador

class CuentaController {

    def cuentaService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

    def lista = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def resultado = cuentaService.lista(lista.params)
        [cuentas: resultado.lista, totalDeCuentas:resultado.cantidad]
    }

    def nueva = {
        def cuenta = new Cuenta()
        cuenta.properties = params
        return [cuenta: cuenta]
    }

    def crea = {
        def cuenta = new Cuenta(params)
        try {
            cuenta = cuentaService.crea(cuenta)

            flash.message = "${message(code: 'default.created.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), cuenta.descripcion])}"
            redirect(action: "ver", id: cuenta.id)
        } catch(Exception e) {
            log.error("No se pudo crear la cuenta",e)
            render(view: "nueva", model: [cuenta: cuenta])
        }
    }

    def ver = {
        def cuenta = cuentaService.obtine(params.id)
        if (!cuenta) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])}"
            redirect(action: "lista")
        }
        else {
            [cuenta: cuenta]
        }
    }

    def edita = {
        def cuenta = cuentaService.obtiene(params.id)
        if (!cuenta) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])}"
            redirect(action: "lista")
        }
        else {
            return [cuenta: cuenta]
        }
    }

    def actualiza = {
        def cuenta = cuentaService.obtiene(params.id)
        if (cuenta) {
            if (params.version) {
                def version = params.version.toLong()
                if (cuenta.version > version) {
                    
                    cuenta.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cuenta.label', default: 'Cuenta')] as Object[], "Another user has updated this Cuenta while you were editing")
                    render(view: "edita", model: [cuenta: cuenta])
                    return
                }
            }
            try {
                cuenta.properties = params
                cuenta = cuentaService.actualiza(cuenta)
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), cuenta.descripcion])}"
                redirect(action: "ver", id: cuenta.id)
            } catch(Exception e) {
                log.error("No se pudo actualizar la cuenta",e)
                render(view: "edita", model: [cuenta: cuenta])
            }

        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])}"
            redirect(action: "lista")
        }
    }

    def elimina = {
        log.debug("Eliminando la cuenta")
        def cuenta = Cuenta.load(params.id)
        if (cuenta) {
            try {
                log.debug("Mandando a eliminar la cuenta")
                cuentaService.elimina(cuenta)
                log.debug("Cuenta eliminada")
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])}"
                redirect(action: "lista")
            } catch(Exception e) {
                log.error("No se puedo eliminar la cuenta ${params.id}",e)
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])}"
                redirect(action: "ver", id: params.id)
            }
        } else {
            log.debug("No se pudo cargar la cuenta")
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])}"
            redirect(action: "lista")
        }

    }
}
