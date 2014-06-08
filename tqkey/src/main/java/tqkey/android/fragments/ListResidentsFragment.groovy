package tqkey.android.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import com.devspark.progressfragment.ProgressFragment
import com.loopj.android.http.AsyncHttpClient
import tqkey.android.R
import tqkey.android.adapters.ListResidentsAdapter
import tqkey.android.entity.User
import tqkey.android.events.FailureCallApiEvent
import tqkey.android.events.SuccessCallApiEvent
import tqkey.android.handlers.ApiResponseHandler

public class ListResidentsFragment extends ProgressFragment implements
        SwipeRefreshLayout.OnRefreshListener, ApiResponseHandler.ApiResponseHandlerDelegate,
        AdapterView.OnItemClickListener {

    private ListView mListView

    private ListResidentsAdapter mAdapter

    private View mContentView

    private SwipeRefreshLayout mSwipeRefresh

    static ListResidentsFragment newInstance() {
        return new ListResidentsFragment()
    }

    public ListResidentsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_residents_list, container, false)
        mSwipeRefresh = (SwipeRefreshLayout) mContentView.findViewById(R.id.swipe_refresh)
        mSwipeRefresh.setColorScheme(R.color.color1, R.color.color2, R.color.color3, R.color.color4)
        mSwipeRefresh.setOnRefreshListener(this)
        mListView = (ListView) mContentView.findViewById(R.id.list_residents)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = new ListResidentsAdapter(getActivity())
        mListView.setAdapter(mAdapter)
        mListView.setOnItemClickListener(this)
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle)
        setContentView(mContentView)
        loadTqHouseApi()
    }

    @Override
    public void onRefresh() {
        loadTqHouseApi()
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User user = (User) parent.getAdapter().getItem(position)
        Uri uri = Uri.parse(getResources().getString(R.string.twitter_profile, user.getName()))
        startActivity(new Intent(Intent.ACTION_VIEW, uri))
    }

    @Override
    public void onSuccessCallApi(SuccessCallApiEvent event) {
        mAdapter.setData(event.getUsers())
        mSwipeRefresh.setRefreshing(false)
        setContentShown(true)
    }

    @Override
    public void onFailureCallApi(FailureCallApiEvent event) {
        setEmptyText(R.string.no_data)
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu)
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                onRefresh()
                return true
            default:
                return super.onOptionsItemSelected(item)
        }
    }

    private void loadTqHouseApi() {
        setContentShown(false)
        mAdapter.clear()
        new AsyncHttpClient().get(getString(R.string.tqhouse_api), new ApiResponseHandler(this))
    }

}