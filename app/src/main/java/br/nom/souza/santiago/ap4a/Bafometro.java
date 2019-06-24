package br.nom.souza.santiago.ap4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Bafometro extends AppCompatActivity {
    Intent it;
    protected Bundle parametros;

    protected float peso;
    protected boolean sexo;
    protected int nCopos;
    protected boolean jejum;

    protected float tAlcoolemia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bafometro);
    }
    protected void enviarDados(View v){

        boolean enviar = true;
        String dado;
        parametros = new Bundle();
        EditText etPeso;
        EditText etSexo;
        EditText etNcopos;
        EditText etJejum;
        etPeso = (EditText) findViewById(R.id.etPeso);
        etSexo = (EditText) findViewById(R.id.etSexo);
        etNcopos = (EditText) findViewById(R.id.etNcopos);
        etJejum = (EditText) findViewById(R.id.etJejum);

        Log.i("Bafometro",getLocalClassName()+" Coletando dados tela");

        dado = etPeso.getText().toString();
        if(dado.equals("")){
           enviar = false;
            Log.i("Bafometro",getLocalClassName()+" Dado invalido"+" peso");

        }else{
            peso = Float.parseFloat(dado);
        }

        if((etSexo.getText().toString().equals("m")) || (etSexo.getText().toString().equals("M"))) {
            sexo = true;
        } else{
            if((etSexo.getText().toString().equals("f")) || (etSexo.getText().toString().equals("F"))){
                sexo = false;
            } else{
                enviar = false;
                Log.i("Bafometro",getLocalClassName()+" Dado invalido"+" sexo");
            }
        }
        dado = etNcopos.getText().toString();
        if(dado.equals("")){
            enviar = false;
            Log.i("Bafometro",getLocalClassName()+" Dado invalido"+" nCopos");
        }else {
            nCopos = Integer.parseInt(dado);
        }
        if((etJejum.getText().toString().equals("s")) || (etJejum.getText().toString().equals("S"))){
            jejum = true;
        }else{
            if((etJejum.getText().toString().equals("n")) || (etJejum.getText().toString().equals("N"))){
                jejum = false;
            }else{
                enviar = false;
                Log.i("Bafometro",getLocalClassName()+" Dado invalido"+" Jejum");
            }
        }

            if (enviar) {
                Log.i("Bafometro", getLocalClassName() + " Dado validos");
                parametros.putFloat("peso", peso);
                parametros.putBoolean("sexo", sexo);
                parametros.putInt("nCopos", nCopos);
                parametros.putBoolean("jejum", jejum);

                it = new Intent(getBaseContext(), Tela2.class);
                it.putExtras(parametros);
                startActivityForResult(it,10);
            } else {
                Toast.makeText(this, "Verifique os Dados", Toast.LENGTH_LONG).show();
            }

        }
        protected void onActivityResult(int codigoRequisicao, int codigoResultado, Intent it){
            if(it == null){
                Toast.makeText(this, "Dados não Retornados", Toast.LENGTH_LONG).show();
                return;
            }else{
                if(codigoRequisicao == 10){
                    if(codigoResultado == 1) {
                        tAlcoolemia = it.getFloatExtra("tAlcoolemia",10);
                        if (tAlcoolemia > 0.09) {
                            Toast.makeText(this, "Taxa de Alcoolemia: " + tAlcoolemia + " \n Classificação: Pessoa Alcoolizada", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this, "Taxa de Alcoolemia: " + tAlcoolemia + " \n Classificação: Pessoa Não Alcoolizada", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }

        }

}
