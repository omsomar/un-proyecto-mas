#language: es
@E2E_QR_INFO_LOG_TRANSACCIONAL

Característica: Servicio get info log transaccional consultandolo con todos los campos a filtrar
  yo como usuario de los servicios de Pagos sin friccion
  requiero realizar una consulta de los logs transaccionales usando todos los campos opcionales en los filtros de busqueda
  para verificar que se encuentran en la BD

  @GetInfoLogCamposEnBd
  Esquema del escenario: Get info log transaccional con todos los campos
    Dado que el usuario quiere consumir uno de los servicios de pago sin friccion
    Cuando el utiliza el servicio de Get Info Log Transaccional
      | escenario   | peticion     | numeroPagina    | tamanoPagina     | canal       | numRastreo     | tipoCtaDestino | numCtaDestino | tipoDocDestino | numDocDestino | pointOfSale     | cashRegister     | cashRegisterSerial     | seller     |
      | <escenario> | <peticionId> | <paginationKey> | <paginationSize> | <channelQr> | <numRastreoId> | <tipoCtaId>    | <numCtaId>    | <tipoDocId>    | <numDocId>    | <pointOfSaleId> | <cashRegisterId> | <cashRegisterSerialId> | <sellerId> |
    Entonces el podra verificar los campos en la BD Postgres

    Ejemplos:
      | escenario      | peticionId | paginationKey | paginationSize | channelQr | numRastreoId | tipoCtaId | numCtaId    | tipoDocId | numDocId  | pointOfSaleId           | cashRegisterId | cashRegisterSerialId | sellerId  |
      | todosLosCampos | getInfo    | 0             | 1              | Nequi     | 9z699zzg39op | S         | 40670213026 | CC        | 625160213 | CEOH Piso 4 Puesto 1587 | Caja 4         |                      | Alejandra |

