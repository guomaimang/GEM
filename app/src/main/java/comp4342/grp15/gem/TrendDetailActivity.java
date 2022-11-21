package comp4342.grp15.gem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class TrendDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend_detail);
        TextView posterName = (TextView) findViewById(R.id.trend_detail_poster);
        TextView location = (TextView) findViewById(R.id.trend_detail_location);
        TextView post_time =(TextView) findViewById(R.id.trend_detail_post_time);
        TextView message = (TextView)findViewById(R.id.trend_detail_message);
        ImageView image = (ImageView) findViewById(R.id.trend_detail_image);

        location.setText("Location: " + getIntent().getStringExtra("location"));
        Glide.with(getBaseContext()).load("https://comp4342.hjm.red/public/picture/" + getIntent().getStringExtra("pic_name")).into(image);
        post_time.setText("Post time: " + getIntent().getStringExtra("post_time"));
        message.setText("Message: " + getIntent().getStringExtra("message"));
        posterName.setText("Poster: " + getIntent().getStringExtra("username"));
    }

    public void onBackPressed() {
        this.finish();//左滑退出activity
    }

}