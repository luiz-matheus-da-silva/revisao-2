package com.example.luisaugustoalmeidamenegazzo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private EditText editNomeProd, editCodProd, editPreco, editQtdEstoque, editCategoria;

    private ProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNomeProd = findViewById(R.id.editNomeProd);
        editCodProd = findViewById(R.id.editCodProd);
        editPreco = findViewById(R.id.editPreco);
        editQtdEstoque = findViewById(R.id.editQtdEstoque);
        editCategoria = findViewById(R.id.editCategoria);
        Button btnCadastrar = findViewById(R.id.buttonCadastrar);
        Button btnVisualizar = findViewById(R.id.buttonVisualizar);

        ProductDatabase db = Room.databaseBuilder(getApplicationContext(),
                ProductDatabase.class, "product-database").allowMainThreadQueries().build();
        productDao = db.productDao();

        btnCadastrar.setOnClickListener(v -> {
            Log.d("MainActivity", "Botão Cadastrar Produto clicado!");

            String nomeProd = editNomeProd.getText().toString();
            String codProd = editCodProd.getText().toString();
            String preco = editPreco.getText().toString();
            String qtdEstoque = editQtdEstoque.getText().toString();
            String nomeCategoria = editCategoria.getText().toString();

            Log.d("MainActivity", "Nome: " + nomeProd + ", Código: " + codProd + ", Preço: " + preco + ", Quantidade: " + qtdEstoque);

            if (!nomeProd.isEmpty() && !codProd.isEmpty() && !preco.isEmpty() && !qtdEstoque.isEmpty()) {
                Double doublePreco = Double.parseDouble(preco);
                Integer intQtdEstoque = Integer.parseInt(qtdEstoque);

                Product product = new Product(nomeProd, codProd, doublePreco, intQtdEstoque, nomeCategoria);
                productDao.insert(product);

                Log.d("MainActivity", "Produto inserido no banco de dados.");

                Toast.makeText(MainActivity.this, "Produto cadastrado!", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("MainActivity", "Erro: Campos obrigatórios vazios!");

                Toast.makeText(MainActivity.this, "Preencha os campos obrigatórios!", Toast.LENGTH_SHORT).show();
            }
        });

        btnVisualizar.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ListarProdutos.class))
        );
    }
}