package com.app.gdzc.sbdj.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseFragment;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.photo.SelectPhotoActivity;
import com.app.gdzc.sbdj.SbdjActivity;
import com.app.gdzc.sbdj.model.SbdjModel;
import com.app.gdzc.sbdj.presenter.SbdjPresenter;
import com.app.gdzc.utils.ENavigate;
import com.bigkoo.pickerview.TimePickerView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public class SbdjFragment extends BaseFragment<IEmptyInterFace, SbdjModel, SbdjPresenter> implements ISbdjView, View.OnClickListener {

    @InjectView(R.id.llayout_root)
    LinearLayout mLayoutRoot;
    @InjectView(R.id.iv_camera_yq)
    ImageView mIvCameraYq;
    @InjectView(R.id.iv_camera_fp)
    ImageView mIvCameraFp;
    @InjectView(R.id.iv_image_yq)
    ImageView mIvYq;
    @InjectView(R.id.iv_image_fp)
    ImageView mIvFp;
    @InjectView(R.id.iv_del_yq)
    ImageView mIvDelYq;
    @InjectView(R.id.iv_del_fp)
    ImageView mIvDelFp;

    private Map<String, String> SbdeMap = new HashMap<>();
    private List<TsxxBean> mList;
    private TextView mTempTv;

    private static final int request_code = 1000;
    private static final int request_code_yq = 1001;
    private static final int request_code_fp = 1002;

    private TimePickerView popTime;
    private String time_tag;
    private File fileNewLogo;
    public Boolean mloadListBool = true;

    @Override
    protected void localCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sbdj);
        setTitle("设备登记");
        showLeft();
        setOnclickListener(this, mIvCameraFp, mIvCameraYq, mIvDelFp, mIvDelYq);
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
                            ENavigate.startActivityForResult(getActivity(), SbdjActivity.class, request_code, bundle);
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
                            ENavigate.startActivityForResult(getActivity(), SbdjActivity.class, request_code, bundle);
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
                            ENavigate.startActivityForResult(getActivity(), SbdjActivity.class, request_code, bundle);
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
                            closeInput();
                            mTempTv = tv_input;
                            time_tag = tsxxBean.getShowContent();
                            popTime.show();
                        }
                    });
                    break;
                case "dj":
                    et_input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
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
        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case request_code:
                    SbdeMap.put(data.getStringExtra("colName"), data.getStringExtra("colCode"));
                    mTempTv.setText(data.getStringExtra("colValue"));
                    mTempTv.setHint(data.getStringExtra("colCode"));
                    break;
                case request_code_yq: {
                    String stringPath = data.getStringExtra(SelectPhotoActivity.Extra.IMAGE_PATH);
                    String stringUri = data.getStringExtra(SelectPhotoActivity.Extra.IMAGE_URI);
                    if (!TextUtils.isEmpty(stringPath)) {
                        fileNewLogo = new File(stringPath);
                        Log.i("image_file", "\nImagePath: " + stringPath + "\nImageUri: " + stringUri + "\nfileNewLogo: " + fileNewLogo.getPath() + "\nfileNewLogo.getName: " + fileNewLogo.getName());
                        mloadListBool = false;
                        mIvYq.setImageURI(Uri.parse(stringUri));
                        SbdeMap.put("仪器照片", fileNewLogo.getName());
                    }
                }
                break;
                case request_code_fp: {
                    String stringPath = data.getStringExtra(SelectPhotoActivity.Extra.IMAGE_PATH);
                    String stringUri = data.getStringExtra(SelectPhotoActivity.Extra.IMAGE_URI);
                    if (!TextUtils.isEmpty(stringPath)) {
                        fileNewLogo = new File(stringPath);
                        Log.i("image_file", "\nImagePath: " + stringPath + "\nImageUri: " + stringUri + "\nfileNewLogo: " + fileNewLogo.getPath() + "\nfileNewLogo.getName: " + fileNewLogo.getName());
                        mloadListBool = false;
                        mIvFp.setImageURI(Uri.parse(stringUri));
                        SbdeMap.put("发票照片", fileNewLogo.getName());
                    }
                }
                break;
            }
        }
    }

    private void closeInput() {
        InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getActivity().getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_camera_yq:
                startSelectPhotoActivity(request_code_yq);
                break;
            case R.id.iv_camera_fp:
                startSelectPhotoActivity(request_code_fp);
                break;
            case R.id.iv_del_yq:
                mIvYq.setImageDrawable(null);
                SbdeMap.put("仪器照片", "");
                break;
            case R.id.iv_del_fp:
                mIvFp.setImageDrawable(null);
                SbdeMap.put("发票照片", "");
                break;
        }
    }

    private void startSelectPhotoActivity(int requestCode) {
        Intent picIntent = new Intent(getActivity(), SelectPhotoActivity.class);
        picIntent.putExtra(SelectPhotoActivity.Extra.HAS_TO_CUT, true);
        picIntent.putExtra(SelectPhotoActivity.Extra.ASPECT_X, 4);
        picIntent.putExtra(SelectPhotoActivity.Extra.ASPECT_Y, 3);
        startActivityForResult(picIntent, requestCode);
    }
}
