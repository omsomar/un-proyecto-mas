Feature: listar intenciones de compra realizadas por el comercio
  Yo como asesor bancolombia deseo listar las intenciones de compra realizadas para conocer el detalle de las mismas

  Scenario Outline: Listar por filtros con datos erroneos
    Given se consume el servicio para listar las intenciones de compra
    When se envian los filtros de consulta
      | idQr    | tipoDoc   | numDoc   | tipoCuenta   | numCuenta   | idVentaComercio     | paginationSize   |
      | <id_qr> | <tipoDoc> | <numDoc> | <tipoCuenta> | <numCuenta> | <id_venta_comercio> | <paginationSize> |
    Then se verifica que el servicio no retorne informacion

    Examples:
      | id_venta_comercio | id_qr | tipoDoc    | numDoc | tipoCuenta | numCuenta | paginationSize |
      |                   |       |            |        |            | c         | 1              |
      |                   | *     |            |        |            |           | 1              |
      |                   | -     |            |        |            |           | 1              |
      |                   |       | *          |        |            |           | 1              |
      |                   |       | -          |        |            |           | 1              |
      |                   |       |            | *      |            |           | 1              |
      |                   |       |            | -      |            |           | 1              |
      |                   |       |            |        | *          |           | 1              |
      |                   |       |            |        | -          |           | 1              |
      |                   |       |            |        |            | *         | 1              |
      |                   |       |            |        |            | -         | 1              |
      |                   | #     |            |        |            |           | 1              |
      |                   |       | #          |        |            |           | 1              |
      |                   |       |            | #      |            |           | 1              |
      |                   |       |            |        | #          |           | 1              |
      |                   |       |            |        |            | #         | 1              |
      |                   |       | ALL        |        |            |           | 1              |
      |                   |       |            |        | cc         |           | 1              |
      |                   |       | 0          |        |            |           | 1              |
      |                   |       |            | c      |            |           | 1              |
      |                   |       |            |        | 0          |           | 1              |
      |                   |       | abcdf      |        |            |           | 1              |
      |                   |       |            |        | abcdf      |           | 1              |
      |                   |       | "*"        |        |            |           | 1              |
      |                   |       | ""         |        |            |           | 1              |
      |                   |       | "          |        |            |           | 1              |
      |                   |       | SELECT "*" |        |            |           | 1              |
      |                   |       |            |        | SELECT "*" |           | 1              |