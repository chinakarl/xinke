import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class RunGeneCode
{
    public static void main(final String args[]) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {

        final List<String> warnings = new ArrayList<String>();
        final boolean overwrite = true;
        final File configFile = new File(RunGeneCode.class.getResource("").getPath()+"config.xml");
        final ConfigurationParser cp = new ConfigurationParser(warnings);
        final Configuration config = cp.parseConfiguration(configFile);
        final DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        final MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        System.out.println("完成!");
    }
}
