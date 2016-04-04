package br.com.stormsecurity.mobicine.service;

import com.google.inject.ImplementedBy;

import br.com.stormsecurity.mobicine.service.impl.AbstractServiceImpl;

/**
 * Created by Dennys on 28/03/2016.
 */

@ImplementedBy(AbstractServiceImpl.class)
public interface AbstractyService {

     boolean processarRespostaServidor(boolean sucesso, String data);

}
