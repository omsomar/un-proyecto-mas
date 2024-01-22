#language: es
@E2E_QR_ALL_INFO_LOG_TRANSACCIONAL

Característica: Servicio Get All Info Log transaccional con obligatorios
  yo como usuario de los servicios de Pagos sin friccion
  requiero realizar una consulta de todos los logs transaccionales usando solo los campos obligatorios en los filtros de busqueda
  para verificar su correcto funcionamiento

  @GetAllInfoLogTransaccionalCamposObligatorios
  Esquema del escenario: Get All Info Log Transaccional con campos obligatorios
    Dado que el usuario quiere consumir uno de los servicios de pago sin friccion
    Cuando el utiliza el servicio de Get All Info Log Transaccional
      | escenario   | peticion     | numeroPagina    | tamanoPagina     | canal       | numRastreo     | tipoCtaDestino | numCtaDestino | tipoDocDestino | numDocDestino | pointOfSale     | cashRegister     | cashRegisterSerial     | seller     | estado     |
      | <escenario> | <peticionId> | <paginationKey> | <paginationSize> | <channelQr> | <numRastreoId> | <tipoCtaId>    | <numCtaId>    | <tipoDocId>    | <numDocId>    | <pointOfSaleId> | <cashRegisterId> | <cashRegisterSerialId> | <sellerId> | <estadoId> |
    Entonces el podra ver el resultado exitoso de la consulta
      | escenario   | peticion     |
      | <escenario> | <peticionId> |

    Ejemplos:
      | escenario                    | peticionId | paginationKey | paginationSize | channelQr | numRastreoId | tipoCtaId | numCtaId  | tipoDocId | numDocId  | pointOfSaleId           | cashRegisterId | cashRegisterSerialId | sellerId  | estadoId |
      | camposObligatorios           | getAllInfo | 0             | 1              |           |                 |           |             |           |          |                         |                |                      |           |          |
      | camposObligatoriosOpcionales | getAllInfo | 0             | 5              |           |              |           |           |           |           |                         |                |                      |           | rejected |
      | todosLosCampos               | getAllInfo | 0             | 5              | Nequi     | 2237583765b2 | S         | 40670213026 | CC        | 625160213 | CEOH Piso 4 Puesto 1587 | Caja 4         |                      | Alejandra | approved |
