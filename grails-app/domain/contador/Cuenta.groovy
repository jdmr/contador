package contador

class Cuenta {

    String nombre
    String descripcion

    static constraints = {
        nombre(maxSize:64,blank:false)
        descripcion(maxSize:128,blank:false)
    }

    String toString() {
        return descripcion
    }
}
