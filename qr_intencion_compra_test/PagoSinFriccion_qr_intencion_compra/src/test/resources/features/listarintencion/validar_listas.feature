Feature: listar intenciones de compra realizadas por el comercio
  Yo como asesor bancolombia deseo listar las intenciones de compra realizadas para conocer el detalle de las mismas

  Scenario: realizar la lista de intenciones de compra exitosamente con multiple data
    Given se consume el servicio para listar las intenciones de compra
    When se envian los filtros de consulta
      | idQr                                 | tipoDoc | numDoc | tipoCuenta | numCuenta | idVentaComercio | paginationSize |
      | 31e5bcea-5edb-4b07-b781-37abb68c1f4b |         |        |            |           |                 | 5              |
    Then se verifica que el resultado sea exitoso
      | amount      | commerceInvoiceId | qrId                                 | customerLoyaltyId | createdAt               | intentionStatus |
      | 39780544.14 | 23340             | 31e5bcea-5edb-4b07-b781-37abb68c1f4b | LifeMiles         | 2021-07-02T17:48:37.632 | EXITOSA         |
      | 445000.00   | 23340             | 31e5bcea-5edb-4b07-b781-37abb68c1f4b | LifeMiles         | 2021-07-02T17:48:37.632 | EXITOSA         |
      | 35900.00    | 23340             | 31e5bcea-5edb-4b07-b781-37abb68c1f4b | Puntos Cencosud   | 2021-07-02T17:48:37.632 | CANCELADA       |
      | 4900000.99  | 23340             | 31e5bcea-5edb-4b07-b781-37abb68c1f4b | PuntosColombia    | 2021-07-02T17:48:37.632 | EXPIRADA        |
      | 45600.00    | 23340             | 31e5bcea-5edb-4b07-b781-37abb68c1f4b | Puntos Cencosud   | 2021-07-02T17:48:37.632 | EXPIRADA        |

  Scenario: realizar la lista de intenciones de compra exitosamente
    Given se consume el servicio para listar las intenciones de compra
    When se envian los filtros de consulta
      | idQr                                 | tipoDoc | numDoc | tipoCuenta | numCuenta   | idVentaComercio | paginationSize |
      | 31e5bceb-5edb-4b07-b781-37abb68c1f3b | CC      | 111    | S          | 40670264005 | ZHE445          | 5              |
    Then se verifica que el resultado sea exitoso
      | amount  | commerceInvoiceId | qrId                                 | customerLoyaltyId | createdAt               | intentionStatus |
      | 1000.00 | ZHE445            | 31e5bceb-5edb-4b07-b781-37abb68c1f3b | LifeMiles         | 2021-07-02T17:48:37.632 | EXITOSA         |
