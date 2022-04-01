package sio.nsi.prospect.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sio.nsi.prospect.R;
import sio.nsi.prospect.model.Prospect;

public class AccueilActivity extends AppCompatActivity {
    private RecyclerView recycler_View;
    ProspectAdaptateur adaptateur;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueilactivity);

        recycler_View = (RecyclerView) findViewById(R.id.recycler_View);

        setRecyclerView();
    }

    private void setRecyclerView() {
        recycler_View.setHasFixedSize(true);
        recycler_View.setLayoutManager(new LinearLayoutManager(this));
        adaptateur = new ProspectAdaptateur(this,getList());
        recycler_View.setAdapter(adaptateur);
    }

    private List<Prospect> getList() {

        List<Prospect> prospect_list= new ArrayList<>();
        prospect_list.add(new Prospect("Ricardo","Milos","pepeIndustry"));
        return prospect_list;
    }
}
