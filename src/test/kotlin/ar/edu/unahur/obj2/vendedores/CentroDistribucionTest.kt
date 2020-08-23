package ar.edu.unahur.obj2.vendedores

import io.kotest.assertions.show.show
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class CentroDistribucionTest: DescribeSpec({
    val buenosAires =  Provincia( 2890151 )
    val cordoba =  Provincia( 1430000)
    val entreRios =  Provincia(1235994)
    val santaFe =  Provincia( 490171)
    val corrientes =  Provincia(346334)
    val rioNegro =  Provincia(688873)

    val chivilcoy =  Ciudad(provincia = buenosAires)
    val zarate =  Ciudad(provincia = buenosAires)
    val rafaela =  Ciudad(provincia = santaFe)
    val diamante =  Ciudad(provincia = entreRios)
    val sanFrancisco =  Ciudad(provincia = cordoba)
    val ituzaingo = Ciudad(provincia = buenosAires)
    val rosario =  Ciudad(provincia = santaFe)
    val bariloche =  Ciudad(provincia = rioNegro)

    val certificacion1 =  Certificacion(esDeProducto = true, puntaje = 10)
    val certificacion2 =  Certificacion(esDeProducto = false, puntaje = 5)
    val certificacion3 =  Certificacion(esDeProducto = true, puntaje = 20)
    val certificacion4 =  Certificacion(esDeProducto = false, puntaje = 15)
    val certificacion5 =  Certificacion(esDeProducto = true, puntaje = 25)
    val certificacion6 =  Certificacion(esDeProducto = false, puntaje = 10)


    val vendedorFijo =  VendedorFijo(ciudadOrigen = rafaela)
    vendedorFijo.certificaciones.add(certificacion1)
    vendedorFijo.certificaciones.add(certificacion4)

    val vendedorViajero =  Viajante(listOf(buenosAires, santaFe, rioNegro))
    vendedorViajero.certificaciones.add(certificacion5)
    vendedorViajero.certificaciones.add(certificacion4)

    val comercio =  ComercioCorresponsal(listOf(ituzaingo, chivilcoy, bariloche, diamante))
    comercio.certificaciones.add(certificacion5)
    comercio.certificaciones.add(certificacion1)
    comercio.certificaciones.add(certificacion3)


    val centro = centroDeDistribucion(ciudad = chivilcoy)
    centro.aniadirVendedor(vendedorFijo)
    centro.aniadirVendedor(vendedorViajero)

    describe ("Añadir un vendedor"){
        it("Un vendedor que no esta"){
            centro.aniadirVendedor(comercio)
            centro.vendedores.shouldContainAll(comercio, vendedorFijo, vendedorViajero)
        }
        it("Un vendedor que si esta"){
            shouldThrowAny{
                centro.aniadirVendedor(vendedorViajero)
            }
        }
    }

    describe("CentroDeDistribucion"){
        it ("Vendedor Estrella"){
            centro.vendedorEstrella().shouldBe(comercio)
        }

        centro.vendedores.clear()

        it ("vendedor estrella sin vendedores"){
            shouldThrowAny {
                centro.vendedorEstrella()
            }
        }
    }

    describe ("puedeCubrirCiudad"){
        centro.aniadirVendedor(vendedorFijo)
        centro.aniadirVendedor(vendedorViajero)
        centro.aniadirVendedor(comercio)

        it ("Si puede cubrir" ){
            centro.puedeCubrirCiudad(rosario).shouldBeTrue()
        }
        it ("No puede cubrir" ){
            centro.puedeCubrirCiudad(sanFrancisco).shouldBeFalse()
        }
    }

    describe ("Vendedor generico"){
        it ("Vendedores genericos"){
            centro.vendedoresGenerico().shouldContainAll(vendedorFijo, vendedorViajero)
        }
    }

    describe ("EsRobusto"){
        it ("No es robusto"){
            centro.esRobusto().shouldBeFalse()
        }
    }
})