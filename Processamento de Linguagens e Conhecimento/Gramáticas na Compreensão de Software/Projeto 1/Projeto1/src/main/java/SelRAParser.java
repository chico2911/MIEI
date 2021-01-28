// Generated from C:/Users/Gon�alo Pinto/Documents/@Goncalo/Universidade/4�ANO/1Semestre/PLC_Gram�ticas na Compreens�o de Software/TPCs e Trabalhos Pr�ticos/Projeto1\SelRA.g4 by ANTLR 4.9

    import java.io.*;
    import java.util.*;
    import java.text.*;
    import java.util.stream.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SelRAParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AO=1, IDR=2, IDA=3, IDC=4, NUM=5, ENSINAR=6, ENSINA=7, HIFEN=8, PONTOVIRGULA=9, 
		VIRGULA=10, PONTO=11, LPAR=12, RPAR=13, PONTOINTERROGACAO=14, DOISPONTOS=15, 
		STR=16, WS=17;
	public static final int
		RULE_selRA = 0, RULE_dicionariocaracteristicas = 1, RULE_linhacaracteristica = 2, 
		RULE_caracteristicaprincipal = 3, RULE_sinonimos = 4, RULE_sinonimo = 5, 
		RULE_desc = 6, RULE_recursos = 7, RULE_recurso = 8, RULE_idrecurso = 9, 
		RULE_intervaloidade = 10, RULE_idademin = 11, RULE_idademax = 12, RULE_idade = 13, 
		RULE_carateristicas = 14, RULE_caracteristica = 15, RULE_alunos = 16, 
		RULE_aluno = 17, RULE_idaluno = 18, RULE_conceitos = 19, RULE_conceito = 20, 
		RULE_idconceito = 21, RULE_ensinamentos = 22, RULE_ensinamento = 23, RULE_questoes = 24, 
		RULE_questao = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"selRA", "dicionariocaracteristicas", "linhacaracteristica", "caracteristicaprincipal", 
			"sinonimos", "sinonimo", "desc", "recursos", "recurso", "idrecurso", 
			"intervaloidade", "idademin", "idademax", "idade", "carateristicas", 
			"caracteristica", "alunos", "aluno", "idaluno", "conceitos", "conceito", 
			"idconceito", "ensinamentos", "ensinamento", "questoes", "questao"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "'-'", "';'", "','", 
			"'.'", "'['", "']'", "'?'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AO", "IDR", "IDA", "IDC", "NUM", "ENSINAR", "ENSINA", "HIFEN", 
			"PONTOVIRGULA", "VIRGULA", "PONTO", "LPAR", "RPAR", "PONTOINTERROGACAO", 
			"DOISPONTOS", "STR", "WS"
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

	@Override
	public String getGrammarFileName() { return "SelRA.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    public File myFile;

	    class Aluno{
	        String id;
	        int idade;
	        ArrayList<String> carateristicas;
	    }

	    class Recurso{
	        String id;
	        String descricao;
	        int idademin;
	        int idademax;
	        ArrayList<String> carateristicas;

	        public String toString() {
	            return "Recurso " + id + ", descricao= " + descricao + ", idade ideal=[" + idademin + "," + idademax + "], carateristicas= " + carateristicas + ";";
	        }
	    }

	    class Conceito{
	        String id;
	        String descricao;
	    }

	    class Ensinamento{
	        String idRecurso;
	        String idConceito;
	    }

	    public void criaFicheiro(){
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	        Date d = new Date();
	        try{
	            String namefile = dateFormat.format(d) + ".html";
	            myFile = new File(namefile);
	            if (myFile.createNewFile()) {
	                System.out.println("File created: " + myFile.getName() + "\n");
	            } else {
	                System.out.println("File already exists.\n");
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void criaEstrutura(){
	        String s = "<!DOCTYPE html> \n <html> \n <head> \n <title>SelRA</title> \n <meta charset=\"utf-8\" /> \n </head> \n <body> \n";
	        try{
	            FileWriter fr = new FileWriter(myFile, true);
	            fr.write(s);
	            fr.close();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void escreveFicheiro(File f, String s){
	        try{
	            FileWriter fr = new FileWriter(f, true);
	            fr.write(s);
	            fr.close();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void fechaEstrutura(){
	        String s = "</body> \n </html> \n";
	        try{
	            FileWriter fr = new FileWriter(myFile, true);
	            fr.write(s);
	            fr.close();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	public SelRAParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SelRAContext extends ParserRuleContext {
		public DicionariocaracteristicasContext dicionariocaracteristicas;
		public RecursosContext recursos;
		public AlunosContext alunos;
		public ConceitosContext conceitos;
		public EnsinamentosContext ensinamentos;
		public DicionariocaracteristicasContext dicionariocaracteristicas() {
			return getRuleContext(DicionariocaracteristicasContext.class,0);
		}
		public List<TerminalNode> HIFEN() { return getTokens(SelRAParser.HIFEN); }
		public TerminalNode HIFEN(int i) {
			return getToken(SelRAParser.HIFEN, i);
		}
		public RecursosContext recursos() {
			return getRuleContext(RecursosContext.class,0);
		}
		public AlunosContext alunos() {
			return getRuleContext(AlunosContext.class,0);
		}
		public ConceitosContext conceitos() {
			return getRuleContext(ConceitosContext.class,0);
		}
		public EnsinamentosContext ensinamentos() {
			return getRuleContext(EnsinamentosContext.class,0);
		}
		public QuestoesContext questoes() {
			return getRuleContext(QuestoesContext.class,0);
		}
		public TerminalNode PONTO() { return getToken(SelRAParser.PONTO, 0); }
		public SelRAContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selRA; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterSelRA(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitSelRA(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitSelRA(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelRAContext selRA() throws RecognitionException {
		SelRAContext _localctx = new SelRAContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_selRA);
		 criaFicheiro(); criaEstrutura(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			((SelRAContext)_localctx).dicionariocaracteristicas = dicionariocaracteristicas(myFile);
			setState(53);
			match(HIFEN);
			setState(54);
			((SelRAContext)_localctx).recursos = recursos(myFile);
			setState(55);
			match(HIFEN);
			setState(56);
			((SelRAContext)_localctx).alunos = alunos(myFile);
			setState(57);
			match(HIFEN);
			setState(58);
			((SelRAContext)_localctx).conceitos = conceitos(myFile);
			setState(59);
			match(HIFEN);
			setState(60);
			((SelRAContext)_localctx).ensinamentos = ensinamentos(myFile, ((SelRAContext)_localctx).recursos.mapRecursos, ((SelRAContext)_localctx).conceitos.mapConceitos);
			setState(61);
			match(HIFEN);
			setState(62);
			questoes(myFile, ((SelRAContext)_localctx).recursos.mapRecursos, ((SelRAContext)_localctx).alunos.mapAlunos, ((SelRAContext)_localctx).conceitos.mapConceitos, ((SelRAContext)_localctx).ensinamentos.listEnsinamentos, ((SelRAContext)_localctx).dicionariocaracteristicas.mapCaracteristicas);
			setState(63);
			match(PONTO);
			   fechaEstrutura();
			            try {
			                java.awt.Desktop.getDesktop().browse(myFile.toURI());
			            }
			            catch (IOException e) {
			                e.printStackTrace();
			            }
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DicionariocaracteristicasContext extends ParserRuleContext {
		public File f;
		public HashMap<String,ArrayList<String>> mapCaracteristicas;
		public LinhacaracteristicaContext lc1;
		public LinhacaracteristicaContext lc2;
		public List<LinhacaracteristicaContext> linhacaracteristica() {
			return getRuleContexts(LinhacaracteristicaContext.class);
		}
		public LinhacaracteristicaContext linhacaracteristica(int i) {
			return getRuleContext(LinhacaracteristicaContext.class,i);
		}
		public List<TerminalNode> PONTOVIRGULA() { return getTokens(SelRAParser.PONTOVIRGULA); }
		public TerminalNode PONTOVIRGULA(int i) {
			return getToken(SelRAParser.PONTOVIRGULA, i);
		}
		public DicionariocaracteristicasContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public DicionariocaracteristicasContext(ParserRuleContext parent, int invokingState, File f) {
			super(parent, invokingState);
			this.f = f;
		}
		@Override public int getRuleIndex() { return RULE_dicionariocaracteristicas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterDicionariocaracteristicas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitDicionariocaracteristicas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitDicionariocaracteristicas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DicionariocaracteristicasContext dicionariocaracteristicas(File f) throws RecognitionException {
		DicionariocaracteristicasContext _localctx = new DicionariocaracteristicasContext(_ctx, getState(), f);
		enterRule(_localctx, 2, RULE_dicionariocaracteristicas);
		 ((DicionariocaracteristicasContext)_localctx).mapCaracteristicas =  new HashMap<String,ArrayList<String>>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			((DicionariocaracteristicasContext)_localctx).lc1 = linhacaracteristica(_localctx.f, _localctx.mapCaracteristicas);
			 ((DicionariocaracteristicasContext)_localctx).mapCaracteristicas =  ((DicionariocaracteristicasContext)_localctx).lc1.mapCaracteristicasOut; 
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTOVIRGULA) {
				{
				{
				setState(68);
				match(PONTOVIRGULA);
				setState(69);
				((DicionariocaracteristicasContext)_localctx).lc2 = linhacaracteristica(_localctx.f, _localctx.mapCaracteristicas);
				 ((DicionariocaracteristicasContext)_localctx).mapCaracteristicas =  ((DicionariocaracteristicasContext)_localctx).lc2.mapCaracteristicasOut; 
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LinhacaracteristicaContext extends ParserRuleContext {
		public File f;
		public HashMap<String,ArrayList<String>> mapCaracteristicasIn;
		public HashMap<String,ArrayList<String>> mapCaracteristicasOut;
		public CaracteristicaprincipalContext caracteristicaprincipal;
		public SinonimosContext sinonimos;
		public CaracteristicaprincipalContext caracteristicaprincipal() {
			return getRuleContext(CaracteristicaprincipalContext.class,0);
		}
		public TerminalNode DOISPONTOS() { return getToken(SelRAParser.DOISPONTOS, 0); }
		public TerminalNode LPAR() { return getToken(SelRAParser.LPAR, 0); }
		public SinonimosContext sinonimos() {
			return getRuleContext(SinonimosContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(SelRAParser.RPAR, 0); }
		public LinhacaracteristicaContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public LinhacaracteristicaContext(ParserRuleContext parent, int invokingState, File f, HashMap<String,ArrayList<String>> mapCaracteristicasIn) {
			super(parent, invokingState);
			this.f = f;
			this.mapCaracteristicasIn = mapCaracteristicasIn;
		}
		@Override public int getRuleIndex() { return RULE_linhacaracteristica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterLinhacaracteristica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitLinhacaracteristica(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitLinhacaracteristica(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinhacaracteristicaContext linhacaracteristica(File f,HashMap<String,ArrayList<String>> mapCaracteristicasIn) throws RecognitionException {
		LinhacaracteristicaContext _localctx = new LinhacaracteristicaContext(_ctx, getState(), f, mapCaracteristicasIn);
		enterRule(_localctx, 4, RULE_linhacaracteristica);
		 ((LinhacaracteristicaContext)_localctx).mapCaracteristicasOut =  new HashMap<String,ArrayList<String>>(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			((LinhacaracteristicaContext)_localctx).caracteristicaprincipal = caracteristicaprincipal();
			setState(78);
			match(DOISPONTOS);
			setState(79);
			match(LPAR);
			setState(80);
			((LinhacaracteristicaContext)_localctx).sinonimos = sinonimos(_localctx.f);
			setState(81);
			match(RPAR);
			   if(!(_localctx.mapCaracteristicasIn.containsKey((((LinhacaracteristicaContext)_localctx).caracteristicaprincipal!=null?_input.getText(((LinhacaracteristicaContext)_localctx).caracteristicaprincipal.start,((LinhacaracteristicaContext)_localctx).caracteristicaprincipal.stop):null)))){
			                                                            if(!(((LinhacaracteristicaContext)_localctx).sinonimos.lista.contains((((LinhacaracteristicaContext)_localctx).caracteristicaprincipal!=null?_input.getText(((LinhacaracteristicaContext)_localctx).caracteristicaprincipal.start,((LinhacaracteristicaContext)_localctx).caracteristicaprincipal.stop):null)))){
			                                                                ((LinhacaracteristicaContext)_localctx).sinonimos.lista.add(((((LinhacaracteristicaContext)_localctx).caracteristicaprincipal!=null?_input.getText(((LinhacaracteristicaContext)_localctx).caracteristicaprincipal.start,((LinhacaracteristicaContext)_localctx).caracteristicaprincipal.stop):null)).toLowerCase());
			                                                            }
			                                                            _localctx.mapCaracteristicasIn.put(((((LinhacaracteristicaContext)_localctx).caracteristicaprincipal!=null?_input.getText(((LinhacaracteristicaContext)_localctx).caracteristicaprincipal.start,((LinhacaracteristicaContext)_localctx).caracteristicaprincipal.stop):null)).toLowerCase(),((LinhacaracteristicaContext)_localctx).sinonimos.lista);
			                                                        }
			                                                        else{
			                                                            String s = "<p> ERRO: Característica " + (((LinhacaracteristicaContext)_localctx).caracteristicaprincipal!=null?_input.getText(((LinhacaracteristicaContext)_localctx).caracteristicaprincipal.start,((LinhacaracteristicaContext)_localctx).caracteristicaprincipal.stop):null) + " já referida... </p>\n" ;
			                                                            escreveFicheiro(_localctx.f,s);
			                                                        }
			                                                        ((LinhacaracteristicaContext)_localctx).mapCaracteristicasOut =  _localctx.mapCaracteristicasIn;
			                                                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaracteristicaprincipalContext extends ParserRuleContext {
		public TerminalNode STR() { return getToken(SelRAParser.STR, 0); }
		public CaracteristicaprincipalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caracteristicaprincipal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterCaracteristicaprincipal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitCaracteristicaprincipal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitCaracteristicaprincipal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaracteristicaprincipalContext caracteristicaprincipal() throws RecognitionException {
		CaracteristicaprincipalContext _localctx = new CaracteristicaprincipalContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_caracteristicaprincipal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(STR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SinonimosContext extends ParserRuleContext {
		public File f;
		public ArrayList<String> lista;
		public SinonimoContext s1;
		public SinonimoContext s2;
		public List<SinonimoContext> sinonimo() {
			return getRuleContexts(SinonimoContext.class);
		}
		public SinonimoContext sinonimo(int i) {
			return getRuleContext(SinonimoContext.class,i);
		}
		public List<TerminalNode> VIRGULA() { return getTokens(SelRAParser.VIRGULA); }
		public TerminalNode VIRGULA(int i) {
			return getToken(SelRAParser.VIRGULA, i);
		}
		public SinonimosContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public SinonimosContext(ParserRuleContext parent, int invokingState, File f) {
			super(parent, invokingState);
			this.f = f;
		}
		@Override public int getRuleIndex() { return RULE_sinonimos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterSinonimos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitSinonimos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitSinonimos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SinonimosContext sinonimos(File f) throws RecognitionException {
		SinonimosContext _localctx = new SinonimosContext(_ctx, getState(), f);
		enterRule(_localctx, 8, RULE_sinonimos);
		 ((SinonimosContext)_localctx).lista =  new ArrayList<String>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			((SinonimosContext)_localctx).s1 = sinonimo(_localctx.f, _localctx.lista);
			 ((SinonimosContext)_localctx).lista =  ((SinonimosContext)_localctx).s1.listaOut; 
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRGULA) {
				{
				{
				setState(88);
				match(VIRGULA);
				setState(89);
				((SinonimosContext)_localctx).s2 = sinonimo(_localctx.f, _localctx.lista);
				 ((SinonimosContext)_localctx).lista =  ((SinonimosContext)_localctx).s2.listaOut; 
				}
				}
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SinonimoContext extends ParserRuleContext {
		public File f;
		public ArrayList<String> listaIn;
		public ArrayList<String> listaOut;
		public DescContext desc;
		public DescContext desc() {
			return getRuleContext(DescContext.class,0);
		}
		public SinonimoContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public SinonimoContext(ParserRuleContext parent, int invokingState, File f, ArrayList<String> listaIn) {
			super(parent, invokingState);
			this.f = f;
			this.listaIn = listaIn;
		}
		@Override public int getRuleIndex() { return RULE_sinonimo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterSinonimo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitSinonimo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitSinonimo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SinonimoContext sinonimo(File f,ArrayList<String> listaIn) throws RecognitionException {
		SinonimoContext _localctx = new SinonimoContext(_ctx, getState(), f, listaIn);
		enterRule(_localctx, 10, RULE_sinonimo);
		 ((SinonimoContext)_localctx).listaOut =  new ArrayList<String>(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			((SinonimoContext)_localctx).desc = desc();
			   if(!(_localctx.listaIn.contains((((SinonimoContext)_localctx).desc!=null?_input.getText(((SinonimoContext)_localctx).desc.start,((SinonimoContext)_localctx).desc.stop):null)))){
			                            _localctx.listaIn.add(((((SinonimoContext)_localctx).desc!=null?_input.getText(((SinonimoContext)_localctx).desc.start,((SinonimoContext)_localctx).desc.stop):null)).toLowerCase());
			                        }
			                        else{
			                            String s = "<p> ERRO: Característica " + (((SinonimoContext)_localctx).desc!=null?_input.getText(((SinonimoContext)_localctx).desc.start,((SinonimoContext)_localctx).desc.stop):null) + " já referida... </p>\n";
			                            escreveFicheiro(_localctx.f,s);
			                        }
			                        ((SinonimoContext)_localctx).listaOut =  _localctx.listaIn;
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DescContext extends ParserRuleContext {
		public TerminalNode STR() { return getToken(SelRAParser.STR, 0); }
		public DescContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_desc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterDesc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitDesc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitDesc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescContext desc() throws RecognitionException {
		DescContext _localctx = new DescContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_desc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(STR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecursosContext extends ParserRuleContext {
		public File f;
		public HashMap<String,Recurso> mapRecursos;
		public RecursoContext r1;
		public RecursoContext r2;
		public List<RecursoContext> recurso() {
			return getRuleContexts(RecursoContext.class);
		}
		public RecursoContext recurso(int i) {
			return getRuleContext(RecursoContext.class,i);
		}
		public List<TerminalNode> PONTOVIRGULA() { return getTokens(SelRAParser.PONTOVIRGULA); }
		public TerminalNode PONTOVIRGULA(int i) {
			return getToken(SelRAParser.PONTOVIRGULA, i);
		}
		public RecursosContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public RecursosContext(ParserRuleContext parent, int invokingState, File f) {
			super(parent, invokingState);
			this.f = f;
		}
		@Override public int getRuleIndex() { return RULE_recursos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterRecursos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitRecursos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitRecursos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecursosContext recursos(File f) throws RecognitionException {
		RecursosContext _localctx = new RecursosContext(_ctx, getState(), f);
		enterRule(_localctx, 14, RULE_recursos);
		 ((RecursosContext)_localctx).mapRecursos =  new HashMap<String,Recurso>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			((RecursosContext)_localctx).r1 = recurso(_localctx.f, _localctx.mapRecursos);
			 ((RecursosContext)_localctx).mapRecursos =  ((RecursosContext)_localctx).r1.mapRecursosOut; 
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTOVIRGULA) {
				{
				{
				setState(104);
				match(PONTOVIRGULA);
				setState(105);
				((RecursosContext)_localctx).r2 = recurso(_localctx.f, _localctx.mapRecursos);
				 ((RecursosContext)_localctx).mapRecursos =  ((RecursosContext)_localctx).r2.mapRecursosOut; 
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecursoContext extends ParserRuleContext {
		public File f;
		public HashMap<String,Recurso> mapRecursosIn;
		public HashMap<String,Recurso> mapRecursosOut;
		public IdrecursoContext idrecurso;
		public DescContext desc;
		public IntervaloidadeContext intervaloidade;
		public CarateristicasContext c;
		public IdrecursoContext idrecurso() {
			return getRuleContext(IdrecursoContext.class,0);
		}
		public DescContext desc() {
			return getRuleContext(DescContext.class,0);
		}
		public IntervaloidadeContext intervaloidade() {
			return getRuleContext(IntervaloidadeContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(SelRAParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SelRAParser.RPAR, 0); }
		public CarateristicasContext carateristicas() {
			return getRuleContext(CarateristicasContext.class,0);
		}
		public RecursoContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public RecursoContext(ParserRuleContext parent, int invokingState, File f, HashMap<String,Recurso> mapRecursosIn) {
			super(parent, invokingState);
			this.f = f;
			this.mapRecursosIn = mapRecursosIn;
		}
		@Override public int getRuleIndex() { return RULE_recurso; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterRecurso(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitRecurso(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitRecurso(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecursoContext recurso(File f,HashMap<String,Recurso> mapRecursosIn) throws RecognitionException {
		RecursoContext _localctx = new RecursoContext(_ctx, getState(), f, mapRecursosIn);
		enterRule(_localctx, 16, RULE_recurso);
		 ((RecursoContext)_localctx).mapRecursosOut =  new HashMap<String,Recurso>(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			((RecursoContext)_localctx).idrecurso = idrecurso();
			setState(114);
			((RecursoContext)_localctx).desc = desc();
			setState(115);
			((RecursoContext)_localctx).intervaloidade = intervaloidade(_localctx.f,(((RecursoContext)_localctx).idrecurso!=null?_input.getText(((RecursoContext)_localctx).idrecurso.start,((RecursoContext)_localctx).idrecurso.stop):null));
			setState(116);
			match(LPAR);
			setState(117);
			((RecursoContext)_localctx).c = carateristicas(_localctx.f);
			setState(118);
			match(RPAR);
			    Recurso r = new Recurso();
			                                                                                            r.id = (((RecursoContext)_localctx).idrecurso!=null?_input.getText(((RecursoContext)_localctx).idrecurso.start,((RecursoContext)_localctx).idrecurso.stop):null);
			                                                                                            r.descricao = (((RecursoContext)_localctx).desc!=null?_input.getText(((RecursoContext)_localctx).desc.start,((RecursoContext)_localctx).desc.stop):null);
			                                                                                            r.idademin = ((RecursoContext)_localctx).intervaloidade.min;
			                                                                                            r.idademax = ((RecursoContext)_localctx).intervaloidade.max;
			                                                                                            r.carateristicas = ((RecursoContext)_localctx).c.listCarateristicas;
			                                                                                            if(!(_localctx.mapRecursosIn.containsKey((((RecursoContext)_localctx).idrecurso!=null?_input.getText(((RecursoContext)_localctx).idrecurso.start,((RecursoContext)_localctx).idrecurso.stop):null)))){
			                                                                                                _localctx.mapRecursosIn.put((((RecursoContext)_localctx).idrecurso!=null?_input.getText(((RecursoContext)_localctx).idrecurso.start,((RecursoContext)_localctx).idrecurso.stop):null),r);
			                                                                                            }
			                                                                                            else{
			                                                                                               String s = "<p>ERRO: Recurso " + (((RecursoContext)_localctx).idrecurso!=null?_input.getText(((RecursoContext)_localctx).idrecurso.start,((RecursoContext)_localctx).idrecurso.stop):null) + " já referido...</p>\n" ;
			                                                                                               escreveFicheiro(_localctx.f,s);
			                                                                                            }
			                                                                                            ((RecursoContext)_localctx).mapRecursosOut =  _localctx.mapRecursosIn;
			                                                                                        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdrecursoContext extends ParserRuleContext {
		public TerminalNode IDR() { return getToken(SelRAParser.IDR, 0); }
		public IdrecursoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idrecurso; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterIdrecurso(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitIdrecurso(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitIdrecurso(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdrecursoContext idrecurso() throws RecognitionException {
		IdrecursoContext _localctx = new IdrecursoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_idrecurso);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(IDR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntervaloidadeContext extends ParserRuleContext {
		public File f;
		public String id;
		public int max;
		public int min;
		public IdademinContext idademin;
		public IdademaxContext idademax;
		public TerminalNode LPAR() { return getToken(SelRAParser.LPAR, 0); }
		public IdademinContext idademin() {
			return getRuleContext(IdademinContext.class,0);
		}
		public TerminalNode HIFEN() { return getToken(SelRAParser.HIFEN, 0); }
		public IdademaxContext idademax() {
			return getRuleContext(IdademaxContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(SelRAParser.RPAR, 0); }
		public IntervaloidadeContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public IntervaloidadeContext(ParserRuleContext parent, int invokingState, File f, String id) {
			super(parent, invokingState);
			this.f = f;
			this.id = id;
		}
		@Override public int getRuleIndex() { return RULE_intervaloidade; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterIntervaloidade(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitIntervaloidade(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitIntervaloidade(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntervaloidadeContext intervaloidade(File f,String id) throws RecognitionException {
		IntervaloidadeContext _localctx = new IntervaloidadeContext(_ctx, getState(), f, id);
		enterRule(_localctx, 20, RULE_intervaloidade);
		 ((IntervaloidadeContext)_localctx).max = 0; ((IntervaloidadeContext)_localctx).min = 0; 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(LPAR);
			setState(124);
			((IntervaloidadeContext)_localctx).idademin = idademin();
			setState(125);
			match(HIFEN);
			setState(126);
			((IntervaloidadeContext)_localctx).idademax = idademax();
			setState(127);
			match(RPAR);
			   if(((IntervaloidadeContext)_localctx).idademax.max>=((IntervaloidadeContext)_localctx).idademin.min) {
			                                                        ((IntervaloidadeContext)_localctx).max = ((IntervaloidadeContext)_localctx).idademax.max;
			                                                        ((IntervaloidadeContext)_localctx).min = ((IntervaloidadeContext)_localctx).idademin.min;
			                                                    }
			                                                    else{
			                                                        String s = "<p>ERRO: Intervalo de idades incorrecto no recurso " + _localctx.id + "</p>\n";
			                                                        escreveFicheiro(_localctx.f,s);
			                                                    }
			                                                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdademinContext extends ParserRuleContext {
		public int min;
		public IdadeContext idade;
		public IdadeContext idade() {
			return getRuleContext(IdadeContext.class,0);
		}
		public IdademinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idademin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterIdademin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitIdademin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitIdademin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdademinContext idademin() throws RecognitionException {
		IdademinContext _localctx = new IdademinContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_idademin);
		 ((IdademinContext)_localctx).min =  0; 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			((IdademinContext)_localctx).idade = idade();
			 ((IdademinContext)_localctx).min =  Integer.parseInt((((IdademinContext)_localctx).idade!=null?_input.getText(((IdademinContext)_localctx).idade.start,((IdademinContext)_localctx).idade.stop):null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdademaxContext extends ParserRuleContext {
		public int max;
		public IdadeContext idade;
		public IdadeContext idade() {
			return getRuleContext(IdadeContext.class,0);
		}
		public IdademaxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idademax; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterIdademax(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitIdademax(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitIdademax(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdademaxContext idademax() throws RecognitionException {
		IdademaxContext _localctx = new IdademaxContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_idademax);
		 ((IdademaxContext)_localctx).max =  0; 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			((IdademaxContext)_localctx).idade = idade();
			 ((IdademaxContext)_localctx).max =  Integer.parseInt((((IdademaxContext)_localctx).idade!=null?_input.getText(((IdademaxContext)_localctx).idade.start,((IdademaxContext)_localctx).idade.stop):null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdadeContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(SelRAParser.NUM, 0); }
		public IdadeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idade; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterIdade(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitIdade(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitIdade(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdadeContext idade() throws RecognitionException {
		IdadeContext _localctx = new IdadeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_idade);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CarateristicasContext extends ParserRuleContext {
		public File f;
		public ArrayList<String> listCarateristicas;
		public CaracteristicaContext c1;
		public CaracteristicaContext c2;
		public List<CaracteristicaContext> caracteristica() {
			return getRuleContexts(CaracteristicaContext.class);
		}
		public CaracteristicaContext caracteristica(int i) {
			return getRuleContext(CaracteristicaContext.class,i);
		}
		public List<TerminalNode> VIRGULA() { return getTokens(SelRAParser.VIRGULA); }
		public TerminalNode VIRGULA(int i) {
			return getToken(SelRAParser.VIRGULA, i);
		}
		public CarateristicasContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CarateristicasContext(ParserRuleContext parent, int invokingState, File f) {
			super(parent, invokingState);
			this.f = f;
		}
		@Override public int getRuleIndex() { return RULE_carateristicas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterCarateristicas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitCarateristicas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitCarateristicas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CarateristicasContext carateristicas(File f) throws RecognitionException {
		CarateristicasContext _localctx = new CarateristicasContext(_ctx, getState(), f);
		enterRule(_localctx, 28, RULE_carateristicas);
		 ((CarateristicasContext)_localctx).listCarateristicas =  new ArrayList<String>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			((CarateristicasContext)_localctx).c1 = caracteristica(_localctx.f,_localctx.listCarateristicas);
			 ((CarateristicasContext)_localctx).listCarateristicas =  ((CarateristicasContext)_localctx).c1.listCarateristicasOut; 
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRGULA) {
				{
				{
				setState(140);
				match(VIRGULA);
				setState(141);
				((CarateristicasContext)_localctx).c2 = caracteristica(_localctx.f,_localctx.listCarateristicas);
				 ((CarateristicasContext)_localctx).listCarateristicas =  ((CarateristicasContext)_localctx).c2.listCarateristicasOut; 
				}
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaracteristicaContext extends ParserRuleContext {
		public File f;
		public ArrayList<String> listCarateristicasIn;
		public ArrayList<String> listCarateristicasOut;
		public DescContext desc;
		public DescContext desc() {
			return getRuleContext(DescContext.class,0);
		}
		public CaracteristicaContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CaracteristicaContext(ParserRuleContext parent, int invokingState, File f, ArrayList<String> listCarateristicasIn) {
			super(parent, invokingState);
			this.f = f;
			this.listCarateristicasIn = listCarateristicasIn;
		}
		@Override public int getRuleIndex() { return RULE_caracteristica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterCaracteristica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitCaracteristica(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitCaracteristica(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaracteristicaContext caracteristica(File f,ArrayList<String> listCarateristicasIn) throws RecognitionException {
		CaracteristicaContext _localctx = new CaracteristicaContext(_ctx, getState(), f, listCarateristicasIn);
		enterRule(_localctx, 30, RULE_caracteristica);
		 ((CaracteristicaContext)_localctx).listCarateristicasOut =  new ArrayList<String>(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			((CaracteristicaContext)_localctx).desc = desc();
			   if(!(_localctx.listCarateristicasIn.contains((((CaracteristicaContext)_localctx).desc!=null?_input.getText(((CaracteristicaContext)_localctx).desc.start,((CaracteristicaContext)_localctx).desc.stop):null)))){
			                            _localctx.listCarateristicasIn.add(((((CaracteristicaContext)_localctx).desc!=null?_input.getText(((CaracteristicaContext)_localctx).desc.start,((CaracteristicaContext)_localctx).desc.stop):null)).toLowerCase());
			                        }
			                        else{
			                            String s = "<p> ERRO: Característica " + (((CaracteristicaContext)_localctx).desc!=null?_input.getText(((CaracteristicaContext)_localctx).desc.start,((CaracteristicaContext)_localctx).desc.stop):null) + " já referida... </p>\n" ;
			                            escreveFicheiro(_localctx.f,s);
			                        }
			                        ((CaracteristicaContext)_localctx).listCarateristicasOut =  _localctx.listCarateristicasIn;
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlunosContext extends ParserRuleContext {
		public File f;
		public HashMap<String,Aluno> mapAlunos;
		public AlunoContext a1;
		public AlunoContext a2;
		public List<AlunoContext> aluno() {
			return getRuleContexts(AlunoContext.class);
		}
		public AlunoContext aluno(int i) {
			return getRuleContext(AlunoContext.class,i);
		}
		public List<TerminalNode> PONTOVIRGULA() { return getTokens(SelRAParser.PONTOVIRGULA); }
		public TerminalNode PONTOVIRGULA(int i) {
			return getToken(SelRAParser.PONTOVIRGULA, i);
		}
		public AlunosContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AlunosContext(ParserRuleContext parent, int invokingState, File f) {
			super(parent, invokingState);
			this.f = f;
		}
		@Override public int getRuleIndex() { return RULE_alunos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterAlunos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitAlunos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitAlunos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlunosContext alunos(File f) throws RecognitionException {
		AlunosContext _localctx = new AlunosContext(_ctx, getState(), f);
		enterRule(_localctx, 32, RULE_alunos);
		 ((AlunosContext)_localctx).mapAlunos =  new HashMap<String,Aluno>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			((AlunosContext)_localctx).a1 = aluno(_localctx.f, _localctx.mapAlunos);
			 ((AlunosContext)_localctx).mapAlunos =  ((AlunosContext)_localctx).a1.mapAlunosOut; 
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTOVIRGULA) {
				{
				{
				setState(154);
				match(PONTOVIRGULA);
				setState(155);
				((AlunosContext)_localctx).a2 = aluno(_localctx.f, _localctx.mapAlunos);
				 ((AlunosContext)_localctx).mapAlunos =  ((AlunosContext)_localctx).a2.mapAlunosOut; 
				}
				}
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlunoContext extends ParserRuleContext {
		public File f;
		public HashMap<String,Aluno> mapAlunosIn;
		public HashMap<String,Aluno> mapAlunosOut;
		public IdalunoContext idaluno;
		public IdadeContext idade;
		public CarateristicasContext c;
		public IdalunoContext idaluno() {
			return getRuleContext(IdalunoContext.class,0);
		}
		public IdadeContext idade() {
			return getRuleContext(IdadeContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(SelRAParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SelRAParser.RPAR, 0); }
		public CarateristicasContext carateristicas() {
			return getRuleContext(CarateristicasContext.class,0);
		}
		public AlunoContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AlunoContext(ParserRuleContext parent, int invokingState, File f, HashMap<String,Aluno> mapAlunosIn) {
			super(parent, invokingState);
			this.f = f;
			this.mapAlunosIn = mapAlunosIn;
		}
		@Override public int getRuleIndex() { return RULE_aluno; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterAluno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitAluno(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitAluno(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlunoContext aluno(File f,HashMap<String,Aluno> mapAlunosIn) throws RecognitionException {
		AlunoContext _localctx = new AlunoContext(_ctx, getState(), f, mapAlunosIn);
		enterRule(_localctx, 34, RULE_aluno);
		((AlunoContext)_localctx).mapAlunosOut =  new HashMap<String,Aluno>();
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			((AlunoContext)_localctx).idaluno = idaluno();
			setState(164);
			((AlunoContext)_localctx).idade = idade();
			setState(165);
			match(LPAR);
			setState(166);
			((AlunoContext)_localctx).c = carateristicas(_localctx.f);
			setState(167);
			match(RPAR);
			   Aluno a = new Aluno();
			                                                        a.id = (((AlunoContext)_localctx).idaluno!=null?_input.getText(((AlunoContext)_localctx).idaluno.start,((AlunoContext)_localctx).idaluno.stop):null);
			                                                        a.idade = Integer.parseInt((((AlunoContext)_localctx).idade!=null?_input.getText(((AlunoContext)_localctx).idade.start,((AlunoContext)_localctx).idade.stop):null));
			                                                        a.carateristicas = ((AlunoContext)_localctx).c.listCarateristicas;
			                                                        if(!(_localctx.mapAlunosIn.containsKey((((AlunoContext)_localctx).idaluno!=null?_input.getText(((AlunoContext)_localctx).idaluno.start,((AlunoContext)_localctx).idaluno.stop):null)))){
			                                                            _localctx.mapAlunosIn.put((((AlunoContext)_localctx).idaluno!=null?_input.getText(((AlunoContext)_localctx).idaluno.start,((AlunoContext)_localctx).idaluno.stop):null),a);
			                                                        }
			                                                        else{
			                                                            String s = "<p>ERRO: Aluno " + (((AlunoContext)_localctx).idaluno!=null?_input.getText(((AlunoContext)_localctx).idaluno.start,((AlunoContext)_localctx).idaluno.stop):null) + "já referido...</p>\n";
			                                                            escreveFicheiro(_localctx.f,s);
			                                                        }
			                                                        ((AlunoContext)_localctx).mapAlunosOut =  _localctx.mapAlunosIn;
			                                                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdalunoContext extends ParserRuleContext {
		public TerminalNode IDA() { return getToken(SelRAParser.IDA, 0); }
		public IdalunoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idaluno; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterIdaluno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitIdaluno(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitIdaluno(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdalunoContext idaluno() throws RecognitionException {
		IdalunoContext _localctx = new IdalunoContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_idaluno);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(IDA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConceitosContext extends ParserRuleContext {
		public File f;
		public HashMap<String,Conceito> mapConceitos;
		public ConceitoContext c1;
		public ConceitoContext c2;
		public List<ConceitoContext> conceito() {
			return getRuleContexts(ConceitoContext.class);
		}
		public ConceitoContext conceito(int i) {
			return getRuleContext(ConceitoContext.class,i);
		}
		public List<TerminalNode> PONTOVIRGULA() { return getTokens(SelRAParser.PONTOVIRGULA); }
		public TerminalNode PONTOVIRGULA(int i) {
			return getToken(SelRAParser.PONTOVIRGULA, i);
		}
		public ConceitosContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ConceitosContext(ParserRuleContext parent, int invokingState, File f) {
			super(parent, invokingState);
			this.f = f;
		}
		@Override public int getRuleIndex() { return RULE_conceitos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterConceitos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitConceitos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitConceitos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConceitosContext conceitos(File f) throws RecognitionException {
		ConceitosContext _localctx = new ConceitosContext(_ctx, getState(), f);
		enterRule(_localctx, 38, RULE_conceitos);
		 ((ConceitosContext)_localctx).mapConceitos =  new HashMap<String,Conceito>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			((ConceitosContext)_localctx).c1 = conceito(_localctx.f, _localctx.mapConceitos);
			 ((ConceitosContext)_localctx).mapConceitos =  ((ConceitosContext)_localctx).c1.mapConceitosOut; 
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTOVIRGULA) {
				{
				{
				setState(174);
				match(PONTOVIRGULA);
				setState(175);
				((ConceitosContext)_localctx).c2 = conceito(_localctx.f, _localctx.mapConceitos);
				 ((ConceitosContext)_localctx).mapConceitos =  ((ConceitosContext)_localctx).c2.mapConceitosOut; 
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConceitoContext extends ParserRuleContext {
		public File f;
		public HashMap<String,Conceito> mapConceitosIn;
		public HashMap<String,Conceito> mapConceitosOut;
		public IdconceitoContext idconceito;
		public DescContext desc;
		public IdconceitoContext idconceito() {
			return getRuleContext(IdconceitoContext.class,0);
		}
		public DescContext desc() {
			return getRuleContext(DescContext.class,0);
		}
		public ConceitoContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ConceitoContext(ParserRuleContext parent, int invokingState, File f, HashMap<String,Conceito> mapConceitosIn) {
			super(parent, invokingState);
			this.f = f;
			this.mapConceitosIn = mapConceitosIn;
		}
		@Override public int getRuleIndex() { return RULE_conceito; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterConceito(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitConceito(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitConceito(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConceitoContext conceito(File f,HashMap<String,Conceito> mapConceitosIn) throws RecognitionException {
		ConceitoContext _localctx = new ConceitoContext(_ctx, getState(), f, mapConceitosIn);
		enterRule(_localctx, 40, RULE_conceito);
		 ((ConceitoContext)_localctx).mapConceitosOut =  new HashMap<String,Conceito>(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			((ConceitoContext)_localctx).idconceito = idconceito();
			setState(184);
			((ConceitoContext)_localctx).desc = desc();
			   Conceito c = new Conceito();
			                                    c.id = (((ConceitoContext)_localctx).idconceito!=null?_input.getText(((ConceitoContext)_localctx).idconceito.start,((ConceitoContext)_localctx).idconceito.stop):null);
			                                    c.descricao = (((ConceitoContext)_localctx).desc!=null?_input.getText(((ConceitoContext)_localctx).desc.start,((ConceitoContext)_localctx).desc.stop):null);
			                                    if(!(_localctx.mapConceitosIn.containsKey((((ConceitoContext)_localctx).idconceito!=null?_input.getText(((ConceitoContext)_localctx).idconceito.start,((ConceitoContext)_localctx).idconceito.stop):null)))){
			                                        _localctx.mapConceitosIn.put((((ConceitoContext)_localctx).idconceito!=null?_input.getText(((ConceitoContext)_localctx).idconceito.start,((ConceitoContext)_localctx).idconceito.stop):null),c);
			                                    }
			                                    else{
			                                        String s = "<p>ERRO: Conceito " + (((ConceitoContext)_localctx).idconceito!=null?_input.getText(((ConceitoContext)_localctx).idconceito.start,((ConceitoContext)_localctx).idconceito.stop):null) + " já referido...</p> \n";
			                                        escreveFicheiro(_localctx.f,s);
			                                    }
			                                    ((ConceitoContext)_localctx).mapConceitosOut =  _localctx.mapConceitosIn;
			                                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdconceitoContext extends ParserRuleContext {
		public TerminalNode IDC() { return getToken(SelRAParser.IDC, 0); }
		public IdconceitoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idconceito; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterIdconceito(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitIdconceito(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitIdconceito(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdconceitoContext idconceito() throws RecognitionException {
		IdconceitoContext _localctx = new IdconceitoContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_idconceito);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(IDC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnsinamentosContext extends ParserRuleContext {
		public File f;
		public HashMap<String,Recurso> mapRecursos;
		public HashMap<String,Conceito> mapConceitos;
		public ArrayList<Ensinamento> listEnsinamentos;
		public EnsinamentoContext e1;
		public EnsinamentoContext e2;
		public List<EnsinamentoContext> ensinamento() {
			return getRuleContexts(EnsinamentoContext.class);
		}
		public EnsinamentoContext ensinamento(int i) {
			return getRuleContext(EnsinamentoContext.class,i);
		}
		public List<TerminalNode> PONTOVIRGULA() { return getTokens(SelRAParser.PONTOVIRGULA); }
		public TerminalNode PONTOVIRGULA(int i) {
			return getToken(SelRAParser.PONTOVIRGULA, i);
		}
		public EnsinamentosContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public EnsinamentosContext(ParserRuleContext parent, int invokingState, File f, HashMap<String,Recurso> mapRecursos, HashMap<String,Conceito> mapConceitos) {
			super(parent, invokingState);
			this.f = f;
			this.mapRecursos = mapRecursos;
			this.mapConceitos = mapConceitos;
		}
		@Override public int getRuleIndex() { return RULE_ensinamentos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterEnsinamentos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitEnsinamentos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitEnsinamentos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnsinamentosContext ensinamentos(File f,HashMap<String,Recurso> mapRecursos,HashMap<String,Conceito> mapConceitos) throws RecognitionException {
		EnsinamentosContext _localctx = new EnsinamentosContext(_ctx, getState(), f, mapRecursos, mapConceitos);
		enterRule(_localctx, 44, RULE_ensinamentos);
		 ((EnsinamentosContext)_localctx).listEnsinamentos =  new ArrayList<Ensinamento>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			((EnsinamentosContext)_localctx).e1 = ensinamento(_localctx.f,_localctx.listEnsinamentos,_localctx.mapRecursos, _localctx.mapConceitos);
			 ((EnsinamentosContext)_localctx).listEnsinamentos =  ((EnsinamentosContext)_localctx).e1.listEnsinamentosOut; 
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTOVIRGULA) {
				{
				{
				setState(191);
				match(PONTOVIRGULA);
				setState(192);
				((EnsinamentosContext)_localctx).e2 = ensinamento(_localctx.f,_localctx.listEnsinamentos,_localctx.mapRecursos, _localctx.mapConceitos);
				 ((EnsinamentosContext)_localctx).listEnsinamentos =  ((EnsinamentosContext)_localctx).e2.listEnsinamentosOut; 
				}
				}
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnsinamentoContext extends ParserRuleContext {
		public File f;
		public ArrayList<Ensinamento> listEnsinamentosIn;
		public HashMap<String,Recurso> mapRecursos;
		public HashMap<String,Conceito> mapConceitos;
		public ArrayList<Ensinamento> listEnsinamentosOut;
		public IdrecursoContext idrecurso;
		public IdconceitoContext idconceito;
		public IdrecursoContext idrecurso() {
			return getRuleContext(IdrecursoContext.class,0);
		}
		public TerminalNode ENSINA() { return getToken(SelRAParser.ENSINA, 0); }
		public IdconceitoContext idconceito() {
			return getRuleContext(IdconceitoContext.class,0);
		}
		public EnsinamentoContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public EnsinamentoContext(ParserRuleContext parent, int invokingState, File f, ArrayList<Ensinamento> listEnsinamentosIn, HashMap<String,Recurso> mapRecursos, HashMap<String,Conceito> mapConceitos) {
			super(parent, invokingState);
			this.f = f;
			this.listEnsinamentosIn = listEnsinamentosIn;
			this.mapRecursos = mapRecursos;
			this.mapConceitos = mapConceitos;
		}
		@Override public int getRuleIndex() { return RULE_ensinamento; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterEnsinamento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitEnsinamento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitEnsinamento(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnsinamentoContext ensinamento(File f,ArrayList<Ensinamento> listEnsinamentosIn,HashMap<String,Recurso> mapRecursos,HashMap<String,Conceito> mapConceitos) throws RecognitionException {
		EnsinamentoContext _localctx = new EnsinamentoContext(_ctx, getState(), f, listEnsinamentosIn, mapRecursos, mapConceitos);
		enterRule(_localctx, 46, RULE_ensinamento);
		 ((EnsinamentoContext)_localctx).listEnsinamentosOut =  new ArrayList<Ensinamento>(); 
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			((EnsinamentoContext)_localctx).idrecurso = idrecurso();
			setState(201);
			match(ENSINA);
			setState(202);
			((EnsinamentoContext)_localctx).idconceito = idconceito();
			   if(!(_localctx.mapRecursos.containsKey((((EnsinamentoContext)_localctx).idrecurso!=null?_input.getText(((EnsinamentoContext)_localctx).idrecurso.start,((EnsinamentoContext)_localctx).idrecurso.stop):null)))){
			                                    String s = "<p>ERRO: Recurso " + (((EnsinamentoContext)_localctx).idrecurso!=null?_input.getText(((EnsinamentoContext)_localctx).idrecurso.start,((EnsinamentoContext)_localctx).idrecurso.stop):null) + " inexistente...</p>\n" ;
			                                    escreveFicheiro(_localctx.f,s);
			                                }
			                                else {
			                                    if(!(_localctx.mapConceitos.containsKey((((EnsinamentoContext)_localctx).idconceito!=null?_input.getText(((EnsinamentoContext)_localctx).idconceito.start,((EnsinamentoContext)_localctx).idconceito.stop):null)))){
			                                        String s = "<p>ERRO: Conceito " + (((EnsinamentoContext)_localctx).idconceito!=null?_input.getText(((EnsinamentoContext)_localctx).idconceito.start,((EnsinamentoContext)_localctx).idconceito.stop):null) + " inexistente...</p>\n";
			                                        escreveFicheiro(_localctx.f,s);
			                                    }
			                                    else{
			                                        Ensinamento e = new Ensinamento();
			                                        e.idRecurso = (((EnsinamentoContext)_localctx).idrecurso!=null?_input.getText(((EnsinamentoContext)_localctx).idrecurso.start,((EnsinamentoContext)_localctx).idrecurso.stop):null);
			                                        e.idConceito = (((EnsinamentoContext)_localctx).idconceito!=null?_input.getText(((EnsinamentoContext)_localctx).idconceito.start,((EnsinamentoContext)_localctx).idconceito.stop):null);
			                                        if(!(_localctx.listEnsinamentosIn.contains(e))){
			                                            _localctx.listEnsinamentosIn.add(e);
			                                        }
			                                        else{
			                                            String s = "<p>ERRO: Ensinamento já referido...</p>\n";
			                                            escreveFicheiro(_localctx.f,s);
			                                        }
			                                    }
			                                }
			                                ((EnsinamentoContext)_localctx).listEnsinamentosOut =  _localctx.listEnsinamentosIn;
			                            
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestoesContext extends ParserRuleContext {
		public File f;
		public HashMap<String,Recurso> mapRecursos;
		public HashMap<String,Aluno> mapAlunos;
		public HashMap<String,Conceito> mapConceitos;
		public ArrayList<Ensinamento> listEnsinamentos;
		public HashMap<String,ArrayList<String>> mapCaracteristicas;
		public List<QuestaoContext> questao() {
			return getRuleContexts(QuestaoContext.class);
		}
		public QuestaoContext questao(int i) {
			return getRuleContext(QuestaoContext.class,i);
		}
		public QuestoesContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public QuestoesContext(ParserRuleContext parent, int invokingState, File f, HashMap<String,Recurso> mapRecursos, HashMap<String,Aluno> mapAlunos, HashMap<String,Conceito> mapConceitos, ArrayList<Ensinamento> listEnsinamentos, HashMap<String,ArrayList<String>> mapCaracteristicas) {
			super(parent, invokingState);
			this.f = f;
			this.mapRecursos = mapRecursos;
			this.mapAlunos = mapAlunos;
			this.mapConceitos = mapConceitos;
			this.listEnsinamentos = listEnsinamentos;
			this.mapCaracteristicas = mapCaracteristicas;
		}
		@Override public int getRuleIndex() { return RULE_questoes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterQuestoes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitQuestoes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitQuestoes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestoesContext questoes(File f,HashMap<String,Recurso> mapRecursos,HashMap<String,Aluno> mapAlunos,HashMap<String,Conceito> mapConceitos,ArrayList<Ensinamento> listEnsinamentos,HashMap<String,ArrayList<String>> mapCaracteristicas) throws RecognitionException {
		QuestoesContext _localctx = new QuestoesContext(_ctx, getState(), f, mapRecursos, mapAlunos, mapConceitos, listEnsinamentos, mapCaracteristicas);
		enterRule(_localctx, 48, RULE_questoes);
		 String header = "<h1 style=\"text-align:center\"> Respostas às Questões Fornecidas </h1>"; escreveFicheiro(_localctx.f,header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			questao(_localctx.f, _localctx.mapRecursos, _localctx.mapAlunos, _localctx.mapConceitos, _localctx.listEnsinamentos, _localctx.mapCaracteristicas);
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ENSINAR) {
				{
				{
				setState(206);
				questao(_localctx.f, _localctx.mapRecursos, _localctx.mapAlunos, _localctx.mapConceitos, _localctx.listEnsinamentos, _localctx.mapCaracteristicas);
				}
				}
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestaoContext extends ParserRuleContext {
		public File f;
		public HashMap<String,Recurso> mapRecursos;
		public HashMap<String,Aluno> mapAlunos;
		public HashMap<String,Conceito> mapConceitos;
		public ArrayList<Ensinamento> listEnsinamentos;
		public HashMap<String,ArrayList<String>> mapCaracteristicas;
		public IdconceitoContext idconceito;
		public IdalunoContext idaluno;
		public TerminalNode ENSINAR() { return getToken(SelRAParser.ENSINAR, 0); }
		public IdconceitoContext idconceito() {
			return getRuleContext(IdconceitoContext.class,0);
		}
		public TerminalNode AO() { return getToken(SelRAParser.AO, 0); }
		public IdalunoContext idaluno() {
			return getRuleContext(IdalunoContext.class,0);
		}
		public TerminalNode PONTOINTERROGACAO() { return getToken(SelRAParser.PONTOINTERROGACAO, 0); }
		public QuestaoContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public QuestaoContext(ParserRuleContext parent, int invokingState, File f, HashMap<String,Recurso> mapRecursos, HashMap<String,Aluno> mapAlunos, HashMap<String,Conceito> mapConceitos, ArrayList<Ensinamento> listEnsinamentos, HashMap<String,ArrayList<String>> mapCaracteristicas) {
			super(parent, invokingState);
			this.f = f;
			this.mapRecursos = mapRecursos;
			this.mapAlunos = mapAlunos;
			this.mapConceitos = mapConceitos;
			this.listEnsinamentos = listEnsinamentos;
			this.mapCaracteristicas = mapCaracteristicas;
		}
		@Override public int getRuleIndex() { return RULE_questao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).enterQuestao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SelRAListener ) ((SelRAListener)listener).exitQuestao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SelRAVisitor ) return ((SelRAVisitor<? extends T>)visitor).visitQuestao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestaoContext questao(File f,HashMap<String,Recurso> mapRecursos,HashMap<String,Aluno> mapAlunos,HashMap<String,Conceito> mapConceitos,ArrayList<Ensinamento> listEnsinamentos,HashMap<String,ArrayList<String>> mapCaracteristicas) throws RecognitionException {
		QuestaoContext _localctx = new QuestaoContext(_ctx, getState(), f, mapRecursos, mapAlunos, mapConceitos, listEnsinamentos, mapCaracteristicas);
		enterRule(_localctx, 50, RULE_questao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(ENSINAR);
			setState(213);
			((QuestaoContext)_localctx).idconceito = idconceito();
			setState(214);
			match(AO);
			setState(215);
			((QuestaoContext)_localctx).idaluno = idaluno();
			setState(216);
			match(PONTOINTERROGACAO);
			   if(!(_localctx.mapConceitos.containsKey((((QuestaoContext)_localctx).idconceito!=null?_input.getText(((QuestaoContext)_localctx).idconceito.start,((QuestaoContext)_localctx).idconceito.stop):null)))){
			                                        String s = "<p>ERRO: Conceito " + (((QuestaoContext)_localctx).idconceito!=null?_input.getText(((QuestaoContext)_localctx).idconceito.start,((QuestaoContext)_localctx).idconceito.stop):null) + " inexistente...</p>\n";
			                                        escreveFicheiro(_localctx.f,s);
			                                    }
			                                    else {
			                                        if(!(_localctx.mapAlunos.containsKey((((QuestaoContext)_localctx).idaluno!=null?_input.getText(((QuestaoContext)_localctx).idaluno.start,((QuestaoContext)_localctx).idaluno.stop):null)))){
			                                            String s = "<p>ERRO: Aluno " + (((QuestaoContext)_localctx).idaluno!=null?_input.getText(((QuestaoContext)_localctx).idaluno.start,((QuestaoContext)_localctx).idaluno.stop):null) + " inexistente...</p>\n";
			                                            escreveFicheiro(_localctx.f,s);
			                                        }
			                                        else{
			                                            // GET ALUNO ESPECIFICO CM AS SUAS CARACTERISTICAS (MAPALUNOS)
			                                            Aluno a = new Aluno();
			                                            a = _localctx.mapAlunos.get((((QuestaoContext)_localctx).idaluno!=null?_input.getText(((QuestaoContext)_localctx).idaluno.start,((QuestaoContext)_localctx).idaluno.stop):null));
			                                            ArrayList<String> listCarateristicas = new ArrayList<String>();
			                                            listCarateristicas = a.carateristicas;

			                                            // GET RECURSOS Q ENSINAM CONCEITO A ENSINAR (LISTENSINAMENTOS)
			                                            HashMap<String,Recurso> recursosfiltrados = new HashMap<String,Recurso>();
			                                            for(Ensinamento e : _localctx.listEnsinamentos){
			                                                if(e.idConceito.equals((((QuestaoContext)_localctx).idconceito!=null?_input.getText(((QuestaoContext)_localctx).idconceito.start,((QuestaoContext)_localctx).idconceito.stop):null))){
			                                                    Recurso r = new Recurso();
			                                                    r = _localctx.mapRecursos.get(e.idRecurso);

			                                                    // COMPARAR A IDADE DO ALUNO COM A IDADE IDEAL DO RECURSO
			                                                    if(a.idade >= r.idademin && a.idade <= r.idademax){
			                                                        recursosfiltrados.put(e.idRecurso,r);
			                                                    }
			                                                }
			                                            }

			                                            // NORMALIZAR AS CARACTERISTICAS DOS ALUNOS
			                                            ArrayList<String> listCarateristicasNormalizadas = new ArrayList<String>();
			                                            for(String caract : listCarateristicas){
			                                                for(HashMap.Entry<String,ArrayList<String>> entry : _localctx.mapCaracteristicas.entrySet()){
			                                                    ArrayList<String> sinonimos = new ArrayList<String>();
			                                                    sinonimos = entry.getValue();
			                                                    if(sinonimos.contains(caract)){
			                                                        if(!(listCarateristicasNormalizadas.contains(entry.getKey()))){
			                                                            listCarateristicasNormalizadas.add(entry.getKey());
			                                                        }
			                                                    }
			                                                }
			                                            }

			                                            // ASSOCIAR A CADA RECURSO QUE ENSINA O CONCEITO A MAIOR PERCENTAGEM ENCONTRADA
			                                            HashMap<String,Double> correspondencias = new HashMap<String,Double>();

			                                            // PERCORRER CADA RECURSO FILTRADO OBTER AS CARATERISTICAS DESTE
			                                            for (HashMap.Entry<String,Recurso> entry : recursosfiltrados.entrySet()) {
			                                                Recurso r = new Recurso();
			                                                r = entry.getValue();
			                                                ArrayList<String> listCarateristicasRecursos = new ArrayList<String>();
			                                                listCarateristicasRecursos = r.carateristicas;

			                                                // NORMALIZAR AS CARACTERISTICAS DOS RECURSOS
			                                                ArrayList<String> listCarateristicasRecursosNormalizadas = new ArrayList<String>();
			                                                for(String caract : listCarateristicasRecursos){
			                                                    for(HashMap.Entry<String,ArrayList<String>> entryRecurso : _localctx.mapCaracteristicas.entrySet()){
			                                                        ArrayList<String> sinonimos = new ArrayList<String>();
			                                                        sinonimos = entryRecurso.getValue();
			                                                        if(sinonimos.contains(caract)){
			                                                            if(!(listCarateristicasRecursosNormalizadas.contains(entryRecurso.getKey()))){
			                                                                listCarateristicasRecursosNormalizadas.add(entryRecurso.getKey());
			                                                            }
			                                                        }
			                                                    }
			                                                }

			                                                double percentagem = 0.0;

			                                                ArrayList<String> matching = new ArrayList<String>();
			                                                matching = (ArrayList<String>) listCarateristicasNormalizadas.stream().filter(listCarateristicasRecursosNormalizadas::contains).collect(Collectors.toList());

			                                                //VERIFICAR SE A CARACTERISTICA DE UM ALUNO ESTA PRESENTE NA LISTA DE CARACTERISTICAS
			                                                if(matching.size() != 0){
			                                                    percentagem = ( (double)  matching.size() / listCarateristicasRecursosNormalizadas.size() ) * 100  ;
			                                                }

			                                                // ADICIONAR / ATUALIZAR A MAIOR PERCENTAGEM DO RECURSO
			                                                if(correspondencias.containsKey(r.id)){
			                                                    if(correspondencias.get(r.id) < percentagem){
			                                                        correspondencias.replace(r.id,percentagem);
			                                                    }
			                                                }
			                                                else{
			                                                    correspondencias.put(r.id,percentagem);
			                                                }
			                                            }

			                                            // ARVORE ORDENADA POR PERCENTAGEM QUE CONTEM UMA CONJUNTO DE RECURSOS UNICOS
			                                            TreeMap<Double, HashSet<Recurso>> resposta = new TreeMap<Double,HashSet<Recurso>>();

			                                            // ITERAR SOBRE AS CORRESPONDENCIAS DE IDRECURSO - PERCENTAGEM PARA OBTER OS RECURSOS
			                                            for (HashMap.Entry<String,Double> entry : correspondencias.entrySet()) {
			                                                Recurso r = new Recurso();
			                                                r = recursosfiltrados.get(entry.getKey());

			                                                HashSet<Recurso> listRecursos = new HashSet<Recurso>();
			                                                if(resposta.containsKey(entry.getValue())){
			                                                    listRecursos = resposta.get(entry.getValue());
			                                                    listRecursos.add(r);
			                                                    resposta.replace(entry.getValue(),listRecursos);
			                                                }
			                                                else{
			                                                    listRecursos.add(r);
			                                                    resposta.put(entry.getValue(),listRecursos);
			                                                }
			                                            }


			                                            String questao = "<h3> Questão :  ENSINAR " + (((QuestaoContext)_localctx).idconceito!=null?_input.getText(((QuestaoContext)_localctx).idconceito.start,((QuestaoContext)_localctx).idconceito.stop):null) + " AO " + (((QuestaoContext)_localctx).idaluno!=null?_input.getText(((QuestaoContext)_localctx).idaluno.start,((QuestaoContext)_localctx).idaluno.stop):null) + " ? </h3> \n";
			                                            escreveFicheiro(_localctx.f,questao);
			                                            String info = "<p> Para ensinar o conceito " + (((QuestaoContext)_localctx).idconceito!=null?_input.getText(((QuestaoContext)_localctx).idconceito.start,((QuestaoContext)_localctx).idconceito.stop):null) + " ao aluno " + (((QuestaoContext)_localctx).idaluno!=null?_input.getText(((QuestaoContext)_localctx).idaluno.start,((QuestaoContext)_localctx).idaluno.stop):null) + " cuja idade é " + a.idade +" que possui as seguintes caracteristicas : " + listCarateristicas.toString() + ", existe os seguintes recursos:</p>\n";
			                                            escreveFicheiro(_localctx.f,info);
			                                            if (resposta.size() == 0){
			                                                String semrecursos = "<ul> <li> Não existe recursos disponíveis ... </li> </ul> \n";
			                                                escreveFicheiro(_localctx.f,semrecursos);
			                                            }
			                                            else{
			                                                for (Map.Entry<Double,HashSet<Recurso>> entry : resposta.entrySet()) {
			                                                    String corresp = "<ul> <li> Com uma correspondência de " + entry.getKey() + "% ... <ul> \n";
			                                                    escreveFicheiro(_localctx.f,corresp);
			                                                    for(Recurso r : entry.getValue()){
			                                                        String resp = " <li>" + r.toString() + "</li>\n";
			                                                        escreveFicheiro(_localctx.f,resp);
			                                                    }
			                                                    String fecho = "</ul> </li> </ul>";
			                                                    escreveFicheiro(_localctx.f,fecho);
			                                                }
			                                            }
			                                            String hr = "<hr>";
			                                            escreveFicheiro(_localctx.f,hr);
			                                        }
			                                    }
			                                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\23\u00de\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3K\n\3\f\3\16\3N\13\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6_\n\6\f\6\16\6b\13\6\3"+
		"\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\7\to\n\t\f\t\16\tr\13\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0093"+
		"\n\20\f\20\16\20\u0096\13\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\7\22\u00a1\n\22\f\22\16\22\u00a4\13\22\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u00b5\n\25\f\25"+
		"\16\25\u00b8\13\25\3\26\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\7\30\u00c6\n\30\f\30\16\30\u00c9\13\30\3\31\3\31\3\31\3\31\3"+
		"\31\3\32\3\32\7\32\u00d2\n\32\f\32\16\32\u00d5\13\32\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\2\2\34\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\2\2\2\u00cb\2\66\3\2\2\2\4D\3\2\2\2\6O\3\2\2\2\bV\3"+
		"\2\2\2\nX\3\2\2\2\fc\3\2\2\2\16f\3\2\2\2\20h\3\2\2\2\22s\3\2\2\2\24{\3"+
		"\2\2\2\26}\3\2\2\2\30\u0084\3\2\2\2\32\u0087\3\2\2\2\34\u008a\3\2\2\2"+
		"\36\u008c\3\2\2\2 \u0097\3\2\2\2\"\u009a\3\2\2\2$\u00a5\3\2\2\2&\u00ac"+
		"\3\2\2\2(\u00ae\3\2\2\2*\u00b9\3\2\2\2,\u00bd\3\2\2\2.\u00bf\3\2\2\2\60"+
		"\u00ca\3\2\2\2\62\u00cf\3\2\2\2\64\u00d6\3\2\2\2\66\67\5\4\3\2\678\7\n"+
		"\2\289\5\20\t\29:\7\n\2\2:;\5\"\22\2;<\7\n\2\2<=\5(\25\2=>\7\n\2\2>?\5"+
		".\30\2?@\7\n\2\2@A\5\62\32\2AB\7\r\2\2BC\b\2\1\2C\3\3\2\2\2DE\5\6\4\2"+
		"EL\b\3\1\2FG\7\13\2\2GH\5\6\4\2HI\b\3\1\2IK\3\2\2\2JF\3\2\2\2KN\3\2\2"+
		"\2LJ\3\2\2\2LM\3\2\2\2M\5\3\2\2\2NL\3\2\2\2OP\5\b\5\2PQ\7\21\2\2QR\7\16"+
		"\2\2RS\5\n\6\2ST\7\17\2\2TU\b\4\1\2U\7\3\2\2\2VW\7\22\2\2W\t\3\2\2\2X"+
		"Y\5\f\7\2Y`\b\6\1\2Z[\7\f\2\2[\\\5\f\7\2\\]\b\6\1\2]_\3\2\2\2^Z\3\2\2"+
		"\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\13\3\2\2\2b`\3\2\2\2cd\5\16\b\2de\b"+
		"\7\1\2e\r\3\2\2\2fg\7\22\2\2g\17\3\2\2\2hi\5\22\n\2ip\b\t\1\2jk\7\13\2"+
		"\2kl\5\22\n\2lm\b\t\1\2mo\3\2\2\2nj\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2"+
		"\2\2q\21\3\2\2\2rp\3\2\2\2st\5\24\13\2tu\5\16\b\2uv\5\26\f\2vw\7\16\2"+
		"\2wx\5\36\20\2xy\7\17\2\2yz\b\n\1\2z\23\3\2\2\2{|\7\4\2\2|\25\3\2\2\2"+
		"}~\7\16\2\2~\177\5\30\r\2\177\u0080\7\n\2\2\u0080\u0081\5\32\16\2\u0081"+
		"\u0082\7\17\2\2\u0082\u0083\b\f\1\2\u0083\27\3\2\2\2\u0084\u0085\5\34"+
		"\17\2\u0085\u0086\b\r\1\2\u0086\31\3\2\2\2\u0087\u0088\5\34\17\2\u0088"+
		"\u0089\b\16\1\2\u0089\33\3\2\2\2\u008a\u008b\7\7\2\2\u008b\35\3\2\2\2"+
		"\u008c\u008d\5 \21\2\u008d\u0094\b\20\1\2\u008e\u008f\7\f\2\2\u008f\u0090"+
		"\5 \21\2\u0090\u0091\b\20\1\2\u0091\u0093\3\2\2\2\u0092\u008e\3\2\2\2"+
		"\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\37"+
		"\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0098\5\16\b\2\u0098\u0099\b\21\1\2"+
		"\u0099!\3\2\2\2\u009a\u009b\5$\23\2\u009b\u00a2\b\22\1\2\u009c\u009d\7"+
		"\13\2\2\u009d\u009e\5$\23\2\u009e\u009f\b\22\1\2\u009f\u00a1\3\2\2\2\u00a0"+
		"\u009c\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2"+
		"\2\2\u00a3#\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\5&\24\2\u00a6\u00a7"+
		"\5\34\17\2\u00a7\u00a8\7\16\2\2\u00a8\u00a9\5\36\20\2\u00a9\u00aa\7\17"+
		"\2\2\u00aa\u00ab\b\23\1\2\u00ab%\3\2\2\2\u00ac\u00ad\7\5\2\2\u00ad\'\3"+
		"\2\2\2\u00ae\u00af\5*\26\2\u00af\u00b6\b\25\1\2\u00b0\u00b1\7\13\2\2\u00b1"+
		"\u00b2\5*\26\2\u00b2\u00b3\b\25\1\2\u00b3\u00b5\3\2\2\2\u00b4\u00b0\3"+
		"\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		")\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\5,\27\2\u00ba\u00bb\5\16\b\2"+
		"\u00bb\u00bc\b\26\1\2\u00bc+\3\2\2\2\u00bd\u00be\7\6\2\2\u00be-\3\2\2"+
		"\2\u00bf\u00c0\5\60\31\2\u00c0\u00c7\b\30\1\2\u00c1\u00c2\7\13\2\2\u00c2"+
		"\u00c3\5\60\31\2\u00c3\u00c4\b\30\1\2\u00c4\u00c6\3\2\2\2\u00c5\u00c1"+
		"\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8"+
		"/\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00cb\5\24\13\2\u00cb\u00cc\7\t\2"+
		"\2\u00cc\u00cd\5,\27\2\u00cd\u00ce\b\31\1\2\u00ce\61\3\2\2\2\u00cf\u00d3"+
		"\5\64\33\2\u00d0\u00d2\5\64\33\2\u00d1\u00d0\3\2\2\2\u00d2\u00d5\3\2\2"+
		"\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\63\3\2\2\2\u00d5\u00d3"+
		"\3\2\2\2\u00d6\u00d7\7\b\2\2\u00d7\u00d8\5,\27\2\u00d8\u00d9\7\3\2\2\u00d9"+
		"\u00da\5&\24\2\u00da\u00db\7\20\2\2\u00db\u00dc\b\33\1\2\u00dc\65\3\2"+
		"\2\2\nL`p\u0094\u00a2\u00b6\u00c7\u00d3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}