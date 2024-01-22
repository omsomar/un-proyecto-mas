#language: es
@E2E_VINCULACION

Característica: Servicio consultar vinculación por id con resultado fallido
  yo como usuario de los servicios de Pagos sin friccion
  requiero consultar una vinculación por su id de forma errónea
  para verificar el fallo esperado al incumplir con la debida estructura de la peticion

  @ConsultarVinculacionFallida
  Esquema del escenario: Consultar de forma errónea una vinculación filtrando por el id
    Dado que el usuario quiere consumir uno de los servicios de comision
    Cuando el utiliza el servicio de consultar vinculacion
      | idVinculacion   | escenario   |
      | <idVinculacion> | <escenario> |
    Entonces el podra ver el resultado erroneo de la consulta <codigoRespuesta>

    Ejemplos:
      | idVinculacion                        | escenario              | codigoRespuesta |
      |                                      | idVacio                | 400             |
      | 21246802-30i0240#4545                | idInvalido             | 400             |
      | b557ff07-034f-4dcb-a80e-be4ead35b1aa | sinId                  | 400             |
      | bf230293-7f6e-41c3-bb5b-e06e572cs8a8 | idInexistente          | 400             |
      | "343434                              | caracteresNoPermitidos | 400             |
      | %454545                              | caracteresNoPermitidos | 400             |
      | bf230293_7f6e_41c3-bb5b_e06e572cb8a8 | caracteresNoPermitidos | 400             |
      | '23235d'234csd\sdasd                 | caracteresNoPermitidos | 400             |
      | 0e233eb7-0b9c-4b5f-91bd-fa77332b7a5d | recursoErroneo         | 404             |


