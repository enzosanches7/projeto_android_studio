package com.example.enzosanches; // CONFIRA SE ESTE PACOTE ESTÁ IGUAL AO SEU DO APP

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PecaAdapter extends RecyclerView.Adapter<PecaAdapter.PecaViewHolder> {

    private List<Peca> listaPecas;

    public PecaAdapter(List<Peca> listaPecas) {
        this.listaPecas = listaPecas;
    }

    @NonNull
    @Override
    public PecaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_peca, parent, false);
        return new PecaViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull PecaViewHolder holder, int position) {
        Peca peca = listaPecas.get(position);
        holder.txtNome.setText(peca.getNome());
        holder.txtCategoria.setText(peca.getCategoria());
        holder.txtPreco.setText("R$ " + peca.getPreco());
    }

    @Override
    public int getItemCount() {
        return listaPecas.size();
    }

    public static class PecaViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtCategoria, txtPreco;

        public PecaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtItemNome);
            txtCategoria = itemView.findViewById(R.id.txtItemCategoria);
            txtPreco = itemView.findViewById(R.id.txtItemPreco);
        }
    }
}