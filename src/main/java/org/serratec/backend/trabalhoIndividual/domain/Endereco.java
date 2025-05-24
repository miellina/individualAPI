package org.serratec.backend.trabalhoIndividual.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable
@Table(name = "enderecos")
public class Endereco {

    @NotNull(message = "A rua é obrigatória")
    @NotBlank(message = "A rua não pode estar vazia")
    @Size(min = 5, max = 200, message = "A rua deve ter entre 5 e 200 caracteres")
    @Column(nullable = false, length = 200)
    private String rua;

    @NotNull(message = "A cidade é obrigatória")
    @NotBlank(message = "A cidade não pode estar vazia")
    @Size(min = 2, max = 100, message = "A cidade deve ter entre 2 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String cidade;

    @NotNull(message = "O estado é obrigatório")
    @NotBlank(message = "O estado não pode estar vazio")
    @Size(min = 2, max = 50, message = "O estado deve ter entre 2 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String estado;

    @NotNull(message = "O CEP é obrigatório")
    @NotBlank(message = "O CEP não pode estar vazio")
    @Column(nullable = false, length = 10)
    private String cep;

    public Endereco() {
    }
    
    public Endereco(String rua, String cidade, String estado, String cep, String numero, String complemento) {
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}