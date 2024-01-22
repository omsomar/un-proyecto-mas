#language: es

Característica: listar intenciones de compra realizadas por el comercio
  Yo como asesor bancolombia
  deseo listar las intenciones de compra realizadas
  para conocer el detalle de las mismas


  Esquema del escenario: realizar la lista de intenciones de compra exitosamente
    Dado se consume el servicio para listar las intenciones de compra
    Cuando se envian los filtros de consulta
      | idQr    | tipoDoc   | numDoc   | tipoCuenta   | numCuenta   | idVentaComercio     | paginationSize   |
      | <id_qr> | <tipoDoc> | <numDoc> | <tipoCuenta> | <numCuenta> | <id_venta_comercio> | <paginationSize> |
    Entonces se verifica que el servicio retorne informacion

    Ejemplos:
      | id_venta_comercio | id_qr                                | tipoDoc | numDoc | tipoCuenta | numCuenta   | paginationSize |
      | ZHE445            |                                      |         |        |            |             | 1              |
      |                   | 31e5bcea-5edb-4b07-b781-37abb68c1f4b |         |        |            |             | 1              |
      |                   |                                      | CC      |        |            |             | 1              |
      |                   |                                      |         | 111    |            |             | 1              |
      |                   |                                      |         |        | S          |             | 1              |
      |                   |                                      |         |        |            | 40670264005 | 1              |


  Esquema del escenario: Listar por filtros para verificar paginación
    Dado se consume el servicio para listar las intenciones de compra
    Cuando se envian los filtros de consulta
      | idQr    | tipoDoc   | numDoc   | tipoCuenta   | numCuenta   | idVentaComercio     | paginationSize   |
      | <id_qr> | <tipoDoc> | <numDoc> | <tipoCuenta> | <numCuenta> | <id_venta_comercio> | <paginationSize> |
    Entonces se verifica que la paginacion sea la esperada

    Ejemplos:
      | id_venta_comercio | id_qr | tipoDoc | numDoc | tipoCuenta | numCuenta | paginationSize |
      |                   |       |         |        | S          |           | 5              |
      |                   |       |         | 111    |            |           | 5              |
      |                   |       |         | 1      |            |           | 5              |

