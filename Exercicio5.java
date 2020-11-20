/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio5;

/**
 *
 * @author Usuario
 */
/*Problema do Sapateiro
Variáveis de decisão:
x1 -> quantidade de P1
x2 -> quantidade de P2
FO: MAX Z = 120x1+150x2
SA: 2x1+4x2<=100 -> recurso de P1
    3x1+2x2<=90 -> recurso de P2
    5x1+3x2<=120 -> recurso de P3
    x1, x2 >= 0
*/
import gurobi.*;
public class Exercicio5 {
public static void main(String[]args){
    

try{
    GRBEnv env = new GRBEnv();
    GRBEnv GRBEnv;
    GRBEnv = new GRBEnv("Estagirios");
    GRBModel model = new GRBModel(env);
//Criando variáveis
GRBVar x1 = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "x1");
GRBVar x2 = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "x2");

//Definindo o objetivo de otimização MAX: 120x1+150x2
GRBLinExpr expr = new GRBLinExpr();
expr.addTerm(120.0, x1);
expr.addTerm(150.0, x2);
model.setObjective(expr, GRB.MAXIMIZE);

//Adicionando restrições
//2x1+4x2<=100
GRBLinExpr expr1;
expr = new GRBLinExpr();
expr.addTerm(2.0, x1);
expr.addTerm(4.0, x2);
model.addConstr(expr, GRB.LESS_EQUAL, 100.0, "c0");

//3x1+2x2<=90
expr = new GRBLinExpr();
expr.addTerm(3.0, x1);
expr.addTerm(2.0, x2);
model.addConstr(expr, GRB.LESS_EQUAL, 90.0, "c1");

//5x1+3x2<=120
expr = new GRBLinExpr();
expr.addTerm(5.0, x1);
expr.addTerm(3.0, x2);
model.addConstr(expr, GRB.LESS_EQUAL, 120.0, "c2");

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

