package org.jrimum.texgit.ex.avancado.bradesco;

import static org.jrimum.utilix.Collections.hasElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jrimum.texgit.Record;
import org.jrimum.texgit.ex.avancado.banco.AbstractTransacaoDeTitulo;

/**
 * <p>
 * Informações da transação do título segundo banco Bradesco
 * </p>
 * 
 * @author <a href=http://gilmatryx.googlepages.com/>Gilmar P.S.L.</a>
 */
public class TransacaoDeTitulo extends AbstractTransacaoDeTitulo{

	/**
	 * <p>
	 * Códigos em inteiros.
	 * </p>
	 */
	private List<Integer> codigoDeMotivosDeOcorrencia = new ArrayList<Integer>(
			5);

	

	public TransacaoDeTitulo(Record transacao) {

		super(transacao);
		
		if (getTransacao() != null) {

			/*
			 * Todos em lista para manter a informacao
			 */
			this.codigoDeMotivosDeOcorrencia.add(this.getCodigoDeMotivo1());
			this.codigoDeMotivosDeOcorrencia.add(this.getCodigoDeMotivo2());
			this.codigoDeMotivosDeOcorrencia.add(this.getCodigoDeMotivo3());
			this.codigoDeMotivosDeOcorrencia.add(this.getCodigoDeMotivo4());
			this.codigoDeMotivosDeOcorrencia.add(this.getCodigoDeMotivo5());

		} 
	}

	public List<Integer> getCodigosDeMotivos() {

		return Collections.unmodifiableList(this.codigoDeMotivosDeOcorrencia);
	}

	public String getMotivosFormatado() {
		StringBuilder sb = new StringBuilder("");

		if (hasElement(getCodigoDeMotivosDeOcorrencia())) {
			Iterator<Integer> i = getCodigosDeMotivos().iterator();
			while(i.hasNext()) {
				sb.append(i.next());
				if (i.hasNext()){
					sb.append(", ");
				}
			}
		}

		return sb.toString();
	}

	public String getMotivoConfirmacaoDeProtesto() {

		final Character motivo = getTransacao()
				.getValue("MotivoConfirmacaoDeProtesto");

		if (motivo != null) {
			if (motivo.equals('D')) {
				return "DESPREZADO";
			} else {
				if (motivo.equals('A'))
					return "ACEITO";
			}
		}
		return "DESCONHECIDO";
	}

	public List<Integer> getCodigoDeMotivosDeOcorrencia() {
		return codigoDeMotivosDeOcorrencia;
	}

	public String getNumeroControleDoParticipante() {

		return getTransacao().getValue("NumeroControleDoParticipante");
	}

	public String getNossoNumeroComDigito() {

		return getTransacao().getValue("NossoNumeroComDigito");
	}

	public Integer getCarteira() {

		return getTransacao().getValue("Carteira");
	}

	public Integer getCodigoDeOcorrencia() {

		return getTransacao().getValue("CodigoDeOcorrencia");
	}

	public Date getDataDaOcorrencia() {

		return getTransacao().getValue("DataDaOcorrencia");
	}

	public String getNumeroDoDocumento() {

		return getTransacao().getValue("NumeroDoDocumento");
	}

	public BigDecimal getValor() {

		return getTransacao().getValue("Valor");
	}

	public String getCodigoCompensacaoBancoRecebedor() {

		return getTransacao().getValue("CodigoCompensacaoBancoRecebedor");
	}

	public String getPrefixoDaAgenciaRecebedora() {

		return getTransacao().getValue("PrefixoDaAgenciaRecebedora");
	}

	public BigDecimal getDespesasDeCobranca() {

		return getTransacao().getValue("DespesasDeCobranca");
	}

	public BigDecimal getOutrasDespesasCustasDeProtesto() {

		return getTransacao().getValue("OutrasDespesasCustasDeProtesto");
	}

	public BigDecimal getJurosOperacaoEmAtraso() {

		return getTransacao().getValue("JurosOperacaoEmAtraso");
	}

	public BigDecimal getIOFDevido() {

		return getTransacao().getValue("IOF_Devido");
	}

	public BigDecimal getAbatimentoConcedido() {

		return getTransacao().getValue("AbatimentoConcedido");
	}

	public BigDecimal getDescontoConcedido() {

		return getTransacao().getValue("DescontoConcedido");
	}

	public BigDecimal getValorPago() {

		return getTransacao().getValue("ValorPago");
	}

	public BigDecimal getJurosDeMora() {

		return getTransacao().getValue("JurosDeMora");
	}

	public BigDecimal getOutrosCreditos() {

		return getTransacao().getValue("OutrosCreditos");
	}

	public Date getDataDoCredito() {

		return getTransacao().getValue("DataDoCredito");
	}

	public String getOrigemDoPagamento() {

		return getTransacao().getValue("OrigemDoPagamento");
	}

	public Integer getCodigoDeMotivo1() {

		return getTransacao().getValue("CodigoDeMotivo1");
	}

	public Integer getCodigoDeMotivo2() {

		return getTransacao().getValue("CodigoDeMotivo2");
	}

	public Integer getCodigoDeMotivo3() {

		return getTransacao().getValue("CodigoDeMotivo3");
	}

	public Integer getCodigoDeMotivo4() {

		return getTransacao().getValue("CodigoDeMotivo4");
	}

	public Integer getCodigoDeMotivo5() {

		return getTransacao().getValue("CodigoDeMotivo5");
	}

	public String getNumeroDoCartorio() {

		return getTransacao().getValue("NumeroDoCartorio");
	}

	public String getNumeroDoProtocolo() {

		return getTransacao().getValue("NumeroDoProtocolo");
	}

}
