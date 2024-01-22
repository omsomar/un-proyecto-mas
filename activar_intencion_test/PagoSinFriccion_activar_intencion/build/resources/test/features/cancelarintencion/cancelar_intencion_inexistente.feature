#language: es
@E2E_QR_ESTATICO_DINAMICO

Característica: Servicio cancelar intencion de compra con resultado fallido
  yo como usuario de los servicios de Pagos sin friccion
  requiero validar como se comporta la app cuando se intenta con una intencion
  que no existe

  @CancelarIntencionFallida
  Esquema del escenario: Cancelar intencion de compra no existente
    Dado que el usuario quiere consumir uno de los servicios del modelo estatico dinamico
    Cuando el utiliza el servicio de cancelar intencion de compra
      | servicio   | escenario   |
      | <servicio> | <escenario> |
    Entonces el podra ver que la peticion fue fallida

    Ejemplos:
      | servicio          | escenario   |
      | /intention/cancel | noExistente |
