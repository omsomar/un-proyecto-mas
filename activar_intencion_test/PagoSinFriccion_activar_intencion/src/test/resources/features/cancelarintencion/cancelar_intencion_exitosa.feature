#language: es
@E2E_QR_ESTATICO_DINAMICO

Característica: Servicio cancelar intencion de compra con resultado exitoso
  yo como usuario de los servicios de Pagos sin friccion
  requiero cancelar una intencion de compra
  para verificar su correcto funcionamiento

  @CancelarIntencionExitosa
  Esquema del escenario: Cancelar intencion de compra con estructura completa
    Dado que el usuario quiere consumir uno de los servicios del modelo estatico dinamico
    Cuando el utiliza el servicio de cancelar intencion de compra
      | servicio   | escenario   | razonCancelacion   |
      | <servicio> | <escenario> | <razonCancelacion> |
    Entonces el podra ver el resultado exitoso en la cancelacion de la intencion
      | ultimoEscenario   |
      | <ultimoEscenario> |

    Ejemplos:
      | servicio          | escenario          | razonCancelacion      | ultimoEscenario |
      | /intention/cancel | todosLosCampos     | PorValorErrado        |                 |
      | /intention/cancel | todosLosCampos     | PorCambioProducto     |                 |
      | /intention/cancel | todosLosCampos     | PorDescuentoErrado    |                 |
      | /intention/cancel | todosLosCampos     | PorPropinaErrada      |                 |
      | /intention/cancel | todosLosCampos     | PorCambioMedioPago    |                 |
      | /intention/cancel | todosLosCampos     | PorCancelacionCliente |                 |
      | /intention/cancel | camposObligatorios |                       | Si              |