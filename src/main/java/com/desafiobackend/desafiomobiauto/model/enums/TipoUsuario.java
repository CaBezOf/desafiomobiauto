package com.desafiobackend.desafiomobiauto.model.enums;

public enum TipoUsuario {

    ADMIN("admin"),
    PROPRIETARIO("proprietario"),
    GERENTE("gerente"),
    ASSISTENTE("assistente");

    private final String tipoUsuario;

    TipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario(){
        return tipoUsuario;
    }
    public boolean isAutorizadoTransferirOportunidade() {
        return this == PROPRIETARIO || this == GERENTE;
    }
    public boolean isAssistente() {
        return this == ASSISTENTE ;
    }
    public boolean isAdministrador() {
        return this == ADMIN ;
    }
    public boolean isAutorizado() {
        return this == ADMIN || this == PROPRIETARIO;
    }
}
