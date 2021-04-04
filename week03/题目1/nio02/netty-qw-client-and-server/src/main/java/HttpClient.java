import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author tqw
 */
public class HttpClient {


    public static void main(String[] args) throws IOException {
        String proxyPort = System.getProperty("proxyPort", "8888");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://127.0.0.1:" + proxyPort);
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                System.out.println("响应内容长度为:" + entity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(entity));

                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity);
            }

        }


    }


}
