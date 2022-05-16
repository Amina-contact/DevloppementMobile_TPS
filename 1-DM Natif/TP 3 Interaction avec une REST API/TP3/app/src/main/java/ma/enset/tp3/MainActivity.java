package ma.enset.tp3;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import ma.enset.tp3.model.ListNewsAdapter;
import ma.enset.tp3.model.ListNewsResponse;
import ma.enset.tp3.model.News;
import ma.enset.tp3.service.RestServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listViewNews=findViewById(R.id.listViewNews);
        EditText editTextSearch=findViewById(R.id.editTextSearch);
        EditText editTextSearchDate=findViewById(R.id.editTextSearchDate);
        Button buttonSearch=findViewById(R.id.buttonSearch);
        String api = "cc5c8fd08e4c4c289700eae3f90d84e3";
        List<News> data=new ArrayList<>();
        //ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
        ListNewsAdapter adapter=new ListNewsAdapter(this,R.layout.list_news_item,data);
        listViewNews.setAdapter(adapter);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RestServiceAPI serviceAPI=retrofit.create(RestServiceAPI.class);
        listViewNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),data.get(i).getLogin(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),NewActivity.class);
                intent.putExtra("News",data.get(i));
                startActivity(intent);
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.clear();
                String key=editTextSearch.getText().toString();
                String date=editTextSearchDate.getText().toString();
                Call<ListNewsResponse> callNews=serviceAPI.listNewsByKey(key,date,"cc5c8fd08e4c4c289700eae3f90d84e3");
                callNews.enqueue(new Callback<ListNewsResponse>() {
                    @Override
                    public void onResponse(Call<ListNewsResponse> call, Response<ListNewsResponse> response) {
                        ListNewsResponse listNews=response.body();
                       Log.i("info",call.request().url().toString());
                        for (News newss:listNews.getNewsList()) {
                            data.add(newss);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(Call<ListNewsResponse> call, Throwable t) {
                        Log.e("error","Erreur de réseau");
                    }
                });
            }
        });
    }
}