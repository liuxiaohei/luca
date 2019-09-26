package org.ld.examples;

import org.apache.tomcat.util.ExceptionUtils;
import org.junit.Test;
import org.ld.enums.SystemErrorCodeEnum;
import org.ld.exception.ErrorCode;
import org.ld.utils.Logger;
import org.ld.utils.UuidUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.stream.Stream;

public class DemoTest {

    @Test
    public void demo() {
        System.out.println(new ErrorCode(SystemErrorCodeEnum.SUCCESS()).getMessage());
    }

    @Test
    public void sendMail() {

    }

    /**
     * 无限流
     */
    @Test
    public void infiniteStream() {
        Logger logger = Logger.newInstance();
        //Stream.iterate(0, i -> ++i).limit(1000).forEach(e -> Logger.newInstance().info(() -> "" + e));
        Stream.generate(UuidUtils::getShortUuid).limit(1000000).forEach(e -> logger.info(() -> "" + e));
    }

//    @Test
//    public void demo1() {
//        try {
//            final AbstractJPPFClassLoader cl = (AbstractJPPFClassLoader) getClass().getClassLoader();
//            final URL[] urls = cl.getURLs();
//            boolean found = false;
//            // is shutdown.jar already in the classpath ?
//            for (URL url : urls) {
//                if (url.toString().indexOf("shutdown.jar") >= 0) {
//                    found = true;
//                    break;
//                }
//            }
//            // if not let's add it dynamically
//            if (!found) {
//                File file = new File(jbossHome + "/bin/shutdown.jar");
//                cl.addURL(file.toURI().toURL());
//                file = new File(jbossHome + "/client/jbossall-client.jar");
//                cl.addURL(file.toURI().toURL());
//                final JarFile jar = new JarFile(file);
//                final Manifest manifest = jar.getManifest();
//                final String classPath = manifest.getMainAttributes().getValue("Class-Path");
//                final String[] libs = classPath.split("\\s");
//                final File dir = file.getParentFile();
//                for (String s : libs) cl.addURL(new File(dir, s).toURI().toURL());
//                jar.close();
//            }
//            // with shutdown.jar in the classpath, we can now invoke the ocmmand
//            // org.jboss.Shutdown.main("-S") to shtudown the server
//            System.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
//            System.setProperty("jboss.boot.loader.name", "shutdown.bat");
//            final Class<?> clazz = cl.loadClass("org.jboss.Shutdown");
//            final Method method = clazz.getDeclaredMethod("main", String[].class);
//            System.out.println("shutting down by invoking " + method);
//            method.invoke((Object) null, (Object) new String[]{"-S"});
//        } catch (final Exception e) {
//            e.printStackTrace();
//
//        }
//
//    }
}
