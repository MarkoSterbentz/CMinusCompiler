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
public class Return implements Statement {

  private final Expression expr;

  public Return(Expression expr) {
    this.expr = expr;
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    builder.append(prefix);
    if (expr == null) {
      builder.append("return;\n");
    } else {
      builder.append("return ");
      expr.toCminus(builder, prefix);
      builder.append(";\n");
    }
  }

  @Override
  public EvalResult toMIPS(StringBuilder code, StringBuilder data,
          SymbolTable symbolTable, RegisterAllocator regAllocator) {

    if (this.expr != null) {
      String rRegister = this.expr.toMIPS(code, data, symbolTable, regAllocator).getRegister();

      code.append("\taddi\t$sp, ").append(symbolTable.getReturnStackPointerOffset());
      code.append("\t\t#Reset the offset of the stack pointer\n");

      code.append("\tsw\t").append(rRegister).append(", ").append(-4).append("($sp)");
      code.append("\t#Store the return result on the stack\n");

      regAllocator.clear(rRegister);
    }

    symbolTable.restoreRegisters(code);

    code.append("\tjr\t$ra").append("\t\t#Return to caller\n");

    return EvalResult.createVoidResult();
  }
}
