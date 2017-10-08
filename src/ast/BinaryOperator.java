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
public class BinaryOperator implements Expression {

  private final Expression lhs, rhs;
  private final BinaryOperatorType type;

  public BinaryOperator(Expression lhs, BinaryOperatorType type, Expression rhs) {
    this.lhs = lhs;
    this.type = type;
    this.rhs = rhs;
  }

  public BinaryOperator(Expression lhs, String type, Expression rhs) {
    this.lhs = lhs;
    this.type = BinaryOperatorType.fromString(type);
    this.rhs = rhs;
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    lhs.toCminus(builder, prefix);
    builder.append(" ").append(type).append(" ");
    rhs.toCminus(builder, prefix);
  }

  @Override
  public EvalResult toMIPS(
          StringBuilder code, StringBuilder data,
          SymbolTable symbolTable, RegisterAllocator regAllocator) {
    // CURRENTLY ONLY DOES ARITHMETIC OPERATIONS
    // store the results of the expressions in registers
    EvalResult lhsEvalResult = lhs.toMIPS(code, data, symbolTable, regAllocator);
    EvalResult rhsEvalResult = rhs.toMIPS(code, data, symbolTable, regAllocator);
    String resultRegister = regAllocator.getAny();
    switch (this.type) {
      case PLUS:
        code.append("\tadd\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      case MINUS:
        code.append("\tsub\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      case TIMES:
        code.append("\tmul\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      case DIVIDE:
        code.append("\tdiv\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      case MOD:
        code.append("\trem\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      case OR:
        code.append("\tor\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      case AND:
        code.append("\tand\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      case LE:
        code.append("\tsle\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      case LT:
        code.append("\tslt\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      case GT:
        code.append("\tsgt\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      case GE:
        code.append("\tsge\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      case EQ:
        code.append("\tseq\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      case NE:
        code.append("\tsne\t").append(resultRegister).append(", ").append(lhsEvalResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
        break;
      default:
        break;
    }
    code.append("\t#Evaluate binary operation\n");

    // deallocate the registers
    regAllocator.clear(lhsEvalResult.getRegister());
    regAllocator.clear(rhsEvalResult.getRegister());

    return EvalResult.createRegisterResult(resultRegister, VarType.INT);
  }
}
