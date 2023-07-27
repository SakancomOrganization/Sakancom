package email;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class YmlHandler {
    // private constructor to hide the public one
    private YmlHandler() {

    }

    private static Map<String, Object> init() throws FileNotFoundException {
        Map<String, Object> data;
        InputStream inputStream = new FileInputStream("email-config.yml");
        Yaml yml = new Yaml();
        data = yml.load(inputStream);
        return data;
    }

    public static String getValue(String key) throws FileNotFoundException {
        return init().get(key).toString();
    }
}
