package br.com.stormsecurity.mobicine.domain;

/**
 * Created by Meta on 01/04/2016.
 */
public class item {

    private String texto;

    public item(String texto) {
        this.texto = texto;
    }

    public item() {

    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
