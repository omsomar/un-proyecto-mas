#language: es
Característica: Imposibilitar la eliminación de las exoneraciones para filtros inválidos
  yo como usuario de los servicios de Pagos sin friccion
  requiero que el servicio de eliminar exoneraciones vencidas no se ejecute correctamente cuando los parámetros de filtro no son válidos
  para comprobar la respuesta de error del servicio

  Esquema del escenario: Verificar que el servicio de eliminar exoneraciones retorne el error correspondiente al enviar parámetros de consulta inválidos
    Dado que el usuario quiere consumir uno de los servicios de pagos sin friccion
    Cuando se utiliza el servicio eliminacion de exoneracion con parametros de filtro invalidos
      | fecha   |
      | <fecha> |
    Entonces el podra ver la respuesta de error del servicio <codRespuesta>

    Ejemplos:
      | fecha       | codRespuesta |
      |             | 400          |
      | 20-01-2022  | 400          |
      | 2021-1-3    | 400          |
      | 32423324    | 400          |
      | Ene-01-2021 | 400          |
      | 2010-09-01  | 200          |




