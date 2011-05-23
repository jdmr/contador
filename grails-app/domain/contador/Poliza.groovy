package contador

class Poliza {
    String folio
    String descripcion
    String estatus = 'ABIERTA'
    String clasificacion = 'DIARIO'
    Set transacciones
    Date dateCreated
    Date lastUpdated

    static hasMany = [transacciones:Transaccion]

    static constraints = {
        folio(blank:false,maxSize:64)
        descripcion(blank:false)
        estatus(maxSize:64, inList:['ABIERTA','CERRADA','CANCELADA'])
        clasificacion(maxSize:64, inList:['DIARIO','INGRESOS','EGRESOS'])
    }
}
