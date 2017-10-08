/*
 * Code formatter project
 * CS 4481
 */
package ast;

import formatter.RegisterAllocator;
import formatter.SymbolTable;

/**
 *
 * @author edwajohn
 */
public class UnaryOperator implements Expression {

  private final UnaryOperatorType type;
  private final Expression expression;

  public UnaryOperator(String type, Expression expression) {
    this.type = UnaryOperatorType.fromString(type);
    this.expression = expression;
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    builder.append(type);
    expression.toCminus(builder, prefix);
  }

  @Override
  public EvalResult toMIPS(StringBuilder code, StringBuilder data,
          SymbolTable symbolTable, RegisterAllocator regAllocator) {

    EvalResult expressionResult = expression.toMIPS(code, data, symbolTable, regAllocator);

    EvalResult returnResult = EvalResult.createVoidResult();
    if (this.type.equals(UnaryOperatorType.NEG)) {
      String register = expressionResult.getRegister();
      code.append("\tneg\t").append(register).append(", ").append(register);
      code.append("\t#Negate the expression.\n");
      returnResult = EvalResult.createRegisterResult(register, VarType.INT);
    } else if (this.type.equals(UnaryOperatorType.NOT)) {
      String register = expressionResult.getRegister();
      code.append("\tnot\t").append(register).append(", ").append(register);
      code.append("\t#Logically negate the expression.\n");
      code.append("\taddi\t").append(register).append(", 2");
      code.append("\t\t#Convert boolean from 2's complement\n");
      returnResult = EvalResult.createRegisterResult(register, VarType.BOOL);
    }

    return returnResult;
  }
}
