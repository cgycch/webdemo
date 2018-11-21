package webdemo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * Servlet implementation class XMLServlet
 */
@WebServlet("/xml")
public class XMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public XMLServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestId = request.getParameter("__requestId");
		if (requestId == null || requestId.equals("123")) {
			List<Map<String, Object>> list = new ArrayList<>();
			String Report_Name= request.getParameter("Report_Name");
			String Wss_Acct_No= request.getParameter("Wss_Acct_No");
			String Type= request.getParameter("Type");
			String Date= request.getParameter("Date");
			for (int i = 0; i < 2; i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("Report_Name", Report_Name);
				map.put("Wss_Acct_No", Wss_Acct_No);
				map.put("Type", Type);
				map.put("Date", Date);
				list.add(map);
			}
			String result = listMap2xml(list, "Rows", "row");
			response.getWriter().append(result);
		} else {
			response.getWriter().append(getResult(0));
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public static void main(String[] args) {
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", "AA"+i);
			map.put("phone", "BB"+i);
			map.put("address", "CC"+i);
			list.add(map);
		}
		System.out.println(listMap2xml(list, "Users", "User"));
	}
	public String getResult( int size) {
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", "AA"+i);
			map.put("phone", "BB"+i);
			map.put("address", "CC"+i);
			list.add(map);
		}
		return listMap2xml(list, "Users", "User");
	}

	public static String listMap2xml(List<Map<String, Object>> list, String listRoot, String mapRoot) {
		Document doc = DocumentHelper.createDocument();

		Element rootEle = doc.addElement("result");
		Element msgEle = rootEle.addElement("msg");

		if (null != list && !list.isEmpty()) {
			msgEle.setText("success load data");

			Element listRootEle = rootEle.addElement(listRoot);

			for (Map<String, Object> map : list) {

				Element mapRootELe = listRootEle.addElement(mapRoot);

				Set<Map.Entry<String, Object>> set = map.entrySet();
				Iterator<Map.Entry<String, Object>> iter = set.iterator();
				while (iter.hasNext()) {
					Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter.next();
					mapRootELe.addAttribute(entry.getKey(), (String) entry.getValue());
					//Element ele = mapRootELe.addElement(entry.getKey());
					//ele.setText(String.valueOf(entry.getValue()));
				}
			}
		} else {
			msgEle.setText("no data found");
		}

		StringWriter sw = new StringWriter();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");

		try {

			XMLWriter xmlWriter = new XMLWriter(sw, format);

			xmlWriter.write(doc);
		} catch (IOException ex) {
			System.err.println("IOException: " + ex.getMessage());
		} finally {
			try {
				sw.close();
			} catch (IOException ex) {
				System.err.println("when close IOException: " + ex.getMessage());
			}
		}

		return sw.toString();
	}

}
