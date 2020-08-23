package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import ar.edu.unahur.obj2.vendedores.Ciudad as Ciudad

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }

    describe("esInfluyente"){
      it ("Nunca es influyente"){
        vendedorFijo.esInfluyente().shouldBeFalse()
      }
    }
  }

//*********************************************************************************

  describe("Viajante") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }

    describe ("esInfluyente"){
      it ("La poblacion de sus provincias habilitadas: 1300000"){
        viajante.esInfluyente().shouldBeFalse()
      }
    }
  }

//******************************************************************************************

  describe( "Comercio corresponsal" ){

    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)

    val misiones = Provincia(1300000)
    val sanIgnacio = Ciudad(misiones)
    val obera = Ciudad(misiones)

    val comercioCorresponsal = ComercioCorresponsal(listOf(villaDolores, sanIgnacio))

    describe ("puedeTrabajarEn"){
      it ("Ciudad donde hay sucursal"){
        comercioCorresponsal.puedeTrabajarEn(villaDolores).shouldBeTrue()
      }
      it ("Ciudad donde no hay sucursal"){
        comercioCorresponsal.puedeTrabajarEn(obera).shouldBeFalse()
      }
    }

    describe("esInfluyente"){
      it ("2 ciudades y 2 provincias"){
        comercioCorresponsal.esInfluyente().shouldBeFalse()
      }
    }
  }
})
