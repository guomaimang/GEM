package comp4342.grp15.gem.ui.trend;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TrendViewModel extends ViewModel {

    private MutableLiveData<ArrayList<PostMeta>> mPostMetas;

    public MutableLiveData<ArrayList<PostMeta>> getPostMetas(){
        if(mPostMetas == null){
            mPostMetas = new MutableLiveData<>();
        }

        // get json String from server
        String json = "[{\"id\":11, \"posterName\":\"hnss\", \"message\":\"Trip in England.\" }, { \"id\":8 , \"posterName\":\"guomaimang\", \"message\":\"A rainy day...\" }," +
                "{ \"id\":3, \"posterName\":\"hongshu\", \"message\":\"This is my new Pet!\" }]";

        Type listType = new TypeToken<List<PostMeta>>(){}.getType();
        Gson gson = new Gson();
        List<PostMeta> lPostMetas = gson.fromJson(json, listType);
        ArrayList<PostMeta> aPostMetas = new ArrayList<>(lPostMetas);

        // 先将内容更新到 mPostMetas，再返回 PostMetas
        mPostMetas.setValue(aPostMetas);
        return mPostMetas;
    }


}