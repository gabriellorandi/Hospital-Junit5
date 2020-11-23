package br.ifsp.hospital.model;

import java.util.Date;

public class Paciente {

    private Long cpf;
    private String nome;
    private String email;
    private String telefone;
    private Date dataNascimento;
    private Sexo sexo;
    private String planoSaude;

    public void atualizar(Paciente paciente) {

        if(paciente.getCpf() != null) {
            this.cpf = paciente.getCpf();
        }

        if(paciente.getNome() != null) {
            this.nome = paciente.getNome();
        }

        if(paciente.getEmail() != null) {
            this.email = paciente.getEmail();
        }

        if(paciente.getTelefone() != null) {
            this.telefone = paciente.getTelefone();
        }

        if(paciente.getDataNascimento() != null) {
            this.dataNascimento = paciente.getDataNascimento();
        }

        if(paciente.getSexo() != null) {
            this.sexo = paciente.getSexo();
        }

        if(paciente.getPlanoSaude() != null) {
            this.planoSaude = paciente.getPlanoSaude();
        }

    }

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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
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
