package com.example.enzosanches;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PecaAdapter extends RecyclerView.Adapter<PecaAdapter.PecaViewHolder> {

    private List<Peca> listaPecas;
    private OnPecaLongClickListener longClickListener;

    // Interface para fazer a Activity escutar o clique longo
    public interface OnPecaLongClickListener {
        void onPecaLongClick(Peca peca, int position);
    }

    public PecaAdapter(List<Peca> listaPecas) {
        this.listaPecas = listaPecas;
    }

    // Permite definir o clique longo na Activity
    public void setOnPecaLongClickListener(OnPecaLongClickListener listener) {
        this.longClickListener = listener;
    }

    @NonNull
    @Override
    public PecaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_peca, parent, false);
        return new PecaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PecaViewHolder holder, int position) {
        Peca peca = listaPecas.get(position);

        // CORREÇÃO: Usando os nomes das variáveis corretas
        holder.txtItemNome.setText(peca.getNome());
        holder.txtItemCategoria.setText(peca.getCategoria());
        holder.txtItemPreco.setText(peca.getPreco());

        // Configuração do clique longo direto no item
        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onPecaLongClick(peca, position);
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return listaPecas.size();
    }

    public static class PecaViewHolder extends RecyclerView.ViewHolder {
        // CORREÇÃO: Nomes das variáveis atualizados para combinar com os IDs reais
        TextView txtItemNome, txtItemCategoria, txtItemPreco;

        public PecaViewHolder(@NonNull View itemView) {
            super(itemView);

            // CORREÇÃO DEFINITIVA: Usando os IDs EXATOS do seu item_peca.xml
            txtItemNome = itemView.findViewById(R.id.txtItemNome);
            txtItemCategoria = itemView.findViewById(R.id.txtItemCategoria);
            txtItemPreco = itemView.findViewById(R.id.txtItemPreco);
        }
    }
}