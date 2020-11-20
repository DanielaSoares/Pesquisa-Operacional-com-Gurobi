/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio3;

/**
 *
 * @author Usuario
 */
/*Problema da rede de TV local
Variáveis de decisão:
x1 -> frequência semanal de A
x2 -> frequência semanal de B
FO: MAX Z = 3000x1+10000x2
SA: 20x1+10x2<=80 -> restrição de música
    x1+x2>=5 -> restrição de propaganda
    x1, x2 >= 0
*/
import gurobi.*;
public class Exercicio3 {
public static void main(String[]args){
    

try{
    GRBEnv env = new GRBEnv();
    GRBEnv GRBEnv;
    GRBEnv = new GRBEnv("Exercicio3");
    GRBModel model = new GRBModel(env);
//Criando variáveis
GRBVar x1 = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "x1");
GRBVar x2 = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "x2");

//Definindo o objetivo de otimização MAX: 30000X1+10000X2
GRBLinExpr expr = new GRBLinExpr();
expr.addTerm(30000.0, x1);
expr.addTerm(10000.0, x2);
model.setObjective(expr, GRB.MAXIMIZE);

//Adicionando restrições
//20x1+10x2<=80
GRBLinExpr expr1;
expr = new GRBLinExpr();
expr.addTerm(20.0, x1);
expr.addTerm(10.0, x2);
model.addConstr(expr, GRB.LESS_EQUAL, 80.0, "c0");

//x1+x2>=5
expr = new GRBLinExpr();
expr.addTerm(1.0, x1);
expr.addTerm(1.0, x2);
model.addConstr(expr, GRB.GREATER_EQUAL, 5.0, "c1");

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
