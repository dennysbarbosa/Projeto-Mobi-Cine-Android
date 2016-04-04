package br.com.stormsecurity.mobicine.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.stormsecurity.mobicine.domain.OpcaoUsuario;
import br.com.stormsecurity.mobicine.domain.VideoItem;
import br.com.stormsecurity.mobicine.presentation.activity.MainActivityUI;
import br.com.stormsecurity.mobicine.presentation.fragment.Fragment1;
import br.com.stormsecurity.mobicine.presentation.fragment.Fragment2;
import br.com.stormsecurity.mobicine.vo.CargaInicialVO;

/**
 * Created by Dennys on 29/03/2016.
 */
public class AppHelper {

    private static AppHelper instance;
    private String url;
    private CargaInicialVO cargaInicialVO;
    private MainActivityUI mainActivityUI;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private List<VideoItem> videoItemList;
    private List<VideoItem> videoItemListFavoritos;
    private OpcaoUsuario opcaoUsuario;
    private Set<String> listFavoritos;

    public AppHelper() {
    }

    public static AppHelper getInstance() {
        if (instance == null) {
            instance = new AppHelper();
        }
        return instance;
    }

    public Set<String> getListIdFavoritos() {
        if(listFavoritos == null){
            listFavoritos = new HashSet<>();
        }
        return listFavoritos;
    }

    public void setListIdFavoritos(Set<String> listFavoritos) {
        this.listFavoritos = listFavoritos;
    }

    public List<VideoItem> getVideoItemListFavoritos() {
        if(videoItemListFavoritos == null){
            videoItemListFavoritos = new ArrayList<>();
            fragment1.createListaDeFavoritos();
        }
        return videoItemListFavoritos;
    }

    public void setVideoItemListFavoritos(List<VideoItem> videoItemListFavoritos) {
        this.videoItemListFavoritos = videoItemListFavoritos;
    }

    public OpcaoUsuario getOpcaoUsuario() {
        return opcaoUsuario;
    }

    public void setOpcaoUsuario(OpcaoUsuario opcaoUsuario) {
        this.opcaoUsuario = opcaoUsuario;
    }

    public MainActivityUI getMainActivityUI() {
        return mainActivityUI;
    }

    public void setMainActivityUI(MainActivityUI mainActivityUI) {
        this.mainActivityUI = mainActivityUI;
    }

    public Fragment1 getFragment1() {
        return fragment1;
    }

    public void setFragment1(Fragment1 fragment1) {
        this.fragment1 = fragment1;
    }

    public Fragment2 getFragment2() {
        return fragment2;
    }

    public void setFragment2(Fragment2 fragment2) {
        this.fragment2 = fragment2;
    }

    public CargaInicialVO getCargaInicialVO() {
        return cargaInicialVO;
    }

    public void setCargaInicialVO(CargaInicialVO cargaInicialVO) {
        this.cargaInicialVO = cargaInicialVO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<VideoItem> getVideoItemList() {
        return videoItemList;
    }

    public void setVideoItemList(List<VideoItem> videoItemList) {
        this.videoItemList = videoItemList;
    }
}
