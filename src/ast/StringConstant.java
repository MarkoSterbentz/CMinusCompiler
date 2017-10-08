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
public class StringConstant implements Expression {

  private final String value;

  public StringConstant(String value) {
    this.value = value.substring(1, value.length() - 1);
  }

  public void toCminus(StringBuilder builder, final String prefix) {
    builder.append("\"").append(value).append("\"");
  }

  @Override
  public EvalResult toMIPS(StringBuilder code, StringBuilder data,
          SymbolTable symbolTable, RegisterAllocator regAllocator) {
    String newLabel = SymbolTable.createUniqueLabel();
    data.append(newLabel).append(":\t").append(".asciiz\t").append((char) 34).append(this.value).append((char) 34).append("\n");
    return EvalResult.createAddressResult(newLabel, VarType.STRING);
  }
}
