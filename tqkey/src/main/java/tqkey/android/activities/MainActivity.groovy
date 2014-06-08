package tqkey.android.activities

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import tqkey.android.R
import tqkey.android.fragments.ListResidentsFragment

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, ListResidentsFragment.newInstance()).commit()
    }

}