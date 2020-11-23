package br.ifsp.hospital.model;

import java.util.Date;

public class Medico {

    private Integer crm;
    private String nome;
    private String email;
    private Date dataNascimento;
    private Sexo sexo;
    private String especialidade;
    private String universidadeFormacao;
    private String telefone;

    public void atualizar(Medico medico) {

        if(!medico.getNome().equals( this.nome )){
            this.nome = medico.getNome();
        }

        if(!medico.getEmail().equals( this.email )){
            this.email = medico.getEmail();
        }

        if(!medico.getDataNascimento().equals( this.dataNascimento )){
            this.dataNascimento = medico.getDataNascimento();
        }

        if(!medico.getSexo().equals( this.sexo )){
            this.sexo = medico.getSexo();
        }

        if(!medico.getEspecialidade().equals( this.especialidade )){
            this.especialidade = medico.getEspecialidade();
        }

        if(!medico.getUniversidadeFormacao().equals( this.universidadeFormacao )){
            this.universidadeFormacao = medico.getUniversidadeFormacao();
        }

        if(!medico.getTelefone().equals( this.telefone )){
            this.telefone = medico.getTelefone();
        }

    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getUniversidadeFormacao() {
        return universidadeFormacao;
    }

    public void setUniversidadeFormacao(String universidadeFormacao) {
        this.universidadeFormacao = universidadeFormacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return  "crm=" + crm +
                " - nome='" + nome +
                " - email='" + email +
                " - dataNascimento=" + dataNascimento +
                " - sexo=" + sexo +
                " - especialidade='" + especialidade +
                " - universidadeFormacao='" + universidadeFormacao +
                " - telefone='" + telefone;
    }
}
