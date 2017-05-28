package Modelo;

import java.util.Objects;

/**
 * Created by Miquel on 28/05/2017.
 */
public class CategoryUtils implements Constants {

    public static void setPlayerCategory(Players p, Object value, int combo_box_region) {

        if (p.getSexo() == 0) {
            if (combo_box_region == 0) {
                p.setCategoriaEspanya(getValueCategoryPosition(cat_espanya_fem, value));
            }
        } else {
            if (combo_box_region == 0) {
                p.setCategoriaEspanya(getValueCategoryPosition(cat_espanya_masc, value));
            }
        }
    }
    public static int getValueCategoryPosition(String[] list, Object value) {

        for (int i = 0; i < list.length; i++) {
            if (Objects.equals(value.toString(), list[i])) {
                return i;
            }
        }
        return 0;
    }

    //METODO PARA SETTEAR LA categoria SEGUN GENERO Y RANKING ELEGIDO
    public static void setCategoriaCruiserSetValue(Players p, Object value, int combo_box_region) {

        if (p.getSexo() == 1) {
            //SI EL RANKING ES ESPAnyA CAMBIAR LA categoria DE ESPAnyA
            if (combo_box_region == 0) {
                for (int n = 0; n < cat_espanya_cruiser_masc.length; n++) {
                    if (Objects.equals(value.toString(), cat_espanya_cruiser_masc[n])) {
                        p.setCategoriaCruiser(n);
                    }
                }
            }
            //SI EL RANKING ES CATALUnyA
            if (combo_box_region == 1) {
                for (int n = 0; n < cat_catalunya_cruiser_masc.length; n++) {
                    if (Objects.equals(value.toString(), cat_catalunya_cruiser_masc[n])) {
                        p.setCategoriaCruiser(n);
                    }
                }
            }
        }
    }



}
