package br.com.stormsecurity.mobicine.service.impl;


import android.content.res.Resources;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import br.com.stormsecurity.mobicine.helper.AppHelper;
import br.com.stormsecurity.mobicine.presentation.R;
import br.com.stormsecurity.mobicine.presentation.activity.SplashScreenUI;
import br.com.stormsecurity.mobicine.service.CargaInicialService;
import br.com.stormsecurity.mobicine.vo.CargaInicialVO;

public class CargaInicialServiceImpl extends AbstractServiceImpl implements CargaInicialService {

    private SplashScreenUI ui;
    private int[] mFlags;

    @Override
    public void relizarCargaInicial(SplashScreenUI ui) {
        this.ui = ui;
        configurarImagensCategorias();
        try {
            salvarCargaPreLiminar(processarCargaPreLiminar(carregarDadosRaw(ui)));
        } catch (Exception e) {
            // TODO: handle exception
            ui.mensagem(
                    ui.getResources().getString(R.string.falhaCargaInicial),
                    ui.getResources().getString(
                            R.string.falhaProcessarCargaPreLiminar));
            e.printStackTrace();
        }
    }

    private String carregarDadosRaw(SplashScreenUI ui) {

        Resources res = ui.getResources();
        InputStream inputStream = res.openRawResource(R.raw.carga_inicial1);
        return lerTxt(inputStream);
    }

    private CargaInicialVO processarCargaPreLiminar(String data)
            throws JSONException {

        CargaInicialVO cargaInicialVO = AbstractServiceImpl.getGson().fromJson(data,
                CargaInicialVO.class);

        return cargaInicialVO;

    }

    public void relacionarImagens(CargaInicialVO cargaInicialVO) {

        for (int i = 0; i < cargaInicialVO.getListVideoItens().size(); i++) {
            cargaInicialVO.getListVideoItens().get(i).setImagemVideoItem(mFlags[i]);
        }

    }

    private Boolean salvarCargaPreLiminar(CargaInicialVO cargaInicialVO) {
        boolean sucesso = Boolean.FALSE;
        if (cargaInicialVO != null) {
            try {
                // insert no banco de dados
                relacionarImagens(cargaInicialVO);
                AppHelper.getInstance().setCargaInicialVO(cargaInicialVO);
                sucesso = Boolean.TRUE;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return sucesso;
    }

    private static String lerTxt(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }

    private void configurarImagensCategorias() {
        mFlags = new int[]{
                R.drawable.wildlife,
                R.drawable.blender,
                R.drawable.llama,
                R.drawable.teras,
                R.drawable.bunny,
                R.drawable.stevejobs,
                R.drawable.android,
                R.drawable.disneynature,
                R.drawable.vida_selvagem,
                R.drawable.vidareligiosa,
                R.drawable.winter,
                R.drawable.documentario,
                R.drawable.gatoentrometido,
                R.drawable.funnycat,
                R.drawable.tecnologiabebidas,
                R.drawable.documentario,

        };
    }

}
