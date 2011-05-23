package contador

class Poliza {
    String folio
    String descripcion
    Boolean cerrada = false
    String estatus
    String clasificacion
    Set transacciones

    static hasMany = [transacciones:Transaccion]

    static constraints = {
        folio(blank:false,maxSize:64)
        descripcion(blank:false)
        estatus(maxSize:64, inList:['ABIERTA','CERRADA','CANCELADA'])
        clasificacion(maxSize:64, inList:['DIARIO','INGRESOS','EGRESOS'])
    }
}
