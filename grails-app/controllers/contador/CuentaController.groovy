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
        def crea = new Cuenta(params)
        if (crea.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), crea.id])}"
            redirect(action: "ver", id: crea.id)
        }
        else {
            render(view: "create", model: [crea: crea])
        }
    }

    def ver = {
        def cuenta = Cuenta.get(params.id)
        if (!cuenta) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])}"
            redirect(action: "lista")
        }
        else {
            [cuenta: cuenta]
        }
    }

    def edita = {
        def cuenta = Cuenta.get(params.id)
        if (!cuenta) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])}"
            redirect(action: "lista")
        }
        else {
            return [cuenta: cuenta]
        }
    }

    def actualiza = {
        def cuenta = Cuenta.get(params.id)
        if (cuenta) {
            if (params.version) {
                def version = params.version.toLong()
                if (cuenta.version > version) {
                    
                    cuenta.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cuenta.label', default: 'Cuenta')] as Object[], "Another user has updated this Cuenta while you were editing")
                    render(view: "edita", model: [cuenta: cuenta])
                    return
                }
            }
            cuenta.properties = params
            if (!cuenta.hasErrors() && cuenta.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), cuenta.id])}"
                redirect(action: "ver", id: cuenta.id)
            }
            else {
                render(view: "edita", model: [cuenta: cuenta])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])}"
            redirect(action: "lista")
        }
    }

    def delete = {
        def cuenta = Cuenta.get(params.id)
        if (cuenta) {
            try {
                cuenta.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])}"
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])}"
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])}"
            redirect(action: "lista")
        }
    }
}
