// Generated from c:\Users\Gonçalo Pinto\Documents\@Goncalo\Universidade\4ºANO\1Semestre\PLC_Gramáticas na Compreensão de Software\TPCs e Trabalhos Práticos\Projeto1\ComputacaoEscola.g4 by ANTLR 4.8

   import java.util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ComputacaoEscolaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AO=1, IDR=2, IDA=3, IDC=4, NUM=5, ENSINAR=6, ENSINA=7, HIFEN=8, PONTOVIRGULA=9, 
		VIRGULA=10, PONTO=11, LPAR=12, RPAR=13, PONTOINTERROGACAO=14, STR=15, 
		WS=16;
	public static final int
		RULE_computacaoescola = 0, RULE_recursos = 1, RULE_recurso = 2, RULE_idrecurso = 3, 
		RULE_desc = 4, RULE_idade = 5, RULE_carateristicas = 6, RULE_caracteristica = 7, 
		RULE_alunos = 8, RULE_aluno = 9, RULE_idaluno = 10, RULE_conceitos = 11, 
		RULE_conceito = 12, RULE_idconceito = 13, RULE_ensinamentos = 14, RULE_ensinamento = 15, 
		RULE_questoes = 16, RULE_questao = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"computacaoescola", "recursos", "recurso", "idrecurso", "desc", "idade", 
			"carateristicas", "caracteristica", "alunos", "aluno", "idaluno", "conceitos", 
			"conceito", "idconceito", "ensinamentos", "ensinamento", "questoes", 
			"questao"
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

	@Override
	public String getGrammarFileName() { return "ComputacaoEscola.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public ComputacaoEscolaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ComputacaoescolaContext extends ParserRuleContext {
		public RecursosContext recursos;
		public AlunosContext alunos;
		public ConceitosContext conceitos;
		public RecursosContext recursos() {
			return getRuleContext(RecursosContext.class,0);
		}
		public List<TerminalNode> HIFEN() { return getTokens(ComputacaoEscolaParser.HIFEN); }
		public TerminalNode HIFEN(int i) {
			return getToken(ComputacaoEscolaParser.HIFEN, i);
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
		public TerminalNode PONTO() { return getToken(ComputacaoEscolaParser.PONTO, 0); }
		public ComputacaoescolaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_computacaoescola; }
	}

	public final ComputacaoescolaContext computacaoescola() throws RecognitionException {
		ComputacaoescolaContext _localctx = new ComputacaoescolaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_computacaoescola);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			((ComputacaoescolaContext)_localctx).recursos = recursos();
			setState(37);
			match(HIFEN);
			setState(38);
			((ComputacaoescolaContext)_localctx).alunos = alunos();
			setState(39);
			match(HIFEN);
			setState(40);
			((ComputacaoescolaContext)_localctx).conceitos = conceitos();
			setState(41);
			match(HIFEN);
			setState(42);
			ensinamentos(((ComputacaoescolaContext)_localctx).recursos.mapRecursos,((ComputacaoescolaContext)_localctx).conceitos.mapConceitos);
			setState(43);
			match(HIFEN);
			setState(44);
			questoes(((ComputacaoescolaContext)_localctx).alunos.mapAlunos,((ComputacaoescolaContext)_localctx).conceitos.mapConceitos);
			setState(45);
			match(PONTO);
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
		public HashMap<String,Recurso> mapRecursos;
		public RecursoContext r1;
		public RecursoContext r2;
		public List<RecursoContext> recurso() {
			return getRuleContexts(RecursoContext.class);
		}
		public RecursoContext recurso(int i) {
			return getRuleContext(RecursoContext.class,i);
		}
		public List<TerminalNode> PONTOVIRGULA() { return getTokens(ComputacaoEscolaParser.PONTOVIRGULA); }
		public TerminalNode PONTOVIRGULA(int i) {
			return getToken(ComputacaoEscolaParser.PONTOVIRGULA, i);
		}
		public RecursosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recursos; }
	}

	public final RecursosContext recursos() throws RecognitionException {
		RecursosContext _localctx = new RecursosContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_recursos);
		((RecursosContext)_localctx).mapRecursos =  new HashMap<String,Recurso>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			((RecursosContext)_localctx).r1 = recurso(_localctx.mapRecursos);
			((RecursosContext)_localctx).mapRecursos =  ((RecursosContext)_localctx).r1.mapRecursosOut;
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTOVIRGULA) {
				{
				{
				setState(49);
				match(PONTOVIRGULA);
				setState(50);
				((RecursosContext)_localctx).r2 = recurso(_localctx.mapRecursos);
				((RecursosContext)_localctx).mapRecursos =  ((RecursosContext)_localctx).r2.mapRecursosOut;
				}
				}
				setState(57);
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
		public HashMap<String,Recurso> mapRecursosIn;
		public HashMap<String,Recurso> mapRecursosOut;
		public IdrecursoContext idrecurso;
		public DescContext desc;
		public IdadeContext idade;
		public CarateristicasContext c;
		public IdrecursoContext idrecurso() {
			return getRuleContext(IdrecursoContext.class,0);
		}
		public DescContext desc() {
			return getRuleContext(DescContext.class,0);
		}
		public IdadeContext idade() {
			return getRuleContext(IdadeContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(ComputacaoEscolaParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(ComputacaoEscolaParser.RPAR, 0); }
		public CarateristicasContext carateristicas() {
			return getRuleContext(CarateristicasContext.class,0);
		}
		public RecursoContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public RecursoContext(ParserRuleContext parent, int invokingState, HashMap<String,Recurso> mapRecursosIn) {
			super(parent, invokingState);
			this.mapRecursosIn = mapRecursosIn;
		}
		@Override public int getRuleIndex() { return RULE_recurso; }
	}

	public final RecursoContext recurso(HashMap<String,Recurso> mapRecursosIn) throws RecognitionException {
		RecursoContext _localctx = new RecursoContext(_ctx, getState(), mapRecursosIn);
		enterRule(_localctx, 4, RULE_recurso);
		((RecursoContext)_localctx).mapRecursosOut =  new HashMap<String,Recurso>();
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			((RecursoContext)_localctx).idrecurso = idrecurso();
			setState(59);
			((RecursoContext)_localctx).desc = desc();
			setState(60);
			((RecursoContext)_localctx).idade = idade();
			setState(61);
			match(LPAR);
			setState(62);
			((RecursoContext)_localctx).c = carateristicas();
			setState(63);
			match(RPAR);
			      Recurso r = new Recurso();
			                                                                     r.id = (((RecursoContext)_localctx).idrecurso!=null?_input.getText(((RecursoContext)_localctx).idrecurso.start,((RecursoContext)_localctx).idrecurso.stop):null);
			                                                                     r.descricao = (((RecursoContext)_localctx).desc!=null?_input.getText(((RecursoContext)_localctx).desc.start,((RecursoContext)_localctx).desc.stop):null);
			                                                                     r.idade = Integer.parseInt((((RecursoContext)_localctx).idade!=null?_input.getText(((RecursoContext)_localctx).idade.start,((RecursoContext)_localctx).idade.stop):null));
			                                                                     r.carateristicas = ((RecursoContext)_localctx).c.listCarateristicas;
			                                                                     if(!(_localctx.mapRecursosIn.containsKey((((RecursoContext)_localctx).idrecurso!=null?_input.getText(((RecursoContext)_localctx).idrecurso.start,((RecursoContext)_localctx).idrecurso.stop):null)))){
			                                                                        _localctx.mapRecursosIn.put((((RecursoContext)_localctx).idrecurso!=null?_input.getText(((RecursoContext)_localctx).idrecurso.start,((RecursoContext)_localctx).idrecurso.stop):null),r);
			                                                                     }
			                                                                     else{
			                                                                        System.out.println("ERRO: Recurso " + (((RecursoContext)_localctx).idrecurso!=null?_input.getText(((RecursoContext)_localctx).idrecurso.start,((RecursoContext)_localctx).idrecurso.stop):null) + " ja referido...");
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
		public TerminalNode IDR() { return getToken(ComputacaoEscolaParser.IDR, 0); }
		public IdrecursoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idrecurso; }
	}

	public final IdrecursoContext idrecurso() throws RecognitionException {
		IdrecursoContext _localctx = new IdrecursoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_idrecurso);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
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

	public static class DescContext extends ParserRuleContext {
		public TerminalNode STR() { return getToken(ComputacaoEscolaParser.STR, 0); }
		public DescContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_desc; }
	}

	public final DescContext desc() throws RecognitionException {
		DescContext _localctx = new DescContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_desc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
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

	public static class IdadeContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(ComputacaoEscolaParser.NUM, 0); }
		public IdadeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idade; }
	}

	public final IdadeContext idade() throws RecognitionException {
		IdadeContext _localctx = new IdadeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_idade);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
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
		public ArrayList<String> listCarateristicas;
		public CaracteristicaContext c1;
		public CaracteristicaContext c2;
		public List<CaracteristicaContext> caracteristica() {
			return getRuleContexts(CaracteristicaContext.class);
		}
		public CaracteristicaContext caracteristica(int i) {
			return getRuleContext(CaracteristicaContext.class,i);
		}
		public List<TerminalNode> VIRGULA() { return getTokens(ComputacaoEscolaParser.VIRGULA); }
		public TerminalNode VIRGULA(int i) {
			return getToken(ComputacaoEscolaParser.VIRGULA, i);
		}
		public CarateristicasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_carateristicas; }
	}

	public final CarateristicasContext carateristicas() throws RecognitionException {
		CarateristicasContext _localctx = new CarateristicasContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_carateristicas);
		((CarateristicasContext)_localctx).listCarateristicas =  new ArrayList<String>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			((CarateristicasContext)_localctx).c1 = caracteristica(_localctx.listCarateristicas);
			((CarateristicasContext)_localctx).listCarateristicas =  ((CarateristicasContext)_localctx).c1.listCarateristicasOut;
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRGULA) {
				{
				{
				setState(74);
				match(VIRGULA);
				setState(75);
				((CarateristicasContext)_localctx).c2 = caracteristica(_localctx.listCarateristicas);
				((CarateristicasContext)_localctx).listCarateristicas =  ((CarateristicasContext)_localctx).c2.listCarateristicasOut;
				}
				}
				setState(82);
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
		public ArrayList<String> listCarateristicasIn;
		public ArrayList<String> listCarateristicasOut;
		public DescContext desc;
		public DescContext desc() {
			return getRuleContext(DescContext.class,0);
		}
		public CaracteristicaContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CaracteristicaContext(ParserRuleContext parent, int invokingState, ArrayList<String> listCarateristicasIn) {
			super(parent, invokingState);
			this.listCarateristicasIn = listCarateristicasIn;
		}
		@Override public int getRuleIndex() { return RULE_caracteristica; }
	}

	public final CaracteristicaContext caracteristica(ArrayList<String> listCarateristicasIn) throws RecognitionException {
		CaracteristicaContext _localctx = new CaracteristicaContext(_ctx, getState(), listCarateristicasIn);
		enterRule(_localctx, 14, RULE_caracteristica);
		((CaracteristicaContext)_localctx).listCarateristicasOut =  new ArrayList<String>();
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			((CaracteristicaContext)_localctx).desc = desc();
			   if(!(_localctx.listCarateristicasIn.contains((((CaracteristicaContext)_localctx).desc!=null?_input.getText(((CaracteristicaContext)_localctx).desc.start,((CaracteristicaContext)_localctx).desc.stop):null)))){
			                            _localctx.listCarateristicasIn.add((((CaracteristicaContext)_localctx).desc!=null?_input.getText(((CaracteristicaContext)_localctx).desc.start,((CaracteristicaContext)_localctx).desc.stop):null));
			                        }
			                        else{
			                            System.out.println("ERRO: Caracteristica " + (((CaracteristicaContext)_localctx).desc!=null?_input.getText(((CaracteristicaContext)_localctx).desc.start,((CaracteristicaContext)_localctx).desc.stop):null) + " ja referida...");
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
		public HashMap<String,Aluno> mapAlunos;
		public AlunoContext a1;
		public AlunoContext a2;
		public List<AlunoContext> aluno() {
			return getRuleContexts(AlunoContext.class);
		}
		public AlunoContext aluno(int i) {
			return getRuleContext(AlunoContext.class,i);
		}
		public List<TerminalNode> PONTOVIRGULA() { return getTokens(ComputacaoEscolaParser.PONTOVIRGULA); }
		public TerminalNode PONTOVIRGULA(int i) {
			return getToken(ComputacaoEscolaParser.PONTOVIRGULA, i);
		}
		public AlunosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alunos; }
	}

	public final AlunosContext alunos() throws RecognitionException {
		AlunosContext _localctx = new AlunosContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_alunos);
		((AlunosContext)_localctx).mapAlunos =  new HashMap<String,Aluno>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			((AlunosContext)_localctx).a1 = aluno(_localctx.mapAlunos);
			((AlunosContext)_localctx).mapAlunos =  ((AlunosContext)_localctx).a1.mapAlunosOut;
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTOVIRGULA) {
				{
				{
				setState(88);
				match(PONTOVIRGULA);
				setState(89);
				((AlunosContext)_localctx).a2 = aluno(_localctx.mapAlunos);
				((AlunosContext)_localctx).mapAlunos =  ((AlunosContext)_localctx).a2.mapAlunosOut;
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

	public static class AlunoContext extends ParserRuleContext {
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
		public TerminalNode LPAR() { return getToken(ComputacaoEscolaParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(ComputacaoEscolaParser.RPAR, 0); }
		public CarateristicasContext carateristicas() {
			return getRuleContext(CarateristicasContext.class,0);
		}
		public AlunoContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AlunoContext(ParserRuleContext parent, int invokingState, HashMap<String,Aluno> mapAlunosIn) {
			super(parent, invokingState);
			this.mapAlunosIn = mapAlunosIn;
		}
		@Override public int getRuleIndex() { return RULE_aluno; }
	}

	public final AlunoContext aluno(HashMap<String,Aluno> mapAlunosIn) throws RecognitionException {
		AlunoContext _localctx = new AlunoContext(_ctx, getState(), mapAlunosIn);
		enterRule(_localctx, 18, RULE_aluno);
		((AlunoContext)_localctx).mapAlunosOut =  new HashMap<String,Aluno>();
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			((AlunoContext)_localctx).idaluno = idaluno();
			setState(98);
			((AlunoContext)_localctx).idade = idade();
			setState(99);
			match(LPAR);
			setState(100);
			((AlunoContext)_localctx).c = carateristicas();
			setState(101);
			match(RPAR);
			   Aluno a = new Aluno();
			                                                        a.id = (((AlunoContext)_localctx).idaluno!=null?_input.getText(((AlunoContext)_localctx).idaluno.start,((AlunoContext)_localctx).idaluno.stop):null);
			                                                        a.idade = Integer.parseInt((((AlunoContext)_localctx).idade!=null?_input.getText(((AlunoContext)_localctx).idade.start,((AlunoContext)_localctx).idade.stop):null));
			                                                        a.carateristicas = ((AlunoContext)_localctx).c.listCarateristicas;
			                                                        if(!(_localctx.mapAlunosIn.containsKey((((AlunoContext)_localctx).idaluno!=null?_input.getText(((AlunoContext)_localctx).idaluno.start,((AlunoContext)_localctx).idaluno.stop):null)))){
			                                                            _localctx.mapAlunosIn.put((((AlunoContext)_localctx).idaluno!=null?_input.getText(((AlunoContext)_localctx).idaluno.start,((AlunoContext)_localctx).idaluno.stop):null),a);
			                                                        }
			                                                        else{
			                                                            System.out.println("ERRO: Aluno " + (((AlunoContext)_localctx).idaluno!=null?_input.getText(((AlunoContext)_localctx).idaluno.start,((AlunoContext)_localctx).idaluno.stop):null) + "ja referido...");
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
		public TerminalNode IDA() { return getToken(ComputacaoEscolaParser.IDA, 0); }
		public IdalunoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idaluno; }
	}

	public final IdalunoContext idaluno() throws RecognitionException {
		IdalunoContext _localctx = new IdalunoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_idaluno);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
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
		public HashMap<String,Conceito> mapConceitos;
		public ConceitoContext c1;
		public ConceitoContext c2;
		public List<ConceitoContext> conceito() {
			return getRuleContexts(ConceitoContext.class);
		}
		public ConceitoContext conceito(int i) {
			return getRuleContext(ConceitoContext.class,i);
		}
		public List<TerminalNode> PONTOVIRGULA() { return getTokens(ComputacaoEscolaParser.PONTOVIRGULA); }
		public TerminalNode PONTOVIRGULA(int i) {
			return getToken(ComputacaoEscolaParser.PONTOVIRGULA, i);
		}
		public ConceitosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conceitos; }
	}

	public final ConceitosContext conceitos() throws RecognitionException {
		ConceitosContext _localctx = new ConceitosContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_conceitos);
		((ConceitosContext)_localctx).mapConceitos =  new HashMap<String,Conceito>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			((ConceitosContext)_localctx).c1 = conceito(_localctx.mapConceitos);
			((ConceitosContext)_localctx).mapConceitos =  ((ConceitosContext)_localctx).c1.mapConceitosOut;
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTOVIRGULA) {
				{
				{
				setState(108);
				match(PONTOVIRGULA);
				setState(109);
				((ConceitosContext)_localctx).c2 = conceito(_localctx.mapConceitos);
				((ConceitosContext)_localctx).mapConceitos =  ((ConceitosContext)_localctx).c2.mapConceitosOut;
				}
				}
				setState(116);
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
		public ConceitoContext(ParserRuleContext parent, int invokingState, HashMap<String,Conceito> mapConceitosIn) {
			super(parent, invokingState);
			this.mapConceitosIn = mapConceitosIn;
		}
		@Override public int getRuleIndex() { return RULE_conceito; }
	}

	public final ConceitoContext conceito(HashMap<String,Conceito> mapConceitosIn) throws RecognitionException {
		ConceitoContext _localctx = new ConceitoContext(_ctx, getState(), mapConceitosIn);
		enterRule(_localctx, 24, RULE_conceito);
		((ConceitoContext)_localctx).mapConceitosOut =  new HashMap<String,Conceito>();
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			((ConceitoContext)_localctx).idconceito = idconceito();
			setState(118);
			((ConceitoContext)_localctx).desc = desc();
			   Conceito c = new Conceito();
			                                    c.id = (((ConceitoContext)_localctx).idconceito!=null?_input.getText(((ConceitoContext)_localctx).idconceito.start,((ConceitoContext)_localctx).idconceito.stop):null);
			                                    c.descricao = (((ConceitoContext)_localctx).desc!=null?_input.getText(((ConceitoContext)_localctx).desc.start,((ConceitoContext)_localctx).desc.stop):null);
			                                    if(!(_localctx.mapConceitosIn.containsKey((((ConceitoContext)_localctx).idconceito!=null?_input.getText(((ConceitoContext)_localctx).idconceito.start,((ConceitoContext)_localctx).idconceito.stop):null)))){
			                                        _localctx.mapConceitosIn.put((((ConceitoContext)_localctx).idconceito!=null?_input.getText(((ConceitoContext)_localctx).idconceito.start,((ConceitoContext)_localctx).idconceito.stop):null),c);
			                                    }
			                                    else{
			                                        System.out.println("ERRO: Conceito " + (((ConceitoContext)_localctx).idconceito!=null?_input.getText(((ConceitoContext)_localctx).idconceito.start,((ConceitoContext)_localctx).idconceito.stop):null) + "ja referido...");
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
		public TerminalNode IDC() { return getToken(ComputacaoEscolaParser.IDC, 0); }
		public IdconceitoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idconceito; }
	}

	public final IdconceitoContext idconceito() throws RecognitionException {
		IdconceitoContext _localctx = new IdconceitoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_idconceito);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
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
		public List<TerminalNode> PONTOVIRGULA() { return getTokens(ComputacaoEscolaParser.PONTOVIRGULA); }
		public TerminalNode PONTOVIRGULA(int i) {
			return getToken(ComputacaoEscolaParser.PONTOVIRGULA, i);
		}
		public EnsinamentosContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public EnsinamentosContext(ParserRuleContext parent, int invokingState, HashMap<String,Recurso> mapRecursos, HashMap<String,Conceito> mapConceitos) {
			super(parent, invokingState);
			this.mapRecursos = mapRecursos;
			this.mapConceitos = mapConceitos;
		}
		@Override public int getRuleIndex() { return RULE_ensinamentos; }
	}

	public final EnsinamentosContext ensinamentos(HashMap<String,Recurso> mapRecursos,HashMap<String,Conceito> mapConceitos) throws RecognitionException {
		EnsinamentosContext _localctx = new EnsinamentosContext(_ctx, getState(), mapRecursos, mapConceitos);
		enterRule(_localctx, 28, RULE_ensinamentos);
		((EnsinamentosContext)_localctx).listEnsinamentos =  new ArrayList<Ensinamento>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			((EnsinamentosContext)_localctx).e1 = ensinamento(_localctx.listEnsinamentos,_localctx.mapRecursos, _localctx.mapConceitos);
			((EnsinamentosContext)_localctx).listEnsinamentos =  ((EnsinamentosContext)_localctx).e1.listEnsinamentosOut;
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PONTOVIRGULA) {
				{
				{
				setState(125);
				match(PONTOVIRGULA);
				setState(126);
				((EnsinamentosContext)_localctx).e2 = ensinamento(_localctx.listEnsinamentos,_localctx.mapRecursos, _localctx.mapConceitos);
				((EnsinamentosContext)_localctx).listEnsinamentos =  ((EnsinamentosContext)_localctx).e2.listEnsinamentosOut;
				}
				}
				setState(133);
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
		public ArrayList<Ensinamento> listEnsinamentosIn;
		public HashMap<String,Recurso> mapRecursos;
		public HashMap<String,Conceito> mapConceitos;
		public ArrayList<Ensinamento> listEnsinamentosOut;
		public IdrecursoContext idrecurso;
		public IdconceitoContext idconceito;
		public IdrecursoContext idrecurso() {
			return getRuleContext(IdrecursoContext.class,0);
		}
		public TerminalNode ENSINA() { return getToken(ComputacaoEscolaParser.ENSINA, 0); }
		public IdconceitoContext idconceito() {
			return getRuleContext(IdconceitoContext.class,0);
		}
		public EnsinamentoContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public EnsinamentoContext(ParserRuleContext parent, int invokingState, ArrayList<Ensinamento> listEnsinamentosIn, HashMap<String,Recurso> mapRecursos, HashMap<String,Conceito> mapConceitos) {
			super(parent, invokingState);
			this.listEnsinamentosIn = listEnsinamentosIn;
			this.mapRecursos = mapRecursos;
			this.mapConceitos = mapConceitos;
		}
		@Override public int getRuleIndex() { return RULE_ensinamento; }
	}

	public final EnsinamentoContext ensinamento(ArrayList<Ensinamento> listEnsinamentosIn,HashMap<String,Recurso> mapRecursos,HashMap<String,Conceito> mapConceitos) throws RecognitionException {
		EnsinamentoContext _localctx = new EnsinamentoContext(_ctx, getState(), listEnsinamentosIn, mapRecursos, mapConceitos);
		enterRule(_localctx, 30, RULE_ensinamento);
		((EnsinamentoContext)_localctx).listEnsinamentosOut =  new ArrayList<Ensinamento>();
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			((EnsinamentoContext)_localctx).idrecurso = idrecurso();
			setState(135);
			match(ENSINA);
			setState(136);
			((EnsinamentoContext)_localctx).idconceito = idconceito();
			   if(!(_localctx.mapRecursos.containsKey((((EnsinamentoContext)_localctx).idrecurso!=null?_input.getText(((EnsinamentoContext)_localctx).idrecurso.start,((EnsinamentoContext)_localctx).idrecurso.stop):null)))){
			                                    System.out.println("ERRO: Recurso " + (((EnsinamentoContext)_localctx).idrecurso!=null?_input.getText(((EnsinamentoContext)_localctx).idrecurso.start,((EnsinamentoContext)_localctx).idrecurso.stop):null) + " inexistente...");
			                                }
			                                else {
			                                    if(!(_localctx.mapConceitos.containsKey((((EnsinamentoContext)_localctx).idconceito!=null?_input.getText(((EnsinamentoContext)_localctx).idconceito.start,((EnsinamentoContext)_localctx).idconceito.stop):null)))){
			                                        System.out.println("ERRO: Conceito " + (((EnsinamentoContext)_localctx).idconceito!=null?_input.getText(((EnsinamentoContext)_localctx).idconceito.start,((EnsinamentoContext)_localctx).idconceito.stop):null) + " inexistente...");
			                                    }
			                                    else{
			                                        Ensinamento e = new Ensinamento();
			                                        e.idRecurso = (((EnsinamentoContext)_localctx).idrecurso!=null?_input.getText(((EnsinamentoContext)_localctx).idrecurso.start,((EnsinamentoContext)_localctx).idrecurso.stop):null);
			                                        e.idConceito = (((EnsinamentoContext)_localctx).idconceito!=null?_input.getText(((EnsinamentoContext)_localctx).idconceito.start,((EnsinamentoContext)_localctx).idconceito.stop):null);
			                                        if(!(_localctx.listEnsinamentosIn.contains(e))){
			                                            _localctx.listEnsinamentosIn.add(e);
			                                        }
			                                        else{
			                                            System.out.println("ERRO: Ensinamento ja referido...");
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
		public HashMap<String,Aluno> mapAlunos;
		public HashMap<String,Conceito> mapConceitos;
		public List<QuestaoContext> questao() {
			return getRuleContexts(QuestaoContext.class);
		}
		public QuestaoContext questao(int i) {
			return getRuleContext(QuestaoContext.class,i);
		}
		public QuestoesContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public QuestoesContext(ParserRuleContext parent, int invokingState, HashMap<String,Aluno> mapAlunos, HashMap<String,Conceito> mapConceitos) {
			super(parent, invokingState);
			this.mapAlunos = mapAlunos;
			this.mapConceitos = mapConceitos;
		}
		@Override public int getRuleIndex() { return RULE_questoes; }
	}

	public final QuestoesContext questoes(HashMap<String,Aluno> mapAlunos,HashMap<String,Conceito> mapConceitos) throws RecognitionException {
		QuestoesContext _localctx = new QuestoesContext(_ctx, getState(), mapAlunos, mapConceitos);
		enterRule(_localctx, 32, RULE_questoes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			questao(_localctx.mapAlunos, _localctx.mapConceitos);
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ENSINAR) {
				{
				{
				setState(140);
				questao(_localctx.mapAlunos, _localctx.mapConceitos);
				}
				}
				setState(145);
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
		public HashMap<String,Aluno> mapAlunos;
		public HashMap<String,Conceito> mapConceitos;
		public IdconceitoContext idconceito;
		public IdalunoContext idaluno;
		public TerminalNode ENSINAR() { return getToken(ComputacaoEscolaParser.ENSINAR, 0); }
		public IdconceitoContext idconceito() {
			return getRuleContext(IdconceitoContext.class,0);
		}
		public TerminalNode AO() { return getToken(ComputacaoEscolaParser.AO, 0); }
		public IdalunoContext idaluno() {
			return getRuleContext(IdalunoContext.class,0);
		}
		public TerminalNode PONTOINTERROGACAO() { return getToken(ComputacaoEscolaParser.PONTOINTERROGACAO, 0); }
		public QuestaoContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public QuestaoContext(ParserRuleContext parent, int invokingState, HashMap<String,Aluno> mapAlunos, HashMap<String,Conceito> mapConceitos) {
			super(parent, invokingState);
			this.mapAlunos = mapAlunos;
			this.mapConceitos = mapConceitos;
		}
		@Override public int getRuleIndex() { return RULE_questao; }
	}

	public final QuestaoContext questao(HashMap<String,Aluno> mapAlunos,HashMap<String,Conceito> mapConceitos) throws RecognitionException {
		QuestaoContext _localctx = new QuestaoContext(_ctx, getState(), mapAlunos, mapConceitos);
		enterRule(_localctx, 34, RULE_questao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(ENSINAR);
			setState(147);
			((QuestaoContext)_localctx).idconceito = idconceito();
			setState(148);
			match(AO);
			setState(149);
			((QuestaoContext)_localctx).idaluno = idaluno();
			setState(150);
			match(PONTOINTERROGACAO);
			   if(!(_localctx.mapConceitos.containsKey((((QuestaoContext)_localctx).idconceito!=null?_input.getText(((QuestaoContext)_localctx).idconceito.start,((QuestaoContext)_localctx).idconceito.stop):null)))){
			                                        System.out.println("ERRO: Conceito " + (((QuestaoContext)_localctx).idconceito!=null?_input.getText(((QuestaoContext)_localctx).idconceito.start,((QuestaoContext)_localctx).idconceito.stop):null) + " inexistente...");
			                                    }
			                                    else {
			                                        if(!(_localctx.mapAlunos.containsKey((((QuestaoContext)_localctx).idaluno!=null?_input.getText(((QuestaoContext)_localctx).idaluno.start,((QuestaoContext)_localctx).idaluno.stop):null)))){
			                                            System.out.println("ERRO: Aluno " + (((QuestaoContext)_localctx).idaluno!=null?_input.getText(((QuestaoContext)_localctx).idaluno.start,((QuestaoContext)_localctx).idaluno.stop):null) + " inexistente...");
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\22\u009c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\7\38\n\3\f\3\16\3;\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7\bQ\n\b\f\b\16\bT\13\b\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n_\n\n\f\n\16\nb\13\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\7\rs\n\r\f\r\16\r"+
		"v\13\r\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\7\20"+
		"\u0084\n\20\f\20\16\20\u0087\13\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\7\22\u0090\n\22\f\22\16\22\u0093\13\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\2\2\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\2\2\u008f"+
		"\2&\3\2\2\2\4\61\3\2\2\2\6<\3\2\2\2\bD\3\2\2\2\nF\3\2\2\2\fH\3\2\2\2\16"+
		"J\3\2\2\2\20U\3\2\2\2\22X\3\2\2\2\24c\3\2\2\2\26j\3\2\2\2\30l\3\2\2\2"+
		"\32w\3\2\2\2\34{\3\2\2\2\36}\3\2\2\2 \u0088\3\2\2\2\"\u008d\3\2\2\2$\u0094"+
		"\3\2\2\2&\'\5\4\3\2\'(\7\n\2\2()\5\22\n\2)*\7\n\2\2*+\5\30\r\2+,\7\n\2"+
		"\2,-\5\36\20\2-.\7\n\2\2./\5\"\22\2/\60\7\r\2\2\60\3\3\2\2\2\61\62\5\6"+
		"\4\2\629\b\3\1\2\63\64\7\13\2\2\64\65\5\6\4\2\65\66\b\3\1\2\668\3\2\2"+
		"\2\67\63\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:\5\3\2\2\2;9\3\2\2\2"+
		"<=\5\b\5\2=>\5\n\6\2>?\5\f\7\2?@\7\16\2\2@A\5\16\b\2AB\7\17\2\2BC\b\4"+
		"\1\2C\7\3\2\2\2DE\7\4\2\2E\t\3\2\2\2FG\7\21\2\2G\13\3\2\2\2HI\7\7\2\2"+
		"I\r\3\2\2\2JK\5\20\t\2KR\b\b\1\2LM\7\f\2\2MN\5\20\t\2NO\b\b\1\2OQ\3\2"+
		"\2\2PL\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\17\3\2\2\2TR\3\2\2\2UV\5"+
		"\n\6\2VW\b\t\1\2W\21\3\2\2\2XY\5\24\13\2Y`\b\n\1\2Z[\7\13\2\2[\\\5\24"+
		"\13\2\\]\b\n\1\2]_\3\2\2\2^Z\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\23"+
		"\3\2\2\2b`\3\2\2\2cd\5\26\f\2de\5\f\7\2ef\7\16\2\2fg\5\16\b\2gh\7\17\2"+
		"\2hi\b\13\1\2i\25\3\2\2\2jk\7\5\2\2k\27\3\2\2\2lm\5\32\16\2mt\b\r\1\2"+
		"no\7\13\2\2op\5\32\16\2pq\b\r\1\2qs\3\2\2\2rn\3\2\2\2sv\3\2\2\2tr\3\2"+
		"\2\2tu\3\2\2\2u\31\3\2\2\2vt\3\2\2\2wx\5\34\17\2xy\5\n\6\2yz\b\16\1\2"+
		"z\33\3\2\2\2{|\7\6\2\2|\35\3\2\2\2}~\5 \21\2~\u0085\b\20\1\2\177\u0080"+
		"\7\13\2\2\u0080\u0081\5 \21\2\u0081\u0082\b\20\1\2\u0082\u0084\3\2\2\2"+
		"\u0083\177\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086"+
		"\3\2\2\2\u0086\37\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u0089\5\b\5\2\u0089"+
		"\u008a\7\t\2\2\u008a\u008b\5\34\17\2\u008b\u008c\b\21\1\2\u008c!\3\2\2"+
		"\2\u008d\u0091\5$\23\2\u008e\u0090\5$\23\2\u008f\u008e\3\2\2\2\u0090\u0093"+
		"\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092#\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0094\u0095\7\b\2\2\u0095\u0096\5\34\17\2\u0096\u0097\7"+
		"\3\2\2\u0097\u0098\5\26\f\2\u0098\u0099\7\20\2\2\u0099\u009a\b\23\1\2"+
		"\u009a%\3\2\2\2\b9R`t\u0085\u0091";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}