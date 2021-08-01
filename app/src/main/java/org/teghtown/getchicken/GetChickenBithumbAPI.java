package org.teghtown.getchicken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetChickenBithumbAPI {
    String coin, currrency;
    URL url;

    final static String TICKER = "https://api.bithumb.com/public/ticker/"; // {order_currency}_{payment_currency}
    final static String ORDER_BOOK = "https://api.bithumb.com/public/orderbook/"; // {order_currency}_{payment_currency}
    final static String TRANSACTION_HISTORY = "https://api.bithumb.com/public/transaction_history/"; // {order_currency}_{payment_currency}
    final static String ASSETS_STATUS = "https://api.bithumb.com/public/assetsstatus/";// {order_currency}
    final static String BTCI = "https://api.bithumb.com/public/btci/";// NONE

    public GetChickenBithumbAPI() {
    }

    public GetChickenBithumbAPI(String coin) {
        this.coin = coin;
    }
    private static StringBuffer HttpGet(String bitumbURL) throws Exception {

        URL url = new URL(bitumbURL);
        // 요청 url 주소. url 클래스로는 우리가 원하는 기능을 전부 할 수 없으므로 URLConnection 객체로 되돌릴 필요가 있음.

        URLConnection conn = url.openConnection(); // openConnection() : URLConnection로 객체를 바꾸는데 이용되는 메서드. 더 많은 메소드를
        // 이용할 수 있다.
        conn.setDoInput(true);

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line = null;

        while ((line = reader.readLine()) != null) {
            sb.append(line);

        }
        return sb;

        // json print 출력
        // System.out.println(sb);

    }
    // 요청 당시 빗썸 거래소 가상자산 현재가 정보를 제공합니다.
    public StringBuffer GetTicker(String coin, String currency) throws Exception {
        String apiURL = TICKER + coin + "_" + currency;
        StringBuffer httpResult;
        httpResult = HttpGet(apiURL);
        return httpResult;

    }
}
