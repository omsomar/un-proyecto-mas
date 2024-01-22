#language: es
@E2E_VINCULACION

Característica: Servicio listar vinculacion, peticion con todos los campos posibles
  yo como usuario de los servicios de Pagos sin friccion
  requiero consultar la informacion de la vinculacion de comercios realizando peticion con todos los campos posibles
  para verificar la funcionalidad correcta del servicio encargado de listar la informacion del tercero vinculado

  @ListarVinculacionTodosLosCampos
  Esquema del escenario: Consultar Informacion de vinculación, peticion con todos los campos posibles
    Dado que el usuario quiere consumir uno de los servicios de Pago sin Friccion en nube
    Cuando el utiliza el servicio listar vinculacion
      | servicio     | contenidoRequest | numeroPagina    | tamanoPagina     |
      | <servicioId> | <requestContent> | <paginationKey> | <paginationSize> |
    Entonces el podra ver toda la informacion de los campos en el registro de vinculacion consultado

    Ejemplos:
      | servicioId               | requestContent   | paginationKey | paginationSize |
      | /qr-vinculation/get-all/ | Todos los campos | 0             | 50             |
