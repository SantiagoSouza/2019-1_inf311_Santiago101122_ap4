package br.nom.souza.santiago.ap4a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Tela2 extends AppCompatActivity {
    private static float GRAMAS_ALCOOL_CERVEJA = (float)4.8;
    private static float COEFICIENTE_MASCULINO = (float)0.7;
    private static float COEFICIENTE_FEMININO = (float)0.6;
    private static float COEFICIENTE_ALIMENTADO = (float)1.1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
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
        float coeficiente = 0;
        if(jejum){
            if(sexo){
                coeficiente = COEFICIENTE_MASCULINO;
            }else{
                coeficiente = COEFICIENTE_FEMININO;
            }
        }else{
            coeficiente = COEFICIENTE_ALIMENTADO;
        }
        float indece = (GRAMAS_ALCOOL_CERVEJA * ncopos)/(peso * coeficiente);

        return indece;
    }
}
