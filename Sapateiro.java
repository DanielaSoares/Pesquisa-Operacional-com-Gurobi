/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sapateiro;

/**
 *
 * @author Usuario
 */

/*
Problema do Sapateiro
Variáveis de decisão:
x1 -> quantidade de sapatos produzidos
x2 -> quantidade de cintos produzidos
FO: MAX Z = 5x1+2x2
SA: 10x1+12x2<=60
    2x1+1x2<=6
    x1, x2 >= 0
*/
import gurobi.*;
public class Sapateiro {
public static void main(String[]args){
    

try{
    GRBEnv env = new GRBEnv();
    GRBEnv GRBEnv;
    GRBEnv = new GRBEnv("Sapateiro");
    GRBModel model = new GRBModel(env);
//Criando variáveis
GRBVar x1 = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "x1");
GRBVar x2 = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "x2");

//Definindo o objetivo de otimização MAX: 5X1+2X2
GRBLinExpr expr = new GRBLinExpr();
expr.addTerm(5.0, x1);
expr.addTerm(2.0, x2);
model.setObjective(expr, GRB.MAXIMIZE);

//Adicionando restrições
//10X1+12X2<=60
GRBLinExpr expr1;
expr = new GRBLinExpr();
expr.addTerm(10.0, x1);
expr.addTerm(12.0, x2);
model.addConstr(expr, GRB.LESS_EQUAL, 60.0, "c0");

//2x1+x2<=6
expr = new GRBLinExpr();
expr.addTerm(2.0, x1);
expr.addTerm(1.0, x2);
model.addConstr(expr, GRB.LESS_EQUAL, 6.0, "c1");

//Otimizando o modelo
model.optimize();
System.out.println(x1.get(GRB.StringAttr.VarName) +"="+x1.get(GRB.DoubleAttr.X));
System.out.println(x2.get(GRB.StringAttr.VarName) +"="+x2.get(GRB.DoubleAttr.X));

System.out.println("Objetivo:"+model.get(GRB.DoubleAttr.ObjVal));

//Limpando
model.dispose();
env.dispose();
} catch(GRBException e){
    System.out.println("Erro no codigo:"+e.getErrorCode()+"."+e.getMessage());
}


        }
}
