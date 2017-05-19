package Modelo;

public interface Constants {
	//  							0		1		2			3			4			5			6			7
	String[] cat_espanya_masc = {"5 a 6", "7 a 8", "9 a 10", "11 a 12", "13 a 14", "15 a 16", "CRUISER 17 A 39","CRUISER 40 Y MES",
	// 			8				9				10						11
			"HOMES 30 Y MES", "HOMES 17 A 29", "CHAINLESS 12 I -",  "CHAINLESS 13 I +"};

	//									  0		  1			 				2			3			4			5
	String[] cat_espanya_cruiser_masc = {"NO", "Chicos de 13 a 16 años", "17 a 29", "30 a 39", "40 y mas", "PRO 17 y mas años"};

	//							  0		   1		  2			     3			            4
	String[] cat_espanya_fem = {"5 a 7", "8 a 10", "11 a 13", "FEMINAS 15 A 18", "FEMINAS 19 Y MES"};

	//
	String[] cat_catalunya_masc = {"5 a 6", "7 a 8", "9 a 10", "11 a 12", "13 a 14", "15 y menos", "Olimpica Men"};
	//
	String[] cat_catalunya_cruiser_masc = {"NO", "Cruiser Men"};
	//
	String[] cat_catalunya_fem = {"Feminas A", "Feminas B", "Olimpica Women"};

	int[][] mangas_order= {{1,0},{0,0}, {1,1}, {0,1},{1,2}
	, {1,3}, {0,2}, {1,4}, {0,3}, {1,5}
	, {1,6} , {1,7}, {1,8},{2,1}, {2,2}
	, {2,3}, {2,4},{2,5}, {0,4}, {1,9},
			{1,10},{1,11}};

	int[][] positionsList = {{8,2,3},{7,6,1},{6,3,5},{5,1,7},{4,8,2},{3,5,6},{2,7,4},{1,4,8}};

}
