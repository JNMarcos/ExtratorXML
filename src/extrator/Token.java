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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NER == null) ? 0 : NER.hashCode());
		result = prime * result + ((POS == null) ? 0 : POS.hashCode());
		result = prime * result + ((lema == null) ? 0 : lema.hashCode());
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
		if (NER == null) {
			if (other.NER != null)
				return false;
		} else if (!NER.equals(other.NER))
			return false;
		if (POS == null) {
			if (other.POS != null)
				return false;
		} else if (!POS.equals(other.POS))
			return false;
		if (charOffsetBegin != other.charOffsetBegin)
			return false;
		if (charOffsetEnd != other.charOffsetEnd)
			return false;
		if (lema == null) {
			if (other.lema != null)
				return false;
		} else if (!lema.equals(other.lema))
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
		return "<token s_id=\"s_" + idSentenca + "\" type=\"" + "XXXXX" + "\" t_id=\"t_" + idToken + "\" string=\"" 
			+ texto + "\" stem=\"" + texto.toLowerCase() + "\" start=\"" + charOffsetBegin + "\" pos=\"" + POS + "\" orth=\"" + orth +
			"\" length=\"" + texto.length() + "\" end=\"" + charOffsetEnd + "\" ck_ot=\"" + "ZZZZZ" + "\"/>\n";
	}
	
	
	
}
