package contador

class FolioPoliza {
    String nombre = 'temporal'
    Long valor = 0
    Date dateCreated
    Date lastUpdated

    static constraints = {
        nombre(blank:false,maxSize:12,unique:true)
        valor(blank:false)
    }
}
