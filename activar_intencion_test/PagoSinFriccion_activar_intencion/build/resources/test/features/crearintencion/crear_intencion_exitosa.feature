#language: es
@E2E_QR_ESTATICO_DINAMICO

Característica: Servicio crear intencion de compra con resultado exitoso
  yo como usuario de los servicios de Pagos sin friccion
  requiero crear una intencion de compra
  para verificar su correcto funcionamiento

  @CrearIntencionExitosa
  Esquema del escenario: Crear intencion de compra con estructura completa
    Dado que el usuario quiere consumir uno de los servicios del modelo estatico dinamico
    Cuando el utiliza el servicio de crear intencion de compra
      | servicio   | escenario   | valorIntencion   |
      | <servicio> | <escenario> | <valorIntencion> |
    Entonces el podra ver el resultado exitoso en la creacion de la intencion
      | escenario   |
      | <escenario> |

    Ejemplos:
      | servicio   | escenario               | valorIntencion |
      | /intention | todosLosCampos          | 13900          |
      | /intention | todosLosCampos          | 2800           |
      | /intention | camposObligatorios      | 5600           |
      | /intention | camposObligatorios      | 7898           |
      | /intention | todosLosCamposDecimales | 44960.47       |
      | /intention | todosLosCamposDecimales | 44960.75       |
      | /intention | todosLosCamposDecimales | 44960.95       |
      | /intention | todosLosCamposDecimales | 1390000000.76  |
      | /intention | todosLosCamposDecimales | 11250.34       |



