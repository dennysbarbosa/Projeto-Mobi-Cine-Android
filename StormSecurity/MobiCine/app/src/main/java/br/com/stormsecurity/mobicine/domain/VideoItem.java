package br.com.stormsecurity.mobicine.domain;

/**
 * Created by Dennys on 02/04/2016.
 */
public class VideoItem {

    public int id;
    public String titulo;
    public String mediaUrl;
    public int idCategoria;
    public int imagemVideoItem;

    public VideoItem() {
    }

    public int getImagemVideoItem() {
        return imagemVideoItem;
    }

    public void setImagemVideoItem(int imagemVideoItem) {
        this.imagemVideoItem = imagemVideoItem;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
