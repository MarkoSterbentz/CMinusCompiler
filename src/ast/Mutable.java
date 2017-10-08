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
public class Mutable implements Expression, CminusElement {

  private final String id;
  private final Expression index;

  public Mutable(String id, Expression index) {
    this.id = id;
    this.index = index;
  }

  public String getId() {
    return id;
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    builder.append(id);
    if (index != null) {
      builder.append("[");
      index.toCminus(builder, prefix);
      builder.append("]");
    }
  }

  @Override
  public EvalResult toMIPS(StringBuilder code, StringBuilder data,
          SymbolTable symbolTable, RegisterAllocator regAllocator) {
    SymbolInfo mInfo = symbolTable.find(this.id);
    String mRegister = regAllocator.getAny();
    VarType mType = mInfo.getType();

    EvalResult newResult = EvalResult.createRegisterResult(mRegister, mType);
    code.append("\tlw\t").append(mRegister).append(", ").append(mInfo.getAddressOffset()).append("($sp)");
    code.append("\t#Load the mutable from the stack to a register for use.\n");
    return newResult;
  }
}
