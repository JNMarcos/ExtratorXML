/**
 * 
 */
package extrator;

/**
 * @author JN Marcos
 *
 */
public class Token {
	private String idSentenca = "";
	private String idToken = "";
	private String texto;
	private String charOffsetBegin;
	private String charOffsetEnd;
	private String POS;
	private String lema;
	private String NER;
	private String ckOt;
	private String tipo = "";	
	

	public Token(String idSentenca, String idToken) {
		super();
		this.idSentenca = idSentenca;
		this.idToken = idToken;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getCharOffsetBegin() {
		return charOffsetBegin;
	}
	public void setCharOffsetBegin(String charOffsetBegin) {
		this.charOffsetBegin = charOffsetBegin;
	}
	public String getCharOffsetEnd() {
		return charOffsetEnd;
	}
	public void setCharOffsetEnd(String charOffsetEnd) {
		this.charOffsetEnd = charOffsetEnd;
	}
	public String getPOS() {
		return POS;
	}
	public void setPOS(String pOS) {
		POS = pOS;
	}
	public String getLema() {
		return lema;
	}
	public void setLema(String lema) {
		this.lema = lema;
	}
	public String getNER() {
		return NER;
	}
	public void setNER(String nER) {
		NER = nER;
	}
	public String getIdSentenca() {
		return idSentenca;
	}
	public void setIdSentenca(String idSentenca) {
		this.idSentenca = idSentenca;
	}
	public String getIdToken() {
		return idToken;
	}
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	public String getCkOt() {
		return ckOt;
	}
	public void setCkOt(String ckOt) {
		this.ckOt = ckOt;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSentenca == null) ? 0 : idSentenca.hashCode());
		result = prime * result + ((idToken == null) ? 0 : idToken.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
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
		Token other = (Token) obj;
		if (idSentenca == null) {
			if (other.idSentenca != null)
				return false;
		} else if (!idSentenca.equals(other.idSentenca))
			return false;
		if (idToken == null) {
			if (other.idToken != null)
				return false;
		} else if (!idToken.equals(other.idToken))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}
	//retorna 
	@Override
	public String toString() {
		String orth = Character.isUpperCase(texto.charAt(0))? "UpperInitial":"LowerCase";
		return "<token s_id=\"s_" + idSentenca + "\" type=\"" + tipo + "\" t_id=\"t_" + idToken + "\" string=\"" 
			+ texto + "\" stem=\"" + texto.toLowerCase() + "\" start=\"" + charOffsetBegin + "\" pos=\"" + POS + "\" orth=\"" + orth +
			"\" length=\"" + texto.length() + "\" end=\"" + charOffsetEnd + "\" ck_ot=\"" + ckOt + "\"/>\n";
	}
	
	
	
}
