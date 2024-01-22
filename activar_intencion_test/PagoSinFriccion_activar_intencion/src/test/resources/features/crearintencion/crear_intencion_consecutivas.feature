#language: es
@E2E_QR_ESTATICO_DINAMICO

Característica: Crear dos intenciones de compra para el mismo QR
  yo como usuario de los servicios de Pagos sin friccion
  requiero crear dos intenciones de compra para el mismo QR
  para verificar que el sistema cancele automáticamente la primera intención creada y unicamente quede pendiente la última intención registrada

  @CrearIntencionExitosa
  Escenario: Crear más de una intención de compra para el mismo QR y verificar que solo la última intención sea la que quede pendiente
    Dado que el usuario quiere consumir uno de los servicios del modelo estatico dinamico
    Cuando el utiliza el servicio de crear intencion de compra
      | servicio   | escenario                     | valorIntencion |
      | /intention | camposIntencionesConsecutivas | 56398          |
    Y se crea otra intencion de compra para el mismo QR
      | servicio   | escenario                     | valorIntencion |
      | /intention | camposIntencionesConsecutivas | 13455.89       |
    Entonces el podra ver que la intencion nueva queda pendiente y la anterior se cancele



