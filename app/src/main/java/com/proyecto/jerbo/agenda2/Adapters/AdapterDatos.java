package com.proyecto.jerbo.agenda2.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.proyecto.jerbo.agenda2.Clases.Proceso;
import com.proyecto.jerbo.agenda2.R;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> implements View.OnClickListener {

    ArrayList<Proceso> arrayDatos;
    private View.OnClickListener listener;


    public AdapterDatos(ArrayList<Proceso> arrayDatos) {
        this.arrayDatos = arrayDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.cliente.append(arrayDatos.get(position).getCliente());
        holder.expediente.setText(arrayDatos.get(position).getExpediente());
        holder.juzgado.setText(arrayDatos.get(position).getJuzgado());
        holder.especialista.setText(arrayDatos.get(position).getEspecialista());
        holder.itemView.setLongClickable(true);

    }

    @Override
    public int getItemCount() {
        return arrayDatos.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;
    }
    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView cliente,expediente,juzgado,especialista;
        View v;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            cliente=itemView.findViewById(R.id.cliente_proceso_list);
            expediente=itemView.findViewById(R.id.expediente_proceso_list);
            juzgado=itemView.findViewById(R.id.juzgado_proceso_list);
            especialista=itemView.findViewById(R.id.especialista_proceso_list);
            this.v=itemView;


        }

    }
}
