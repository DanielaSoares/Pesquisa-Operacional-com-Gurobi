/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio4;

/**
 *
 * @author Usuario
 */
/*Problema dos cintos de couro
Variáveis de decisão:
x1 -> quantidade de M1/dia
x2 -> quantidade de M2/dia
FO: MAX Z = 4x1+3x2
SA: 2x1+x2<=1000 -> volume de produção
    x1+x2<=800 -> capacidade máxima de produção
    x1<=400 -> disponibilidade diária de fivela para M1
    x2<=700 -> disponibilidade diária de fivela para M2
    x1, x2 >= 0
*/
import gurobi.*;
public class Exercicio4 {
public static void main(String[]args){
    

try{
    GRBEnv env = new GRBEnv();
    GRBEnv GRBEnv;
    GRBEnv = new GRBEnv("Exercicio4");
    GRBModel model = new GRBModel(env);
//Criando variáveis
GRBVar x1 = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "x1");
GRBVar x2 = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "x2");

//Definindo o objetivo de otimização MAX: 4X1+3X2
GRBLinExpr expr = new GRBLinExpr();
expr.addTerm(4.0, x1);
expr.addTerm(3.0, x2);
model.setObjective(expr, GRB.MAXIMIZE);

//Adicionando restrições
//2x1+x2<=1000
GRBLinExpr expr1;
expr = new GRBLinExpr();
expr.addTerm(2.0, x1);
expr.addTerm(1.0, x2);
model.addConstr(expr, GRB.LESS_EQUAL, 1000.0, "c0");

//x1+x2<=800
expr = new GRBLinExpr();
expr.addTerm(1.0, x1);
expr.addTerm(1.0, x2);
model.addConstr(expr, GRB.LESS_EQUAL, 800.0, "c1");

//x1<=400
expr = new GRBLinExpr();
expr.addTerm(1.0, x1);
model.addConstr(expr, GRB.LESS_EQUAL, 400.0, "c2");

//x2<=700
expr = new GRBLinExpr();
expr.addTerm(1.0, x1);
model.addConstr(expr, GRB.LESS_EQUAL, 700.0, "c3");

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

