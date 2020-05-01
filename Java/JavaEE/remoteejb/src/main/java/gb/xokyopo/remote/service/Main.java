package gb.xokyopo.remote.service;
import gb.xokyopo.servlets.ui.remote.impl.EjbRemoteImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

public class Main {

//    public static void main(String[] args) throws IOException, NamingException {
//        Context context = createInitialContext();
//        EjbRemoteImpl prodService = (EjbRemoteImpl) context.lookup("ejb:/webAppJSF/EjbRemote!gb.xokyopo.servlets.ui.remote.impl.EjbRemoteImpl");
//        prodService.getAllCategory().forEach(categoryRep -> {
//            System.out.println(categoryRep.getName());
//        });
//    }
//
//    public static Context createInitialContext() throws IOException, NamingException {
//        final Properties env = new Properties();
//        env.load(Main.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
//        return new InitialContext(env);
//    }

}
