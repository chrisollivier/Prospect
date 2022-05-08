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
import sio.nsi.prospect.tools.DataBaseHelper;

public class AccueilActivity extends AppCompatActivity {
    private RecyclerView recycler_View;
    private DataBaseHelper dataBase;
    private ProspectAdaptateur adaptateur;
    private Button button_AddProspect;
    private Button back_button;
    private Button btnLogout;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        dataBase = new DataBaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueilactivity);
        recycler_View = (RecyclerView) findViewById(R.id.recycler_View);
        setRecyclerView();
        button_AddProspect = (Button) findViewById(R.id.button_AddProspect);
        button_AddProspect.setOnClickListener(addprospect);

        ImageView back_button =findViewById(R.id.back_button);
        back_button.setOnClickListener(backbutton);

        ImageView btnLogout =findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(logout);

    }

    public View.OnClickListener backbutton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AccueilActivity.this, AccueilActivity.class);
            startActivity(intent);
        }
    };

    public View.OnClickListener logout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AccueilActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };

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



