package Modelo;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Miquel on 27/12/2016.
 */


public class Utils implements Constants {
    static File fileToPrintHtml;

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

    public static BufferedWriter createFileAndGetWriterBuffer(String nameOfReport){
        String desktopPath = WindowsUtils.getCurrentUserDesktopPath() + "\\LISTADO " + nameOfReport + ".html" ;
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

    public static void writeIntoTheFileOfMangas(BufferedWriter bw, ArrayList<Manga> arrayListOfOrderedMangas) throws IOException {

        String html = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                "<style type=\"text/css\">\n" +
                ".tg  {border-collapse:collapse;border-spacing:0;}\n" +
                ".tg td{font-family:Arial, sans-serif;font-size:14px;padding:2px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}\n" +
                ".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}\n" +
                ".tg .tg-fbrz{font-weight:bold;font-size:20px;text-align:center;vertical-align:top}\n" +
                ".tg .tg-if35{text-decoration:underline;text-align:center;vertical-align:top}\n" +
                ".tg .tg-yw4l{vertical-align:top}\n" +
                "</style>\n" + createAllGamesManga(arrayListOfOrderedMangas);

        bw.write(html);
        bw.close();
    }
    public static void writeIntoTheFileOfSemifinalsAndFinals(BufferedWriter bw, ArrayList<FinalsManga> arrayListOfOrderedMangas, int semifinalOrFinal) throws IOException {

        String html = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                "<style type=\"text/css\">\n" +
                ".tg  {border-collapse:collapse;border-spacing:0;}\n" +
                ".tg-pagebreak  {border-collapse:collapse;border-spacing:0;}\n" +
                ".tg td{font-family:Arial, sans-serif;font-size:14px;padding:2px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}\n" +
                ".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}\n" +
                ".tg .tg-fbrz{font-weight:bold;font-size:20px;text-align:center;vertical-align:top}\n" +
                ".tg .tg-if35{text-decoration:underline;text-align:center;vertical-align:top}\n" +
                ".tg .tg-yw4l{vertical-align:top}\n" +
                "@media print { .tg-pagebreak {page-break-after: always;}" +
                "</style>\n";

        switch (semifinalOrFinal){

            case 1:
                html += createAllGamesFinal(arrayListOfOrderedMangas);
                break;
            case 2:
                html += createAllGamesSemifinal(arrayListOfOrderedMangas);
                break;
            default:
                html += "NO PUEDO ESCOGER CREATEALLGAMESSEMIFINALOFINAL";
                break;
        }

        bw.write(html);
        bw.close();
    }

    private static String createARowForPlayer(ArrayList <Players> arrayListOfPlayersData, boolean is_moto, boolean with_moto_points) {
        String playerInfo = new String();
        if (is_moto){
            arrayListOfPlayersData.sort(Players.PlayerCampeonatoEspanyaComparator);
        }

        int contador_position_list = 0;

        for (Players playerData : arrayListOfPlayersData) {
            playerInfo += "  <tr>\n";
            for (String aColumn : playerData.getArrayListForPrintMangas(contador_position_list,with_moto_points)) {
                playerInfo += "    <td class=\"tg-yw4l\">" + aColumn + "</td>\n";
            }
            playerInfo += "</tr>\n";
            contador_position_list++;
        }
        return playerInfo;
    }

    public static void  openFile() throws IOException {
        Desktop.getDesktop().browse(fileToPrintHtml.toURI());

    }

    public static void printPreMangas(ArrayList<Manga> arrayListOrderedMangas) throws IOException {
        writeIntoTheFileOfMangas(createFileAndGetWriterBuffer("MANGAS"), arrayListOrderedMangas);
        openFile();
    }

    public static void printSemifinals(ArrayList<FinalsManga> arrayListOrderedMangas) throws IOException {
        writeIntoTheFileOfSemifinalsAndFinals(createFileAndGetWriterBuffer("SEMIFINALS"), arrayListOrderedMangas,2);
        openFile();
    }
    public static void printFinals(ArrayList<FinalsManga> arrayListOrderedMangas) throws IOException {
        writeIntoTheFileOfSemifinalsAndFinals(createFileAndGetWriterBuffer("FINALS"), arrayListOrderedMangas,1);
        openFile();
    }

    public static String createAllGamesManga( ArrayList<Manga> arrayOfAllMangas){
        String html = "";
        int contador_pares = 0;

        for (Manga aManga: arrayOfAllMangas) {
            contador_pares++;

            html +="<table class=\"tg\" style=\"undefined;table-layout: fixed; width: 1027px";
            if (contador_pares%2==0){
                html+= "; page-break-after: always";
            }
            html +=         "\">\n" +
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
                            "    <th class=\"tg-fbrz\" colspan=\"7\">" + "MANGA"+ " " + aManga.getNumero_de_manga() + "</th>\n" +
                            "  </tr>\n" +
                            "  <tr>\n" +
                            "    <th class=\"tg-fbrz\" colspan=\"7\">CATEGORIA ";
                html = getCategoryGender(html,aManga.getMySingleGame().getGender());

                html+=aManga.getMySingleGame().getCategoryName() + "</th>\n" +
                            "  </tr>\n" +
                            "  <tr>\n" +
                            "    <td class=\"tg-if35\">PLACA</td>\n" +
                            "    <td class=\"tg-if35\">NOMBRE</td>\n" +
                            "    <td class=\"tg-if35\">CLUB</td>\n" +
                            "    <td class=\"tg-if35\">RANKING</td>\n" +
                            "    <td class=\"tg-if35\">MOTO 1</td>\n" +
                            "    <td class=\"tg-if35\">MOTO2</td>\n" +
                            "    <td class=\"tg-if35\">MOTO3</td>\n" +
                            "  </tr>\n" + createARowForPlayer(aManga.getMangaPlayersArray(),true, false) +
                            "  </tr>\n" + "</table>"+"<br><br><br>";

        }
        return html;
    }
    public static String createAllGamesSemifinal( ArrayList<FinalsManga> allGamesFinalMangas ) {
        String html = "";
        int contador = 200;

        for (FinalsManga aFinalManga : allGamesFinalMangas) {
            for (Map.Entry aSemifinal : aFinalManga.getSemifinalsMotosMap().entrySet()) {
                Moto tempSemifinalMoto = (Moto) aSemifinal.getValue();
                contador++;
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
                                "    <th class=\"tg-fbrz\" colspan=\"7\">" + "SEMIFINAL" + " " + contador + "</th>\n" +
                                "  </tr>\n" +
                                "  <tr>\n" +
                                "    <th class=\"tg-fbrz\" colspan=\"7\">CATEGORIA ";
                html = getCategoryGender(html,aFinalManga.getMySingleGame().getGender());
                html+= aFinalManga.getMySingleGame().getCategoryName() + "</th>\n" +
                                "  </tr>\n" +
                                "  <tr>\n" +
                                "    <td class=\"tg-if35\">PLACA</td>\n" +
                                "    <td class=\"tg-if35\">NOMBRE</td>\n" +
                                "    <td class=\"tg-if35\">CLUB</td>\n" +
                                "    <td class=\"tg-if35\">RANKING</td>\n" +
                                "    <td class=\"tg-if35\">MOTO 1</td>\n" +
                                "    <td class=\"tg-if35\">MOTO2</td>\n" +
                                "    <td class=\"tg-if35\">MOTO3</td>\n" +
                                "  </tr>\n" + createARowForPlayer(tempSemifinalMoto.getModelMotoTable().getPlayersInMoto(),false,true) +
                                "  </tr>\n" + "</table>" + "<br><br><br>";

            }
        }
        return html;
    }
    public static String createAllGamesFinal( ArrayList<FinalsManga> allGamesFinalMangas ) {
        String html = "";
        int contador = 100;

        for (FinalsManga aFinalManga : allGamesFinalMangas) {
                Moto FinalMoto = aFinalManga.getFinalMoto();
                contador++;
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
                                "    <th class=\"tg-fbrz\" colspan=\"7\">" + "FINAL" + " " + contador + "</th>\n" +
                                "  </tr>\n" +
                                "  <tr>\n" +
                                "    <th class=\"tg-fbrz\" colspan=\"7\">CATEGORIA  ";

                html = getCategoryGender(html,aFinalManga.getMySingleGame().getGender());
                ArrayList <Players> temp = FinalMoto.getModelMotoTable().getPlayersInMoto();
                temp.sort(Players.PlayerPointsComparator);
                html += aFinalManga.getMySingleGame().getCategoryName() + "</th>\n" +
                                "  </tr>\n" +
                                "  <tr>\n" +
                                "    <td class=\"tg-if35\">PLACA</td>\n" +
                                "    <td class=\"tg-if35\">NOMBRE</td>\n" +
                                "    <td class=\"tg-if35\">CLUB</td>\n" +
                                "    <td class=\"tg-if35\">RANKING</td>\n" +
                                "    <td class=\"tg-if35\">MOTO 1</td>\n" +
                                "    <td class=\"tg-if35\">MOTO2</td>\n" +
                                "    <td class=\"tg-if35\">MOTO3</td>\n" +

                                "  </tr>\n" + createARowForPlayer(temp, false, true) +
                                "  </tr>\n" + "</table>" + "<br><br><br>";

        }
        return html;
    }

    private static String getCategoryGender(String html, int gender){
        switch (gender){

            case 0:
                html += "FEMENINA: ";
                return html;
            case 1:
                html += "MASCULINA: ";
                return html;
            case 2:
                html += "CRUISER: ";
                return html;
            default :
                html+= "SIN CATEGORIA? ";
                return html;
        }
    }

}

