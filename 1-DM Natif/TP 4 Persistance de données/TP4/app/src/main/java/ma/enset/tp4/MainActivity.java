package ma.enset.tp4;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import ma.enset.tp4.databinding.ActivityMainBinding;
import ma.enset.tp4.model.Etudiant;
import ma.enset.tp4.model.ListEtudiantAdapter;
import ma.enset.tp4.model.ListeUser;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME="myFile.txt";
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    List<Etudiant> names=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            ListeUser listeUser=new ListeUser();
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fis=openFileInput(FILE_NAME);
                    ObjectInputStream ois =new ObjectInputStream(fis);
                    listeUser=(ListeUser) ois.readObject();
                    System.out.println(listeUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ListView listView= findViewById(R.id.listeViews);

                ListEtudiantAdapter adapter=new ListEtudiantAdapter(MainActivity.this,R.layout.list_user_item,names);
                listView.setAdapter(adapter);
                System.out.println(listeUser);
                names.clear();
                for(int i=0;i<listeUser.getEtudiants().size();i++)
                    names.add(listeUser.getEtudiants().get(i));
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}