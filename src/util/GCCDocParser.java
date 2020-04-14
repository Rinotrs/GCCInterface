package util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class GCCDocParser
{
    private static final String gcc_doc_url = "https://gcc.gnu.org/onlinedocs/gcc/";
    private static final String gcc_doc_local_path = "src\\util\\GCCdocs\\";

    private static final Map<String, String[]> parameters= new HashMap<String, String[]>() {{
        put("compiler",
                new String[]{gcc_doc_local_path + "C++ Dialect Options (Using the GNU Compiler Collection (GCC)).html",
                        gcc_doc_url + "C_002b_002b-Dialect-Options.html#C_002b_002b-Dialect-Options"});
        put("linking",
                new String[]{gcc_doc_local_path + "Link Options (Using the GNU Compiler Collection (GCC)).html",
                        gcc_doc_url + "Link-Options.html#Link-Options"});
        put("execute",
                new String[]{gcc_doc_local_path + "Overall Options (Using the GNU Compiler Collection (GCC)).html",
                        gcc_doc_url + "Overall-Options.html#Overall-Options"});
        put("debugging",
                new String[]{gcc_doc_local_path + "Debugging Options (Using the GNU Compiler Collection (GCC)).html",
                        gcc_doc_url + "Debugging-Options.html#Debugging-Options"});
        put("generation",
                new String[]{gcc_doc_local_path + "Code Gen Options (Using the GNU Compiler Collection (GCC)).html",
                        gcc_doc_url + "Code-Gen-Options.html#Code-Gen-Options"});
        put("optimization",
                new String[]{gcc_doc_local_path + "Optimize Options (Using the GNU Compiler Collection (GCC)).html",
                        gcc_doc_url + "Optimize-Options.html#Optimize-Options"});
        put("developer",
                new String[]{gcc_doc_local_path + "Developer Options (Using the GNU Compiler Collection (GCC)).html",
                        gcc_doc_url + "Developer-Options.html#Developer-Options"});
    }};;
    public static final int ONLINE = 1;
    public static final int LOCAL = 0;

    public static Document getCleanDoc(Document document) {
        return new Cleaner(Whitelist.none().addTags("dd", "dt", "a", "p").addAttributes("a", "name")).clean(document);
    }

    public static Elements getAll(Document document) {
        return getCleanDoc(document).getAllElements().select("dt,dd");
    }

    public static void main(String[] args) throws IOException {

        Document doc = getDocumentation("linking", LOCAL);

        List<String> options = getParametersList(doc);
        String opt = options.get(3);
        String desc = getParameterDescription(doc, opt);

        System.out.println(opt);
        System.out.println(desc);
    }

    public static Document getDocumentation(String section, int repository) throws IOException {
        return getDocLocal(parameters.get(section.toLowerCase())[repository]);
    }

    public GCCDocParser(){
/*        Elements ddTags = cleanDoc.getAllElements().select("dd");
        Elements opts = ddTags.select("a");
        List<String> names = opts.eachAttr("name");
        names.replaceAll(s -> s.replaceAll("index", ""));

        Elements group = ddTags.get(7).children();
        String desc = ddTags.get(7).select("p").text();*/
    }

    public static Document getDocLocal(String docpath) throws IOException {
        File input = new File(docpath);
        return Jsoup.parse(input, "UTF-8", "https://gcc.gnu.org/onlinedocs/gcc/Link-Options.html");
    }

    public static Document getDocOnline(String docurl) throws IOException {
        return Jsoup.connect(docurl).get();
    }

    public static List<String> getParametersList(Document doc) {
        return getCleanDoc(doc).getAllElements().select("dt").eachText();
    }

    public static String getParameterDescription(Document document, String parameter) {
        String description = "";
        Elements elements = getAll(document);
        Optional<Element> dt = elements.stream().filter(element -> parameter.equals(element.select("dt").text())).findAny();
        if (dt.isPresent()) {
            int i = elements.indexOf(dt.get());
            for (int j = i+1; j < elements.size(); j++) {
                if (elements.get(j).tagName().equalsIgnoreCase("dd")){
                    description = elements.get(j).text();
                    break;
                }
            }
        }
        return description;
    }
}