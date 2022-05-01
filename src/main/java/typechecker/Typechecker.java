package typechecker;

import parser.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Typechecker{
	public final Program program;
	
	public final Map<FunctionName, Funcdef> functions;
	
	public Typechecker(final Program program) throws TypeErrorException {
        this.program = program;
        functions = new HashMap<FunctionName, Funcdef>();
        for (final Funcdef fdef : program.functions) {
            if (!functions.containsKey(fdef.fname)) {
                functions.put(fdef.fname, fdef);
            } else {
                throw new TypeErrorException("Function with duplicate name: " + fdef.fname);
            }
        }
    }
	
	public Funcdef getFunctionByName(final FunctionName fname) throws TypeErrorException{
		final Funcdef fdef = functions.get(fname);
		if(fdef == null) {
			throw new TypeErrorException("No such function with name: " + fname);
		}else {
			return fdef;
		}
	}
	
	public Type typeofFunctionCall(final FunctionCallExp exp, final Map<Identifier, Type> typeEnviornment) throws TypeErrorException{
		final Funcdef fdef = getFunctionByName(exp.fname);
		if(exp.params.size() != fdef.arguments.size()) {
			throw new TypeErrorException("Wrong number of arguments for function: " + fdef.fname);
		}
		
		for(int index = 0; index < exp.params.size(); index++) {
			final Type recievedArgumentType = typeof(exp.params.get(index), typeEnviornment);
			final Type expectedArgumentType = fdef.arguments.get(index).type;
			
			if(!recievedArgumentType.equals(expectedArgumentType)) {
				throw new TypeErrorException("Type mismatch on function call argument");
			}
		}
		return fdef.returnType;
	}
	
	//op ::= + | - | * | / | . | < | > | && || ==
	public Type typeofOp(final OpExp exp, final Map<Identifier, Type> typeEnviornment) throws TypeErrorException{
		final Type leftType = typeof(exp.left, typeEnviornment);
		final Type rightType = typeof(exp.right, typeEnviornment);
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
		}else if(exp.op instanceof DotOp) {
			if(leftType instanceof IntType && rightType instanceof IntType) {
				return new IntType();
			}else {
				throw new TypeErrorException("Incorrect type for .");
			}
		}
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
		}else if(exp.op instanceof AndOp) {
			if(leftType instanceof BoolType && rightType instanceof BoolType) {
				return new BoolType();
			}else {
				throw new TypeErrorException("Incorrect types for &&");
			}
		}else if(exp.op instanceof EqualToOp) {
			if(leftType instanceof BoolType && rightType instanceof BoolType) {
				return new BoolType();
			}else {
				throw new TypeErrorException("Incorrect types for ==");
			}
		} else {
			throw new TypeErrorException("Unsupported operation" + exp.op);
		}
	}
	
	//type enviornment: Indentifier -> Type
	public Type typeof(final Exp exp, final Map<Identifier, Type> typeEnviornment) throws TypeErrorException{
		
		if(exp instanceof BooleanLiteralExp) {
			return new BoolType();
		}else if(exp instanceof VoidLiteralExp) {
			return new VoidType();
		}else if(exp instanceof StructNameLiteralExp) {
			return new StructNameType();
		}else if(exp instanceof IntegerLiteralExp) {
			return new IntType();
		}else if(exp instanceof IdentifierExp) {
			final Identifier identifier = ((IdentifierExp)exp).identifier;
			final Type identifierType = typeEnviornment.get(identifier);
			
			if(identifierType == null) {
				throw new TypeErrorException("variable not in scope: " + identifier);
			}else {
				return identifierType;
			}
		}else if(exp instanceof OpExp) {
			return typeofOp((OpExp)exp, typeEnviornment);
		}else if(exp instanceof FunctionCallExp) {
			return typeofFunctionCall((FunctionCallExp)exp, typeEnviornment);
		}else {
			throw new TypeErrorException("Unsupported expression: " + exp);
		}
	}
	
	public static Map<Identifier, Type> addToMap(final Map<Identifier, Type> typeEnviornment,
												 final Identifier key,
												 final Type value){
		final Map<Identifier, Type> retval = new HashMap<Identifier, Type>();
		retval.putAll(typeEnviornment);
		retval.put(key, value);
		return retval;
	}
	
	public Map<Identifier, Type> typecheckVardec(final VardecStmt asDec, 
												 final Map<Identifier, Type> typeEnviornment,
												 final Type returnType) throws TypeErrorException{
		final Type expectedType = asDec.vardec.type;
		final Type receivedType = typeof(asDec.exp, typeEnviornment);
		
		if(receivedType.equals(expectedType)) {
			return addToMap(typeEnviornment, asDec.vardec.variable, expectedType);
		}else {
			throw new TypeErrorException("expected: " + expectedType + ", received: " + receivedType);
		}
	}
	
	public Map<Identifier, Type> typecheckIf(final IfStmt asIf,
            final Map<Identifier, Type> typeEnvironment,
            final Type returnType) throws TypeErrorException {
			final Type receivedType = typeof(asIf.guard, typeEnvironment);
			if (receivedType.equals(new BoolType())) {

				typecheckStmt(asIf.trueBranch, typeEnvironment, returnType);
				typecheckStmt(asIf.falseBranch, typeEnvironment, returnType);
				return typeEnvironment;
			} else {
				throw new TypeErrorException("guard should be bool; received: " + receivedType);
			}
	}
	
	public Map<Identifier, Type> typecheckWhile(final WhileStmt asWhile, final Map<Identifier, Type> typeEnvironment, final Type returnType) throws TypeErrorException{
		final Type receivedType = typeof(asWhile.guard, typeEnvironment);
        if (receivedType.equals(new BoolType())) {
            typecheckStmt(asWhile.body, typeEnvironment, returnType);
            return typeEnvironment;
        } else {
            throw new TypeErrorException("guard should be bool; received: " + receivedType);
        }
	}
	
	public Map<Identifier, Type> typecheckReturn(final ReturnStmt asReturn,
            final Map<Identifier, Type> typeEnvironment,
            final Type returnType) throws TypeErrorException {
			final Type receivedType = typeof(asReturn.exp, typeEnvironment);
			if (returnType.equals(receivedType)) {
				return typeEnvironment;
			} else {
				throw new TypeErrorException("expected return type: " + returnType + ", received: " + receivedType);
			}
	}
	
	public Map<Identifier, Type> typecheckBlock(final BlockStmt asBlock,
											  final Map<Identifier, Type> originalTypeEnviornment, 
											  final Type returnType) throws TypeErrorException{
		Map<Identifier, Type> typeEnviornment = originalTypeEnviornment;
		
		for(final Stmt stmt : asBlock.body) {
			typeEnviornment = typecheckStmt(stmt, typeEnviornment, returnType);
		}
		return originalTypeEnviornment;
	}
	
	public Map<Identifier, Type> typecheckStmt(final Stmt stmt,
											   final Map<Identifier, Type> typeEnviornment, 
											   final Type returnType) throws TypeErrorException{
		if(stmt instanceof VardecStmt) {
			return typecheckVardec((VardecStmt)stmt, typeEnviornment, returnType);
		}else if(stmt instanceof IfStmt) {
			return typecheckIf((IfStmt)stmt, typeEnviornment, returnType);
		}else if(stmt instanceof WhileStmt) {
			return typecheckWhile((WhileStmt)stmt, typeEnviornment, returnType);
		}else if(stmt instanceof ReturnStmt) {
			return typecheckReturn((ReturnStmt)stmt, typeEnviornment, returnType);
		}else {
			throw new TypeErrorException("Unsoupported statement: " + stmt);
		}
	}
}	
