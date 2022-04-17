package sio.nsi.prospect.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sio.nsi.prospect.R;
import sio.nsi.prospect.model.Prospect;
import sio.nsi.prospect.model.User;
import sio.nsi.prospect.tools.APIProspect;
import sio.nsi.prospect.tools.APIUser;
import sio.nsi.prospect.tools.DataBaseHelper;

public class AccueilActivity extends AppCompatActivity {
    private RecyclerView recycler_View;
    private DataBaseHelper dataBase;
    private ProspectAdaptateur adaptateur;
    private Button button_AddProspect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        dataBase = new DataBaseHelper(this);
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
        adaptateur = new ProspectAdaptateur(this, dataBase.readAllProspect());
        recycler_View.setAdapter(adaptateur);
    }


    public View.OnClickListener addprospect = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AccueilActivity.this, AddProspectActivity.class);
            startActivity(intent);
        }

    };
};



