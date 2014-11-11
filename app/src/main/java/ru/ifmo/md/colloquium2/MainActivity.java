package ru.ifmo.md.colloquium2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
    ListView listView;
    Storage data;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view_candidates);
        DataBaseHelper helper = new DataBaseHelper(this, "database.db", null, 1);

        data = new Storage(helper);
        helper.getWritableDatabase();

        adapter = new MyAdapter(data, this);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                RelativeLayout layout = (RelativeLayout) view;
                TextView name = (TextView) layout.findViewById(R.id.candidate_item);
                RelativeLayout editBar = (RelativeLayout) layout.findViewById(R.id.edit_bar);
                if(editBar.getVisibility() == RelativeLayout.GONE)
                    editBar.setVisibility(RelativeLayout.VISIBLE);
            }
        });
        listView.setAdapter(adapter);
    }

    public void addCandidate(View v) {
        AddCandidateDialog dialog = new AddCandidateDialog(this);
        dialog.initFields(data, adapter, listView);
        dialog.show();
    }

    public void onChangeCandidate(View v) {
        Button change = (Button) v;
        RelativeLayout parent1 = (RelativeLayout) change.getParent();
        RelativeLayout layout = (RelativeLayout) parent1.getParent();
        Log.i("MESSAGE", ((TextView)layout.findViewById(R.id.candidate_item)).getText().toString());
    }
}
