package com.proyecto.jerbo.agenda2.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.proyecto.jerbo.agenda2.Clases.Compromiso;
import com.proyecto.jerbo.agenda2.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CompromisoAdapter extends RecyclerView.Adapter<CompromisoAdapter.ViewHolder> implements View.OnClickListener {

    private final List<Compromiso> mValues;
    private View.OnClickListener listener;

    public CompromisoAdapter(List<Compromiso> items) {
        mValues = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_compromiso, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.comp = mValues.get(position);
        String[] fecha= {"","",""};
        try {
            fecha =holder.comp.getFecha().split("/");
            holder.dia.setText(fecha[0]);
            holder.mes.setText(fecha[1]);
            holder.año.setText(fecha[2]);
        } catch (Exception e) {
            /*fecha[0]="11";
            fecha[1]="11";
            fecha[2]="2234";  */
        }


        holder.tipo.setText(holder.comp.getTipo());
        holder.persona.setText(holder.comp.getPersona());
        holder.id_proceso.setText(holder.comp.getId_proceso());
        Log.e("persona", holder.comp.getPersona()+"nada" );
        String inputDateString = "01/22/2013";
        Calendar calCurr = Calendar.getInstance();
        Calendar day = Calendar.getInstance();
        try {
            day.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(holder.comp.getFecha()));
        } catch (ParseException e) {
            Log.e("fecha", holder.comp.getFecha() );
        }
        if (day.after(calCurr)) {
            String put = String.valueOf((day.get(Calendar.DAY_OF_MONTH) - (calCurr.get(Calendar.DAY_OF_MONTH))));
            System.out.println("Days Left: " + put);
            holder.tiempo.setText(put);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView dia, mes, año, tipo, persona, id_proceso, tiempo;
        Compromiso comp;

        public ViewHolder(View view) {
            super(view);
            dia = view.findViewById(R.id.compromiso_list_dia);
            mes = view.findViewById(R.id.compromiso_list_mes);
            año = view.findViewById(R.id.compromiso_list_año);
            tipo = view.findViewById(R.id.compromiso_list_tipo);
            persona = view.findViewById(R.id.compromiso_list_persona);
            id_proceso = view.findViewById(R.id.compromiso_list_proceso);
            tiempo = view.findViewById(R.id.compromiso_list_hora);

            this.mView = view;

        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

}
