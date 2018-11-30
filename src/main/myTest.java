import ast.VisitorImpl;
import ast.node.Program;
import org.antlr.v4.runtime.*;
import java.io.IOException;

public class myTest {
    public static void main(String[] args) throws IOException {
        CharStream reader = CharStreams.fromFileName(args[0]);
        testLexer lexer = new testLexer(reader);   // SmoolaLexer in your project
        CommonTokenStream tokens = new CommonTokenStream(SmoolaLexer);
        testParser parser = new testParser(tokens);   // SmoolaParser in your project
        Program p = parser.program().p; // program is the name of the start rule

        VisitorImpl v = new VisitorImpl();
        p.accept(v);
    }
}
