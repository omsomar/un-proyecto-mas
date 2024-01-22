#language: es

Característica: Servicio listar intenciones de compra realizadas por el comercio
  yo como asesor bancolombia
  requiero listar las intenciones de compra realizadas
  para verificar que pueda navegar entre las distintas páginas de resultado

  Escenario: Validar la navegación por los resultados de las distintas páginas obtenidas en el listado de intenciones
    Dado se consume el servicio para listar las intenciones de compra
    Cuando se envian los filtros de consulta y obtienen las intenciones de la primer pagina de resultado
      | idQr                                 | paginationKey | paginationSize | idVentaComercio |
      | 595c02f2-7954-4051-9572-1e182453d847 | 0             | 20             | 1546285029      |
    Y se obtienen las intenciones de la segunda pagina de resultado
      | idQr                                 | paginationKey | idVentaComercio |
      | 595c02f2-7954-4051-9572-1e182453d847 | 1             | 1546285029      |
    Entonces se podra obtener intenciones distintas en cada pagina de resultado





