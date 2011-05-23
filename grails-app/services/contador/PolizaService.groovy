package contador

import org.springframework.transaction.annotation.Transactional;

class PolizaService {

    static transactional = true

    @Transactional(readOnly = true)
    def lista(params) {
        log.debug("Buscando lista de Polizas")
        def polizas = Poliza.list(params)
        def cantidad = Poliza.count()
        return [lista:polizas, cantidad:cantidad]
    }

    def crea(poliza) {
        log.debug("Creando poliza ${poliza.descripcion}")

        def folio = FolioPoliza.findByNombre('temporal')
        if (!folio) {
            folio = new FolioPoliza()
        }
        folio.valor = folio.valor + 1
        folio.save()

        java.text.NumberFormat nf = java.text.DecimalFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMinimumIntegerDigits(9);
        nf.setMaximumIntegerDigits(9);
        nf.setMaximumFractionDigits(0);

        poliza.folio = nf.format(folio.valor)
        poliza.save()
        return poliza
    }

    @Transactional(readOnly=true)
    def obtiene(id) {
        log.debug("Obteniendo poliza $id")
        return Poliza.get(id)
    }

    def actualiza(poliza) {
        log.debug("Actualizando la poliza $poliza")
        if (poliza.estatus.equals('ABIERTA')) {
            poliza.save()
        } else {
            throw new RuntimeException("No se puede actualizar una poliza cerrada o cancelada")
        }
        return poliza
    }

    def elimina(poliza) {
        log.debug("Eliminando la poliza $poliza")
        if (poliza.estatus.equals('ABIERTA')) {
            poliza.delete()
        } else {
            throw new RuntimeException("No se puede eliminar una poliza cerrada o cancelada")
        }
    }

    def cierra(poliza) {
        log.debug("Cerrando poliza ${poliza.descripcion}")

        def folio = FolioPoliza.findByNombre('poliza')
        if (!folio) {
            folio = new FolioPoliza(nombre:'poliza')
        }
        folio.valor = folio.valor + 1
        folio.save()

        java.text.NumberFormat nf = java.text.DecimalFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMinimumIntegerDigits(9);
        nf.setMaximumIntegerDigits(9);
        nf.setMaximumFractionDigits(0);

        poliza.folio = nf.format(folio.valor)
        poliza.estatus = 'CERRADA'
        poliza.save()
        return poliza
    }

    def cancela(poliza) {
        log.debug("Cancelando poliza ${poliza.descripcion}")
        poliza.estatus = 'CANCELADA'
        poliza.save()
        return poliza
    }
}
