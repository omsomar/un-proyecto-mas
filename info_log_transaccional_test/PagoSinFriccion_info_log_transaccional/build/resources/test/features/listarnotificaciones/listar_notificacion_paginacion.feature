#language: es
@E2E_QR_LISTAR_NOTIFICACIONES

Característica: Paginación del servicio listar las notificaciones
  yo como usuario de los servicios de Pagos sin friccion
  requiero listar las notificaciones realizadas en QR
  para verificar el correcto funcionamiento de la paginación

  @NavegarPorListarNotificaciones
  Escenario: Validar la navegación por los resultados de las distintas páginas obtenidas en el listado de notificaciones
    Dado que el usuario quiere consumir uno de los servicios de pago sin friccion
    Cuando se filtran por los campos de consulta y obtienen los resultados de la primer pagina
      | escenario  | paginationKey | paginationSize | fechaInicio | fechaFin   |
      | paginacion | 0             | 30             | 2021-09-18  | 2021-09-24 |
    Y se obtienen las notificaciones de la segunda pagina de resultado
      | escenario  | paginationKey | paginationSize | fechaInicio | fechaFin   |
      | paginacion | 1             | 30             | 2021-09-18  | 2021-09-24 |
    Entonces el podra ver que los resultados en cada pagina son distintos

  @ValidarPaginacionListarNotificaciones
  Esquema del escenario: Validar total de páginas obtenidas en el listar notificaciones
    Dado que el usuario quiere consumir uno de los servicios de pago sin friccion
    Cuando se filtran por los campos de consulta y obtienen los resultados de la primer pagina
      | escenario   | paginationKey   | paginationSize   | fechaInicio   | fechaFin   |
      | <escenario> | <paginationKey> | <paginationSize> | <fechaInicio> | <fechaFin> |
    Entonces se verifica que la paginacion sea la esperada

    Ejemplos:
      | escenario  | paginationKey | paginationSize | fechaInicio | fechaFin   |
      | paginacion | 0             | 30             | 2021-09-18  | 2021-09-24 |
      | paginacion | 0             | 2              | 2021-10-01  | 2021-10-13 |