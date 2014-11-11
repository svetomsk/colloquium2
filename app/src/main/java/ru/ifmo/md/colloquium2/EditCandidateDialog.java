package ru.ifmo.md.colloquium2;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Svet on 19.10.2014.
 */
public class EditCandidateDialog extends Dialog {
    EditText name;
    Storage data;
    MyAdapter listAdapter;
    ListView lv;

    public EditCandidateDialog(Context context) {
        super(context);
        setContentView(R.layout.add_candidate_dialog);
        setTitle("Add new candidate");
        name = (EditText) findViewById(R.id.subject_name);
    }

    public void initFields(final Storage ds, final MyAdapter listAdapter, final ListView lv) {
        this.data = ds;
        this.listAdapter = listAdapter;
        this.lv = lv;

        addKeyListeners();
        onAddButton();
    }

    private void addKeyListeners() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        name.requestFocus();

        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if(i == EditorInfo.IME_ACTION_SEND) {
                    updateDatabaseAndListView();
                    dismiss();
                    handled = true;
                }
                return handled;
            }
        });
    }

    private void onAddButton() {
        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDatabaseAndListView();
                dismiss();
            }
        });
    }

    private void updateDatabaseAndListView() {
        String candidate = name.getText().toString();
        data.insertCandidate(candidate);
        lv.setAdapter(listAdapter);
    }
}
