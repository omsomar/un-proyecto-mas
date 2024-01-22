#language: es
@E2E_QR_ESTATICO_DINAMICO

Característica: Verificar que las intenciones se expiren al agotar el tiempo de espera determinado para su uso
  yo como usuario de los servicios de Pagos sin friccion
  requiero crear una intención de compra y esperar el tiempo máximo de espera para el uso de la intención
  para verificar que el sistema actualice el estado de la intención a Expirada

  @ExpirarIntencion
  Esquema del escenario: Crear una intención de compra exitosamente y verificar que al agotar el tiempo de espera para usar la intención se actualice su estado a Expirada
    Dado que el usuario quiere consumir uno de los servicios del modelo estatico dinamico
    Cuando el utiliza el servicio de crear intencion de compra
      | servicio   | escenario   | valorIntencion   |
      | <servicio> | <escenario> | <valorIntencion> |
    Entonces se agota tiempo de espera y el logra ver que la intencion queda expirada

    Ejemplos:
      | servicio   | escenario      | valorIntencion |
      | /intention | todosLosCampos | 56398          |


