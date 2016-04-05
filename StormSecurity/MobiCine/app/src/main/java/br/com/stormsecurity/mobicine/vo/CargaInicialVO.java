package br.com.stormsecurity.mobicine.vo;

import java.util.List;

import br.com.stormsecurity.mobicine.domain.InfUsuario;
import br.com.stormsecurity.mobicine.domain.VideoItem;

/**
 * Created by Dennys on 29/03/2016.
 */
public class CargaInicialVO {

    public int id;
    public List<InfUsuario> listInfUsuario;

    public List<VideoItem> listVideoItens;

    public CargaInicialVO() {
    }

    public List<VideoItem> getListVideoItens() {
        return listVideoItens;
    }

    public void setListVideoItens(List<VideoItem> listVideoItens) {
        this.listVideoItens = listVideoItens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<InfUsuario> getListInfUsuario() {
        if (listInfUsuario == null){
            listInfUsuario = getListInfUsuario();
        }
        return listInfUsuario;
    }

    public void setListInfUsuario(List<InfUsuario> listInfUsuario) {
        this.listInfUsuario = listInfUsuario;
    }
}
