package com.example.igorfavachoarero.exretrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtResultado;
    private Button btnOK;
    private EditText edtCep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado = (TextView) findViewById(R.id.txtResultado);
        btnOK = (Button) findViewById(R.id.btnOK);
        edtCep = (EditText) findViewById(R.id.edtCep);
        //criando o Retrofit padrão podendo passar a URL por uma constante
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.postmon.com.br/v1/cep/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //criando uma classe baseada na interface
        PostmonService service = retrofit.create(PostmonService.class);
        //chamando o método da interface o qual se gerou o objeto
        Call<Endereco> call = service.getEndereco(edtCep.getText().toString());
        //fazendo uma chamada assíncrona
        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if (response.isSuccessful()){
                    Endereco endereco = response.body();

                    String strEndereco = endereco.getCidade() +" - "+
                            endereco.getBairro() +" - "+
                            endereco.getLogradouro();
                    txtResultado.setText(strEndereco);
                }else{
                    Toast.makeText(MainActivity.this, response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
