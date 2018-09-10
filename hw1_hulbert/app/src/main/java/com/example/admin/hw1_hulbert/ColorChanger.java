package com.example.admin.hw1_hulbert;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ColorChanger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_changer);

        Button btColor = findViewById(R.id.button);
        btColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int nRandom1Color = (int)((Math.random()*((255-0)+1))+0);
                int nRandom2Color = (int)((Math.random()*((255-0)+1))+0);
                int nRandom3Color = (int)((Math.random()*((255-0)+1))+0);

                EditText txTextToEdit = findViewById(R.id.editText);
                TextView ColorData = findViewById((R.id.ColorData));
                txTextToEdit.setTextColor(Color.rgb(nRandom1Color,nRandom2Color,nRandom3Color));

                //so what you will do is take the number divide by 16 and if the number is > 9 put it in switch else append
                String nRemainder = "";
                int[] nColorArray = {nRandom1Color, nRandom2Color, nRandom3Color};
                int i = 0;
                while(i < nColorArray.length) {
                    if (nColorArray[i] > 9) {
                        switch (nColorArray[i]) {
                            case 10:
                                nRemainder += "A";
                                i++;
                                break;
                            case 11:
                                nRemainder += "B";
                                i++;
                                break;
                            case 12:
                                nRemainder += "C";
                                i++;
                                break;
                            case 13:
                                nRemainder += "D";
                                i++;
                                break;
                            case 14:
                                nRemainder += "E";
                                i++;
                                break;
                            case 15:
                                nRemainder += "F";
                                i++;
                                break;
                            default:
                                nColorArray[i] /= 16;
                                break;
                        }
                    }
                    else
                    {
                        nRemainder += Integer.toString(nColorArray[i]);
                        i++;
                    }
                }

                ColorData.setText("Color: " + nRandom1Color + " r" + nRandom2Color + " g," + nRandom3Color + " b" + "\n #"+nRemainder);
            }
        });
    }
}
