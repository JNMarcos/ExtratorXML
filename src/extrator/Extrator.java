/**
 * 
 */
package extrator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author JN Marcos
 *
 */
public class Extrator {

	public static String NOME_USUARIO = "JN Marcos";
	public static int NUMERO_PASTAS = 2;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String caminhos[] = new String[NUMERO_PASTAS];
		caminhos[0] = "C:\\Users\\" + NOME_USUARIO + "\\Documents\\Stanford\\";
		caminhos[1] = "C:\\Users\\" + NOME_USUARIO + "\\Documents\\Spacy\\";
		
		//"Cria-se" os Files que apontam para as pastas dos outputs
		File pastas[] = new File[NUMERO_PASTAS];
		pastas[0] = new File(caminhos[0]);
		pastas[1] = new File(caminhos[1]);
		
		File[][] arquivos = new File[NUMERO_PASTAS][];
		
		//Arquivo de leitura a partir do caminhoTemporario (string)
		FileReader[] fr = null;
		BufferedReader[] br = null;

		BufferedReader ba = null;

		String linha;
		String resultado[] = new String[NUMERO_PASTAS];
		
		
		//Conjunto de SOMENTE arquivos de uma pasta com outputs a serem limpos
		for (int  i = 0; i < pastas.length; i++){
			arquivos[i] = pastas[i].listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.isFile();
				}
			});
		}
		

		String nomeArquivo = "";
		String caminhoLeitura = "";
		File f = null;
		
		fr = new FileReader[NUMERO_PASTAS];
		br  = new BufferedReader[NUMERO_PASTAS];
		
		//Lê-se a primeira linha		
		for (int i = 0; i < caminhos.length; i++) {
			for (int j = 0; j < arquivos[i].length; j++){
				nomeArquivo = arquivos[i][j].getName();
				System.out.println(nomeArquivo);
				
				caminhoLeitura = caminhos[i] + nomeArquivo;
				f = new File(caminhoLeitura);
				
				//Cria-se o buffer para ler do arquivo
				try {
					fr[i] = new FileReader(f);
					br[i] = new BufferedReader(fr[i]);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				linha = "";
				try {
					System.out.println(linha);
					linha = br[i].readLine();
					resultado[i] = "";
					
					while(linha != null){
						resultado[i] = resultado[i] + " " + linha;
						linha = br[i].readLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				System.out.println(resultado[i]);
			}
			
			
		}
		

	}

}
