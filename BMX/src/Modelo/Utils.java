package Modelo;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Miquel on 27/12/2016.
 */


public class Utils implements Constants {
    static File fileToPrintHtml;
    private ArrayList<String[]> categoriesNames;

    public GridBagConstraints constraints;

    public static String transformStringToHtml(String strToTransform) {
        String ans = "<html>";
        String br = "<br>";
        String[] lettersArr = strToTransform.split("");
        for (String letter : lettersArr) {
            ans += letter + br;
        }
        ans += "</html>";
        return ans;
    }

    public static GridBagConstraints layoutForMotosInMangaPanel(JPanel mangaPanel, ArrayList<Moto> arrayOfMotos, GridBagConstraints constraints) {
        for (int i = 1; i <= 4; i++) {

            constraints.gridx += constraints.gridwidth;
            constraints.gridy = 0;
            constraints.gridwidth = 1;
            constraints.gridheight = 3;
            mangaPanel.add(arrayOfMotos.get(i - 1).getMotoPanel(), constraints);
        }
        return constraints;
    }

    public static void layoutForTheMangaPanel(JPanel mangaPanel, String textoLabel, ArrayList<Moto> motosArrayList){
        GridBagLayout gbl = new GridBagLayout();
        mangaPanel.setLayout(gbl);
        GridBagConstraints constraints = new GridBagConstraints();
        gbl.columnWeights = new double[] {-10.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0};
        gbl.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 0.0;
        JLabel mangaLabel = new JLabel(transformStringToHtml(textoLabel));
        mangaPanel.add(mangaLabel, constraints);

        layoutForMotosInMangaPanel(mangaPanel,motosArrayList,constraints);
    }

    public static BufferedWriter createFileAndGetWriterBuffer(){
        String desktopPath = WindowsUtils.getCurrentUserDesktopPath() + "\\LISTADO MANGAS.html" ;
        FileWriter fw;

        try{
            fileToPrintHtml = new File(desktopPath);
            fw = new FileWriter(desktopPath);
            BufferedWriter bw = new BufferedWriter(fw);

            return bw;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public File getFileListToPrintHtml(){
        return fileToPrintHtml;
    }

    public static void writeIntoTheFile(BufferedWriter bw, ArrayList<Manga> arrayListOfOrderedMangas) throws IOException {

        String html = "<style type=\"text/css\">\n" +
                ".tg  {border-collapse:collapse;border-spacing:0;}\n" +
                ".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}\n" +
                ".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}\n" +
                ".tg .tg-fbrz{font-weight:bold;font-size:20px;text-align:center;vertical-align:top}\n" +
                ".tg .tg-if35{text-decoration:underline;text-align:center;vertical-align:top}\n" +
                ".tg .tg-yw4l{vertical-align:top}\n" +
                "</style>\n" + createAllGamesManga(arrayListOfOrderedMangas);

        bw.write(html);
        bw.close();
    }
    private static String createARowForPlayer(ArrayList <Players> arrayListOfPlayersData) {
        String playerInfo = new String();

        for (Players playerData : arrayListOfPlayersData) {
            playerInfo += "  <tr>\n";
            for (String aColumn : playerData.getArrayListForPrintMangas()) {
                playerInfo += "    <td class=\"tg-yw4l\">" + aColumn + "</td>\n";
            }
            playerInfo += "</tr>\n";
        }
        return playerInfo;
    }
    public static void  openFile() throws IOException {
        Desktop.getDesktop().browse(fileToPrintHtml.toURI());

    }

    public static void printPreMangas(ArrayList<Manga> arrayListOrderedMangas) throws IOException {
        writeIntoTheFile(createFileAndGetWriterBuffer(), arrayListOrderedMangas);
        openFile();

    }

    public static ArrayList<ArrayList<String>> parseArrayListOfMangaData(Manga aManga){
        ArrayList<ArrayList<String>> arrayListWithAllPlayersData =new ArrayList<>();

        for (Players aPlayer: aManga.getMangaPlayersArray()){
            arrayListWithAllPlayersData.add(aPlayer.getArrayListForPrintMangas());
        }
        return arrayListWithAllPlayersData;
    }


    public static String createAllGamesManga(ArrayList<Manga> arrayOfAllMangas){
        String html = "";

        for (Manga aManga: arrayOfAllMangas) {
            html +=
                    "<table class=\"tg\" style=\"undefined;table-layout: fixed; width: 1027px\">\n" +
                            "<colgroup>\n" +
                            "<col style=\"width: 101px\">\n" +
                            "<col style=\"width: 301px\">\n" +
                            "<col style=\"width: 221px\">\n" +
                            "<col style=\"width: 101px\">\n" +
                            "<col style=\"width: 101px\">\n" +
                            "<col style=\"width: 101px\">\n" +
                            "<col style=\"width: 101px\">\n" +
                            "</colgroup>\n" +
                            "  <tr>\n" +
                            "    <th class=\"tg-fbrz\" colspan=\"7\">MANGA " + aManga.getNumero_de_manga() + "</th>\n" +
                            "  </tr>\n" +
                            "  <tr>\n" +
                            "    <td class=\"tg-if35\">PLACA</td>\n" +
                            "    <td class=\"tg-if35\">NOMBRE</td>\n" +
                            "    <td class=\"tg-if35\">CLUB</td>\n" +
                            "    <td class=\"tg-if35\">RANKING</td>\n" +
                            "    <td class=\"tg-if35\">MOTO 1</td>\n" +
                            "    <td class=\"tg-if35\">MOTO2</td>\n" +
                            "    <td class=\"tg-if35\">MOTO3</td>\n" +
                            "  </tr>\n" + createARowForPlayer(aManga.getMangaPlayersArray()) +
                            "  </tr>\n" + "</table>";

        }
        return html;
    }

    public void createCategoriesNamesArray(){

        categoriesNames.add(cat_espanya_fem);
        categoriesNames.add(cat_espanya_masc);
        categoriesNames.add(cat_espanya_cruiser_masc);
    }

    public ArrayList<String[]>  getCategoriesNames(){
        return categoriesNames;
    }

}

