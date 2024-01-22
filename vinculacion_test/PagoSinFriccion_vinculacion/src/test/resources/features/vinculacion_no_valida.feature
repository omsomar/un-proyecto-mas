#language: es
@E2E_PGI_VINCULACION_QR

Característica: Portal de Generacion Interna (PGI) modulo Vinculacion QR
  yo como usuario del portal de Generacion interna (PGI)
  requiero realizar un proceso de Vinculacion QR de comercios ingresando una cuenta no valida
  para verificar la funcionalidad en el bloqueo de continuacion del proceso de Vinculacion QR

  @VinculacionNoValida
  Esquema del escenario: Erro al guardar Vinculación QR - No Valida
    Dado que el usuario se encuentra en el Portal Interno de Generacion
      | dispositivo   | navegador   |
      | <dispositivo> | <navegador> |
    Cuando el escoge el Plan Avanzado para Vinculacion QR <plan>
    Y el comienza el proceso de vinculacion
      | modelo     | actividadNeg   | personasNotificar | tipoDoc   | numDoc   | cantCuentas   | tipoCta   | numeroCta     | numAccionistas   |
      | <modeloId> | <actividadNeg> | <personasNotif>   | <tipoDoc> | <numDoc> | <cantCuentas> | <tipoCta> | <numeroCtaId> | <numAccionistas> |
    Entonces el podra ver un mensaje solicitud de vinculacion no valida <estadoVinculacionId>

    Ejemplos:
      | dispositivo | navegador | plan          | modeloId     | actividadNeg | personasNotif | tipoDoc | numDoc          | cantCuentas | tipoCta | numeroCtaId | numAccionistas | estadoVinculacionId |
      | pc          | chrome    | Plan Avanzado | QR Integrado | Hogar        | 1             | CC      | 000000112285478 | 5           | D       | 00000000123 | 3              | noValida            |
