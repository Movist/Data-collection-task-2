package movist;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Какую картинку скачать?");
        System.out.println("Запрос:");
        Scanner sc = new Scanner(System.in);
        String nameFile = sc.nextLine();
        String query = nameFile.replace(" ", "+");

        Document document = Jsoup.connect("https://www.google.ru/images?q=" + query).get();
        Elements images = document.selectXpath("//img[contains(@class,'rg_i')]");
        /*System.out.println(images.size());*/
        for (int i = 0; i < images.size(); i++) {
            if (images.get(i).hasAttr("data-src")) {
                String strImageURL = images.get(i).attr("data-src");
                String folderPath = "C:\\Users\\Shtigun\\Desktop\\5 сем\\Практика 5 сем\\projs\\Data collection\\task2\\src\\main\\resources\\images";

                URL urlImage = new URL(strImageURL);
                InputStream in = urlImage.openStream();
                byte[] buffer = new byte[4096];
                int n = -1;

                OutputStream os = new FileOutputStream(folderPath + "\\" + nameFile + i + ".jpg");

                while ((n = in.read(buffer)) != -1) {
                    os.write(buffer, 0, n);
                }
                os.close();
            }
        }

    }
}