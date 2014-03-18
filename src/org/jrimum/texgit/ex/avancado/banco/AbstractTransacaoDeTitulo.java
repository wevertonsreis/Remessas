package org.jrimum.texgit.ex.avancado.banco;

import java.math.BigDecimal;
import java.util.Date;

import org.jrimum.texgit.Record;


/**
 * <p>
 * Informações da transação do título segundo banco
 * </p>
 * 
 * @author <a href=http://gilmatryx.googlepages.com/>Gilmar P.S.L.</a>
 */
public abstract class AbstractTransacaoDeTitulo {

	/**
	 * <p>
	 * Registro no arquivo
	 * </p>
	 */
	private Record transacao;
	
	public AbstractTransacaoDeTitulo(Record transacao) {

		if (transacao != null) {

			this.transacao = transacao;
	
		} else {
			throw new IllegalArgumentException("Transacao nula!");
		}
	}

	public final Record getTransacao() {

		return transacao;
	}

	public abstract String getNumeroControleDoParticipante();

	public abstract String getNossoNumeroComDigito();

	public abstract Integer getCarteira();

	public abstract Integer getCodigoDeOcorrencia();

	public abstract Date getDataDaOcorrencia();

	public abstract String getNumeroDoDocumento();

	public abstract BigDecimal getValor();
}
