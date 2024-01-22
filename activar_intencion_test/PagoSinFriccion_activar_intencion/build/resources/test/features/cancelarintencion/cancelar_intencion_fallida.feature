#language: es
@E2E_QR_ESTATICO_DINAMICO

Característica: Servicio cancelar intencion de compra con resultado fallido
  yo como usuario de los servicios de Pagos sin friccion
  requiero cancelar una intencion de compra pero con el body incompleto
  para verificar el fallo esperado al incumplir con la debida estructura de la peticion

  @CancelarIntencionFallida
  Esquema del escenario: Cancelar intencion de compra con campos faltantes en su estructura
    Dado que el usuario quiere consumir uno de los servicios del modelo estatico dinamico
    Cuando el utiliza el servicio de cancelar intencion de compra
      | servicio   | escenario   | campoIncorrecto   |
      | <servicio> | <escenario> | <campoIncorrecto> |
    Entonces el podra ver el resultado fallido en la cancelacion de la intencion <campoIncorrecto> <codigoError>

    Ejemplos:
      | servicio          |  | escenario         | campoIncorrecto    | codigoError |
      | /intention/cancel |  | camposIncompletos |                    | 400         |
      | /intention/cancel |  | camposIncorrectos | razonCancelacion   | 400         |
      | /intention/cancel |  | camposIncorrectos | idIntencion        | 400         |
      | /intention/cancel |  | camposIncorrectos | idIntenInexistente | 409         |
      | /intention/cancel |  | camposIncorrectos | intencionCancelada | 409         |
      | /intention/cancel |  | camposIncorrectos | intencionExpirada  | 409         |
      | /intention/cancel |  | camposIncorrectos | intencionExitosa   | 409         |



