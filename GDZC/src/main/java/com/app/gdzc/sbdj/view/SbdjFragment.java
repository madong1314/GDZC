package com.app.gdzc.sbdj.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseFragment;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.sbdj.SbdjActivity;
import com.app.gdzc.sbdj.model.SbdjModel;
import com.app.gdzc.sbdj.presenter.SbdjPresenter;
import com.app.gdzc.utils.ENavigate;
import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public class SbdjFragment extends BaseFragment<IEmptyInterFace, SbdjModel, SbdjPresenter> implements ISbdjView {

    @InjectView(R.id.llayout_root)
    LinearLayout mLayoutRoot;

    private Map<String, String> SbdeMap = new HashMap<>();
    private List<TsxxBean> mList;
    private TextView mTempTv;

    public static final int requestCode = 1000;

    private TimePickerView popTime;
    private String time_tag;

    @Override
    protected void localCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sbdj);
        setTitle("设备登记");
        showLeft();
        mPresenter.getTsxx();
        initTimePicker();
    }

    private void initTimePicker() {
        popTime = new TimePickerView(getActivity(), TimePickerView.Type.YEAR_MONTH_DAY);
        popTime.setTitle("选择时间");
        popTime.setTime(new Date());
        popTime.setCyclic(false);
        popTime.setCancelable(true);
        popTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                mTempTv.setText(format.format(date));
                SbdeMap.put(time_tag, format.format(date));
            }
        });
    }

    @Override
    protected SbdjPresenter initPresenter() {
        return new SbdjPresenter(getActivity(), this);
    }

    @Override
    public void showView(List<TsxxBean> list) {
        mList = list;
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (final TsxxBean tsxxBean : list) {
            View view = inflater.inflate(R.layout.layout_input_sbdj, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_label);
            EditText et_input = (EditText) view.findViewById(R.id.et_input);
            final TextView tv_input = (TextView) view.findViewById(R.id.tv_input);
            TextView canbenull = (TextView) view.findViewById(R.id.tv_canbenull);
            tv.setText(tsxxBean.getShowContent());
            canbenull.setVisibility(tsxxBean.getCanBeNull().equals("1") ? View.VISIBLE : View.GONE);

            switch (tsxxBean.getColNameEng()) {
                case "flh":
                    tv_input.setVisibility(View.VISIBLE);
                    et_input.setVisibility(View.GONE);
                    tv_input.setHint(tsxxBean.getHintContent());
                    tv_input.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mTempTv = tv_input;
                            Bundle bundle = new Bundle();
                            bundle.putString("title", tsxxBean.getShowContent());
                            bundle.putString("code", tv_input.getHint().toString());
                            bundle.putString("value", tv_input.getText().toString());
                            bundle.putString(SbdjActivity.FRAGMENT, SbdjActivity.FLHFRAGMENT);
                            ENavigate.startActivityForResult(getActivity(), SbdjActivity.class, requestCode, bundle);
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
                            mTempTv = tv_input;
                            Bundle bundle = new Bundle();
                            bundle.putString("title", tsxxBean.getShowContent());
                            bundle.putString("code", tv_input.getHint().toString());
                            bundle.putString("value", tv_input.getText().toString());
                            bundle.putString(SbdjActivity.FRAGMENT, SbdjActivity.DWFRAGMENT);
                            ENavigate.startActivityForResult(getActivity(), SbdjActivity.class, requestCode, bundle);
                        }
                    });
                    break;
                case "xz":
                case "sbly":
                case "syfx":
                case "jfkm":
                    tv_input.setVisibility(View.VISIBLE);
                    et_input.setVisibility(View.GONE);
                    tv_input.setHint(tsxxBean.getHintContent());
                    tv_input.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mTempTv = tv_input;
                            Bundle bundle = new Bundle();
                            bundle.putString("title", tsxxBean.getShowContent());
                            bundle.putString("code", tv_input.getHint().toString());
                            bundle.putString("value", tv_input.getText().toString());
                            bundle.putString(SbdjActivity.FRAGMENT, SbdjActivity.MKFRAGMENT);
                            ENavigate.startActivityForResult(getActivity(), SbdjActivity.class, requestCode, bundle);
                        }
                    });
                    break;
                case "gzrq":
                case "ccrq":
                case "bxrq":
                    tv_input.setVisibility(View.VISIBLE);
                    et_input.setVisibility(View.GONE);
                    tv_input.setHint(tsxxBean.getHintContent());
                    tv_input.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mTempTv = tv_input;
                            time_tag = tsxxBean.getShowContent();
                            popTime.show();
                        }
                    });
                    break;
                default:
                    et_input.setHint(tsxxBean.getHintContent());
                    et_input.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            SbdeMap.put(tsxxBean.getShowContent(), s.toString());
                        }
                    });
                    break;
            }
            mLayoutRoot.addView(view);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.meun_main, menu);
        menu.findItem(R.id.action_right).setTitle("完成");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_right:
                if (ZJBean.isCorrect(SbdeMap, mList)) {
                    ZJBean zjBean = ZJBean.mapToZJBean(SbdeMap);
                    mPresenter.createZJ(zjBean);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        SbdeMap.put(data.getStringExtra("colName"), data.getStringExtra("colCode"));
        mTempTv.setText(data.getStringExtra("colValue"));
        mTempTv.setHint(data.getStringExtra("colCode"));
    }
}
