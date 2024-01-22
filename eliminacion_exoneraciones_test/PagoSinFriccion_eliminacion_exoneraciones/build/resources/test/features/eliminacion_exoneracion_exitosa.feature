#language: es
Característica: Eliminar exitosamente las exoneraciones vencidas
  yo como usuario de los servicios de Pagos sin friccion
  requiero eliminar las exoneraciones vencidas
  para que no se apliquen más dichas exoneraciones definidas

  Esquema del escenario: Verificar que se marquen como inactivas las exoneraciones que se encuentren vencidas para la fecha indicada
    Dado que el usuario quiere consumir uno de los servicios de pagos sin friccion
    Cuando el utiliza el servicio de eliminar las exoneraciones
      | escenario   | fecha   |
      | <escenario> | <fecha> |
    Entonces el podra ver que las exoneraciones vencidas se inactivaron

    Ejemplos:
      | escenario | fecha      |
      | conFecha  | 2021-08-20 |
      | conFecha  | 2022-01-08 |
      | sinFecha  |            |




