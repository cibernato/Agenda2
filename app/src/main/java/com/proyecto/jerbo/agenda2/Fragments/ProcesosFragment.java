package com.proyecto.jerbo.agenda2.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.jerbo.agenda2.Activities.AddCompromisoActivity;
import com.proyecto.jerbo.agenda2.Activities.AddProcesoActivity;
import com.proyecto.jerbo.agenda2.Adapters.AdapterDatos;
import com.proyecto.jerbo.agenda2.Clases.Proceso;
import com.proyecto.jerbo.agenda2.Clases.Utils;
import com.proyecto.jerbo.agenda2.Clases.ConexionSQLiteHelper;
import com.proyecto.jerbo.agenda2.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProcesosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ProcesosFragment extends Fragment {
    ArrayList<Proceso> procesos;
    ConexionSQLiteHelper conx;
    RecyclerView recyclerView;
    View vista;
    Fragment fragment;
    // TODO: Rename and change types of parameters
    private TextView textView;
    private OnFragmentInteractionListener mListener;

    public ProcesosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_procesos, container, false);

        recyclerView = vista.findViewById(R.id.recycler_view);

        procesos = new ArrayList<Proceso>();
        llenarProcesos();
        AdapterDatos adapterDatos = new AdapterDatos(procesos);
        recyclerView.setAdapter(adapterDatos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        if(!confirmacion()){
        adapterDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Proceso env = procesos.get(recyclerView.getChildAdapterPosition(v));
                Intent act = new Intent(getContext(),AddProcesoActivity.class);
                act.putExtra("pro",env);
                startActivity(act);
            }
        });
        }else{
            vista.setBackgroundColor(Color.WHITE
            );
            adapterDatos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((AddCompromisoActivity)getActivity()).recibirProceso(procesos.get(recyclerView.getChildAdapterPosition(v)));
                    getFragmentManager().popBackStack();
                }
            });
        }
        return vista;
    }
    public boolean confirmacion(){
        if(getArguments()!=null &&  getArguments().getString("conf").equals("si")){
            return true;
        }
        return false;

    }
    private void llenarProcesos() {
        conx = new ConexionSQLiteHelper(getContext(), Utils.TABLE_NAME, null, 1);
        SQLiteDatabase db = conx.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_NAME, null);
        while (cursor.moveToNext()) {
            try {
                Proceso env = new Proceso(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                env.setId(Integer.parseInt(cursor.getString(0)));
                procesos.add(env);
            } catch (Exception e) {
                Toast.makeText(getContext(), "creo que esta vacia la wea xd", Toast.LENGTH_SHORT).show();
                Log.e("kappa", e.toString());
            }
        }
        db.close();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.procesos_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
}
