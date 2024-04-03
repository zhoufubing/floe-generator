import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerTest {
  @Test
    public void Test() throws IOException, TemplateException {

    Configuration configuration= new Configuration(Configuration.VERSION_2_3_32);

// Specify the source where the template files come from. Here I set a
// plain directory for it, but non-file-system sources are possible too:
    configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

// From here we will set the settings recommended for new projects. These
// aren't the defaults for backward compatibilty.

// Set the preferred charset template files are stored in. UTF-8 is
// a good choice in most applications:
    configuration.setDefaultEncoding("UTF-8");
    configuration.setNumberFormat("0.######");

    Template template = configuration.getTemplate("myweb.html.ftl");

    HashMap<String,Object> dataModel = new HashMap<>();
    dataModel.put("currentYear", 2023);
    List<Map<String,Object>> menuItems = new ArrayList<>();
    Map<String,Object> menuItem1 = new HashMap<>();
    menuItem1.put("url","https://codefather.cn");
    menuItem1.put("label","支付宝");

    Map<String,Object> menuItem2 = new HashMap<>();
    menuItem2.put("url","https;//laoyujianli.com");
    menuItem2.put("label","苏苏苏");

    menuItems.add(menuItem1);
    menuItems.add(menuItem2);

    dataModel.put("menuItems",menuItems);

    Writer out= new FileWriter("myweb.html");
    template.process(dataModel,out);

    out.close();


  }
}

