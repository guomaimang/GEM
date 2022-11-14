package comp4342.grp15.gem.ui.trend;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import comp4342.grp15.gem.R;

public class TrendListAdapter extends BaseAdapter{
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final ArrayList<PostMeta> postMetas;

    public TrendListAdapter(Context context, ArrayList<PostMeta> postMetas){
        this.mContext = context;
        this.postMetas = postMetas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return postMetas.size();
    }

    @Override
    public Object getItem(int position) {
        return postMetas.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //如果视图为空
        if (convertView == null){
            //此处需要导入包，填写ListView的图标和标题等控件的来源，来自于layout_list_item这个布局文件
            convertView = mLayoutInflater.inflate(R.layout.trend_listview_item,null);
            //生成一个ViewHolder的对象
            holder = new ViewHolder();
            //把layout_list_item对象转移过来，以便修改和赋值
            holder.imageView = (ImageView) convertView.findViewById(R.id.IV_list_Id);
            holder.tvTitle= (TextView) convertView.findViewById(R.id.TV_listTitle_Id);
            holder.tvTime = (TextView) convertView.findViewById(R.id.TV_listTime_Id);
            holder.tvContext= (TextView) convertView.findViewById(R.id.TV_listContext_Id);
            //把这个holder传递进去
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        //给控件赋值
        PostMeta postMeta = postMetas.get(position);

        holder.tvTitle.setText("" + postMeta.getId());
        holder.tvTime.setText("2099-09-09！");
        holder.tvContext.setText(postMeta.getMessage());
        Glide.with(mContext).load("https://static-file.hjm.red/2022/11/13/add995cdf74de.png").into(holder.imageView);
        return convertView;

    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView tvTitle,tvTime,tvContext;
    }
}


