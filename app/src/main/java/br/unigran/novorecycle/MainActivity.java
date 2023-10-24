package br.unigran.novorecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView nome;
    private TextView telefone;
    
    private Button salvar;
    List<Pessoa> dados;


    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        nome = findViewById(R.id.idNome);
        telefone = findViewById(R.id.idTelefone);
        salvar = findViewById(R.id.idSalvar);
        dados = new ArrayList<>();
        recyclerView=findViewById(R.id.idRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ler();
    }

    public void salvar(View view) {
        Pessoa p = new Pessoa();
        p.setNome(nome.getText().toString());
        p.setTelefone(telefone.getText().toString());
        DatabaseReference pessoa = databaseReference.child("pessoa");
        pessoa.push().setValue(p);
        Toast.makeText(getApplicationContext(), "Salvo", Toast.LENGTH_SHORT).show();
        limparcampos();
    }

    public void ler() {
        DatabaseReference pessoa = databaseReference.child("pessoa");
        pessoa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dados.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    Pessoa pessoa = item.getValue(Pessoa.class);
                    dados.add(pessoa);
                }
                recyclerView.setAdapter(new MeuAdapter(dados));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(), "ERRO de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void limparcampos() {
        nome.setText("");
        telefone.setText("");
    }
}