package br.com.stormsecurity.mobicine.presentation.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import br.com.stormsecurity.mobicine.domain.OpcaoUsuario;
import br.com.stormsecurity.mobicine.domain.VideoItem;
import br.com.stormsecurity.mobicine.helper.AppHelper;
import br.com.stormsecurity.mobicine.presentation.R;


public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnPlay;
    private Button btnCompartilhar;
    private Button btnFacebook;
    private Button btnTwitter;
    private Button btnMaisInf;
    private Button btnFavorito;
    private TextView txtTituloOpacaoUser;
    private RelativeLayout relativeLayouFrame1;
    private View rootView;
    private boolean visivel;
    private OpcaoUsuario opcaoUsuario;
    private LoginManager loginManager;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    private SharedPreferences pref;
    SharedPreferences.Editor editor;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        AppHelper.getInstance().setFragment1(this);
        opcaoUsuario = AppHelper.getInstance().getOpcaoUsuario();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment1, container, false);

        relativeLayouFrame1 = (RelativeLayout) rootView.findViewById(R.id.relativeLayouFrame1);
        btnPlay = (Button) rootView.findViewById(R.id.btnPlay);
        btnCompartilhar = (Button) rootView.findViewById(R.id.btnCompartilhar);
        btnFavorito = (Button) rootView.findViewById(R.id.btnFavorito);
        btnFacebook = (Button) rootView.findViewById(R.id.btnFacebookOpcaoUser);
        btnTwitter = (Button) rootView.findViewById(R.id.btnTwitterOpcaoUser);
        btnMaisInf = (Button) rootView.findViewById(R.id.btnMaisInf);
        txtTituloOpacaoUser = (TextView) rootView.findViewById(R.id.txtTituloOpacaoUser);
        relativeLayouFrame1.setBackground(rootView.getResources().getDrawable(opcaoUsuario.getOpcaoEscolhida().getImagemVideoItem()));
        txtTituloOpacaoUser.setText(opcaoUsuario.getOpcaoEscolhida().getTitulo().toString());

        pref = getActivity().getApplicationContext().getSharedPreferences("lista_de_favoritos_key", 0);
        editor = pref.edit();


        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferenciaUsuario();
            }
        });

        btnCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!visivel) {
                    btnFacebook.setVisibility(View.VISIBLE);
                    btnTwitter.setVisibility(View.VISIBLE);
                    visivel = true;
                } else {
                    btnFacebook.setVisibility(View.GONE);
                    btnTwitter.setVisibility(View.GONE);
                    visivel = false;
                }
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppHelper.getInstance().getMainActivityUI().startVideoPlayerActivity();
            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFacebookLogin();
            }
        });

        return rootView;
    }


    private void preferenciaUsuario(){

        if(!AppHelper.getInstance().getListIdFavoritos().isEmpty()) {
            AppHelper.getInstance().getListIdFavoritos().clear();
            convertForSet();

        }
        AppHelper.getInstance().getListIdFavoritos().add(String.valueOf(opcaoUsuario.getOpcaoEscolhida().getId()));

        editor.clear();
        editor.commit();
        editor.putStringSet("lista_de_favoritos_key", AppHelper.getInstance().getListIdFavoritos());
        editor.commit();
        createListaDeFavoritos();
        AppHelper.getInstance().getMainActivityUI().showFavoritoAdicionado();
    }

    private void convertForSet(){
        for (String id: retrieveFavoritoList()){
            AppHelper.getInstance().getListIdFavoritos().add(id);
        }
    }

    private ArrayList<String> retrieveFavoritoList(){
        SharedPreferences sharedPreferences = AppHelper.getInstance().getMainActivityUI().getSharedPreferences("lista_de_favoritos_key", 0);
        Set<String> listIdFavoritos = sharedPreferences.getStringSet("lista_de_favoritos_key", null);

        return new ArrayList<>(listIdFavoritos);
    }

    public void createListaDeFavoritos(){
        if(!AppHelper.getInstance().getCargaInicialVO().getListVideoItens().isEmpty()){
            AppHelper.getInstance().getVideoItemListFavoritos().clear();
        }
        List<String> listIdFavoritos = retrieveFavoritoList();
        if(listIdFavoritos != null) {
            for (VideoItem videoItemCargaInicial : AppHelper.getInstance().getCargaInicialVO().getListVideoItens()) {
                for (String videoItemIdFav : listIdFavoritos) {
                    if (videoItemCargaInicial.getId() == Integer.valueOf(videoItemIdFav)) {
                        AppHelper.getInstance().getVideoItemListFavoritos().add(videoItemCargaInicial);
                        AppHelper.getInstance().getListIdFavoritos().add(String.valueOf(videoItemCargaInicial.getId()));
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void configuracaoFacebook() {
        FacebookSdk.sdkInitialize(AppHelper.getInstance().getMainActivityUI().getApplicationContext());

        if(AccessToken.getCurrentAccessToken()!=null){
            AccessToken token = AccessToken.getCurrentAccessToken();
            if (token != null && token.isExpired()) {
                //onRedeSocialLoginSuccess(token.getUserId(), token.getToken(), Enumerations.ProvedorType.Facebook);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void onFacebookLogin() {

        this.loginManager = LoginManager.getInstance();
        this.callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        this.loginManager.registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("fb", "sucesso");

                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle("App Mobi Cine!")
                        .setContentDescription(opcaoUsuario.getOpcaoEscolhida().getTitulo())
                        .setContentUrl(Uri.parse(opcaoUsuario.getOpcaoEscolhida().getMediaUrl()))
                        .setImageUrl(Uri.parse("https://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png"))
                        .build();

                shareDialog.show(linkContent);
            }

            @Override
            public void onCancel() {
                Log.e("fb", "onCancel");
            }

            @Override
            public void onError(FacebookException e) {
                Log.e("fb", "onError");
            }
        });

        this.loginManager.logInWithReadPermissions(this, Arrays.asList("user_friends", "email"));
    }


}
