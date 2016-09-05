package com.app.gdzc.photo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by 王少岩 on 2016/9/5.
 */
public class SelectPhotoActivity extends SelectBaseActivity {
    public static class Extra {
        public static final String HAS_TO_CUT = "hasToCut";
        public static final String IMAGE_PATH = "imagePath";
        public static final String IMAGE_URI = "imageUri";

        public static final String ASPECT_X = "aspectX";
        public static final String ASPECT_Y = "aspectY";
        public static final String OUTPUT_X = "outputX";
        public static final String OUTPUT_Y = "outputY";
    }

    private static final int kRequest_camera = 0;
    private static final int kRequest_gallery = 1;
    private static final int kRequest_result = 3;
    /**
     * SD卡中自己拍照照片的存储路径
     */
    public static final File SD_CAMERA_DIR = new File(Environment.getExternalStorageDirectory(), "DCIM/Camera");
    /**
     * SD卡中用于缓存路径
     */
    public static final File SD_CACHE_DIR = BaseApplication.getAppContext().getExternalCacheDir();
    public static final File CACHE_DIR = BaseApplication.getAppContext().getCacheDir();
    public static final String PATH_IMAGE_SHOT = "shots";
    public static final String PATH_IMAGE_CAMERA = "Camera";
    private static final int CROP_REQUEST_CODE = 4;
    private final int OUTPUT_DEFAULT = 400;
    private final int ASPECT_DEFAULT = 1;
    private boolean hasToCut = false;
    private File tempFile;
    private View chooseLl;
    private Button firstBtn;
    private Button secondBtn;
    private Button cancelBtn;
    private int aspectX;
    private int aspectY;
    private int outputX;
    private int outputY;
    private boolean isSDCardExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isSDCardExist = isSDCardExist();
        setContentView(R.layout.select_button_page);
        hasToCut = getIntent().getBooleanExtra(Extra.HAS_TO_CUT, hasToCut);
        firstBtn = (Button) findViewById(R.id.select_btn_0);
        secondBtn = (Button) findViewById(R.id.select_btn_1);
        cancelBtn = (Button) findViewById(R.id.select_btn_cancle);
        firstBtn.setOnClickListener(this);
        secondBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        firstBtn.setText("用相机拍照");
        secondBtn.setText("从相册选取");
        chooseLl = findViewById(R.id.select_ll_button);
        setSelectView(chooseLl);
        show();
        Intent intent = getIntent();
        aspectX = intent.getIntExtra(Extra.ASPECT_X, ASPECT_DEFAULT);
        aspectY = intent.getIntExtra(Extra.ASPECT_Y, ASPECT_DEFAULT);
        outputX = intent.getIntExtra(Extra.OUTPUT_X, OUTPUT_DEFAULT);
        outputY = intent.getIntExtra(Extra.OUTPUT_X, OUTPUT_DEFAULT);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.select_btn_0:// 从相机选择
                tempFile = getCameraFile();
                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                intentCamera.putExtra("output", Uri.fromFile(tempFile));
                startActivityForResult(intentCamera, kRequest_camera);

                chooseLl.setVisibility(View.INVISIBLE);
                break;
            case R.id.select_btn_1:// 从相册选择
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, kRequest_gallery);
                break;
            case R.id.select_btn_cancle:
                dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 获取拍摄照片的存储路径
     */
    private File getCameraFile() {
        File filePath = null;
        if (isSDCardExist) {
            filePath = SD_CAMERA_DIR;
        } else {
            filePath = new File(CACHE_DIR, PATH_IMAGE_CAMERA);
        }
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        String tempFileName = System.currentTimeMillis() + ".jpg";
        return new File(filePath, tempFileName);
    }

    /**
     * 获取裁剪后照片的存储路径
     */
    private File getShotFile() {
        File filePath = null;
        if (isSDCardExist) {
            filePath = new File(SD_CACHE_DIR, PATH_IMAGE_SHOT);
        } else {
            filePath = new File(CACHE_DIR, PATH_IMAGE_SHOT);
        }
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        String tempFileName = System.currentTimeMillis() + "A.jpg";
        return new File(filePath, tempFileName);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dismiss();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case kRequest_camera:// 相机
                if (RESULT_OK == resultCode && tempFile != null && tempFile.exists()) {
                    if (hasToCut) {
                        Intent intent = new Intent();
                        intent.setAction("com.android.camera.action.CROP");
                        intent.setDataAndType(Uri.fromFile(tempFile), "image/*");
                        tempFile = getShotFile();
                        intent.putExtra("output", Uri.fromFile(tempFile));
                        intent.putExtra("crop", "true");
                        intent.putExtra("aspectX", aspectX);// 裁剪框比例
                        intent.putExtra("aspectY", aspectY);
                        intent.putExtra("outputX", 200);//解决了htc 剪切回调崩溃问题
                        intent.putExtra("outputY", 200);
                        startActivityForResult(intent, kRequest_result);
                    } else {
                        Intent intent = new Intent();
                        String path = tempFile.getPath();
                        String uri = getFileUri(path);
                        intent.putExtra(Extra.IMAGE_PATH, path);
                        intent.putExtra(Extra.IMAGE_URI, uri);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } else {
                    finish();
                }
                break;
            case kRequest_result:// 相册
                if (RESULT_OK == resultCode && null != data) {
                    if(!hasToCut){
                        String filePath = null;
                        Uri selectedImage = data.getData();
                        if(null != selectedImage){
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};

                            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                            if(cursor.moveToFirst()){
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                filePath = cursor.getString(columnIndex);
                                cursor.close();
                                if(filePath ==null){
                                    Toast.makeText(this,"不支持网络图片,请从本地选择!",Toast.LENGTH_SHORT).show();
                                    onClick(secondBtn);
                                }
                            }
                        }else{
                            filePath = data.getAction().replace("file://", "");
                        }
                        if(null != filePath){
                            tempFile = new File(filePath);
                        }
                    }

                    if(tempFile != null && tempFile.exists()){
                        Intent intent = new Intent();
                        String path = tempFile.getPath();
                        String uri = getFileUri(path);
                        intent.putExtra(Extra.IMAGE_PATH, path);
                        intent.putExtra(Extra.IMAGE_URI,uri);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } else {
                    finish();
                }
                break;

            case kRequest_gallery:
                if (data == null) {
                    return;
                }
                startCrop(data.getData());
                break;
            case CROP_REQUEST_CODE:
                if (data == null) {
                    // TODO 如果之前以后有设置过显示之前设置的图片 否则显示默认的图片
                    return;
                }
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap photo = null;
                    try {
                        photo = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    FileOutputStream stream = null;
                    tempFile = getShotFile();
                    try {
                        stream = new FileOutputStream(tempFile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (photo != null) {
                        photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
                    }
                    if (tempFile != null && tempFile.exists()) {
                        Intent intent = new Intent();
                        String path = tempFile.getPath();
                        String uri = getFileUri(path);
                        intent.putExtra(Extra.IMAGE_PATH, path);
                        intent.putExtra(Extra.IMAGE_URI, uri);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
                break;
            default:
                finish();
                break;
        }
    }

    /**
     * 开始裁剪
     *
     * @param uri
     */
    Uri uritempFile;
    private void startCrop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");//调用Android系统自带的一个图片剪裁页面,
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");//进行修剪
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        //小米系统需要设置 outputx才行
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", false);

        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    /**
     * 判断SD卡是否存在
     *
     * @return
     */
    public static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String getFileUri(String path) {
        return "file://" + path;
    }

    @Override
    public String toString() {
        return "选择照片";
    }
}
