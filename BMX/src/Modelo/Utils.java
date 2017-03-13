package Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Miquel on 27/12/2016.
 */


class Utils {

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

}

