package com.proyecto.jerbo.agenda2.Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.proyecto.jerbo.agenda2.Adapters.SeccionAdapters;
import com.proyecto.jerbo.agenda2.Clases.Utils;
import com.proyecto.jerbo.agenda2.R;

import static android.support.design.widget.TabLayout.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContenedorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContenedorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContenedorFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    View vista;
   private AppBarLayout appBar;
   private TabLayout pestañas;
   private ViewPager viewPager;


    public ContenedorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContenedorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContenedorFragment newInstance(String param1, String param2) {
        ContenedorFragment fragment = new ContenedorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.fragment_contenedor, container, false);

        if (Utils.rotacion==0){
            View parent = (View) container.getParent();
            if (appBar==null){
                appBar= parent.findViewById(R.id.appBar);
                pestañas=new TabLayout(getActivity());

                appBar.addView(pestañas);
                viewPager = vista.findViewById(R.id.ViewPagerInformacion);
                llenarViewPager(viewPager);
                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                });
                pestañas.setupWithViewPager(viewPager);
            }
            if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                pestañas.setTabMode(MODE_FIXED);
                pestañas.setTabGravity(GRAVITY_FILL);
            }else{
                pestañas.setTabMode(MODE_SCROLLABLE);
                pestañas.setTabGravity(GRAVITY_FILL);
            }

        }else{
            Utils.rotacion=1;
        }

        return vista;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (Utils.rotacion==0){
            appBar.removeView(pestañas);
        }

    }
    private void llenarViewPager(ViewPager viewPager) {
        SeccionAdapters adapter = new SeccionAdapters(getFragmentManager());
        adapter.addFragment(new ProcessFragment(),"Proceso");
        adapter.addFragment(new TramiteFragment(),"Tramite");

        adapter.addFragment(new HonorariosFragment(),"Honorarios");
        viewPager.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);

    }





}
