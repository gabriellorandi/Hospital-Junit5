package br.ifsp.hospital.model;

import java.time.ZonedDateTime;

public class Paciente {

    private Long cpf;
    private String nome;
    private String email;
    private String telefone;
    private ZonedDateTime dataNascimento;
    private Sexo sexo;
    private String planoSaude;

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ZonedDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(ZonedDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }

    @Override
    public String toString() {
        return nome + " - " + cpf;
    }
}
