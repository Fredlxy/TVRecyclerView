package tv.lesports.com.tvrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private TvRecyclerView mRecyclerView;
    private TvGridLayoutManager mLayoutManager;
    private List<SearchResultModel> dataList = new ArrayList<>();
    private GuessYouLikeAdapter mAdapter;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (TvRecyclerView) findViewById(R.id.search_result_recyclerView);

        mRecyclerView.setFocusable(false);
        //去掉动画,否则当notify数据的时候,焦点会丢失
        mRecyclerView.setItemAnimator(null);

        mLayoutManager = new TvGridLayoutManager(this,4);
        mLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);


        mRecyclerView.setSelectedItemOffset(180, 180);

        mRecyclerView.setOnLoadMoreListener(new TvRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
              /*  Log.i(TAG,"isLoading = " + isLoading + "mHasMore = " + mHasMore);
                if (!isLoading && mHasMore) {
                    Logger.i(TAG, "分页加载中...");
                    mPage++;
                    doSearchMore();
                }*/
                //Toast.makeText(MainActivity.this,"翻页加载更多中...",Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //mAdapter.addDataList(getMoreData());
                       // mAdapter.notifyDataSetChanged();
                    }
                },200);
            }
        });

        initData();
        mAdapter = new GuessYouLikeAdapter(this,mRecyclerView,mLayoutManager, dataList);
        mRecyclerView.setAdapter(mAdapter);

    }


    public void initData() {
        for (int i = 0; i < 12; i++) {
            dataList.add(new SearchResultModel(i + 1, "title : " + (i + 1)));
        }
    }

    public List<SearchResultModel> getMoreData() {
        List<SearchResultModel> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(new SearchResultModel(i + 1, "title : " + (i + 1)));
        }
        return list;
    }
}
