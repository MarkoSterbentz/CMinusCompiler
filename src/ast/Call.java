/*
 * Code formatter project
 * CS 4481
 */
package ast;

import formatter.RegisterAllocator;
import formatter.SymbolInfo;
import formatter.SymbolTable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edwajohn
 */
public class Call implements Expression {

  private final String id;
  private final List<Expression> args;

  public Call(String id, List<Expression> args) {
    this.id = id;
    this.args = new ArrayList<>(args);
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    builder.append(id).append("(");
    for (Expression arg : args) {
      arg.toCminus(builder, prefix);
      builder.append(", ");
    }
    if (!args.isEmpty()) {
      builder.setLength(builder.length() - 2);
    }
    builder.append(")");
  }

  @Override
  public EvalResult toMIPS(StringBuilder code, StringBuilder data,
          SymbolTable symbolTable, RegisterAllocator regAllocator) {
    if (this.id.equals("println")) {
      if (args.isEmpty()) {
        println(null, code, regAllocator);
        return EvalResult.createVoidResult();
      } else {
        println(args.get(0).toMIPS(code, data, symbolTable, regAllocator), code, regAllocator);
        return EvalResult.createVoidResult();
      }
    } else {
      // Figure out which registers will be used for the call
      List<String> resultRegisters = new ArrayList();
      for (int i = 0; i < args.size(); i++) {
        EvalResult tempResult = args.get(i).toMIPS(code, data, symbolTable, regAllocator);
        resultRegisters.add(tempResult.getRegister());
      }

      // Store data from the registers onto the stack
      int parameterOffset = -8;
      for (int i = 0; i < resultRegisters.size(); i++) {
        code.append("\tsw\t").append(resultRegisters.get(i)).append(", ").append(parameterOffset).append("($sp)");
        code.append("\t#Push parameter onto the stack\n");
        parameterOffset -= 4;
      }

      // Code for calling this function
      code.append("\tjal\tfun_").append(this.id).append("\t\t#Call the function.\n");

      // Reading the return value
      String returnRegister = regAllocator.getAny();
      code.append("\tlw\t").append(returnRegister).append(", ").append(-4).append("($sp)");
      code.append("\t#Return the value of the function call\n");
      return EvalResult.createRegisterResult(returnRegister, VarType.INT);
    }
  }

  private void println(EvalResult eResult, StringBuilder code, RegisterAllocator regAllocator) {
    if (eResult != null) {
      if (VarType.STRING.equals(eResult.getType())) {
        code.append("\tla\t$a0, ").append(eResult.getAddress()).append("\t#Load the string's address in data.\n");
        code.append("\tli\t$v0, 4\t\t#Print string\n");
      } else {
        code.append("\tmove\t$a0, ").append(eResult.getRegister()).append("\t#Load value to be printed into $a0\n");
        if (VarType.BOOL.equals(eResult.getType()) || VarType.INT.equals(eResult.getType())) {
          code.append("\tli\t$v0, 1\t\t#Print boolean/integer\n");
        } else if (VarType.CHAR.equals(eResult.getType())) {
          code.append("\tli\t$v0, 11\t\t#Print char\n");
        }
        regAllocator.clear(eResult.getRegister());
      }
      code.append("\tsyscall\n");
    }

    code.append("\tla\t$a0, newline\n");
    code.append("\tli\t$v0, 4\t\t#$Print newline\n");
    code.append("\tsyscall\n");
  }
}
