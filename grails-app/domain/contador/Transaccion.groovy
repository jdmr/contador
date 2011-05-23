package contador

class Transaccion {
    String descripcion
    BigDecimal importe
    Set origenes
    Set destinos
    Date dateCreated
    Date lastUpdated

    static hasMany = [origenes: Movimiento, destinos: Movimiento]

    static constraints = {
        descripcion(blank:false)
        importe(scale:2,precision:8)
    }
}
