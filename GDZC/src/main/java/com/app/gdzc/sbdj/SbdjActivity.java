package com.app.gdzc.sbdj;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.utils.Utils;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/25.
 */
public class SbdjActivity extends BaseActivity implements SbdjContract.SbdjView {

    @InjectView(R.id.llayout_root)
    LinearLayout mLayoutRoot;

    private SbdjContract.Presenter mPresenter;

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sbdj);
        setTitle("设备登记");
        showLeft();
        new SbdjPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showView(List<TsxxBean> list) {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(TsxxBean tsxxBean : list){
            View view = inflater.inflate(R.layout.common_input_text, null);
            TextView tv = (TextView) view.findViewById(R.id.textview_label);
            EditText et = (EditText) view.findViewById(R.id.edittext_input);
            TextView canbenull = (TextView) view.findViewById(R.id.tv_canbenull);
            tv.setText(tsxxBean.getShowContent());
            et.setHint(tsxxBean.getHintContent());
            canbenull.setVisibility(tsxxBean.getCanBeNull().equals("1")?View.VISIBLE:View.GONE);
            mLayoutRoot.addView(view);
        }

    }

    @Override
    public void setPresenter(SbdjContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meun_main, menu);
        menu.findItem(R.id.action_right).setTitle("完成");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_right:
                Utils.showToast(this, "完成");
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
