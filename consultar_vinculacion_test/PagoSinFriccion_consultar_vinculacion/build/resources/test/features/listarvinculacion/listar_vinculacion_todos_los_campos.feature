#language: es
@E2E_VINCULACION

Característica: Servicio listar vinculacion, peticion con todos los campos posibles
  yo como usuario de los servicios de Pagos sin friccion
  requiero consultar la informacion de la vinculacion de comercios realizando peticion con todos los campos posibles
  para verificar la funcionalidad correcta del servicio encargado de listar la informacion del tercero vinculado

  @ListarVinculacionTodosLosCampos
  Esquema del escenario: Consultar Informacion de vinculación, peticion con todos los campos posibles
    Dado que el usuario quiere consumir uno de los servicios de Pago sin Friccion en nube
    Cuando el utiliza el servicio listar vinculacion
      | contenidoRequest | numeroPagina    | tamanoPagina     | tipoDocComercio   | numDocComercio   | tipoDocRepr   | numDocRepr   | nombreComercio   | tipoCuenta   | numCuenta   | sterlingPath   | fechaInicio   | fechaFin   |
      | <requestContent> | <paginationKey> | <paginationSize> | <tipoDocComercio> | <numDocComercio> | <tipoDocRepr> | <numDocRepr> | <nombreComercio> | <tipoCuenta> | <numCuenta> | <sterlingPath> | <fechaInicio> | <fechaFin> |
    Entonces el podra ver toda la informacion de los campos en el registro de vinculacion consultado <requestContent>

    Ejemplos:
      | requestContent           | paginationKey | paginationSize | tipoDocComercio | numDocComercio  | tipoDocRepr | numDocRepr  | nombreComercio               | tipoCuenta | numCuenta   | sterlingPath                                     | fechaInicio | fechaFin   |
      | fechas                   | 0             | 20             |                 |                 |             |             |                              |            |             |                                                  | 22/09/2021  | 30/09/2021 |
      | fechaInicio              | 0             | 15             |                 |                 |             |             |                              |            |             |                                                  | 22/09/2021  |            |
      | representanteLegal       | 0             | 10             |                 |                 | CC          | 1151960133  |                              |            |             |                                                  |             |            |
      | documentoComercio        | 0             | 10             | NIT             | 000000802022148 |             |             |                              |            |             |                                                  |             |            |
      | comercio                 | 0             | 10             |                 |                 |             |             | Makrocomputadores            |            |             |                                                  |             |            |
      | numCuenta                | 0             | 10             |                 |                 |             |             |                              | S          | 40671266007 |                                                  |             |            |
      | sterlingPath             | 0             | 10             |                 |                 |             |             |                              |            |             | L_AW1315_PagosSinFriccion_ReporteriaTrx_cnxgpsfp |             |            |
      | algunosCamposVinculacion | 0             | 10             | CC              | 11111111111     | CC          | 11111111111 | Distribuidora Zeus Asociados | S          | 22222222222 |                                                  |             |            |
      | camposObligatorios       | 0             | 6              |                 |                 |             |             |                              |            |             |                                                  |             |            |
      | todosLosCampos           | 0             | 5              | CC              | 1989641978      | CC          | 71526654    | LaManzanaVerde               | S          | 40671978016 | L_AW1315_PagosSinFriccion_ReporteriaTrx_cnxgpsfp | 11/07/2021  | 18/11/2021 |
       # Escenarios de prueba que incluye suscripciones
      | fechas                   | 0             | 15             |                 |                 |             |             |                              |            |             |                                                  | 14/04/2022  | 22/04/2022 |
      | fechaInicio              | 0             | 15             |                 |                 |             |             |                              |            |             |                                                  | 25/04/2022  |            |
      | comercio                 | 0             | 5              |                 |                 |             |             | Regalos La primavera         |            |             |                                                  |             |            |
      | comercio                 | 0             | 11             |                 |                 |             |             | SportMax                     |            |             |                                                  |             |            |
      | documentoComercio        | 0             | 12             | CC              | 1187690486      |             |             |                              |            |             |                                                  |             |            |
      | numCuenta                | 0             | 5              |                 |                 |             |             |                              | S          | 4067770050  |                                                  |             |            |
      | algunosCamposSuscripcion | 0             | 5              | CC              | 1187690486      |             |             | VariedadesCarito             | S          | 4067770050  |                                                  | 21/04/2022  | 27/04/2022 |



