#language: es
@E2E_PGI_VINCULACION_QR

Característica: Portal de Generacion Interna (PGI) modulo Vinculacion QR
  yo como usuario del portal de Generacion interna (PGI)
  requiero realizar una vinculación de comercios nuevos al servicio de QR
  para verificar el estado de la solictud de vinculacion quedo en pendiente

  #Se realiza prueba con cédula reportada en listas de control y número de cuenta válido
  @GuardarVinculacionEstadoPendiente
  Esquema del escenario: Completar Vinculacion QR con Estado Pendiente
    Dado que el usuario se encuentra en el Portal Interno de Generacion
      | dispositivo   | navegador   |
      | <dispositivo> | <navegador> |
    Cuando el escoge el Plan Avanzado para Vinculacion QR <plan>
    Y el comienza el proceso de vinculacion y visualiza el estado de solicitud pendiente <estadoVinculacionId>
      | modelo     | actividadNeg   | personasNotificar | tipoDoc   | numDoc   | tipoCta   | numeroCta   |
      | <modeloId> | <actividadNeg> | <personasNotif>   | <tipoDoc> | <numDoc> | <tipoCta> | <numeroCta> |
    Y se complete el proceso de vinculacion
      | modelo     | tipoComision     | valorComision     | exoneracion   | notificacion   | saldosConsolidados   | reporteTrxDiario   | estructuradoPor   | opcionEntrega   | cantCorreos   |
      | <modeloId> | <tipoComisionId> | <valorComisionId> | <exoneracion> | <notificacion> | <saldosConsolidados> | <reporteTrxDiario> | <estructuradoPor> | <opcionEntrega> | <cantCorreos> |
    Entonces el podra ver un mensaje de Vinculacion Exitosa

    Ejemplos:
      | dispositivo | navegador | plan          | modeloId     | actividadNeg | personasNotif | tipoDoc | numDoc     | tipoCta | numeroCta   | estadoVinculacionId | tipoComisionId | valorComisionId | exoneracion | notificacion | saldosConsolidados | reporteTrxDiario | estructuradoPor   | opcionEntrega | cantCorreos |
      | pc          | chrome    | Plan Avanzado | QR Integrado | Mascotas     | 1             | CC      | 1989647189 | S       | 22222222222 | pendiente           | F              | 100             | No          | SI           | Si                 | Si               | documento titular | Descargable   | 2           |
      | pc          | chrome    | Plan Avanzado | QR Estático  | Mercado      | 3             | CC      | 1989647189 | S       | 22222222222 | pendiente           | P              | 1.5             | Si          |              | No                 | no               |                   |               |             |
      | pc          | chrome    | Plan Avanzado | QR Mixto     | Tecnología   | 5             | CC      | 1989647189 | S       | 22222222222 | pendiente           | S              | 0               | No          |              |                    | Si               | documento titular | SFTP          |             |


