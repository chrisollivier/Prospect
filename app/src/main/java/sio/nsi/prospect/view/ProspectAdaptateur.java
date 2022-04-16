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

    public ProspectAdaptateur(Context context, ArrayList<Prospect> prospect_list) {
        this.context = context;
        this.prospect_list = prospect_list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(prospect_list.get(position).getNom() != null && prospect_list.size() > 0) {
            Prospect model = prospect_list.get(position);
            holder.nom_TV.setText(model.getNom());
            holder.prenom_TV.setText(model.getPrenom());
            holder.raisonSocial_TV.setText(model.getRaisonSociale());

        }else{
            return;
        }
    }

    @Override
    public int getItemCount() {
       if(prospect_list != null){
       //     if (prospect_list.size()<=5){
        return prospect_list.size();
       //    }else{
       //        return 5;
       //    }
       }else{
           return 0;
       }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nom_TV,prenom_TV,raisonSocial_TV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nom_TV = itemView.findViewById(R.id.nom_TV);
            prenom_TV = itemView.findViewById(R.id.prenom_TV);
            raisonSocial_TV = itemView.findViewById(R.id.raisonSocial_TV);

        }
    }

}
