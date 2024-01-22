#language: es
@E2E_VINCULACION

Característica: Servicio listar vinculacion, peticion de solo campos obligatorios
  yo como usuario de los servicios de Pagos sin friccion
  requiero consultar la informacion de la vinculacion de comercios realizando peticion con solo campos obligatorios
  para verificar la funcionalidad correcta del servicio encargado de listar la informacion del tercero vinculado

  @ListarVinculacionCamposObligatorios
  Esquema del escenario: Consultar Informacion de vinculación, peticion de solo campos obligatorios
    Dado que el usuario quiere consumir uno de los servicios de Pago sin Friccion en nube
    Cuando el utiliza el servicio listar vinculacion
      | servicio     | contenidoRequest | numeroPagina    | tamanoPagina     |
      | <servicioId> | <requestContent> | <paginationKey> | <paginationSize> |
    Entonces el podra ver los registros existentes de acuerdo a los parametros de busqueda

    Ejemplos:
      | servicioId               | requestContent      | paginationKey | paginationSize |
      | /qr-vinculation/get-all/ | Campos obligatorios | 0             | 50             |
