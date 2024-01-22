#language: es
Característica: Verificar que las exoneraciones que no esten vencidas se mantengan vigentes
  yo como usuario de los servicios de Pagos sin friccion
  requiero que al consumir el servicio eliminar las exoneraciones, no se eliminen las exoneraciones que se encuentren vigentes
  para que se haga el cobro de comisión de forma correspondiente

  Esquema del escenario: Verificar que las exoneraciones que no se encuentren vencidas no se inactiven
    Dado que el usuario quiere consumir uno de los servicios de pagos sin friccion
    Cuando el utiliza el servicio de eliminar las exoneraciones teniendo exoneraciones vigentes
      | escenario   | fecha   |
      | <escenario> | <fecha> |
    Entonces el podra ver que las exoneraciones vigentes no se inactivaron

    Ejemplos:
      | escenario | fecha |
      | sinFecha  |       |




