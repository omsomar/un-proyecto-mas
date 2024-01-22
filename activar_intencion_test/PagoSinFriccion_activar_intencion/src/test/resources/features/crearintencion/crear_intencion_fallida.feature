#language: es
@E2E_QR_ESTATICO_DINAMICO

Característica: Servicio crear intencion de compra con resultado fallido
  yo como usuario de los servicios de Pagos sin friccion
  requiero crear una intencion de compra pero con el body incompleto
  para verificar el fallo esperado al incumplir con la debida estructura de la peticion

  @CrearIntencionFallida
  Esquema del escenario: Crear intencion de compra con campos faltantes en su estructura
    Dado que el usuario quiere consumir uno de los servicios del modelo estatico dinamico
    Cuando el utiliza el servicio de crear intencion de compra
      | servicio   | escenario   | campoFaltante   | campoIncorrecto   |
      | <servicio> | <escenario> | <campoFaltante> | <campoIncorrecto> |
    Entonces el podra ver el resultado fallido en la creacion de la intencion <codigoError>

    Ejemplos:
      | servicio   | escenario         | campoFaltante   | campoIncorrecto   | codigoError |
      | /intention | camposIncompletos | qrId            |                   | 400         |
      | /intention | camposIncompletos | amount          |                   | 400         |
      | /intention | todosLosCampos    | clientSecret    |                   | 401         |
      | /intention | todosLosCampos    | sinCredenciales |                   | 401         |
      | /intention | camposIncorrectos |                 | amount            | 400         |
      | /intention | camposIncorrectos |                 | amountDecimales   | 400         |
      | /intention | camposIncorrectos |                 | qrId              | 400         |
      | /intention | camposIncorrectos |                 | idVentaComercio   | 400         |
      | /intention | camposIncorrectos |                 | idVentaComercioCE | 400         |
      | /intention | camposIncorrectos |                 | numFidelidad      | 400         |
      | /intention | longitudMaxima    |                 | qrId              | 400         |
      | /intention | longitudMaxima    |                 | numFidelidad      | 400         |
      | /intention | longitudMaxima    |                 | amount            | 400         |
      | /intention | longitudMaxima    |                 | idVentaComercio   | 400         |
      | /intention | longitudMaxima    |                 | referencia        | 400         |
      | /intention | camposVacios      |                 |                   | 400         |




