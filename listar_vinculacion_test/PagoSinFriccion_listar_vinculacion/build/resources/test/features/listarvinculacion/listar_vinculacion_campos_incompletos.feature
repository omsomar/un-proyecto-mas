#language: es
@E2E_VINCULACION

Característica: Servicio listar vinculacion
  yo como usuario de los servicios de Pagos sin friccion
  requiero consultar la informacion de la vinculacion de comercios
  para verificar la funcionalidad correcta del servicio encargado de listar la informacion del tercero vinculado

  @ListarVinculacion
  Esquema del escenario: Consultar Informacion de vinculación
    Dado que el usuario quiere consumir uno de los servicios de Pago sin Friccion en nube
    Cuando el utiliza el servicio listar vinculacion
      | servicio     | contenidoRequest | numeroPagina    | tamanoPagina     |
      | <servicioId> | <requestContent> | <paginationKey> | <paginationSize> |
    Entonces el podra ver el resultado fallido al faltar informacion en la peticion

    Ejemplos:
      | servicioId               | requestContent   | paginationKey | paginationSize |

      | /qr-vinculation/get-all/ | Todos los campos |               | 50             |
