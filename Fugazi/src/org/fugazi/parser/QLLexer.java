// Generated from /Users/Sugar/Documents/Msc/Software-Construction/many-ql/Fugazi/src/org/fugazi/grammar/QL.g4 by ANTLR 4.5
package org.fugazi.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		ID=25, NUMBER=26, STRING=27, BOOLEAN=28, INT=29, MONEY=30, COMMENT=31, 
		WS=32, LINE_COMMENT=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "ID", "NUMBER", 
		"STRING", "BOOLEAN", "INT", "MONEY", "COMMENT", "WS", "LINE_COMMENT", 
		"ESC", "UNICODE", "HEX", "DIGIT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'if'", "'('", "')'", "';'", "'='", "'bool'", 
		"'money'", "'int'", "'*'", "'/'", "'+'", "'-'", "'!'", "'>'", "'>='", 
		"'<'", "'<='", "'=='", "'!='", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "ID", "NUMBER", "STRING", "BOOLEAN", "INT", "MONEY", "COMMENT", 
		"WS", "LINE_COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override
	@NotNull
	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public QLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2#\u00f2\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\6\32"+
		"\u0092\n\32\r\32\16\32\u0093\3\33\3\33\5\33\u0098\n\33\3\34\3\34\3\34"+
		"\7\34\u009d\n\34\f\34\16\34\u00a0\13\34\3\34\3\34\3\35\3\35\3\36\6\36"+
		"\u00a7\n\36\r\36\16\36\u00a8\3\37\6\37\u00ac\n\37\r\37\16\37\u00ad\3\37"+
		"\3\37\7\37\u00b2\n\37\f\37\16\37\u00b5\13\37\3\37\3\37\6\37\u00b9\n\37"+
		"\r\37\16\37\u00ba\5\37\u00bd\n\37\3 \3 \3 \3 \7 \u00c3\n \f \16 \u00c6"+
		"\13 \3 \3 \3 \3 \3 \3!\6!\u00ce\n!\r!\16!\u00cf\3!\3!\3\"\3\"\3\"\3\""+
		"\7\"\u00d8\n\"\f\"\16\"\u00db\13\"\3\"\5\"\u00de\n\"\3\"\3\"\3\"\3\"\3"+
		"#\3#\3#\5#\u00e7\n#\3$\3$\3$\3$\3$\3$\3%\3%\3&\3&\3\u00c4\2\'\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E\2G\2I\2K\2\3\2\n\4\2C\\c|\4\2$$^^\b\2$$ccghnntw~~\5\2\13\f\16\17\""+
		"\"\4\2\f\f\17\17\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\3\2\62;\u00fb\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2"+
		"\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3M\3\2\2\2\5R\3\2\2\2\7"+
		"T\3\2\2\2\tV\3\2\2\2\13Y\3\2\2\2\r[\3\2\2\2\17]\3\2\2\2\21_\3\2\2\2\23"+
		"a\3\2\2\2\25f\3\2\2\2\27l\3\2\2\2\31p\3\2\2\2\33r\3\2\2\2\35t\3\2\2\2"+
		"\37v\3\2\2\2!x\3\2\2\2#z\3\2\2\2%|\3\2\2\2\'\177\3\2\2\2)\u0081\3\2\2"+
		"\2+\u0084\3\2\2\2-\u0087\3\2\2\2/\u008a\3\2\2\2\61\u008d\3\2\2\2\63\u0091"+
		"\3\2\2\2\65\u0097\3\2\2\2\67\u0099\3\2\2\29\u00a3\3\2\2\2;\u00a6\3\2\2"+
		"\2=\u00bc\3\2\2\2?\u00be\3\2\2\2A\u00cd\3\2\2\2C\u00d3\3\2\2\2E\u00e3"+
		"\3\2\2\2G\u00e8\3\2\2\2I\u00ee\3\2\2\2K\u00f0\3\2\2\2MN\7h\2\2NO\7q\2"+
		"\2OP\7t\2\2PQ\7o\2\2Q\4\3\2\2\2RS\7}\2\2S\6\3\2\2\2TU\7\177\2\2U\b\3\2"+
		"\2\2VW\7k\2\2WX\7h\2\2X\n\3\2\2\2YZ\7*\2\2Z\f\3\2\2\2[\\\7+\2\2\\\16\3"+
		"\2\2\2]^\7=\2\2^\20\3\2\2\2_`\7?\2\2`\22\3\2\2\2ab\7d\2\2bc\7q\2\2cd\7"+
		"q\2\2de\7n\2\2e\24\3\2\2\2fg\7o\2\2gh\7q\2\2hi\7p\2\2ij\7g\2\2jk\7{\2"+
		"\2k\26\3\2\2\2lm\7k\2\2mn\7p\2\2no\7v\2\2o\30\3\2\2\2pq\7,\2\2q\32\3\2"+
		"\2\2rs\7\61\2\2s\34\3\2\2\2tu\7-\2\2u\36\3\2\2\2vw\7/\2\2w \3\2\2\2xy"+
		"\7#\2\2y\"\3\2\2\2z{\7@\2\2{$\3\2\2\2|}\7@\2\2}~\7?\2\2~&\3\2\2\2\177"+
		"\u0080\7>\2\2\u0080(\3\2\2\2\u0081\u0082\7>\2\2\u0082\u0083\7?\2\2\u0083"+
		"*\3\2\2\2\u0084\u0085\7?\2\2\u0085\u0086\7?\2\2\u0086,\3\2\2\2\u0087\u0088"+
		"\7#\2\2\u0088\u0089\7?\2\2\u0089.\3\2\2\2\u008a\u008b\7(\2\2\u008b\u008c"+
		"\7(\2\2\u008c\60\3\2\2\2\u008d\u008e\7~\2\2\u008e\u008f\7~\2\2\u008f\62"+
		"\3\2\2\2\u0090\u0092\t\2\2\2\u0091\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\64\3\2\2\2\u0095\u0098\5;\36"+
		"\2\u0096\u0098\5=\37\2\u0097\u0095\3\2\2\2\u0097\u0096\3\2\2\2\u0098\66"+
		"\3\2\2\2\u0099\u009e\7$\2\2\u009a\u009d\5E#\2\u009b\u009d\n\3\2\2\u009c"+
		"\u009a\3\2\2\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2"+
		"\2\2\u009e\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1"+
		"\u00a2\7$\2\2\u00a28\3\2\2\2\u00a3\u00a4\t\4\2\2\u00a4:\3\2\2\2\u00a5"+
		"\u00a7\5K&\2\u00a6\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6\3\2\2"+
		"\2\u00a8\u00a9\3\2\2\2\u00a9<\3\2\2\2\u00aa\u00ac\5K&\2\u00ab\u00aa\3"+
		"\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b3\7\60\2\2\u00b0\u00b2\5K&\2\u00b1\u00b0\3\2"+
		"\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00bd\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b8\7\60\2\2\u00b7\u00b9\5"+
		"K&\2\u00b8\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00ab\3\2\2\2\u00bc\u00b6\3\2"+
		"\2\2\u00bd>\3\2\2\2\u00be\u00bf\7\61\2\2\u00bf\u00c0\7,\2\2\u00c0\u00c4"+
		"\3\2\2\2\u00c1\u00c3\13\2\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3\2\2\2"+
		"\u00c4\u00c5\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c4"+
		"\3\2\2\2\u00c7\u00c8\7,\2\2\u00c8\u00c9\7\61\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\u00cb\b \2\2\u00cb@\3\2\2\2\u00cc\u00ce\t\5\2\2\u00cd\u00cc\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2"+
		"\2\2\u00d1\u00d2\b!\2\2\u00d2B\3\2\2\2\u00d3\u00d4\7\61\2\2\u00d4\u00d5"+
		"\7\61\2\2\u00d5\u00d9\3\2\2\2\u00d6\u00d8\n\6\2\2\u00d7\u00d6\3\2\2\2"+
		"\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dd"+
		"\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00de\7\17\2\2\u00dd\u00dc\3\2\2\2"+
		"\u00dd\u00de\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\7\f\2\2\u00e0\u00e1"+
		"\3\2\2\2\u00e1\u00e2\b\"\2\2\u00e2D\3\2\2\2\u00e3\u00e6\7^\2\2\u00e4\u00e7"+
		"\t\7\2\2\u00e5\u00e7\5G$\2\u00e6\u00e4\3\2\2\2\u00e6\u00e5\3\2\2\2\u00e7"+
		"F\3\2\2\2\u00e8\u00e9\7w\2\2\u00e9\u00ea\5I%\2\u00ea\u00eb\5I%\2\u00eb"+
		"\u00ec\5I%\2\u00ec\u00ed\5I%\2\u00edH\3\2\2\2\u00ee\u00ef\t\b\2\2\u00ef"+
		"J\3\2\2\2\u00f0\u00f1\t\t\2\2\u00f1L\3\2\2\2\21\2\u0093\u0097\u009c\u009e"+
		"\u00a8\u00ad\u00b3\u00ba\u00bc\u00c4\u00cf\u00d9\u00dd\u00e6\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}