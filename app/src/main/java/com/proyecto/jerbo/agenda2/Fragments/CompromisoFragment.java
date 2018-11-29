package com.proyecto.jerbo.agenda2.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.proyecto.jerbo.agenda2.Activities.AddCompromisoActivity;
import com.proyecto.jerbo.agenda2.Adapters.CompromisoAdapter;
import com.proyecto.jerbo.agenda2.Clases.Compromiso;
import com.proyecto.jerbo.agenda2.Clases.ConexionSQLiteHelper;
import com.proyecto.jerbo.agenda2.Clases.Utils;
import com.proyecto.jerbo.agenda2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CompromisoFragment extends Fragment {
    private ArrayList<Compromiso> compromisos;
    RecyclerView recyclerView;
    ConexionSQLiteHelper conn;

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CompromisoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CompromisoFragment newInstance(int columnCount) {
        CompromisoFragment fragment = new CompromisoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        conn = new ConexionSQLiteHelper(getContext(), Utils.TABLE_COMPROMISO_NAME, null, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compromiso_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_compromiso);
        compromisos = new ArrayList<>();
        llenarCompromisos();
        CompromisoAdapter adapter = new  CompromisoAdapter(compromisos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Compromiso env = compromisos.get(recyclerView.getChildAdapterPosition(v));
                Intent act = new Intent(getContext(),AddCompromisoActivity.class);
                act.putExtra("comp",env);
                startActivity(act);
            }
        });


        return view;
    }

    private void llenarCompromisos() {
        SQLiteDatabase db = conn.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLE_COMPROMISO_NAME, null);
        while (cursor.moveToNext()) {
            boolean a = true;
            if (Integer.parseInt(cursor.getString(7)) == 0) {
                a = false;
            }
            try {
                Compromiso env = new Compromiso(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        a,
                        cursor.getString(8));
                env.setId(Integer.parseInt(cursor.getString(0)));
                compromisos.add(env);
            } catch (Exception e) {
                Log.e("xd",e.toString());
                Toast.makeText(getContext(), "error pe xd", Toast.LENGTH_SHORT).show();
            }

        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Compromiso item);
    }
}
