package comp4342.grp15.gem.ui.profile;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;

import comp4342.grp15.gem.DBController;
import comp4342.grp15.gem.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    ProfileViewModel profileViewModel;
    DBController dbController;
    SQLiteDatabase writableDatabase;

    Button loginButton;
    Button registerButton;
    TextView userNameText;
    TextView userLoginTime;
    EditText editUserName;
    EditText editPassword;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 绑定
        loginButton = binding.profileLoginButton;
        registerButton = binding.profileSigninButton;
        userNameText = binding.profileUsernameText;
        userLoginTime = binding.profileLoginTime;
        editUserName = binding.profileEdittextUsername;
        editPassword = binding.profileEdittextPassword;

        // 初始化: 设置和展示用户状态
        resetStatus();

        // 登入/注销按钮
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!profileViewModel.getIsLogin()){
                    // 按钮功能为登入
                    String identifier = doLogin(editUserName.getText().toString(),editPassword.getText().toString());
                    if(!identifier.equals("null")){
                        // 登入成功(登入失败什么也不做)
                        ContentValues values = new ContentValues();
                        values.put("username", editUserName.getText().toString());
                        values.put("password", editPassword.getText().toString());
                        values.put("identifier", identifier);
                        writableDatabase.update("user", values, "id = 0", null);
                        resetStatus();
                    }
                }else {
                    // 按钮表示注销
                    ContentValues values = new ContentValues();
                    values.put("username", "null");
                    values.put("password", "null");
                    values.put("identifier", "null");
                    writableDatabase.update("user", values, "id = 0", null);
                    resetStatus();
                }
            }
        });

        // 注册按钮功能
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void resetStatus(){
        dbController = new DBController(requireActivity().getApplicationContext(), "login.db", null, 1);
        writableDatabase = dbController.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from user where id == 1",null);
        while (cursor.moveToNext()){
            String username = cursor.getString(1);
            if (!username.equals("null")){
                // 已经登入
                profileViewModel.setUserName(username);
                profileViewModel.setLogin(true);
            }
        }
        cursor.close();

        if(!profileViewModel.getIsLogin()){
            // 用户没有登入
            userNameText.setText("Not Login.");
            userLoginTime.setText("Not Login.");
            loginButton.setText("LOGIN");
        }else{
            // 用户登入了
            userNameText.setText(profileViewModel.getUsername());
            Date date = new Date();
            userLoginTime.setText(date.toString());
            loginButton.setText("LOGOUT");
            registerButton.setVisibility(View.GONE);
            editUserName.setVisibility(View.GONE);
            editPassword.setVisibility(View.GONE);
        }
    }

    private String doLogin(String username, String password){
        if(username.equals("null") || username.equals("") || password.equals("")){
            Toast.makeText(getContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
            return "null";
        }



        Toast.makeText(getContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
        Log.d("",username);
        return "null";
    }


    private boolean doRegister(String Username, String Password){
        return false;
    }

}