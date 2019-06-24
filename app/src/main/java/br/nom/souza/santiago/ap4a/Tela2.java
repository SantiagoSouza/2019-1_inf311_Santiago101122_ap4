package br.nom.souza.santiago.ap4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Tela2 extends AppCompatActivity {
    private static float GRAMAS_ALCOOL_CERVEJA = (float)4.8;
    private static float COEFICIENTE_MASCULINO = (float)0.7;
    private static float COEFICIENTE_FEMININO = (float)0.6;
    private static float COEFICIENTE_ALIMENTADO = (float)1.1;

    protected Bundle parametros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        Intent it = getIntent();
        parametros = it.getExtras();
    }
    protected void calcular(View v){
       Float a =  calcularClassificarAlcoolemia(parametros.getFloat("peso"),parametros.getBoolean("sexo"), parametros.getInt("nCopos"), parametros.getBoolean("jejum"));
        Intent itR = new Intent();
        itR.putExtra("tAlcoolemia",a);
        setResult(1,itR);
        finish();
    }

    /**
     *
     * @param peso
     * @param sexo true == masculino e false == feminino
     * @param ncopos
     * @param jejum true == Em jejum e false == Esta alimentado
     * @return
     */
    private float calcularClassificarAlcoolemia(float peso, boolean sexo, int ncopos, boolean jejum){
        float coeficiente;
        if(jejum){
            if(sexo){
                coeficiente = COEFICIENTE_MASCULINO;
            }else{
                coeficiente = COEFICIENTE_FEMININO;
            }
        }else{
            coeficiente = COEFICIENTE_ALIMENTADO;
        }


        float indece = (GRAMAS_ALCOOL_CERVEJA * (float)ncopos )/(peso * coeficiente);
        return indece;
    }
}
