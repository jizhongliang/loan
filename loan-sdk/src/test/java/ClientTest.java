import com.hwc.base.sdk.core.Client;
import com.hwc.loan.sdk.borrow.request.ManagePayMorrgageListRequest;
import com.hwc.loan.sdk.borrow.response.ManagePayMorrgageListResponse;

public class ClientTest {
    public static void main(String[] args) {
        Client client = new Client();
        Client.HOST = "http://127.0.0.1:11000";

        ManagePayMorrgageListRequest request = new ManagePayMorrgageListRequest();
        request.setPage(1);
        request.setPageSize(10);
        ManagePayMorrgageListResponse invoke = client.invoke(request);
        System.out.println(invoke);

    }
}
