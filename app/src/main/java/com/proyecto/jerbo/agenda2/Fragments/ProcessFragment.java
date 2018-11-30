package com.proyecto.jerbo.agenda2.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.jerbo.agenda2.Activities.MostrarProcesosActivity;
import com.proyecto.jerbo.agenda2.Clases.Proceso;
import com.proyecto.jerbo.agenda2.Clases.Utils;
import com.proyecto.jerbo.agenda2.Clases.ConexionSQLiteHelper;
import com.proyecto.jerbo.agenda2.R;


public class ProcessFragment extends Fragment {
    ConexionSQLiteHelper conn;
    private Proceso recb;
    private OnFragmentInteractionListener mListener;

    public ProcessFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        conn = new ConexionSQLiteHelper(getContext(), Utils.TABLE_NAME, null, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_process, container, false);
        TextView name = vista.findViewById(R.id.nameProcess);
        name.setText(recb.getCliente().toString());
        TextView expediente = vista.findViewById(R.id.expediente);
        expediente.setText(recb.getExpediente().toString());
        TextView juzgado = vista.findViewById(R.id.name_juzgado);
        juzgado.setText(recb.getJuzgado().toString());
        TextView especialista = vista.findViewById(R.id.name_especialista);
        especialista.setText(recb.getEspecialista().toString());
        setHasOptionsMenu(true);
        return vista;
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


    public void recibirArgs(Bundle args) {
        recb = (Proceso) args.getSerializable("proceso");

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Proceso");
        inflater.inflate(R.menu.process_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getContext(), "que raro", Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.process_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("¿Desea eliminar este proceso?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteProcess();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;

            case android.R.id.home:
                startActivity(new Intent(getContext(), MostrarProcesosActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteProcess() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] params = {String.valueOf(recb.getId())};
        try {
            db.delete(Utils.TABLE_NAME, "id =?", params);
        } catch (Exception e) {
            Log.e("FAIL", e.getLocalizedMessage());
        }

        Toast.makeText(getContext(), "Ya se Eliminó el proceso", Toast.LENGTH_LONG).show();
        db.close();
        getFragmentManager().popBackStack();
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
