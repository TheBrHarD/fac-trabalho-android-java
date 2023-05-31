package com.example.congressounirp.model;

public class Trabalho {


    private String id;
    private String siglaNo;
    private String categoria;
    private String instituicao;
    private String nomeAutor;
    private String nomeOrientador;
    private String titulo;
    private String codigoAvalResumo;
    private String notaResumo;
    private String codigoAvalApres;
    private String notaApres;
    private String notaPoster;
    private String mediaTrab;


    public Trabalho() {
        id = null;
        siglaNo = null;
        categoria = null;
        instituicao = null;
        nomeAutor = null;
        nomeOrientador = null;
        titulo = null;
        codigoAvalResumo = null;
        notaResumo = "";
        codigoAvalApres = null;
        notaApres = "";
        notaPoster = "";
        mediaTrab = "";
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getSiglaNo() {
        return siglaNo;
    }
    public void setSiglaNo(String siglaNo) {
        this.siglaNo = siglaNo;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getNomeOrientador() {
        return nomeOrientador;
    }
    public void setNomeOrientador(String nomeOrientador) {
        this.nomeOrientador = nomeOrientador;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCodigoAvalResumo() {
        return codigoAvalResumo;
    }
    public void setCodigoAvalResumo(String codigoAvalResumo) {
        this.codigoAvalResumo = codigoAvalResumo;
    }

    public String getNotaResumo() {
        return notaResumo;
    }
    public void setNotaResumo(String notaResumo) {
        this.notaResumo = notaResumo;
    }

    public String getCodigoAvalApres() {
        return codigoAvalApres;
    }
    public void setCodigoAvalApres(String codigoAvalApres) {
        this.codigoAvalApres = codigoAvalApres;
    }

    public String getNotaApres() {
        return notaApres;
    }
    public void setNotaApres(String notaApres) { this.notaApres = notaApres; }


    public String getNotaPoster() {
        return notaPoster;
    }
    public void setNotaPoster(String notaPoster) { this.notaPoster = notaPoster; }

    public String getMediaTrab() {
        return mediaTrab;
    }
    public void setMediaTrab(String mediaTrab) {
        this.mediaTrab = mediaTrab;
    }

    @Override
    public String toString() {
        return
                " Título: '" + titulo + '\'' + "     -     '" + siglaNo + '\'' +
                "\n\n Categoria: '" + categoria + '\'' +
                "\n Instituição: '" + instituicao + '\'' +
                "\n Nome do Autor: '" + nomeAutor + '\'' +
                "\n Nome do Orientador: '" + nomeOrientador + '\'' +
                "\n Código do Aval. Resumo: '" + codigoAvalResumo + '\'' +
                "\n Código do Aval. Apresentação: '" + codigoAvalApres + '\'' +
                "\n Nota do Resumo: " + notaResumo +
                "\n Nota da Apresesentação: " + notaApres +
                "\n Nota do Pôster: " + notaPoster +
                "\n Média: " + mediaTrab;
    }
}
