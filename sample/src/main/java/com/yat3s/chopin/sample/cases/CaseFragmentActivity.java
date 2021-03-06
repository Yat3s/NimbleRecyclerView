package com.yat3s.chopin.sample.cases;


import com.yat3s.chopin.sample.R;

/**
 * Created by Yat3s on 04/07/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class CaseFragmentActivity extends BaseCaseActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.case_activity_fragment;
    }

    @Override
    protected void initialize() {
        setupRefreshHeader("refresh.json", 0.2f, 2000);
        setupLoadingFooter("Plane.json", 0.2f, 1500);
    }
}
