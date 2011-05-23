package contador

class PolizaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [polizaInstanceList: Poliza.list(params), polizaInstanceTotal: Poliza.count()]
    }

    def create = {
        def polizaInstance = new Poliza()
        polizaInstance.properties = params
        return [polizaInstance: polizaInstance]
    }

    def save = {
        def polizaInstance = new Poliza(params)
        if (polizaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'poliza.label', default: 'Poliza'), polizaInstance.id])}"
            redirect(action: "show", id: polizaInstance.id)
        }
        else {
            render(view: "create", model: [polizaInstance: polizaInstance])
        }
    }

    def show = {
        def polizaInstance = Poliza.get(params.id)
        if (!polizaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
            redirect(action: "list")
        }
        else {
            [polizaInstance: polizaInstance]
        }
    }

    def edit = {
        def polizaInstance = Poliza.get(params.id)
        if (!polizaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [polizaInstance: polizaInstance]
        }
    }

    def update = {
        def polizaInstance = Poliza.get(params.id)
        if (polizaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (polizaInstance.version > version) {
                    
                    polizaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'poliza.label', default: 'Poliza')] as Object[], "Another user has updated this Poliza while you were editing")
                    render(view: "edit", model: [polizaInstance: polizaInstance])
                    return
                }
            }
            polizaInstance.properties = params
            if (!polizaInstance.hasErrors() && polizaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'poliza.label', default: 'Poliza'), polizaInstance.id])}"
                redirect(action: "show", id: polizaInstance.id)
            }
            else {
                render(view: "edit", model: [polizaInstance: polizaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def polizaInstance = Poliza.get(params.id)
        if (polizaInstance) {
            try {
                polizaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
            redirect(action: "list")
        }
    }
}
