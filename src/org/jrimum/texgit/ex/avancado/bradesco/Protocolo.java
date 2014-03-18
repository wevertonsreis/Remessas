package org.jrimum.texgit.ex.avancado.bradesco;

import java.util.Date;

import org.jrimum.texgit.Record;

/**
 * <p>
 * Bean com as principais informções do registro Header do Arquivo Bradesco CNAB
 * em seus métodos.
 * </p>
 * 
 * @author <a href=http://gilmatryx.googlepages.com/>Gilmar P.S.L.</a>
 * 
 */
public class Protocolo {

	/**
	 * <p>
	 * Record texgit do arquivo Bradesco CNAB.
	 * </p>
	 */
	private Record header;

	public Protocolo(Record header) {
		if (header != null) {
			this.header = header;
		} else {
			throw new IllegalArgumentException("Header nulo!");
		}
	}

	public String getLiteralRetorno() {
		return header.getValue("LiteralRetorno");
	}

	public String getCodigoDeServico() {
		return header.getValue("CodigoDeServico");
	}

	public String getLiteralServico() {
		return header.getValue("LiteralServico");
	}

	public String getCodigoDaEmpresa() {
		return header.getValue("CodigoDaEmpresa");
	}

	public String getNomeDaEmpresa() {
		return header.getValue("NomeDaEmpresa");
	}

	public String getCodigoCompensacao() {
		return header.getValue("CodigoCompensacao");
	}

	public String getNomeBanco() {
		return header.getValue("NomeBanco");
	}

	public Date getDataGravacaoArquivo() {
		return header.getValue("DataGravacaoArquivo");
	}

	public String getNumeroDoAvisoBancario() {
		return header.getValue("NumeroDoAvisoBancario");
	}

	public Date getDataDoCredito() {
		return header.getValue("DataDoCredito");
	}

}