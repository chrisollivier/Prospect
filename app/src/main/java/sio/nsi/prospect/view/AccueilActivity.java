package sio.nsi.prospect.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sio.nsi.prospect.R;
import sio.nsi.prospect.model.Prospect;
import sio.nsi.prospect.model.User;

public class AccueilActivity extends AppCompatActivity {
    private RecyclerView recycler_View;
    ProspectAdaptateur adaptateur;
    private Button button_AddProspect;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueilactivity);
        recycler_View = (RecyclerView) findViewById(R.id.recycler_View);
        setRecyclerView();
        button_AddProspect = (Button) findViewById(R.id.button_AddProspect);
        button_AddProspect.setOnClickListener(addprospect);
    }

    private void setRecyclerView() {
        recycler_View.setHasFixedSize(true);
        recycler_View.setLayoutManager(new LinearLayoutManager(this));
        adaptateur = new ProspectAdaptateur(this,getList());
        recycler_View.setAdapter(adaptateur);
    }

    private List<Prospect> getList() {

        ArrayList<Prospect> prospect_list= new ArrayList<>();
        prospect_list.add(new Prospect("Ricardo","Milos","pepeIndustry"));
        prospect_list.add(new Prospect("Ricardo3","Milos","pepeIndustry"));
        prospect_list.add(new Prospect("Ricardo2","Milos","pepeIndustry"));
        prospect_list.add(new Prospect("Ricardo6","Milos","pepeIndustry"));
        prospect_list.add(new Prospect("Ricardo9","Milos","pepeIndustry"));
        return prospect_list;
    }

    public View.OnClickListener addprospect = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AccueilActivity.this, AddProspectActivity.class);
            startActivity(intent);
        }

        };
    };



