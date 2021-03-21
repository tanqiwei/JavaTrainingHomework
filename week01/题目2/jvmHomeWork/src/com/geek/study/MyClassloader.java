package com.geek.study;


import java.io.*;
import java.lang.reflect.Method;
import java.util.Objects;
/**
 * @author tqw
 */
public class MyClassloader extends ClassLoader {

    public static final String HELLO_XLASS = "Hello.xlass";
    public static final String HELLO = "Hello";

    public static void main(String[] args) {
        try {
            Object helloInstance = new MyClassloader().findClass(HELLO).newInstance();
            Class<?> HelloClass = helloInstance.getClass();
            Method[] methods = HelloClass.getMethods();
            for (Method method : methods) {
                if (Objects.equals(method.getParameterCount(), 0) &&
                        Objects.equals(method.getDeclaringClass(), HelloClass)) {
                    method.invoke(helloInstance);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] encode = toByteArray(HELLO_XLASS);
            byte[] originalBytes = new byte[encode.length];
            for (int i = 0; i < encode.length; i++) {
                originalBytes[i] = (byte) (255 - encode[i]);
            }
            return defineClass(name, originalBytes, 0, originalBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(f))) {
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        }
    }
}
