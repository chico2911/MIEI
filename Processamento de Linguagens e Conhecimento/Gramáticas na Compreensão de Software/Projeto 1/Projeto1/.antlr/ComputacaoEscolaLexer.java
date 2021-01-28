// Generated from c:\Users\Gonçalo Pinto\Documents\@Goncalo\Universidade\4ºANO\1Semestre\PLC_Gramáticas na Compreensão de Software\TPCs e Trabalhos Práticos\Projeto1\ComputacaoEscola.g4 by ANTLR 4.8

   import java.util.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ComputacaoEscolaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AO=1, IDR=2, IDA=3, IDC=4, NUM=5, ENSINAR=6, ENSINA=7, HIFEN=8, PONTOVIRGULA=9, 
		VIRGULA=10, PONTO=11, LPAR=12, RPAR=13, PONTOINTERROGACAO=14, STR=15, 
		WS=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"AO", "IDR", "IDA", "IDC", "NUM", "ENSINAR", "ENSINA", "HIFEN", "PONTOVIRGULA", 
			"VIRGULA", "PONTO", "LPAR", "RPAR", "PONTOINTERROGACAO", "STR", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "'-'", "';'", "','", 
			"'.'", "'['", "']'", "'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AO", "IDR", "IDA", "IDC", "NUM", "ENSINAR", "ENSINA", "HIFEN", 
			"PONTOVIRGULA", "VIRGULA", "PONTO", "LPAR", "RPAR", "PONTOINTERROGACAO", 
			"STR", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	    class Aluno{
	        String id;
	        int idade;
	        ArrayList<String> carateristicas;
	    }
	    class Recurso{
	        String id;
	        String descricao;
	        int idade;
	        ArrayList<String> carateristicas;
	    }
	    class Conceito{
	        String id;
	        String descricao;
	    }
	    class Ensinamento{
	        String idRecurso;
	        String idConceito;
	    }


	public ComputacaoEscolaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ComputacaoEscola.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22n\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\3\3\3\6\3)\n\3\r\3\16\3*\3\4\3\4\6\4/\n\4\r\4\16\4\60\3\5\3\5\6\5"+
		"\65\n\5\r\5\16\5\66\3\6\6\6:\n\6\r\6\16\6;\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\20\3\20\7\20]\n\20\f\20\16\20`\13\20\3\20\3"+
		"\20\3\21\5\21e\n\21\3\21\3\21\6\21i\n\21\r\21\16\21j\3\21\3\21\2\2\22"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22\3\2\r\4\2CCcc\4\2QQqq\3\2\62;\6\2CCIIRR~~\4\2GGgg\4\2PPpp\4"+
		"\2UUuu\4\2KKkk\4\2TTtt\3\2$$\4\2\13\13\"\"\2u\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\3#\3\2\2\2\5&\3\2\2\2\7,\3\2\2"+
		"\2\t\62\3\2\2\2\139\3\2\2\2\r=\3\2\2\2\17E\3\2\2\2\21L\3\2\2\2\23N\3\2"+
		"\2\2\25P\3\2\2\2\27R\3\2\2\2\31T\3\2\2\2\33V\3\2\2\2\35X\3\2\2\2\37Z\3"+
		"\2\2\2!h\3\2\2\2#$\t\2\2\2$%\t\3\2\2%\4\3\2\2\2&(\7T\2\2\')\t\4\2\2(\'"+
		"\3\2\2\2)*\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\6\3\2\2\2,.\t\5\2\2-/\t\4\2\2"+
		".-\3\2\2\2/\60\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\b\3\2\2\2\62\64\7"+
		"E\2\2\63\65\t\4\2\2\64\63\3\2\2\2\65\66\3\2\2\2\66\64\3\2\2\2\66\67\3"+
		"\2\2\2\67\n\3\2\2\28:\t\4\2\298\3\2\2\2:;\3\2\2\2;9\3\2\2\2;<\3\2\2\2"+
		"<\f\3\2\2\2=>\t\6\2\2>?\t\7\2\2?@\t\b\2\2@A\t\t\2\2AB\t\7\2\2BC\t\2\2"+
		"\2CD\t\n\2\2D\16\3\2\2\2EF\t\6\2\2FG\t\7\2\2GH\t\b\2\2HI\t\t\2\2IJ\t\7"+
		"\2\2JK\t\2\2\2K\20\3\2\2\2LM\7/\2\2M\22\3\2\2\2NO\7=\2\2O\24\3\2\2\2P"+
		"Q\7.\2\2Q\26\3\2\2\2RS\7\60\2\2S\30\3\2\2\2TU\7]\2\2U\32\3\2\2\2VW\7_"+
		"\2\2W\34\3\2\2\2XY\7A\2\2Y\36\3\2\2\2Z^\7$\2\2[]\n\13\2\2\\[\3\2\2\2]"+
		"`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_a\3\2\2\2`^\3\2\2\2ab\7$\2\2b \3\2\2\2"+
		"ce\7\17\2\2dc\3\2\2\2de\3\2\2\2ef\3\2\2\2fi\7\f\2\2gi\t\f\2\2hd\3\2\2"+
		"\2hg\3\2\2\2ij\3\2\2\2jh\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\b\21\2\2m\"\3\2"+
		"\2\2\13\2*\60\66;^dhj\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}