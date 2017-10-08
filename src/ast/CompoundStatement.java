/*
 * Code formatter project
 * CS 4481
 */
package ast;

import formatter.RegisterAllocator;
import formatter.SymbolTable;
import java.util.List;

/**
 *
 * @author edwajohn
 */
public class CompoundStatement implements Statement {

  private final List<Statement> statements;
  private final SymbolTable symbolTable;

  public CompoundStatement(List<Statement> statements, SymbolTable symbolTable) {
    this.statements = statements;
    this.symbolTable = symbolTable;
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    builder.append(prefix).append("{\n");
    for (Statement s : statements) {
      s.toCminus(builder, prefix + "  ");
    }
    builder.append(prefix).append("}\n");
  }

  @Override
  public EvalResult toMIPS(StringBuilder code, StringBuilder data,
          SymbolTable parentSymbolTable, RegisterAllocator regAllocator) {

    code.append("\taddi\t $sp, $sp, ").append(-parentSymbolTable.getARSize());
    code.append("\t#Move the stack pointer for the compound statement\n");

    for (int i = 0; i < statements.size(); i++) {
      EvalResult tempResult = statements.get(i).toMIPS(code, data, this.symbolTable, regAllocator);
      if (tempResult.getRegister() != null) {
        regAllocator.clear(tempResult.getRegister());
      }
    }

    code.append("\taddi\t $sp, $sp, ").append(parentSymbolTable.getARSize());
    code.append("\t#Reset the stack pointer when leaving the compound statement\n");

    return EvalResult.createVoidResult();
  }
}
