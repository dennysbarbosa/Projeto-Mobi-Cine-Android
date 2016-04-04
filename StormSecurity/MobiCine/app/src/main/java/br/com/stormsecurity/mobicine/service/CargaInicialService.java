package br.com.stormsecurity.mobicine.service;

import com.google.inject.ImplementedBy;

import br.com.stormsecurity.mobicine.presentation.activity.SplashScreenUI;

/**
 * Created by Dennys on 29/03/2016.
 */

@ImplementedBy(CargaInicialService.class)
public interface CargaInicialService {

     void relizarCargaInicial(SplashScreenUI ui)throws Exception;
}
