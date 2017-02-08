package fc.com.fcrecycleview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.util.ArrayList;

import fc.com.fcrecycleview.adapter.LoadMoreCombinationFcAdapterTest;
import fc.recycleview.LoadMoreRecycleView;
import fc.recycleview.OnLoadMoreListener;


public class LoadMoreCombinationFcActivity extends ActionBarActivity implements OnLoadMoreListener {

    private LoadMoreRecycleView loadMoreRecycleView;
    private LoadMoreCombinationFcAdapterTest adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        loadMoreRecycleView = (LoadMoreRecycleView) findViewById(R.id.fc_recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        loadMoreRecycleView.setLayoutManager(linearLayoutManager);
        adapter = new LoadMoreCombinationFcAdapterTest(this);

        loadMoreRecycleView.setAdapter(adapter);
        loadMoreRecycleView.setOnLoadMoreListener(this);
    }

    public void addData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0 ; i < 10; i++) {
            list.add("DATA_" + (i+1));
        }
        adapter.addAll(list);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Math.random() < 0.5) {
                    Toast.makeText(LoadMoreCombinationFcActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                    loadMoreRecycleView.notifyError();
                } else {
                    addData();
                    loadMoreRecycleView.notifyNormal();
                }
            }
        }, 2000);
    }
}
