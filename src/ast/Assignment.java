/*
 * Code formatter project
 * CS 4481
 */
package ast;

import formatter.RegisterAllocator;
import formatter.SymbolInfo;
import formatter.SymbolTable;

/**
 *
 * @author edwajohn
 */
public class Assignment implements Expression, CminusElement {

  private final Mutable mutable;
  private final AssignmentType type;
  private final Expression rhs;

  public Assignment(Mutable mutable, String assign, Expression rhs) {
    this.mutable = mutable;
    this.type = AssignmentType.fromString(assign);
    this.rhs = rhs;
  }

  public void toCminus(StringBuilder builder, final String prefix) {
    mutable.toCminus(builder, prefix);
    if (rhs != null) {
      builder.append(" ").append(type.toString()).append(" ");
      rhs.toCminus(builder, prefix);
    } else {
      builder.append(type.toString());

    }
  }

  @Override
  public EvalResult toMIPS(StringBuilder code, StringBuilder data,
          SymbolTable symbolTable, RegisterAllocator regAllocator) {

    SymbolInfo mutableInfo = symbolTable.find(this.mutable.getId());

    EvalResult mutableResult = this.mutable.toMIPS(code, data, symbolTable, regAllocator);

    if (this.rhs == null) {
      switch (this.type) {
        case INC:
          code.append("\taddi\t").append(mutableResult.getRegister()).append(", ").append("1");
          code.append("\t#Increment the mutable\n");
          break;
        case DEC:
          code.append("\taddi\t").append(mutableResult.getRegister()).append(", ").append("-1");
          code.append("\t#Increment the mutable\n");
          break;
        default:
          break;
      }
    } else {
      EvalResult rhsEvalResult = this.rhs.toMIPS(code, data, symbolTable, regAllocator);
      switch (this.type) {
        case EQUALS:
          code.append("\tmove\t").append(mutableResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
          code.append("\t#Store rhs into mutable\n");
          break;
        case PLUS:
          code.append("\tadd\t").append(mutableResult.getRegister()).append(", ").append(mutableResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
          code.append("\t#Add rhs to the mutable\n");
          break;
        case MINUS:
          code.append("\tsub\t").append(mutableResult.getRegister()).append(", ").append(mutableResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
          code.append("\t#Subtract rhs from the mutable\n");
          break;
        case TIMES:
          code.append("\tmul\t").append(mutableResult.getRegister()).append(", ").append(mutableResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
          code.append("\t#Multiply mutable by rhs\n");
          break;
        case DIVIDE:
          code.append("\tdiv\t").append(mutableResult.getRegister()).append(", ").append(mutableResult.getRegister()).append(", ").append(rhsEvalResult.getRegister());
          code.append("\t#Divide mutable by rhs\n");
          break;
        default:
          break;
      }
      regAllocator.clear(rhsEvalResult.getRegister());
    }

    code.append("\tsw\t").append(mutableResult.getRegister()).append(", ").append(mutableInfo.getAddressOffset()).append("($sp)");
    code.append("\t#Store the mutable value on the stack.\n");

    regAllocator.clear(mutableResult.getRegister());

    return EvalResult.createVoidResult();
  }

}
