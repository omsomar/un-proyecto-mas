#language: es
@E2E_QR_LISTAR_NOTIFICACIONES

Característica: Consultar de forma errónea las notificaciones de QR
  yo como usuario de los servicios de Pagos sin friccion
  requiero realizar una consulta fallida de las notificaciones realizadas en QR
  para verificar la respuesta de error entregada por el servicio

  @ListarNotificacionFallida
  Esquema del escenario: Consultar de manera fallida las notificaciones de QR
    Dado que el usuario quiere consumir uno de los servicios de pago sin friccion
    Cuando se filtran por los campos de consulta incorrectos
      | escenario   | campoIncorrecto   | paginationSize   | tipoDoc   | numDoc   | tipoCuenta   | numCuenta   | fechaInicio   | fechaFin   |
      | <escenario> | <campoIncorrecto> | <paginationSize> | <tipoDoc> | <numDoc> | <tipoCuenta> | <numCuenta> | <fechaInicio> | <fechaFin> |
    Entonces el podra ver el resultado fallido de la consulta de notificaciones <codigoError> <campoIncorrecto>

    Ejemplos:
      | escenario         | campoIncorrecto          | codigoError | paginationSize | tipoDoc | numDoc                | tipoCuenta | numCuenta         | fechaInicio | fechaFin   |
      | camposIncompletos | paginationKey            | 400         | 5              |         |                       |            |                   |             |            |
      | camposIncompletos | paginationSize           | 400         |                |         |                       |            |                   |             |            |
      | camposIncorrectos | fechaInicioMayorFechaFin | 400         | 5              |         |                       |            |                   | 2021-09-14  | 2021-09-01 |
      | camposIncorrectos | fechanSinResultados      | 200         | 5              |         |                       |            |                   | 2012-09-01  | 2012-09-30 |
      | camposIncorrectos | tipoDocInvalido          | 400         | 10             | CP      |                       |            |                   |             |            |
      | camposIncorrectos | tipoDocSinResultados     | 200         | 10             | PASS    |                       |            |                   | 2021-09-07  | 2021-09-23 |
      | camposIncorrectos | formatoFechaInvalido     | 400         | 10             | CC      | 625160213             |            |                   | 2021/09/20  | 2021/09/24 |
      | camposIncorrectos | paginationKey            | 400         | 10             | CC      | 625160213             |            |                   | 2021-09-20  | 2021-09-24 |
      | camposIncorrectos | paginationSize           | 400         | sss            | CC      | 625160213             |            |                   | 2021-09-20  | 2021-09-24 |
      | camposIncorrectos | paginationKeyInexistente | 200         | 40             | CC      | 625160213             |            |                   | 2021-09-20  | 2021-09-24 |
      | camposVacios      |                          | 400         |                |         |                       |            |                   |             |            |
      | longitudMaxima    | numCuenta                | 400         | 10             |         |                       | S          | 07400015349155673 |             |            |
      | longitudMaxima    | numDoc                   | 400         | 10             | CC      | 113041222222222289135 |            |                   |             |            |
      | recursoErroneo    |                          | 404         | 100            |         |                       |            |                   |             |            |





