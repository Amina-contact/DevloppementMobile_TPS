package ma.enset.tp3.model;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import ma.enset.tp3.R;
public class ListNewsAdapter extends ArrayAdapter<News> {
    private int resource;
    public ListNewsAdapter(@NonNull Context context, int resource, @NonNull List<News> news) {
        super(context, resource, news);
        this.resource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(resource,parent,false);
        }
        TextView textViewTitle=listItemView.findViewById(R.id.textViewTitle);
        TextView textViewBublishedAt=listItemView.findViewById(R.id.textViewBublishedAt);
        TextView textViewAuthor=listItemView.findViewById(R.id.textViewAuthor);
        ImageView imageView=listItemView.findViewById(R.id.imageView);
        textViewBublishedAt.setText(getItem(position).getPublishedAt());
        textViewTitle.setText(getItem(position).getTitle());
        textViewAuthor.setText(getItem(position).getAuthor());

        Runnable thread= new Runnable(){
            @Override
            public void run() {
                try {
                    Log.i("info",getItem(position).getUrlToImage());
                    URL url=new URL(getItem(position).getUrlToImage());
                    Bitmap bitmap= BitmapFactory.decodeStream(url.openStream());
                    imageView.setImageBitmap(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t=new Thread(thread);
        t.start();
        return listItemView;
    }
}
