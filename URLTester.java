
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

public class URLTester {
	
	static String checkAndAddProtocol(String url) {
		if(url.indexOf("://") == -1) {
			return "http://"+url;
		} else return url;
	}

	static String getConnectStatus(String url) {
		try {
			(new URL(url)).openStream().close();
			return "Success";
		} catch(Exception e) {
			return "Failure: "+e;
		}
	}

	public static void main(String args[]) {
		String fname = args[0];
		String outstr = "";

		try {
			BufferedReader fin = new BufferedReader(new FileReader(fname));

			String url = fin.readLine();
			outstr+=("<table>\n<tr>\n<td>URL</td><td>Status</td>\n</tr>");
			while(url != null) {
				url = checkAndAddProtocol(url);
				String statString = getConnectStatus(url);
				outstr+=("\n<tr><td>"+url+"</td>\n<td style='background-color: ");
				if(statString.equals("Success")) {
					outstr+=("green");
				} else {
					outstr+=("red");
				}
				outstr+=("'>\n\t"+statString+"\n</td>\n</tr>");
				url = fin.readLine();
			}
			outstr+=("\n</table>");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(outstr);
	}
}
