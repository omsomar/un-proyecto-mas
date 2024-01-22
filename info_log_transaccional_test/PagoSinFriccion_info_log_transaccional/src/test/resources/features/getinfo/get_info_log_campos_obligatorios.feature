#language: es
@E2E_QR_INFO_LOG_TRANSACCIONAL

Característica: Servicio get info log transaccional consultandolo solamente con los campos obligatorios a filtrar
  yo como usuario de los servicios de Pagos sin friccion
  requiero realizar una consulta de los logs transaccionales usando solo los campos obligatorios en los filtros de busqueda
  para verificar su correcto funcionamiento

  @GetInfoLogTransaccionalCamposObligatorios
  Esquema del escenario: Get info log transaccional con campos obligatorios
    Dado que el usuario quiere consumir uno de los servicios de pago sin friccion
    Cuando el utiliza el servicio de Get Info Log Transaccional
      | escenario   | peticion     | numeroPagina    | tamanoPagina     | canal       | numRastreo     | tipoCtaDestino | numCtaDestino | tipoDocDestino | numDocDestino | pointOfSale     | cashRegister     | cashRegisterSerial     | seller     |
      | <escenario> | <peticionId> | <paginationKey> | <paginationSize> | <channelQr> | <numRastreoId> | <tipoCtaId>    | <numCtaId>    | <tipoDocId>    | <numDocId>    | <pointOfSaleId> | <cashRegisterId> | <cashRegisterSerialId> | <sellerId> |
    Entonces el podra ver el resultado exitoso de la consulta
      | escenario   | peticion     |
      | <escenario> | <peticionId> |

    Ejemplos:
      | escenario                    | peticionId | paginationKey | paginationSize | channelQr | numRastreoId    | tipoCtaId | numCtaId    | tipoDocId | numDocId | pointOfSaleId           | cashRegisterId | cashRegisterSerialId | sellerId  |
      | camposObligatorios           | getInfo    | 1             | 1              |           |                 |           |             |           |          |                         |                |                      |           |
      | camposObligatoriosOpcionales | getInfo    | 0             | 5              |           | TR1614093800528 |           |             |           |          |                         |                |                      |           |
      | todosLosCampos               | getInfo    | 0             | 5              | APP       | QR374832247     | S         | 40670264005 | CC        | 25130264 | CEOH Piso 4 Puesto 1587 | Caja 4         | Caja Serial          | Alejandra |
