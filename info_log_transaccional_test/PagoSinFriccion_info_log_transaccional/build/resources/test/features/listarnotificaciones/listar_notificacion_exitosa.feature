#language: es
@E2E_QR_LISTAR_NOTIFICACIONES

Característica: Listar las notificaciones de forma exitosa
  yo como usuario de los servicios de Pagos sin friccion
  requiero realizar una consulta de las notificaciones realizadas en QR
  para verificar su correcto funcionamiento

  @ListarNotificacionExitosa
  Esquema del escenario: Consultar de forma exitosa las notificaciones de QR
    Dado que el usuario quiere consumir uno de los servicios de pago sin friccion
    Cuando se filtran por los campos de consulta
      | escenario   | paginationSize   | tipoDoc   | numDoc   | tipoCuenta   | numCuenta   | fechaInicio   | fechaFin   |
      | <escenario> | <paginationSize> | <tipoDoc> | <numDoc> | <tipoCuenta> | <numCuenta> | <fechaInicio> | <fechaFin> |
    Entonces el podra ver el resultado exitoso de la consulta de notificaciones
      | escenario   | paginationSize   |
      | <escenario> | <paginationSize> |

    Ejemplos:
      | escenario          | paginationSize | tipoDoc | numDoc    | tipoCuenta | numCuenta   | fechaInicio | fechaFin   |
      | todosLosCampos     | 5              | CC      | 625160213 | D          | 40610213004 | 2021-09-19  | 2021-09-24 |
      | todosLosCampos     | 5              | CC      | 51266     | S          | 40671266007 | 2021-09-10  | 2021-09-14 |
      | camposObligatorios | 5              |         |           |            |             |             |            |
      | fechas             | 5              |         |           |            |             | 2021-09-10  | 2021-09-14 |
      | fechaInicial       | 5              |         |           |            |             | 2021-06-01  |            |
      | fechasYtipoDoc     | 5              | CC      | 51266     |            |             | 2021-09-19  | 2021-09-24 |
      | fechasYtipoCuenta  | 5              |         |           | S          | 40671266007 | 2021-09-10  | 2021-09-14 |
      | tipoDoc            | 5              | CC      |           |            |             |             |            |
      | tipoCuenta         | 5              |         |           | D          |             |             |            |
      | tipoNumDoc         | 5              | CC      | 625160214 |            |             |             |            |
      | tipoNumCuenta      | 5              |         |           | D          | 40610213004 |             |            |

