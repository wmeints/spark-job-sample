package com.infosupport.sparkjob

case class Customer(
    customerNumber: String,
    firstName: String,
    lastName: String,
    shippingAddress: Address,
    invoiceAddress: Address
)
case class Address(street: String, buildingNumber: String, zipCode: String, city: String)
