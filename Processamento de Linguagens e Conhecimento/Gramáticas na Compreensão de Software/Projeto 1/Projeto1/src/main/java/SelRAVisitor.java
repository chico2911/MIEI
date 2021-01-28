// Generated from C:/Users/Gonçalo Pinto/Documents/@Goncalo/Universidade/4ºANO/1Semestre/PLC_Gramáticas na Compreensão de Software/TPCs e Trabalhos Práticos/Projeto1\SelRA.g4 by ANTLR 4.9

    import java.io.*;
    import java.util.*;
    import java.text.*;
    import java.util.stream.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SelRAParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SelRAVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SelRAParser#selRA}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelRA(SelRAParser.SelRAContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#dicionariocaracteristicas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDicionariocaracteristicas(SelRAParser.DicionariocaracteristicasContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#linhacaracteristica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinhacaracteristica(SelRAParser.LinhacaracteristicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#caracteristicaprincipal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaracteristicaprincipal(SelRAParser.CaracteristicaprincipalContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#sinonimos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSinonimos(SelRAParser.SinonimosContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#sinonimo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSinonimo(SelRAParser.SinonimoContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#desc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesc(SelRAParser.DescContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#recursos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecursos(SelRAParser.RecursosContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#recurso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecurso(SelRAParser.RecursoContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#idrecurso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdrecurso(SelRAParser.IdrecursoContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#intervaloidade}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervaloidade(SelRAParser.IntervaloidadeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#idademin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdademin(SelRAParser.IdademinContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#idademax}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdademax(SelRAParser.IdademaxContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#idade}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdade(SelRAParser.IdadeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#carateristicas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCarateristicas(SelRAParser.CarateristicasContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#caracteristica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaracteristica(SelRAParser.CaracteristicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#alunos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlunos(SelRAParser.AlunosContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#aluno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAluno(SelRAParser.AlunoContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#idaluno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdaluno(SelRAParser.IdalunoContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#conceitos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConceitos(SelRAParser.ConceitosContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#conceito}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConceito(SelRAParser.ConceitoContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#idconceito}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdconceito(SelRAParser.IdconceitoContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#ensinamentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnsinamentos(SelRAParser.EnsinamentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#ensinamento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnsinamento(SelRAParser.EnsinamentoContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#questoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestoes(SelRAParser.QuestoesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SelRAParser#questao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestao(SelRAParser.QuestaoContext ctx);
}