package sio.nsi.prospect.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import sio.nsi.prospect.R;
import sio.nsi.prospect.model.Prospect;
import sio.nsi.prospect.tools.DataBaseHelper;

import java.util.ArrayList;

public class AccueilActivity extends AppCompatActivity implements ProspectAdaptateur.OnProspectListener {
    private RecyclerView recycler_View;
    private DataBaseHelper dataBase;
    private ProspectAdaptateur adaptateur;
    private ArrayList<Prospect> prospectsList;
    private Button button_AddProspect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        dataBase = new DataBaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueilactivity);

        prospectsList = dataBase.readAllProspect();

        recycler_View = (RecyclerView) findViewById(R.id.recycler_View);
        setRecyclerView();
        button_AddProspect = (Button) findViewById(R.id.button_AddProspect);
        button_AddProspect.setOnClickListener(addprospect);

        ImageView back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(backbutton);

        ImageView btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(logout);

    }

    public View.OnClickListener backbutton = v -> {
        Intent intent = new Intent(AccueilActivity.this, AccueilActivity.class);
        startActivity(intent);
    };

    public View.OnClickListener logout = v -> {
        Intent intent = new Intent(AccueilActivity.this, LoginActivity.class);
        startActivity(intent);
    };

    public View.OnClickListener addprospect = v -> {
        Intent intent = new Intent(AccueilActivity.this, AddProspectActivity.class);
        startActivity(intent);
    };

    private void setRecyclerView() {
        recycler_View.setHasFixedSize(true);
        recycler_View.setLayoutManager(new LinearLayoutManager(this));
        adaptateur = new ProspectAdaptateur(this, prospectsList, this);
        recycler_View.setAdapter(adaptateur);
    }

    @Override
    public void onProspectClick(int position) {
        Intent intent = new Intent(this, DisplayProspectActivity.class);
        intent.putExtra("ProspectId", prospectsList.get(position).getId());
        intent.putExtra("ProspectNom", prospectsList.get(position).getNom());
        intent.putExtra("ProspectPrenom", prospectsList.get(position).getPrenom());
        intent.putExtra("ProspectRS", prospectsList.get(position).getRaisonSociale());
        intent.putExtra("ProspectSiret", prospectsList.get(position).getSiret());
        intent.putExtra("ProspectScore", prospectsList.get(position).getScore());
        intent.putExtra("ProspectMail", prospectsList.get(position).getMail());
        intent.putExtra("ProspectTel", prospectsList.get(position).getTel());
        startActivity(intent);
        this.finish();
    }
};



