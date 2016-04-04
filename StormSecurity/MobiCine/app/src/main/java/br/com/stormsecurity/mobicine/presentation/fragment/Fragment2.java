package br.com.stormsecurity.mobicine.presentation.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.stormsecurity.mobicine.domain.VideoItem;
import br.com.stormsecurity.mobicine.helper.AppHelper;
import br.com.stormsecurity.mobicine.presentation.R;
import br.com.stormsecurity.mobicine.presentation.activity.MainActivityUI;
import br.com.stormsecurity.mobicine.presentation.activity.VideosRelacionadosAdapter;


public class Fragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView lstViewVideosRelacionados;
    private View rootView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivityUI mainActivity;
    private OnFragmentInteractionListener mListener;
    private List<VideoItem> videoItemList;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
       AppHelper.getInstance().setFragment2(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment2, container, false);
        lstViewVideosRelacionados = (ListView) rootView.findViewById(R.id.lstViewVideosRelacionados);
        lstViewVideosRelacionados.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppHelper.getInstance().getVideoItemList().get(position);
                AppHelper.getInstance().getMainActivityUI().executarVideo(position);
            }
        });

        return rootView;
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


    public void montarAdpter(){

        VideosRelacionadosAdapter adapter1 = new VideosRelacionadosAdapter(AppHelper.getInstance().getMainActivityUI(), AppHelper.getInstance().getOpcaoUsuario().getListItensRelacionados());
        lstViewVideosRelacionados.setAdapter(adapter1);
    }


    @Override
    public void onStart() {
        super.onStart();
    }
}