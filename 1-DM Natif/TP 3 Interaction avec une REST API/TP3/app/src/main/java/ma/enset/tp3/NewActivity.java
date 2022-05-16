package ma.enset.tp3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.net.URL;

import ma.enset.tp3.model.News;
public class NewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_profile);
        StrictMode.ThreadPolicy strictMode=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(strictMode);
        Intent intent=getIntent();
        News news=(News)intent.getSerializableExtra("News");
        TextView textView=findViewById(R.id.textViewTitleL);
        TextView textAuthor=findViewById(R.id.textViewAuthor);
        TextView  textViewDate=findViewById(R.id.textViewPublishedAt);
        ImageView imageView=findViewById(R.id.imageViewProfile);
        textView.setText(news.getTitle());
        textAuthor.setText(news.getAuthor());
        textViewDate.setText(news.getPublishedAt());

        URL url= null;
        try {
            url = new URL(news.getUrlToImage());

        Bitmap bitmap= BitmapFactory.decodeStream(url.openStream());
        imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* Runnable thread= new Runnable(){
             @Override
             public void run() {
                 try {
                     Log.i("info", news.getUrlToImage());
                 URL url=new URL(news.getUrlToImage());
                 Bitmap bitmap= BitmapFactory.decodeStream(url.openStream());
                 imageView.setImageBitmap(bitmap);

                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         };
        Thread t=new Thread(thread);
        t.start();*/
    }
}
