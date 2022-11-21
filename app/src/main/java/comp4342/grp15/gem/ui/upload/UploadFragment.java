package comp4342.grp15.gem.ui.upload;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import comp4342.grp15.gem.DBController;
import comp4342.grp15.gem.MainActivity;
import comp4342.grp15.gem.databinding.FragmentUploadBinding;


public class UploadFragment extends Fragment {

    private FragmentUploadBinding binding;
    private ImageView photo;
    private Uri imgUri;
    private Bitmap bitmap;
    private EditText message;
    private String location = "null";
    DBController dbController;
    SQLiteDatabase writableDatabase;
    String username = "null";
    String identifier = "null";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUploadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 绑定对象
        photo = binding.uploadPhotoImage;
        photo.setVisibility(View.VISIBLE);
        message = binding.uploadMessageMuitedittext;

        // 获取mainActivity
        MainActivity mainActivity = (MainActivity) getActivity();

        // 从数据库获取用户信息
        dbController = new DBController(requireActivity().getApplicationContext(), "login.db", null, 1);
        writableDatabase = dbController.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from user where id == 1", null);
        while (cursor.moveToNext()) {
            if (!cursor.getString(1).equals("null")) {
                // 已经登入
                username = cursor.getString(1);
                identifier = cursor.getString(3);
            } else {
                // 没有登入
                Toast.makeText(getContext(), "Please login first!", Toast.LENGTH_SHORT).show();
            }
            cursor.close();

            // 监听图片按钮
            photo.setOnClickListener(new ImageView.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mainActivity.checkPermissionAllGranted(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                    })) {
                        dialog();
                    } else {
                        mainActivity.firstRequestPermissions();
                    }
                }
            });

            // 监听POST按钮
            Button postButton = binding.uploadPostButton;
            postButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doUpload();
                }
            });
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void dialog(){
        String[] choices = {"From Album", "From Camera"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Please Choose")
                .setItems(choices, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            //选择照片按钮
                            toPicture();
                        }else if (which == 1){
                            //已经有权限，打开相机
                            toCamera();
                        }
                    }
                });
        builder.create();
        builder.show();
    }

    // 跳转相册
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);  //跳转到 ACTION_IMAGE_CAPTURE
        intent.setType("image/*");
        startActivityForResult(intent, 200);
        Log.i(TAG, "跳转相册成功");
    }

    // 跳转相机
    private void toCamera() {
        //创建File对象，用于存储拍照后的图片
        File outputImage = new File(getActivity().getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        if (outputImage.exists()) {
            outputImage.delete();
        } else {
            try {
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //判断SDK版本高低，ImageUri方法不同
        if (Build.VERSION.SDK_INT >= 24) {
            imgUri = FileProvider.getUriForFile(getActivity(), "comp4342.grp15.gem.fileprovider", outputImage);
        } else {
            imgUri = Uri.fromFile(outputImage);
        }

        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        startActivityForResult(intent, 100);
    }

    // 获取得到的照片
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    try {
                        //将拍摄的照片显示出来
                        bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imgUri));
                        photo.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 200:
                if (resultCode == RESULT_OK) {
                    try {
                        //将相册的照片显示出来
                        Uri uri_photo = data.getData();
                        bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri_photo));
                        photo.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    // 把filePath地址对应的图片转换成Bitmap，然后再将bitmap转换成Base64字符串String
    private String bitmapToString() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    // 上传
    private void doUpload(){
        if (username.equals("null")){
            Toast.makeText(getContext(), "Please Login first!", Toast.LENGTH_SHORT).show();
            return;
        } else if (message.getText().toString().length() >= 256 || message.getText().toString().length() <= 10){
            Toast.makeText(getContext(), "Invalid message length", Toast.LENGTH_SHORT).show();
            return;
        }else if (bitmap == null){
            Toast.makeText(getContext(), "Please select a picture", Toast.LENGTH_SHORT).show();
            return;
        }else if (Objects.equals(location, "")){
            Toast.makeText(getContext(), "Cannot get location", Toast.LENGTH_SHORT).show();
            return;
        }



    }

    //
    // 获取地理坐标
    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // 当GPS定位信息发生改变时，更新定位
            updateShow(location);
        }
    };

    


}





