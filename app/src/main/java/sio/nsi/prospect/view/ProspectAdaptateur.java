package sio.nsi.prospect.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sio.nsi.prospect.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sio.nsi.prospect.model.Prospect;

public class ProspectAdaptateur extends RecyclerView.Adapter<ProspectAdaptateur.ViewHolder> {
    Context context;
    ArrayList<Prospect> prospect_list;
    private OnProspectListener onProspectListener;

    public ProspectAdaptateur(Context context, ArrayList<Prospect> prospect_list, OnProspectListener onProspectListener ) {
        this.context = context;
        this.prospect_list = prospect_list;
        this.onProspectListener =onProspectListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent, false);
        return new ViewHolder(view, onProspectListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (prospect_list.get(position).getNom() != null && prospect_list.size() > 0) {
            Prospect model = prospect_list.get(position);
            holder.nom_TV.setText(model.getNom());
            holder.prenom_TV.setText(model.getPrenom());
            holder.raisonSocial_TV.setText(model.getRaisonSociale());
        }
    }

    @Override
    public int getItemCount() {
        if (prospect_list != null) {
            return prospect_list.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nom_TV, prenom_TV, raisonSocial_TV;
        OnProspectListener onProspectListener;

        public ViewHolder(@NonNull View itemView, OnProspectListener onProspectListener) {
            super(itemView);
            nom_TV = itemView.findViewById(R.id.nom_TV);
            prenom_TV = itemView.findViewById(R.id.prenom_TV);
            raisonSocial_TV = itemView.findViewById(R.id.raisonSocial_TV);
            this.onProspectListener = onProspectListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onProspectListener.onProspectClick(getAdapterPosition());
        }
    }

    public interface OnProspectListener {
        void onProspectClick(int position);
    }
}
