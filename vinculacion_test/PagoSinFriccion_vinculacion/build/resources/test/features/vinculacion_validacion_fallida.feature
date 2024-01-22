#language: es
@E2E_PGI_VINCULACION_QR

Característica: Completar el proceso de vinculación al plan avanzado con cuenta inválida
  yo como usuario del portal de Generacion interna (PGI)
  requiero realizar un proceso de Vinculacion QR de comercios ingresando una cuenta no apta
  para verificar la funcionalidad en el bloqueo de continuacion del proceso de Vinculacion QR

  #Se realiza prueba con número de cuenta inválido
  # prueba con num de cuenta inválido y documento reportado en listas de control
  # prueba con num de cuenta de pensionados no cumple con condiciones
  @VinculacionValidacionFallida
  Esquema del escenario: Fallo al guardar Informacion de Vinculación QR
    Dado que el usuario se encuentra en el Portal Interno de Generacion
      | dispositivo   | navegador   |
      | <dispositivo> | <navegador> |
    Cuando el escoge el Plan Avanzado para Vinculacion QR <plan>
    Y el comienza el proceso de vinculacion
      | modelo     | actividadNeg   | personasNotificar | tipoDoc   | numDoc   | tipoCta   | numeroCta   | numAccionistas   |
      | <modeloId> | <actividadNeg> | <personasNotif>   | <tipoDoc> | <numDoc> | <tipoCta> | <numeroCta> | <numAccionistas> |
    Entonces el podra ver un mensaje con el estado fallido en la solicitud de la validacion <estadoVinculacionId>

    Ejemplos:
      | dispositivo | navegador | plan          | modeloId     | actividadNeg     | personasNotif | tipoDoc | numDoc      | tipoCta | numeroCta   | numAccionistas | estadoVinculacionId |
      | pc          | chrome    | Plan Avanzado | QR Mixto     | Moda y vestuario | 1             | NIT     | 44444444444 | S       | 33333333333 | 2              | fallida             |
      | pc          | chrome    | Plan Avanzado | QR Integrado | Mascotas         | 1             | CC      | 154645687   | S       | 07400015388 |                | fallida             |
      | pc          | chrome    | Plan Avanzado | QR Estático  | Hogar            | 1             | CC      | 1989647189  | S       | 11111111111 | 1              | fallida             |
