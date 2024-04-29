import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class Reverse {

	public static void main(String[] args) throws IOException {
		InputStream fis = new FileInputStream("input.txt");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String temp;
		ArrayList<String> data = new ArrayList<String>();
		
		FileOutputStream fos = new FileOutputStream("output.txt");
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		
		
		while((temp = br.readLine()) != null) data.add(temp);
		
		for(int i=data.size()-1; i>=0; i--) {
			bw.write(data.get(i));
			bw.newLine();
		}
		
		bw.flush();
		br.close(); bw.close();
		isr.close(); osw.close();
		fis.close(); fos.close();
	}

}
