package contador

class Movimiento {
    Cuenta cuenta
    BigDecimal importe

    static belongsTo = Cuenta

    static constraints = {
        cuenta()
        importe(scale:2,precision:8)
    }
}
