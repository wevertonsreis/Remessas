package org.jrimum.texgit.ex.avancado.bradesco;

import static org.jrimum.utilix.Collections.checkNotEmpty;
import static org.jrimum.utilix.Collections.hasElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jrimum.texgit.Record;
import org.jrimum.texgit.ex.avancado.banco.AbstractFlatFile;


/**
 * <p>
 * Representação do arquivo em termos de protocolo, sumáiro, e transações de
 * títulos.
 * </p>
 * 
 * @author <a href=http://gilmatryx.googlepages.com/>Gilmar P.S.L.</a>
 * 
 */
public class ArquivoRetorno extends AbstractFlatFile{

	/**
	 * <p>
	 * Bean protocolo
	 * </p>
	 */
	private Protocolo protocolo;
	
	/**
	 * <p>
	 * Lista com as trasações de títulos contidas no arquivo
	 * </p>
	 */
	private List<TransacaoDeTitulo> transacoes;

	/**
	 * <p>
	 * Bean sumário
	 * </p>
	 */
	private Sumario sumario;

	
	private static final String LAYOUT = "LayoutBradescoCNAB400RetornoParaLer.txg.xml";

	
	private ArquivoRetorno(){
		
		super(LAYOUT);
	}
	
	private ArquivoRetorno(String cfgFile) {
		
		super(cfgFile);
	}
	
	public static ArquivoRetorno newInstance(List<String> lines) {
		
		checkNotEmpty(lines, "Linhas ausentes!");

		ArquivoRetorno ff = new ArquivoRetorno();
		
		ff.read(lines);
		
		ff.loadInfo();
		
		return ff;

	}

	public Map<Integer, Collection<TransacaoDeTitulo>> getTransacoesPorCodigoDeOcorrencia() {

		Map<Integer, Collection<TransacaoDeTitulo>> transacoesPorOcorrencias = new TreeMap<Integer, Collection<TransacaoDeTitulo>>();

		if (hasElement(getTransacoes())) {

			for (TransacaoDeTitulo transacao : getTransacoes()) {
				try {
					if (!transacoesPorOcorrencias.containsKey(transacao
							.getCodigoDeOcorrencia())) {
						ArrayList<TransacaoDeTitulo> trans = new ArrayList<TransacaoDeTitulo>();
						trans.add(transacao);
						transacoesPorOcorrencias.put(transacao
								.getCodigoDeOcorrencia(), trans);
					} else {
						transacoesPorOcorrencias.get(
								transacao.getCodigoDeOcorrencia()).add(
								transacao);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return transacoesPorOcorrencias;
		}

		return Collections.emptyMap();
	}

	public Collection<String> getColecaoDeNossoNumero() {

		Set<String> numeros = new HashSet<String>();

		if (hasElement(getTransacoes())) {
			for (TransacaoDeTitulo t : getTransacoes()) {
				numeros.add(t.getNossoNumeroComDigito());
			}
		}

		return numeros;
	}

	public Protocolo getProtocolo() {
		return protocolo;
	}

	public Sumario getSumario() {

		return sumario;
	}

	public List<TransacaoDeTitulo> getTransacoes() {

		return transacoes;
	}

	private void loadInfo() {

		this.protocolo = new Protocolo(getFlatFile().getRecord("Header"));
		this.sumario = new Sumario(getFlatFile().getRecord("Trailler"));

		Collection<Record> registrosDeTransacoes = getFlatFile().getRecords(
				"TransacaoTitulo");

		if (hasElement(registrosDeTransacoes)) {
			ArrayList<TransacaoDeTitulo> transacoes = new ArrayList<TransacaoDeTitulo>(
					registrosDeTransacoes.size());
			for (Record registro : registrosDeTransacoes) {
				try {
					transacoes.add(new TransacaoDeTitulo(registro));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.transacoes = transacoes;
		}
	}

}
