package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetProperties {
    private static GetProperties instance = null;
    private String host;
    private String v_user;
    private String v_pwd;
    private String v_dni;
    private String s_dni;
    private String s_pwd;
    private String s_user;
    private String browser;


    private GetProperties() {
        Properties properties = new Properties();
        String nameFile = System.getProperty("envFile") == null ? "qa.properties" : System.getProperty("envFile");
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nameFile);
        if (inputStream != null) {
            try {
                properties.load(inputStream);
                host = properties.getProperty("host");
                v_user = resolveEnvVars(properties.getProperty("v_user"));
                v_pwd = resolveEnvVars(properties.getProperty("v_pwd"));
                v_dni = resolveEnvVars(properties.getProperty("v_dni"));
                s_dni = resolveEnvVars(properties.getProperty("s_dni"));
                s_pwd = resolveEnvVars(properties.getProperty("s_pwd"));
                s_user = resolveEnvVars(properties.getProperty("s_user"));
                browser = properties.getProperty("browser");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static GetProperties getInstance() {
        if (instance == null)
            instance = new GetProperties();
        return instance;
    }

    public String getHost() {
        return host;
    }



    public String getBrowser() {
        return browser;
    }


    public String getV_user() {
        return v_user;
    }

    public String getV_pwd() {
        return v_pwd;
    }

    public String getV_dni() {
        return v_dni;
    }

    public String getS_dni() {
        return s_dni;
    }

    public String getS_pwd() {
        return s_pwd;
    }

    public String getS_user() {
        return s_user;
    }

    /*
     * Returns input string with environment variable references expanded, e.g. $SOME_VAR or ${SOME_VAR}
     */
    private String resolveEnvVars(String input)
    {
        if (null == input)
        {
            return null;
        }
        // match ${ENV_VAR_NAME} or $ENV_VAR_NAME
        Pattern p = Pattern.compile("\\$\\{(\\w+)\\}|\\$(\\w+)");
        Matcher m = p.matcher(input); // get a matcher object
        StringBuffer sb = new StringBuffer();
        while(m.find()){
            String envVarName = null == m.group(1) ? m.group(2) : m.group(1);
            String envVarValue = System.getenv(envVarName);
            m.appendReplacement(sb, null == envVarValue ? "" : envVarValue);
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
