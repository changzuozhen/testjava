package 设计模式_design_pattern.行为型;

import utils.LogUtils;

import java.util.StringTokenizer;

/**
 * https://cyc2018.github.io/CS-Notes/#/notes/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F?id=_3-%E8%A7%A3%E9%87%8A%E5%99%A8%EF%BC%88interpreter%EF%BC%89
 * 3. 解释器（Interpreter）
 * Intent
 * 为语言创建解释器，通常由语言的语法和语法分析来定义。
 * <p>
 * Class Diagram
 * TerminalExpression：终结符表达式，每个终结符都需要一个 TerminalExpression。
 * Context：上下文，包含解释器之外的一些全局信息。
 * <p>
 * <p>
 * Implementation
 * 以下是一个规则检验器实现，具有 and 和 or 规则，通过规则可以构建一颗解析树，用来检验一个文本是否满足解析树定义的规则。
 * <p>
 * 例如一颗解析树为 D And (A Or (B C))，文本 "D A" 满足该解析树定义的规则。
 * <p>
 * 这里的 Context 指的是 String。
 */
public class 解释器interpreter {

    /**
     * 构建解析树
     */
    public static Expression buildInterpreterTree() {
        // Literal
        Expression terminal1 = new TerminalExpression("A");
        Expression terminal2 = new TerminalExpression("B");
        Expression terminal3 = new TerminalExpression("C");
        Expression terminal4 = new TerminalExpression("D");
        // B C
        Expression alternation1 = new OrExpression(terminal2, terminal3);
        // A Or (B C)
        Expression alternation2 = new OrExpression(terminal1, alternation1);
        // D And (A Or (B C))
        return new AndExpression(terminal4, alternation2);
    }

    public static void main(String[] args) {
        Expression define = buildInterpreterTree();
        String context1 = "D A";
        String context2 = "A B";
        String context3 = "C D";
        LogUtils.d(define.interpret(context1));
        LogUtils.d(define.interpret(context2));
        LogUtils.d(define.interpret(context3));
    }
}

abstract class Expression {
    public abstract boolean interpret(String str);
}

class TerminalExpression extends Expression {

    private String literal = null;

    public TerminalExpression(String str) {
        literal = str;
    }

    public boolean interpret(String str) {
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            String test = st.nextToken();
            if (test.equals(literal)) {
                return true;
            }
        }
        return false;
    }
}

class AndExpression extends Expression {

    private Expression expression1 = null;
    private Expression expression2 = null;

    public AndExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public boolean interpret(String str) {
        return expression1.interpret(str) && expression2.interpret(str);
    }
}

class OrExpression extends Expression {
    private Expression expression1 = null;
    private Expression expression2 = null;

    public OrExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public boolean interpret(String str) {
        return expression1.interpret(str) || expression2.interpret(str);
    }
}
