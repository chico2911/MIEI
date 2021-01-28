import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            SelRALexer lexer = new SelRALexer(CharStreams.fromFileName("testes.txt"));
            CommonTokenStream stream = new CommonTokenStream(lexer);
            SelRAParser parser = new SelRAParser(stream);
            parser.selRA();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
