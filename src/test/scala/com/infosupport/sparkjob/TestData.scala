package com.infosupport.sparkjob

import org.scalacheck._
import faker._

import faker.ResourceLoader.Implicits._

object TestData {
  def address: Gen[Address] = for {
    street         <- Arbitrary.arbitrary[address.StreetAddress]
    buildingNumber <- Gen.choose(1, 100).map(_.toString)
    zipCode        <- Arbitrary.arbitrary[address.PostalCode]
    city           <- Arbitrary.arbitrary[address.City]
  } yield Address(street.value, buildingNumber, zipCode.value, city.value)

  def customer: Gen[Customer] = for {
    customerNumber  <- Gen.choose(1, 10000).map(x => s"C${x}")
    firstName       <- Gen.alphaStr suchThat (_.nonEmpty)
    lastName        <- Gen.alphaStr suchThat (_.nonEmpty)
    invoiceAddress  <- address
    shippingAddress <- address
  } yield Customer(customerNumber, firstName, lastName, invoiceAddress, shippingAddress)
}
