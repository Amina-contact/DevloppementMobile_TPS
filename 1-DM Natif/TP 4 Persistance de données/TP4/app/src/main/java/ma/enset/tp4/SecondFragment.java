package ma.enset.tp4;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ma.enset.tp4.databinding.FragmentSecondBinding;
import ma.enset.tp4.model.Etudiant;
import ma.enset.tp4.model.ListeUser;
public class SecondFragment extends Fragment {
    private static final String FILE_NAME="myFile.txt";
    private FragmentSecondBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editTextNom=view.findViewById(R.id.editTextNom);
        EditText editTextPrenom=view.findViewById(R.id.editTextPrenom);
        EditText editTextAge=view.findViewById(R.id.editTextAge);
        System.out.println(editTextNom.getText().toString());
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListeUser listeUser = new ListeUser();
                try {
                    FileInputStream fis = getActivity().openFileInput(FILE_NAME);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    listeUser = (ListeUser) ois.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Etudiant etudiant = new Etudiant();
                    FileOutputStream fos = getActivity().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    ObjectOutputStream pw = new ObjectOutputStream(fos);
                    etudiant.setNom(editTextNom.getText().toString());
                    etudiant.setPrenom(editTextPrenom.getText().toString());
                    etudiant.setAge(Integer.parseInt(editTextAge.getText().toString()));
                    listeUser.getEtudiants().add(etudiant);
                    pw.writeObject(listeUser);
                    pw.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity().getApplicationContext(), "Les données ont été enregistrées !!", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}