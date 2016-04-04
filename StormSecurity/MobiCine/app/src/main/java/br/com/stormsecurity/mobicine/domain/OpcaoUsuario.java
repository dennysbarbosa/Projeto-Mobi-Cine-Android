package br.com.stormsecurity.mobicine.domain;

import java.util.List;

/**
 * Created by Dennys on 02/04/2016.
 */
public class OpcaoUsuario {

    private VideoItem opcaoEscolhida;
    private List<VideoItem> listItensRelacionados;
    private int index;

    public OpcaoUsuario(VideoItem opcaoEscolhida, List<VideoItem> listItensRelacionados, int index) {
        this.opcaoEscolhida = opcaoEscolhida;
        this.listItensRelacionados = listItensRelacionados;
        this.index = index;
    }

    public VideoItem getOpcaoEscolhida() {
        return opcaoEscolhida;
    }

    public void setOpcaoEscolhida(VideoItem opcaoEscolhida) {
        this.opcaoEscolhida = opcaoEscolhida;
    }

    public List<VideoItem> getListItensRelacionados() {
        return listItensRelacionados;
    }

    public void setListItensRelacionados(List<VideoItem> listItensRelacionados) {
        this.listItensRelacionados = listItensRelacionados;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
