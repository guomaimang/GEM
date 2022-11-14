package comp4342.grp15.gem.ui.trend;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import comp4342.grp15.gem.R;

public class TrendListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public TrendListAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
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
        holder.tvTitle.setText("这是标题！");
        holder.tvTime.setText("2099-09-09！");
        holder.tvContext.setText("显示内容！");
        Glide.with(mContext).load("https://static-file.hjm.red/2022/11/13/add995cdf74de.png").into(holder.imageView);
        return convertView;

    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView tvTitle,tvTime,tvContext;
    }
}


