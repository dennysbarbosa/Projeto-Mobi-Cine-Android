package br.com.stormsecurity.mobicine.domain;

/**
 * Created by Dennys on 29/03/2016.
 */
public class InfUsuario {

    public int id;
    public String titulo;
    public String nome;
    public int imagemCategoria;

    public InfUsuario() {
    }

    public int getImagemCategoria() {
        return imagemCategoria;
    }

    public void setImagemCategoria(int imagemCategoria) {
        this.imagemCategoria = imagemCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
