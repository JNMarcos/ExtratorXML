/**
 * 
 */
package extrator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JN Marcos
 *
 */
public class Chunk {
	private String texto;
	private String tipo;
	private String idSentenca;
	private String idChunking;
	private List<Integer> indicesTokens = new ArrayList<>();
	
	public void addIndice(Integer i) {
		indicesTokens.add(i);
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getIdSentenca() {
		return idSentenca;
	}
	public void setIdSentenca(String idSentenca) {
		this.idSentenca = idSentenca;
	}
	public String getIdChunking() {
		return idChunking;
	}
	public void setIdChunking(String idChunking) {
		this.idChunking = idChunking;
	}
	@Override
	public String toString() {
		String tipo;
		if (this.tipo.startsWith("n")) {//ESSA NÃO É A REGRA CORRETA, APENAS PARA TESTE (CONSULTAR PARA DEFINIR)
			tipo = "np";
		} else tipo = "vp";
		
		String chunk =  "<chunkig text=\"" + texto + "\" s_id=\"" + idSentenca + "\" type=\"" +
				tipo + "\" ck_id=\"" + idChunking + "\" >\n<tokens>\n";
		String[] tokens = texto.split(" ");
		String t = "";
		for(int i = 0; i < tokens.length; i++) {
			t += "<token t_id=\"t_" + "TEM QUE ACHAR UMA MANEIRA" + "\" string=\"" + tokens[i] + "\"/>\n";
		}
		chunk+= t + "</tokens>\n</chunking>\n";
		return chunk;
	}
	
	
}
