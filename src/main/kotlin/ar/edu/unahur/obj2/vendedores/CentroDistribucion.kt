package ar.edu.unahur.obj2.vendedores

class centroDeDistribucion(val ciudad: Ciudad){

    val vendedores = mutableListOf<Vendedor>()

    fun aniadirVendedor(vendedor: Vendedor){
        if (!vendedores.contains(vendedor)) {
            vendedores.add(vendedor)
        }else{
            throw Exception ("El vendedor ya esta en el centro")
        }
    }

    fun sacarVendedor(vendedor: Vendedor){
        vendedores.remove(vendedor)
    }

    fun vendedorEstrella(): Vendedor?{
        if (!vendedores.isEmpty()){
            return vendedores.maxBy { vendedor -> vendedor.puntajeCertificaciones() }
        }else{
            throw Exception ("No hay vendedores")
        }
    }

    fun puedeCubrirCiudad(ciudad: Ciudad): Boolean{
        return vendedores.any { vendedor -> vendedor.puedeTrabajarEn(ciudad) }
    }

    fun vendedoresGenerico(): List<Vendedor>{
        return vendedores.filter { vendedor -> vendedor.otrasCertificaciones() >= 1}
    }

    fun esRobusto(): Boolean{
        return vendedores.count { vendedores -> vendedores.esFirme() } >= 3
    }
}