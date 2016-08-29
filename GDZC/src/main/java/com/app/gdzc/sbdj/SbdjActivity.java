package com.app.gdzc.sbdj;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.sbdj.dw.DwActivity;
import com.app.gdzc.sbdj.fl.FlActivity;
import com.app.gdzc.utils.ENavigate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/25.
 */
public class SbdjActivity extends BaseActivity implements SbdjContract.SbdjView {

    @InjectView(R.id.llayout_root)
    LinearLayout mLayoutRoot;

    private Map<String, String> SbdeMap = new HashMap<>();
    private List<TsxxBean> mList;
    private SbdjContract.Presenter mPresenter;
    private TextView mLydwh;

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sbdj);
        setTitle("设备登记");
        showLeft();
        new SbdjPresenter(this);
        mPresenter.start();
    }

    @Override
    public void showView(List<TsxxBean> list) {
        mList = list;
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(final TsxxBean tsxxBean : list){
            View view = inflater.inflate(R.layout.layout_input_sbdj, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_label);
            EditText et_input = (EditText) view.findViewById(R.id.et_input);
            final TextView tv_input = (TextView) view.findViewById(R.id.tv_input);
            TextView canbenull = (TextView) view.findViewById(R.id.tv_canbenull);
            tv.setText(tsxxBean.getShowContent());
            canbenull.setVisibility(tsxxBean.getCanBeNull().equals("1")?View.VISIBLE:View.GONE);

            switch (tsxxBean.getColNameEng()){
                case "flh":
                    tv_input.setVisibility(View.VISIBLE);
                    et_input.setVisibility(View.GONE);
                    tv_input.setHint(tsxxBean.getHintContent());
                    tv_input.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mLydwh = tv_input;
                            Bundle bundle = new Bundle();
                            bundle.putString("flmc", tv_input.getText().toString());
                            ENavigate.startActivityForResult(SbdjActivity.this, FlActivity.class, 1000, bundle);
                        }
                    });
                break;
                case "lydwh":
                    tv_input.setVisibility(View.VISIBLE);
                    et_input.setVisibility(View.GONE);
                    tv_input.setHint(tsxxBean.getHintContent());
                    tv_input.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mLydwh = tv_input;
                            Bundle bundle = new Bundle();
                            bundle.putString("dwmc", tv_input.getText().toString());
                            ENavigate.startActivityForResult(SbdjActivity.this, DwActivity.class, 1000, bundle);
                        }
                    });
                break;
                default:
                    et_input.setHint(tsxxBean.getHintContent());
                    et_input.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {}

                        @Override
                        public void afterTextChanged(Editable s) {
                            SbdeMap.remove(tsxxBean.getShowContent());
                            SbdeMap.put(tsxxBean.getShowContent(), s.toString());
                        }
                    });
                break;
            }
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
                if(ZJBean.isCorrect(SbdeMap, mList)){
                    ZJBean zjBean = ZJBean.mapToZJBean(SbdeMap);
                }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK) return;
        switch (requestCode){
            case 1000:
                SbdeMap.remove(data.getStringExtra("colName"));
                SbdeMap.put(data.getStringExtra("colName"), data.getStringExtra("colCode"));
                mLydwh.setText(data.getStringExtra("colValue"));
            break;
        }
    }
}
