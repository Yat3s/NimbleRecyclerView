package com.yat3s.nimblerecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.yat3s.nimblerecyclerview.widget.ScrollableView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private NimbleRecyclerView mRecyclerView;
    private ScrollableView mScrollableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (NimbleRecyclerView) findViewById(R.id.recycler_view);
        mScrollableView = (ScrollableView) findViewById(R.id.scrollable_view);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScrollableView.starBackHome(200, 200);
            }
        });


        final View header = getLayoutInflater().inflate(R.layout.layout_refresh_header, null, false);
        TodoAdapter todoAdapter = new TodoAdapter(this, generateMockData());
        mRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.getRecyclerView().setAdapter(todoAdapter);
        mRecyclerView.setRefreshHeaderView(new NimbleRecyclerView.RefreshHeaderViewProvider() {
            @Override
            public View provideContentView() {
                return header;
            }

            @Override
            public void onStartRefresh() {
                Log.d(TAG, "onStartRefresh: ");
            }

            @Override
            public void onRefreshComplete() {
                Log.d(TAG, "onRefreshComplete: ");
            }

            @Override
            public void onRefreshHeaderViewScrollChange(int progress) {
                Log.d(TAG, "onRefreshHeaderViewScrollChange: " + progress);
            }
        });
        mRecyclerView.setOnRefreshListener(new NimbleRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: ");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.refreshComplete();
                    }
                }, 500);
            }
        });

        mRecyclerView.getRecyclerView().addItemDecoration(new HeaderItemDecoration(this, header, todoAdapter));
        mRecyclerView.getRecyclerView().addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        mRecyclerView.setOnRefreshListener(new NimbleRecyclerView.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Toast.makeText(MainActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
//                mRecyclerView.refreshComplete();
//            }
//        });
//
//        mRecyclerView.setOnLoadMoreListener(new NimbleRecyclerView.OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                Toast.makeText(MainActivity.this, "LoadMore", Toast.LENGTH_SHORT).show();
//                mRecyclerView.loadMoreComplete();
//            }
//        });
    }

    private ArrayList<Task> generateMockData() {
        String[] taskNames = getResources().getStringArray(R.array.animals);
        ArrayList<Task> tasks = new ArrayList<>();
        for (String taskName : taskNames) {
            tasks.add(new Task(taskName));
        }
        return tasks;
    }

    static class TodoAdapter extends NimbleAdapter<Task, NimbleViewHolder> implements StickyHeaderAdapter<NimbleViewHolder> {
        public TodoAdapter(Context context, List<Task> data) {
            super(context, data);
        }

        @Override
        protected void bindDataToItemView(NimbleViewHolder holder, Task task, int position) {
            holder.setTextView(R.id.title_tv, task.title);
        }

        @Override
        protected int getItemViewLayoutId(int position, Task data) {
            return R.layout.item_task;
        }

        @Override
        public void onBindHeaderViewHolder(NimbleViewHolder holder, int position) {
            holder.setTextView(R.id.header_tv, mDataSource.get(position).title);
        }

        @Override
        public int getHeaderViewLayoutId(int position) {
            return R.layout.header_layout;
        }

        @Override
        public boolean hasHeader(int position) {
            return position % 8 == 0 && position != 0;
        }
    }

    static class Task {
        public String title;

        public Task(String title) {
            this.title = title;
        }
    }
}
