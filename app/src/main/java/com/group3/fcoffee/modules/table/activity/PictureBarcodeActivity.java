package com.group3.fcoffee.modules.table.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.group3.fcoffee.BuildConfig;
import com.group3.fcoffee.R;
import com.group3.fcoffee.modules.management.presenter.ManagementPresenter;
import com.group3.fcoffee.modules.management.view.ManagementView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.File;
import java.io.FileNotFoundException;

public class PictureBarcodeActivity extends AppCompatActivity implements View.OnClickListener, ManagementView {

    private Button mBtnOpenCamera;
    private Button mBtnVerify;
    private Button mBtnCanncel;

    private TextView mResultBody;
    private String discountCode;

    private BarcodeDetector detector;
    private Uri imageUri;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private static final int CAMERA_REQUEST = 101;
    private static final String TAG = "PictureBarcodeActivity";
    private static final String SAVED_INSTANCE_URI = "uri";
    private static final String SAVED_INSTANCE_RESULT = "result";

    private int billId;

    private ManagementPresenter mManagementPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_barcode);

        try {
            mManagementPresenter = new ManagementPresenter(this);

            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("bundle_billId");
            billId = bundle.getInt("billId");

            initViews();

            if (savedInstanceState != null) {
                if (imageUri != null) {
                    imageUri = Uri.parse(savedInstanceState.getString(SAVED_INSTANCE_URI));
                    mResultBody.setText(savedInstanceState.getString(SAVED_INSTANCE_RESULT));
                }
            }

            detector = new BarcodeDetector.Builder(getApplicationContext()).setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE).build();

            if (!detector.isOperational()) {
                mResultBody.setText("Chưa quét");
                return;
            }
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Hệ thống đang bận", Toast.LENGTH_SHORT).show();
            Log.e(TAG, ex.getMessage());
        }


    }

    private void initViews() {
        try{
            mResultBody = findViewById(R.id.txtResultsBody);
            mBtnOpenCamera = findViewById(R.id.btnOpenCamera);
            mBtnOpenCamera.setOnClickListener(this);

            mBtnVerify = findViewById(R.id.btnVerify);
            mBtnVerify.setOnClickListener(this);

            mBtnCanncel = findViewById(R.id.btnCanncel);
            mBtnCanncel.setOnClickListener(this);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Hệ thống đang bận", Toast.LENGTH_SHORT).show();
            Log.e(TAG, ex.getMessage());
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOpenCamera:
                ActivityCompat.requestPermissions(PictureBarcodeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                break;
            case R.id.btnCanncel:
                finish();
                break;
            case R.id.btnVerify:
                if(discountCode == null){
                    Toast.makeText(getApplicationContext(), "Chưa nhận ra mã code!!!", Toast.LENGTH_SHORT).show();
                }else{
                    String discountString = discountCode.substring(8);
                    try {
                        float discount = Float.parseFloat(discountString);
                        if(discount < 0 || discount > 100){
                            Toast.makeText(getApplicationContext(), "Mã giảm giá không hợp lệ", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        mManagementPresenter.addDiscount(billId, discount);
                    }catch (Exception ex){
                        Toast.makeText(getApplicationContext(), "Mã giảm giá có lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        try {
            switch (requestCode) {
                case REQUEST_CAMERA_PERMISSION:
                    if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED && grantResult[1] == PackageManager.PERMISSION_GRANTED) {
                        takeBarcodePicture();
                    } else {
                        Toast.makeText(getApplicationContext(), "Chưa cấp quyền truy cập máy ảnh hoặc bộ sưu tập", Toast.LENGTH_SHORT).show();
                    }
            }
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
            Log.e(TAG, ex.getMessage());
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            launchMediaScanIntent();
            try {
                Bitmap bitmap = decodeBitmapUri(this, imageUri);
                if (detector.isOperational() && bitmap != null) {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<Barcode> barcodes = detector.detect(frame);
                    for (int index = 0; index < barcodes.size(); index++) {
                        Barcode code = barcodes.valueAt(index);
                        mResultBody.setText(code.displayValue);
                        discountCode = code.displayValue;

                        int type = barcodes.valueAt(index).valueFormat;
                        switch (type) {
                            case Barcode.CONTACT_INFO:
                                Log.i(TAG, code.contactInfo.title);
                                break;
                            case Barcode.EMAIL:
                                Log.i(TAG, code.displayValue);
                                break;
                            case Barcode.ISBN:
                                Log.i(TAG, code.rawValue);
                                break;
                            case Barcode.PHONE:
                                Log.i(TAG, code.phone.number);
                                break;
                            case Barcode.PRODUCT:
                                Log.i(TAG, code.rawValue);
                                break;
                            case Barcode.SMS:
                                Log.i(TAG, code.sms.message);
                                break;
                            case Barcode.TEXT:
                                Log.i(TAG, code.displayValue);
                                break;
                            case Barcode.URL:
                                Log.i(TAG, "url: " + code.displayValue);
                                break;
                            case Barcode.WIFI:
                                Log.i(TAG, code.wifi.ssid);
                                break;
                            case Barcode.GEO:
                                Log.i(TAG, code.geoPoint.lat + ":" + code.geoPoint.lng);
                                break;
                            case Barcode.CALENDAR_EVENT:
                                Log.i(TAG, code.calendarEvent.description);
                                break;
                            case Barcode.DRIVER_LICENSE:
                                Log.i(TAG, code.driverLicense.licenseNumber);
                                break;
                            default:
                                Log.i(TAG, code.rawValue);
                                break;
                        }
                    }
                    if (barcodes.size() == 0) {
                        mResultBody.setText("Không thể phát hiện được mã code. Vui lòng thử lại.");
                    }
                } else {
                    mResultBody.setText("Không thể khởi tạo mã code. Vui lòng thử lại.");
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Không thể tải hình ảnh", Toast.LENGTH_SHORT)
                        .show();
                Log.e(TAG, e.toString());
            }
        }
    }

    private void takeBarcodePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "pic.jpg");
        imageUri = FileProvider.getUriForFile(PictureBarcodeActivity.this,
                BuildConfig.APPLICATION_ID + ".provider", photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CAMERA_REQUEST);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (imageUri != null) {
            outState.putString(SAVED_INSTANCE_URI, imageUri.toString());
            outState.putString(SAVED_INSTANCE_RESULT, mResultBody.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    private void launchMediaScanIntent() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(imageUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private Bitmap decodeBitmapUri(Context ctx, Uri uri) throws FileNotFoundException {
        int targetW = 600;
        int targetH = 600;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }

    @Override
    public void onDrinkSuccess() {

    }

    @Override
    public void onCheckoutSuccess() {

    }

    @Override
    public void onRemoveDrinkSuccess() {

    }

    @Override
    public void onDrinkFail(String message) {

    }

    @Override
    public void onDiscountSuccess() {
        Toast.makeText(this, "Thêm mã giảm giá thành công", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onDiscountFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
