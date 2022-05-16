package ma.enset.tp4.model;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.text.BreakIterator;
import java.util.List;
import ma.enset.tp4.R;
public class ListEtudiantAdapter extends ArrayAdapter<Etudiant> {
    private int resource;
    public ListEtudiantAdapter(@NonNull Context context, int resource, @NonNull List<Etudiant> etudiants) {
        super(context, resource, etudiants);
        this.resource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        TextView textViewLogin = listItemView.findViewById(R.id.textViewNme);
        TextView textViewPrenom = listItemView.findViewById(R.id.textViewPrnme);
        TextView textViewAge = listItemView.findViewById(R.id.textViewAge);

        textViewLogin.setText(getItem(position).getNom());
        textViewPrenom.setText(getItem(position).getPrenom());
        textViewAge.setText((String.valueOf(getItem(position).getAge())));
        return listItemView;
}}