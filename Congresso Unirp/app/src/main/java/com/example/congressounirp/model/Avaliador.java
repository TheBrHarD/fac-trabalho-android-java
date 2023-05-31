package com.example.congressounirp.model;

public class Avaliador {


    private String codigoAval;
    private String nomeAval;
    private String emailAval;
    private String cursoAval;
    private String areaAval;
    private String celularAval;


    public Avaliador() {
        codigoAval = null;
        nomeAval = null;
        emailAval = null;
        cursoAval = null;
        areaAval = null;
        celularAval = null;
    }

    public String getCodigoAval() {return codigoAval;}
    public void setCodigoAval(String codigoAval) {this.codigoAval = codigoAval;}

    public String getNomeAval() {return nomeAval;}
    public void setNomeAval(String nomeAval) {this.nomeAval = nomeAval;}

    public String getEmailAval() {return emailAval;}
    public void setEmailAval(String emailAval) {this.emailAval = emailAval;}

    public String getCursoAval() {return cursoAval;}
    public void setCursoAval(String cursoAval) {this.cursoAval = cursoAval;}

    public String getAreaAval() {return areaAval;}
    public void setAreaAval(String areaAval) {this.areaAval = areaAval;}

    public String getCelularAval() {return celularAval;}
    public void setCelularAval(String celularAval) {this.celularAval = celularAval;}


}
