#language: es
@E2E_QR_INFO_LOG_TRANSACCIONAL

Característica: Servicio get info log transaccional consultandolo sin los campos obligatorios a filtrar
  yo como usuario de los servicios de Pagos sin friccion
  requiero realizar una consulta de los logs transaccionales sin usar uno de los campos obligatorios en los filtros de busqueda
  para verificar el fallo previsto

  @GetInfoLogTransaccionalCamposFaltantes
  Esquema del escenario: Get info log transaccional con campos faltantes
    Dado que el usuario quiere consumir uno de los servicios de pago sin friccion
    Cuando el utiliza el servicio de Get Info Log Transaccional
      | escenario   | peticion     | numeroPagina    | tamanoPagina     |
      | <escenario> | <peticionId> | <paginationKey> | <paginationSize> |
    Entonces el podra ver el resultado fallido en la consulta

    Ejemplos:
      | escenario       | peticionId | paginationKey | paginationSize |
      | camposFaltantes | getInfo    |               | 1              |
