package util;

import java.io.IOException;
import controller.Context;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import view.components.frames.CustomErrorDialogue;


public class CurrencyService {
    
    private static boolean isInitialised = false;
    
    private static final String MEXICO_URL = "https://www.forbes.com/advisor/money-transfer/currency-converter/gbp-mxn/?amount=1";
    private static final String EU_URL = "https://www.forbes.com/advisor/money-transfer/currency-converter/gbp-eur/?amount=1";
    private static final String BULGARIA_URL = "https://www.forbes.com/advisor/money-transfer/currency-converter/gbp-bgn/?amount=1";
    private static final String USA_URL = "https://www.forbes.com/advisor/money-transfer/currency-converter/gbp-usd/?amount=1";
    private static final String COLOMBIA_URL = "https://www.forbes.com/advisor/money-transfer/currency-converter/gbp-cop/?amount=1";
    private static final String HONG_KONG_URL = "https://www.forbes.com/advisor/money-transfer/currency-converter/gbp-hkd/?amount=1";
    private static final String CHINA_URL = "https://www.forbes.com/advisor/money-transfer/currency-converter/gbp-cny/?amount=1";
    private static final String SOUTH_KOREA_URL = "https://www.forbes.com/advisor/money-transfer/currency-converter/gbp-krw/?amount=1";
    private static final String POLAND_URL = "https://www.forbes.com/advisor/money-transfer/currency-converter/gbp-pln/?amount=1";
    private static final String CHILE_URL = "https://www.forbes.com/advisor/money-transfer/currency-converter/gbp-clp/?amount=1"; 
    private static final String ISRAEL_URL = "https://www.forbes.com/advisor/money-transfer/currency-converter/gbp-ils/?amount=1";
    
    public static float bulgariaRate;
    public static float chileRate;
    public static float chinaRate;
    public static float colombiaRate;
    public static float euRate;
    public static float hongKongRate;
    public static float israelRate;
    public static float mexicoRate;
    public static float koreaRate;
    public static float usaRate;
    public static float polandRate;
    
    public static void init(){
        
        if(!isInitialised){
            bulgariaRate = getRateForCountry(Currency.BGN);
            chileRate = getRateForCountry(Currency.CLP);
            chinaRate = getRateForCountry(Currency.CNY);
            colombiaRate = getRateForCountry(Currency.COP);
            euRate = getRateForCountry(Currency.EUR);
            hongKongRate = getRateForCountry(Currency.HKD);
            israelRate = getRateForCountry(Currency.ILS);
            mexicoRate = getRateForCountry(Currency.MXN);
            koreaRate = getRateForCountry(Currency.KRW);
            usaRate = getRateForCountry(Currency.USD);
            polandRate = getRateForCountry(Currency.PLN);
            isInitialised = true;
        }
    }
    
    public static float getRateForCountry (Currency c) {
        String countryUrl = getUrl(c);
        StringBuilder sb = new StringBuilder();
        if(countryUrl != null) {
            try {
                Document doc = Jsoup.connect(countryUrl).get();
                Elements divs = doc.select("div.result-box-c1-c2").first().children();
                for(Element div : divs){
                    if(div.text().contains("1 " + c + " = ")){
                        sb.append(div.text())
                                .delete(0, 8)
                                .delete(sb.length()-5, sb.length());
                        return Float.parseFloat(sb.toString());
                    }
                }
            }
            catch (IOException e) {
                new CustomErrorDialogue(true, "Failed to connect to forbes.com", Context.getParentFrame(), true).showDialogue();
            }
        }
        return 0;
    }
    
        private static String getUrl(Currency country){
        String c = null;
        switch(country){
            case MXN -> c = MEXICO_URL;
            case EUR -> c = EU_URL;
            case BGN -> c = BULGARIA_URL;
            case USD -> c = USA_URL;
            case COP -> c = COLOMBIA_URL;
            case HKD -> c = HONG_KONG_URL;
            case CNY -> c = CHINA_URL;
            case CLP -> c = CHILE_URL;
            case ILS -> c = ISRAEL_URL;
            case KRW -> c = SOUTH_KOREA_URL;
            case PLN -> c = POLAND_URL;
        }
        return c;
    }
    
    public enum Currency {
        EUR, MXN, BGN, USD, COP, HKD, CNY, ILS, KRW, PLN, CLP
        
        /*
        EUR - Euro
        MXN - Mexican Peso
        BGN - Bulgarian Lev
        USD - US Dollar
        COP - Columbian Peso
        HKD - Hong Kong Dollar
        CNY - Chines Yuan
        ILS - Israel Shekel
        KRW - South Koren Won
        PLN - Polish Zloty
        CLP - Chilean Peso
        */
    }
}