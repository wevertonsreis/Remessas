package org.jrimum.texgit.ex.avancado.banco;

import static org.jrimum.utilix.text.DateFormat.DDMMYY_B;
import static org.jrimum.utilix.text.DecimalFormat.MONEY_DD_BR;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.ex.avancado.bradesco.ArquivoRetorno;
import org.jrimum.texgit.ex.avancado.bradesco.Protocolo;
import org.jrimum.texgit.ex.avancado.bradesco.Sumario;
import org.jrimum.texgit.ex.avancado.bradesco.TransacaoDeTitulo;

public class ExemploProcessarRetorno {

	public static void main(String[] args) throws IOException {
		
		File fileExemplo = new File("CB090900.RET");
		
		List<String> lines = FileUtils.readLines(fileExemplo, "UTF8");
		
		ArquivoRetorno ff = ArquivoRetorno.newInstance(lines);
		
		/*
		 * Header do arquivo
		 */
		Protocolo p = ff.getProtocolo();
		
		System.out.println(p.getLiteralRetorno()+": "+DDMMYY_B.format(p.getDataGravacaoArquivo()));
		System.out.println("Data de credito: "+DDMMYY_B.format(p.getDataDoCredito()));
		
		/*
		 * Trailler do arquivo
		 */
		Sumario s = ff.getSumario();
		
		System.out.println("Quantidade total de titulos em cobranca no banco: "+s.getQuantidadeDeTitulosEmCobranca());
		System.out.println("Valor total de titulos em cobranca no banco: "+MONEY_DD_BR.format(s.getValorTotalEmCobranca()));
		
		System.out.println("Total de titulos no arquivo: "+ff.getTransacoes().size());
		
		Map<Integer,Collection<TransacaoDeTitulo>> titulosByOcorrencia = ff.getTransacoesPorCodigoDeOcorrencia();
		
		Integer LIQUIDACAO = 6;
		
		System.out.println("Pagamentos:");
		
		for(TransacaoDeTitulo t : titulosByOcorrencia.get(LIQUIDACAO)){
			System.out.println(String.format(
					"NossoNumero: %s, Valor: %s, Juros: %s"
					, t.getNossoNumeroComDigito()
					, MONEY_DD_BR.format(t.getValorPago())
					, MONEY_DD_BR.format(t.getJurosDeMora()))
					);
		}
					
	}
}
