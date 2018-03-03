package nreinas;
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.ArrayList;

/**
 * @author Salvador Vera Franco 
 *  NRainas class 1.0
 */
public class NReinas {
    private final int[][] tablero ;
    public ArrayList<String> listaResultados=new ArrayList<>();
   
    public NReinas(int N) {
        tablero =new int[N][N];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j]=0;
            }
        }
    }
    
    public void backtracking(int etapa){
        for (int i = 0; i < tablero.length; i++) {
            if(isSolucion(i,etapa)){
                tablero[i][etapa]=1;
                if(etapa== tablero.length-1){
                    guardarResultado();
                }else{
                    backtracking(etapa+1);
                 }   
                tablero[i][etapa]=0;
            }
        }
    }
    
    public boolean isSolucion(int i,int etapa){
        //Filas
        for (int j = 0; j <=i; j++){ 
            if(tablero[j][etapa]==1)
                return false;
        }    
        //Columnas
        for (int j = 0; j <= etapa; j++){ 
            if(tablero[i][j]==1)
                return false;
        }    
        //Diagonal IZQ
        int d=i;
        for (int j = etapa; j >= 0 && d >=0; j--) {
            if(tablero[d][j]==1)
                return false;
            d--;
        }    
        //Diagonal DER
        d=i;
        for (int j = etapa; j >= 0 && d<tablero.length; j--){ 
            if(tablero[d][j]==1)
                return false;
            d++;
        }
        return true;
    }
    
    public void printResultados(){
        listaResultados.forEach((a)->{
            System.out.println(a+"\n");
        });
        System.out.println("Numero de Soluciones: "+listaResultados.size());
    }

    public static void main(String[] args) {
        NReinas nR=new NReinas(14);
        nR.backtracking(0);
        nR.printResultados();
    }

    private void guardarResultado() {
        String s="";
        for (int i = 0; i < tablero.length; i++) {
            
            for (int j = 0; j < tablero.length; j++) {
                if(tablero[i][j]==1){
                    if(i==tablero.length-1)
                        s+=i+","+j;
                    else 
                        s+=i+","+j+" ";
                }    
            }
        }
        listaResultados.add(s);
    }
    
}
