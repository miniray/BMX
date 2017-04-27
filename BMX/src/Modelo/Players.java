package Modelo;

import java.util.ArrayList;
import java.util.Comparator;

public class Players {

    private int id = 0;
    private int licencia = 0;
    private String placa = "";
    private String nombre = "";
    private String apellido_1 = "";
    private String apellido_2 = "";
    private String club = "";
    private int anyo_nacimiento = 0;
    private int sexo = 0;
    private String provincia = "";
    private int categoria_espanya;
    private int categoria_regional;
    private int categoria_cruiser;
    private int total_points;
    private int points_moto1;
    private int points_moto2;
    private int points_moto3;


    private int ranking_liga_regional = 0;
    private int ranking_copa_regional = 0;
    private int ranking_campeonato_regional = 0;
    private int ranking_liga_espanyola = 0;
    private int ranking_copa_espanyola = 0;
    private int ranking_campeonato_espanya = 0;
    private int ranking_otro_regional = 0;
    private int ranking_otro_nacional = 0;
    private int ranking_cruiser_regional = 0;
    private int ranking_cruiser_nacional = 0;
    private int ranking_a_comparar = 0;

    //PREGUNTAR A ALBERT QUE SE USARÁ MAS EN LAS COMEPTICIONES
    private int rankingAConcursar;


    public Players() {
    }

    public Players(int id, int licencia, String placa,
                   String nombre, String apellido_1, String apellido_2,
                   int anyo_nacimiento, int categoria_espanya, int categoria_regional,
                   int categoria_cruiser, int sexo, String provincia,
                   int ranking_liga_regional, int ranking_copa_regional, int ranking_campeonato_regional,
                   int ranking_otro_regional,
                   int ranking_liga_espanyola, int ranking_copa_espanyola, int ranking_campeonato_espanya,
                   int ranking_otro_nacional,
                   int ranking_cruiser_regional, int ranking_cruiser_nacional, String club) {

        this.id = id;
        this.licencia = licencia;
        this.placa = placa;
        this.nombre = nombre;
        this.apellido_1 = apellido_1;
        this.apellido_2 = apellido_2;
        this.anyo_nacimiento = anyo_nacimiento;
        this.sexo = sexo;
        this.categoria_espanya = categoria_espanya;
        this.categoria_regional = categoria_regional;
        this.categoria_cruiser = categoria_cruiser;
        this.provincia = provincia;

        this.ranking_liga_regional = ranking_liga_regional;
        this.ranking_copa_regional = ranking_copa_regional;
        this.ranking_campeonato_regional = ranking_campeonato_regional;
        this.ranking_liga_espanyola = ranking_liga_espanyola;
        this.ranking_copa_espanyola = ranking_copa_espanyola;
        this.ranking_campeonato_espanya = ranking_campeonato_espanya;

        this.ranking_otro_regional = ranking_otro_regional;
        this.ranking_otro_nacional = ranking_otro_nacional;
        this.ranking_cruiser_regional = ranking_cruiser_regional;
        this.ranking_cruiser_nacional = ranking_cruiser_nacional;
        this.total_points = 0;
        this.club = club;
    }

    //ID
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    //LICENCIA
    public void setLicencia(int licencia) {
        this.licencia = licencia;
    }

    public int getLicencia() {
        return licencia;
    }

    //PLACA
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    //NOMBRE
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    //APELLIDO1
    public void setApellido1(String apellido_1) {
        this.apellido_1 = apellido_1;
    }

    public String getApellido1() {
        return apellido_1;
    }

    //APELLIDO2
    public void setApellido2(String apellido_2) {
        this.apellido_2 = apellido_2;
    }

    public String getApellido2() {
        return apellido_2;
    }

    //AnyO
    public void setAnyoNacimiento(int anyo_nacimiento) {
        this.anyo_nacimiento = anyo_nacimiento;
    }

    public int getAnyoNacimiento() {
        return anyo_nacimiento;
    }

    //categoria ESPAnyA
    public void setCategoriaEspanya(int categoria_espanya) {
        this.categoria_espanya = categoria_espanya;

    }

    public int getCategoriaEspanya() {
        return categoria_espanya;
    }

    //categoria REGIONAL
    private void setCategoriaRegional() {
        this.categoria_regional = 0;
    }

    public int getCategoriaRegional() {
        return categoria_regional;
    }

    //categoria CRUISER
    public void setCategoriaCruiser(int categoria_cruiser) {
        this.categoria_cruiser = categoria_cruiser;
    }

    public int getCategoriaCruiser() {
        return categoria_cruiser;
    }

    //SEXO
    public void setSexo(int sexo) {
        if (this.sexo != sexo) {
            this.setCategoriaEspanya(0);
            this.setCategoriaRegional();
            this.setCategoriaCruiser(0);
            this.sexo = sexo;
        } else {
            this.sexo = sexo;
        }
    }

    public int getSexo() {
        return sexo;
    }

    //PROVINCIA
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getProvincia() {
        return provincia;
    }

    //RANKINGS REGIONALES
    public void setRankingLigaRegional(int ranking_liga_regional) {
        this.ranking_liga_regional = ranking_liga_regional;
    }

    public int getRankingLigaRegional() {
        return ranking_liga_regional;
    }

    public void setRankingCopaRegional(int ranking_copa_regional) {
        this.ranking_copa_regional = ranking_copa_regional;
    }

    public int getRankingCopaRegional() {
        return ranking_copa_regional;
    }

    public void setRankingCampeonatoRegional(int ranking_campeonato_regional) {
        this.ranking_campeonato_regional = ranking_campeonato_regional;
    }

    public int getRankingCampeonatoRegional() {
        return ranking_campeonato_regional;
    }

    //RANKINGS ESPAnyA
    public void setRankingLigaEspanyola(int ranking_liga_espanyola) {
        this.ranking_liga_espanyola = ranking_liga_espanyola;
    }

    public int getRankingLigaEspanyola() {
        return ranking_liga_espanyola;
    }

    public void setRankingCopaEspanyola(int ranking_copa_espanyola) {
        this.ranking_copa_espanyola = ranking_copa_espanyola;
    }

    public int getRankingCopaEspanyola() {
        return ranking_copa_espanyola;
    }

    public void setRankingCampeonatoEspanya(int ranking_campeonato_espanya) {
        this.ranking_campeonato_espanya = ranking_campeonato_espanya;
    }

    public int getRankingCampeonatoEspanya() {
        return ranking_campeonato_espanya;
    }

    //RANKINGS OTROS
    public void setOtroRankingRegional(int ranking_otro_regional) {
        this.ranking_otro_regional = ranking_otro_regional;
    }

    public int getOtroRankingRegional() {
        return ranking_otro_regional;
    }

    public void setOtroRankingNacional(int ranking_otro_nacional) {
        this.ranking_otro_nacional = ranking_otro_nacional;
    }

    public int getOtroRankingNacional() {
        return ranking_otro_nacional;
    }

    public String getDescripcion() {
        return "ID: " + id + " LICENCIA: " + licencia +
                " PLACA: " + placa + "NOMBRE: " + nombre +
                " APELLIDO1 : " + apellido_1 + " APELLIDO2: " + apellido_2 +
                " AnyO DE NACIMIENTO: " + anyo_nacimiento + " SEXO: " + sexo +
                " CAT ESPAnyA: " + categoria_espanya + " CAT REG: " + categoria_regional +
                " CAT CRUISER: " + categoria_cruiser + " RANK LIGA REG: " + ranking_liga_regional +
                " RANK COPA REG: " + ranking_copa_regional + " RANK CAMP REG: " + ranking_campeonato_regional +
                " RANK LIGA ESP: " + ranking_liga_espanyola + " RANK COPA ESP: " + ranking_copa_espanyola +
                " RANK CAMP ESP: " + ranking_campeonato_espanya + " RANK OTRO REG: " + ranking_otro_regional +
                " RANK OTRO NAC: " + ranking_otro_nacional;
    }

    //RANKINGS CRUISER
    public void setRankingCruiserRegional(int ranking_cruiser_regional) {
        this.ranking_cruiser_regional = ranking_cruiser_regional;
    }

    public int getRankingCruiserRegional() {
        return ranking_cruiser_regional;
    }

    public void setRankingCruiserNacional(int ranking_cruiser_nacional) {
        this.ranking_cruiser_nacional = ranking_cruiser_nacional;
    }

    public int getRankingCruiserNacional() {
        return ranking_cruiser_nacional;
    }

    public void setRankingAComparar(int ranking_a_comparar) {
        this.ranking_a_comparar = ranking_a_comparar;
    }

    public int getRankingAComparar() {
        return ranking_a_comparar;
    }

    public void setPoints(){
        this.total_points = 0;
    }

    public int getPoints(){
        return this.total_points;
    }

    public void setPoints_moto1(int points_moto1) {
        this.points_moto1 = points_moto1;
    }

    public void setPoints_moto2(int points_moto2) {
        this.points_moto2 = points_moto2;
    }

    public void setPoints_moto3(int points_moto3) {
        this.points_moto3 = points_moto3;
    }

    public int getPoints_moto1() {
        return points_moto1;
    }

    public int getPoints_moto2() {
        return points_moto2;
    }

    public int getPoints_moto3() {
        return points_moto3;
    }



    public void addPoints(int points){
        this.total_points = this.total_points + points;
    }

    public static final Comparator<Players> PlayerCampeonatoEspanyaComparator = (p1, p2) -> {
        int playerRankingCampeonatoEspanya1 = p1.getRankingCampeonatoEspanya();
        int playerRankingCampeonatoEspanya2 = p2.getRankingCampeonatoEspanya();

        return playerRankingCampeonatoEspanya2 - playerRankingCampeonatoEspanya1;

    };

    public static final Comparator<Players> PlayerCruiserEspanyaComparator = (p1, p2) -> {
        int playerCruiserRankingEspanya1 = p1.getRankingCruiserNacional();
        int playerCruiserRankingEspanya2 = p2.getRankingCruiserNacional();

        return playerCruiserRankingEspanya2 - playerCruiserRankingEspanya1;

    };

    public static final Comparator<Players> PlayerPointsComparator = (p1, p2) -> {
        int playerPoints1 = p1.getPoints();
        int playerPoints2 = p2.getPoints();

        if ((playerPoints1 - playerPoints2) == 0){
            if (p1.getPoints_moto3()<p2.getPoints_moto3()){
                playerPoints1 -= 1;
                System.out.println("PLACA: " + p1.getPlaca() + " QUEDÓ MEJOR EN LA MOTO 3 QUE LA PLACA" + p2.getPlaca());
            }else{
                playerPoints2 -= 1;
                System.out.println("PLACA: " + p2.getPlaca() + " QUEDÓ MEJOR EN LA MOTO 3 QUE LA PLACA" + p1.getPlaca());
            }
        }

        return playerPoints1 - playerPoints2;

    };

    public ArrayList<String> getArrayListForPrintMangas(){

        ArrayList<String> arrayToPrint = new ArrayList<>();

        arrayToPrint.add(placa);
        arrayToPrint.add(nombre+" " + apellido_1+ " " + apellido_2);
        arrayToPrint.add(club);
        arrayToPrint.add(String.valueOf(ranking_campeonato_espanya));
        arrayToPrint.add("");
        arrayToPrint.add("");
        arrayToPrint.add("");

        return arrayToPrint;
    }


}
	