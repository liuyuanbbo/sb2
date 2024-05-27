// Generated from D:/worksp/ideaProjects/sb2/micro-srv-c/src/main/java/zmz/antlr4/Hello.g4 by ANTLR 4.13.1
package zmz.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(HelloParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(HelloParser.ExprContext ctx);
}