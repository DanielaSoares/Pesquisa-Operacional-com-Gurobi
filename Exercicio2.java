/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio2;

/**
 *
 * @author Usuario
 */
/*Problema dos Produtos P1 e P2
Variáveis de decisão:
x1 -> quantidade de produtos P1
x2 -> quantidade de Produtos P2
FO: MAX Z = 100x1+150x2
SA: 2x1+3x2<=120
    x1<=40
    X2<=30
    x1, x2 >= 0
*/
import gurobi.*;
public class Exercicio2 {
public static void main(String[]args){
    

try{
    GRBEnv env = new GRBEnv();
    GRBEnv GRBEnv;
    GRBEnv = new GRBEnv("Exercicio2");
    GRBModel model = new GRBModel(env);
    
//Criando variáveis
GRBVar x1 = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "x1");
GRBVar x2 = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "x2");

//Definindo o objetivo de otimização MAX: 100X1+150X2
GRBLinExpr expr = new GRBLinExpr();
expr.addTerm(100.0, x1);
expr.addTerm(150.0, x2);
model.setObjective(expr, GRB.MAXIMIZE);

//Adicionando restrições
//2x1+3x2<=120
GRBLinExpr expr1;
expr = new GRBLinExpr();
expr.addTerm(2.0, x1);
expr.addTerm(3.0, x2);
model.addConstr(expr, GRB.LESS_EQUAL, 120.0, "c0");

//x1<=40
expr = new GRBLinExpr();
expr.addTerm(1.0, x1);
model.addConstr(expr, GRB.LESS_EQUAL, 40.0, "c1");

//x2<=30
expr = new GRBLinExpr();
expr.addTerm(1.0, x2);
model.addConstr(expr, GRB.LESS_EQUAL, 30.0, "c1");

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

