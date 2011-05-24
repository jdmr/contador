package contador

class PolizaController {

    def polizaService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

    def lista = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def resultado = polizaService.lista(params)
        [polizas: resultado.lista, totalDePolizas:resultado.cantidad]
    }

    def nueva = {
        def poliza = new Poliza()
        poliza.properties = params
        return [poliza: poliza]
    }

    def crea = {
        def poliza = new Poliza(params)
        try {
            poliza = polizaService.crea(poliza)
            
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'poliza.label', default: 'Poliza'), poliza.id])}"
            redirect(action: "ver", id: poliza.id)
        } catch(Exception e) {
            log.error("No se pudo crea la poliza",e)
            render(view: "nueva", model: [poliza: poliza])
        }
    }

    def ver = {
        def poliza = polizaService.obtiene(params.id)
        if (!poliza) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
            redirect(action: "lista")
        }
        else {
            [poliza: poliza]
        }
    }

    def edita = {
        def poliza = polizaService.obtiene(params.id)
        if (!poliza) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
            redirect(action: "lista")
        }
        else {
            return [poliza: poliza]
        }
    }

    def actualiza = {
        def poliza = polizaService.obtiene(params.id)
        if (poliza) {
            if (params.version) {
                def version = params.version.toLong()
                if (poliza.version > version) {
                    
                    poliza.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'poliza.label', default: 'Poliza')] as Object[], "Another user has updated this Poliza while you were editing")
                    render(view: "edita", model: [poliza: poliza])
                    return
                }
            }
            try {
                poliza.properties = params
                poliza = polizaService.actualiza(poliza)
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'poliza.label', default: 'Poliza'), poliza.id])}"
                redirect(action: "ver", id: poliza.id)
            } catch(Exception e) {
                log.error("No se pudo actualizar la poliza",e)
                render(view: "edita", model: [poliza: poliza])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
            redirect(action: "lista")
        }
    }

    def elimina = {
        log.debug("Eliminando la poliza ${params.id}")
        def poliza = Poliza.load(params.id)
        if (poliza) {
            try {
                polizaService.elimina(poliza)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
                redirect(action: "lista")
            } catch(Exception e) {
                log.error("No se pudo elimina la poliza ${params.id}",e) 
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
            redirect(action: "lista")
        }
    }

    def cierra = {
        log.debug("Cerrando la poliza ${params.id}")
        def poliza = polizaService.obtiene(params.id)
        if (poliza) {
            polizaService.cierra(poliza)
            flash.message = "${message(code: 'poliza.cierra.message', args: [poliza.folio])}"
            redirect(action: "ver", id: poliza.id)
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
            redirect(action: "lista")
        }
    }

    def cancela = {
        log.debug("Cancelando la poliza ${params.id}")
        def poliza = polizaService.obtiene(params.id)
        if (poliza) {
            polizaService.cancela(poliza)
            flash.message = "${message(code: 'poliza.cancela.message', args: [poliza.folio])}"
            redirect(action: "ver", id: poliza.id)
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])}"
            redirect(action: "lista")
        }
    }
}
