package Modelo;

/**
 * Created by Miquel on 27/12/2016.
 */
public class Categories implements Constants {

    private int category_esp;
    private int category_region;
    private int region;

    public Categories (int category_esp, int category_region, int region){
        this.category_esp = category_esp;
        this.category_region = category_region;
        this.region = region;

    }

    public void setCategoryEsp(int set_category_esp){
        category_esp = set_category_esp;
    }
    public void setCategoryRegion(int set_category_region){
        category_region = set_category_region;
    }
    public void setRegion(int region){
        this.region = region;
    }

    public int getCategoryEsp(){
        return category_esp;
    }

    public int getCategoryRegion(){
        return category_region;
    }

    public int getRegion(){
        return region;
    }


}
