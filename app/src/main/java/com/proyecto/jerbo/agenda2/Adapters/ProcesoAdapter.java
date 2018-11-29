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
import java.util.List;

public class ProcesoAdapter extends RecyclerView.Adapter<ProcesoAdapter.ProcesoHolder> {
    List<Proceso> arrayDatos = new ArrayList<Proceso>();

    @NonNull
    @Override
    public ProcesoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ProcesoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcesoHolder holder, int position) {
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

    class ProcesoHolder extends RecyclerView.ViewHolder {
        TextView cliente,expediente,juzgado,especialista;
        View v;
        public ProcesoHolder(View itemView) {
            super(itemView);
            cliente=itemView.findViewById(R.id.cliente_proceso_list);
            expediente=itemView.findViewById(R.id.expediente_proceso_list);
            juzgado=itemView.findViewById(R.id.juzgado_proceso_list);
            especialista=itemView.findViewById(R.id.especialista_proceso_list);
            this.v=itemView;
        }
    }
    public void setDatos(List<Proceso> procesos){
        this.arrayDatos=procesos;
        notifyDataSetChanged();
    }
}
