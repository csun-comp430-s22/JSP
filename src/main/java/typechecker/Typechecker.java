package typechecker;

import lexer.*;
import parser.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.util.HashMap;

public class Typechecker{
	public final Program program;
	/*public static final String BASE_FUNCTION = "Pointer";
	
	public static FunctionDefinition getPointer(final FunctName fname,
            final Map<FunctName, FunctionDefinition> pointers) throws TypeErrorException {
        if (fname.name.equals(BASE_FUNCTION)) {
            return null;
        } else {
            final FunctionDefinition fdef = pointers.get(fname);
            if (fdef == null) {
                throw new TypeErrorException("no such function pointer: " + fname);
            } else {
                return fdef;
            }
        }
    }

    public FunctionDefinition getPointer(final FunctName fname) throws TypeErrorException {
        return getPointer(fname, pointers);
    }*/
	
	public final Map<FunctName, Functiondef> functions;
	
	public Typechecker(final Program program) throws TypeErrorException {
        this.program = program;
        functions = new HashMap<FunctName, Functiondef>();
        for (final Functiondef fdef : program.functions) {
            if (!functions.containsKey(fdef.functionName)) {
                functions.put((FunctName) fdef.functionName,fdef);
            } else {
                throw new TypeErrorException("Function with duplicate name: " + fdef.functionName);
            }
        }
    }
	
	public Functiondef getFunctionByName(final FunctNameExp fname) throws TypeErrorException{
		final Functiondef fdef = functions.get(fname);
		if(fdef == null) {
			throw new TypeErrorException("No function with name: " + fname);
		}else {
			return fdef;
		}
	}
	
	public Functiondef getFunctionByType(final VarExp exp) throws TypeErrorException{
		final Functiondef fdef = functions.get(exp);
		if(fdef == null) {
			throw new TypeErrorException("No function with type: " + exp);
		}else {
			return fdef;
		}
	}
	
	public Type typeofFunctionCall(final FunctCallExp exp, 
								   final Map<Var, Type> typeEnvironment) throws TypeErrorException{
		final Functiondef fdef = (Functiondef) getFunctionByName(exp.fname);
		if(exp.params.size() != fdef.params.size()) {
			throw new TypeErrorException("Wrong number of arguments for function: " + fdef.functionName);
		}
		
		for(int index = 0; index < exp.params.size(); index++) {
			final Type recievedParamType = typeofExp((Exp) exp.params.get(index), typeEnvironment);
			final Type expectedParamType = fdef.params.get(index).type;
			
			if(!recievedParamType.equals(expectedParamType)) {
				throw new TypeErrorException("Type mismatch on function call argument");
			}
		}
		return fdef.returnType;
	}
	
	public Type typeofFunctionPointerCall(final FunctPointerCallExp exp, final Map<Var, Type> typeEnviornment) throws TypeErrorException{
		final Functiondef fdef = (Functiondef) getFunctionByType(exp.var);
		
		if(exp.params.size() != fdef.params.size()) {
			throw new TypeErrorException("Wrong number of pointers for function: " + fdef.functionName);
		}
		
		for(int index = 0; index < exp.params.size(); index++) {
			final Type recievedParamType = typeofExp((Exp)exp.params.get(index), typeEnviornment);
			final Type expectedParamType = fdef.params.get(index).type;
			
			if(!recievedParamType.equals(expectedParamType)) {
				throw new TypeErrorException("Type mismatch on function call argument");
			}
		}
		return fdef.returnType;
	}
	
	//op ::= + | - | * | / | . | < | > | && || ==
	public Type typeofOp(final OpExp exp, 
						 final Map<Var, Type> typeEnvironment) throws TypeErrorException{
		final Type leftType = typeofExp(exp.left, typeEnvironment);
		final Type rightType = typeofExp(exp.right, typeEnvironment);
		
		if(exp.op instanceof AddOp) {
			if(leftType instanceof IntType && rightType instanceof IntType) {
				return new IntType();
			}else {
				throw new TypeErrorException("Incorrect types for +");
			}
		}else if(exp.op instanceof MinusOp) {
			if(leftType instanceof IntType && rightType instanceof IntType) {
				return new IntType();
			}else {
				throw new TypeErrorException("Incorrect types for -");
			}
		}else if(exp.op instanceof MultiplicationOp) {
			if(leftType instanceof IntType && rightType instanceof IntType) {
				return new IntType();
			}else {
				throw new TypeErrorException("Incorrect types for *");
			}
		}else if(exp.op instanceof DivisionOp) {
			if(leftType instanceof IntType && rightType instanceof IntType) {
				return new IntType();
			}else {
				throw new TypeErrorException("Incorrect types for /");
			}
		}/*else if(exp.op instanceof DotOp) {
			if(leftType instanceof IntType && rightType instanceof IntType) {
				return new IntType();
			}else {
				throw new TypeErrorException("Incorrect type for field.");
			}
		}else if(exp.op instanceof PointerOp) {
			if(leftType instanceof IntType && rightType instanceof IntType) {
				return new IntType();
			}else {
				throw new TypeErrorException("Incorrect type for var*");
			}
		}
		else if(exp.op instanceof PointerLhsOp) {
			if(leftType instanceof IntType) {
				return new IntType();
			}else {
				throw new TypeErrorException("Incorrect type for");
			}
		}
		else if(exp.op instanceof PointerTypeOp) {
			if(leftType instanceof IntType) {
				return new IntType();
			}else {
				throw new TypeErrorException("Incorrect type for");
			}
		}*/
		else if(exp.op instanceof LessThanOp) {
			if(leftType instanceof IntType && rightType instanceof IntType) {
				return new BoolType();
			}else {
				throw new TypeErrorException("Incorrect types for <");
			}
		}else if(exp.op instanceof GreaterThanOp) {
			if(leftType instanceof IntType && rightType instanceof IntType) {
				return new BoolType();
			}else {
				throw new TypeErrorException("Incorrect types for >");
			}
		}else if(exp.op instanceof EqualToOp) {
			if(leftType instanceof IntType && rightType instanceof IntType) {
				return new BoolType();
			}else {
				throw new TypeErrorException("Incorrect types for ==");
			}
		} else {
			throw new TypeErrorException("Unsupported operation" + exp.op);
		}
	}
	
	//type enviornment: Identifier -> Type
	public Type typeofExp(final Exp exp, 
						  final Map<Var, Type> typeEnvironment) throws TypeErrorException{
		if(exp instanceof IntegerExp) {
			return new IntType();
		}else if(exp instanceof BooleanLiteralExp){
			return new BoolType();
		}else if(exp instanceof VarExp) {
			final Var var = ((VarExp)exp).var;
			final Type varType = typeEnvironment.get(var);
			
			if(varType == null) {
				throw new TypeErrorException("variable not in scope: " + var);
			}else {
				return varType;
			}
		}else if(exp instanceof OpExp) {
			return typeofOp((OpExp)exp, typeEnvironment);
		}else if(exp instanceof FunctCallExp) {
			return typeofFunctionCall((FunctCallExp)exp, typeEnvironment);
		}else {
			throw new TypeErrorException("Unsupported expression: " + exp);
		}
	}
	
	public static Map<Var, Type> addToMap(final Map<Var, Type> typeEnviornment,
										  final Var var,
										  final Type type){
		final Map<Var, Type> retval = new HashMap<Var, Type>();
		retval.putAll(typeEnviornment);
		retval.put(var, type);
		return retval;
	}
	
	public Map<Var, Type> typecheckVardec(final VardecStmt asDec, 
										  final Map<Var, Type> typeEnviornment,
										  final Type returnType) throws TypeErrorException{
		final Type expectedType = asDec.vardec.type;
		final Type receivedType = typeofExp(asDec.vardec.varExp, typeEnviornment);
		
		if(receivedType.equals(expectedType)) {
			return addToMap(typeEnviornment, asDec.vardec.varExp.var, expectedType);
		}else {
			throw new TypeErrorException("expected: " + expectedType + ", received: " + receivedType);
		}
	}

	public Map<Var, Type> typecheckIf(final IfStmt asIf,
            						  final Map<Var, Type> typeEnvironment,
            						  final Type returnType) throws TypeErrorException {
			final Type receivedType = typeofExp(asIf.guard, typeEnvironment);
			if (receivedType.equals(new BoolType())) {
				typecheckStmt(asIf.trueBranch, typeEnvironment, returnType);
				typecheckStmt(asIf.falseBranch, typeEnvironment, returnType);
				return typeEnvironment;
			} else {
				throw new TypeErrorException("guard should be bool; received: " + receivedType);
			}
	}
	
	public Map<Var, Type> typecheckWhile(final WhileStmt asWhile, 
										 final Map<Var, Type> typeEnvironment, 
										 final Type returnType) throws TypeErrorException{
		final Type receivedType = typeofExp(asWhile.guard, typeEnvironment);
        if (receivedType.equals(new BoolType())) {
            typecheckStmt(asWhile.body, typeEnvironment, returnType);
            return typeEnvironment;
        } else {
            throw new TypeErrorException("guard should be bool; received: " + receivedType);
        }
	}
	
	public Map<Var, Type> typecheckReturn(final ReturnStmt asReturn,
            							  final Map<Var, Type> typeEnvironment,
            							  final Type returnType) throws TypeErrorException {
			final Type receivedType = typeofExp(asReturn.exp, typeEnvironment);
			if (returnType.equals(receivedType)) {
				return typeEnvironment;
			} else {
				throw new TypeErrorException("expected return type: " + returnType + ", received: " + receivedType);
			}
	}
	
	public Map<Var, Type> typecheckBlock(final BlockStmt asBlock,
										 final Map<Var, Type> originalTypeEnviornment, 
										 final Type returnType) throws TypeErrorException{
		Map<Var, Type> typeEnviornment = originalTypeEnviornment;
		
		for(final Stmt stmt : asBlock.stmt) {
			typeEnviornment = typecheckStmt(stmt, typeEnviornment, returnType);
		}
		return originalTypeEnviornment;
	}
	
	public Map<Var, Type> typecheckPrint(final PrintStmt asPrint,
										 final Map<Var, Type> typeEnviornemnt,
										 final Type returnType) throws TypeErrorException{
		typeofExp(((PrintStmt)asPrint).exp, typeEnviornemnt);
		return typeEnviornemnt;
	}
	
	public Map<Var, Type> typecheckStmt(final Stmt stmt,
										final Map<Var, Type> typeEnviornment, 
										final Type returnType) throws TypeErrorException{
		if(stmt instanceof VardecStmt) {
			return typecheckVardec((VardecStmt)stmt, typeEnviornment, returnType);
		}else if(stmt instanceof IfStmt) {
			return typecheckIf((IfStmt)stmt, typeEnviornment, returnType);
		}else if(stmt instanceof WhileStmt) {
			return typecheckWhile((WhileStmt)stmt, typeEnviornment, returnType);
		}else if(stmt instanceof ReturnStmt) {
			return typecheckReturn((ReturnStmt)stmt, typeEnviornment, returnType);
		}else if(stmt instanceof BlockStmt) {
			return typecheckBlock((BlockStmt)stmt, typeEnviornment, returnType);
		}else if(stmt instanceof PrintStmt){
			return typecheckPrint((PrintStmt)stmt, typeEnviornment, returnType);
		}else {
			throw new TypeErrorException("Unsupported statement: " + stmt);
		}
	}
	
	//fundef ::= type fname(vardec*) stmt
	public void typecheckFunction(final Functiondef funcdef) throws TypeErrorException{
		final Map<Var, Type> typeEnviornment = new HashMap<Var, Type>();
		for(final Vardec vardec : funcdef.params) {
			if(!typeEnviornment.containsKey(vardec.varExp.var)) {
				throw new TypeErrorException("Duplicate var name: " + vardec.varExp.var);
			}else {
				typeEnviornment.put(vardec.varExp.var, vardec.type);
			}
		}
		typecheckStmt(funcdef.body, typeEnviornment, funcdef.returnType);
	}
	
	public void typecheckWholeProgram() throws TypeErrorException{
		for(final Functiondef fdef : program.functions) {
			typecheckFunction(fdef);
		}
	}

	public void typecheck() throws TypeErrorException {
		for(final Functiondef fdef : program.functions) {
			typecheckFunction((Functiondef) fdef);
		}
	}
}
