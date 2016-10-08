package com.baseui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.baseui.R;
import com.baseui.utils.RMB;


/**
 * Created by 王少岩 on 2016/5/31.
 */
public class InputTextDialog extends Dialog {

    public final static int INPUT_TXT = 0;
    public final static int INPUT_QUANTITY = 1;
    public final static int INPUT_MONEY = 2;

    // 变量
    private Activity mContext;
    private OnDialogFinishListener mListener;

    private TextView tvTitle;
    private TextView tvInputName;
    private TextView tvLeftButton;
    private TextView tvRightButton;
    private EditText etInput;
    private int mType;

    public InputTextDialog(Activity context, String msg,int type,InputTextDialog.OnDialogFinishListener listener) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_input);
        mContext = context;
        mType = type;
        setCanceledOnTouchOutside(false);
        mListener = listener;
        setCancelable(false);
        initControls(msg);
    }
    private void initControls(String msg){
        tvTitle = (TextView) findViewById(R.id.tv_dialog_title);
        tvInputName = (TextView) findViewById(R.id.tv_input_name);
        etInput = (EditText) findViewById(R.id.et_input);
        tvLeftButton = (TextView) findViewById(R.id.tv_dialog_left_button);
        tvRightButton = (TextView) findViewById(R.id.tv_dialog_right_button);
        tvTitle.setText(mContext.getString(R.string.app_name));
        tvInputName.setText(msg);
        tvLeftButton.setOnClickListener(mOnClickListener);
        tvRightButton.setOnClickListener(mOnClickListener);
        if (mType == INPUT_MONEY){
            etInput.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
            etInput.setFilters((new InputFilter[]{RMB.createInputFilter(6, 2)}));
        }else if (mType == INPUT_QUANTITY){
            etInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }
    public void setTitle(String title){
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
    }

    public void hideTitle(){
        tvTitle.setVisibility(View.GONE);
    }

    public void setDialogFinishListener(OnDialogFinishListener listener){
        mListener = listener;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String input = etInput.getText().toString();
            int i = v.getId();
            if (i == R.id.tv_dialog_left_button) {
                if (mListener != null) {
                    mListener.onCancel();
                }
                InputTextDialog.this.dismiss();

            } else if (i == R.id.tv_dialog_right_button) {
                if (mListener != null) {
                    mListener.onFinish(input);
                }
                InputTextDialog.this.dismiss();

            }
        }
    };

    public interface OnDialogFinishListener{
        void onFinish(String input);
        void onCancel();
    }

    public EditText getInputView(){
        return etInput;
    }
}
