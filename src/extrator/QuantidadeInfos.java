/**
 * 
 */
package extrator;

/**
 * @author JN Marcos
 *
 */
public class QuantidadeInfos {
	private int quantidadeSentenca;
	private int quantidadeChunks;
	private int quantidadeTokens;
	
	public QuantidadeInfos() {
		quantidadeSentenca = 0;
		quantidadeChunks = 0;
		quantidadeTokens = 0;
	}
	
	
	public int getQuantidadeSentenca() {
		return quantidadeSentenca;
	}
	public void setQuantidadeSentenca(int quantidadeSentenca) {
		this.quantidadeSentenca = quantidadeSentenca;
	}
	public int getQuantidadeChunks() {
		return quantidadeChunks;
	}
	public void setQuantidadeChunks(int quantidadeChunks) {
		this.quantidadeChunks = quantidadeChunks;
	}
	public int getQuantidadeTokens() {
		return quantidadeTokens;
	}
	public void setQuantidadeTokens(int quantidadeTokens) {
		this.quantidadeTokens = quantidadeTokens;
	}
	
	
}
