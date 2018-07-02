/**
 * 
 */
package extrator;

import java.util.List;

/**
 * @author JN Marcos
 *
 */
public class Sentenca {
	private String idSentenca;
	private String texto;
	private List<Token> tokens;
	private List<Chunk> chunks;
	private boolean hasNe;
	
	public Sentenca(String texto) {
		super();
		this.texto = texto;
	}
	

	public String getIdSentenca() {
		return idSentenca;
	}
	public void setIdSentenca(String idSentenca) {
		this.idSentenca = idSentenca;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public List<Token> getTokens() {
		return tokens;
	}
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
		verificarSeTemNE();
	}
	
	public List<Chunk> getChunks() {
		return chunks;
	}

	public void setChunks(List<Chunk> chunks) {
		this.chunks = chunks;
	}
	public boolean isHasNe() {
		return hasNe;
	}
	public void setHasNe(boolean hasNe) {
		this.hasNe = hasNe;
	}

	//verifica se tem entidade nomeada
	private void verificarSeTemNE() {
		setHasNe(false);
		for (int i = 0; i < tokens.size(); i++) {
			if (!tokens.get(i).getNER().equals("O")) {
				setHasNe(true);
				break;
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		result = prime * result + ((tokens == null) ? 0 : tokens.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sentenca other = (Sentenca) obj;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		if (tokens == null) {
			if (other.tokens != null)
				return false;
		} else if (!tokens.equals(other.tokens))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "<sentence text=\"" + texto + "\" s_id=\"" + idSentenca + 
				"\" has_ne=\"" + Boolean.toString(hasNe) + "\">\n";
	}
	
	
}
