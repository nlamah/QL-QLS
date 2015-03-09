import com.kls.ast.ASTGenerator;
import com.kls.ast.node.StylesheetNode;
import com.kls.parser.KLSLexer;
import com.kls.parser.KLSParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;

/**
 * Created by Timon on 02.03.2015.
 */
public class KLSMain {

    public static void main(String[] args) {
        System.out.println("KLS 0.0.1-alpha");
        File file = new File("stylesheet.kls");
        InputStream is;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException fnf){
            System.out.println("File not found!");
            return;
        }
        ANTLRInputStream input;
        try {
            input = new ANTLRInputStream(is);
        } catch (IOException io){
            System.out.println("Error while creating the ANTLR input stream.");
            return;
        }

        KLSLexer lexer = new KLSLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KLSParser parser = new KLSParser(tokens);
        ParseTree tree = parser.stylesheet();

        ASTGenerator astGenerator = new ASTGenerator(file.toString());
        StylesheetNode ast = (StylesheetNode) astGenerator.visit(tree);
    }
}