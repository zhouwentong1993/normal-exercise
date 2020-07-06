import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class Test {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("x", 9200, "http")
                        , new HttpHost("y", 9200, "http")
                        , new HttpHost("z", 9200, "http")));
        GetRequest request = new GetRequest("jimu_test", "workflow", "0a4bd891-2936-466b-8ceb-c5dc899e8969");

        GetResponse documentFields = client.get(request);
        System.out.println(client.toString());
    }
}
