import java.math.*;
import	java.util.Scanner; 
class vgbot {
	
    public static void main(String[] args){
   
		int spielfeld[][] = new int[7][6];
		int zz=0; // ist ne variable um zu erkennen wer am zug ist 0 ist p1.
			
		for(int j=0;j<20;j++){
			if(zz==0){ // wenn p1 drann ist, soll auch zugp1 aufgerufen werden.
				spielfeld = spielfeld_updater(spielfeld, zugp1(spielfeld), zz);
				if (sieg_tester(spielfeld)==1){
					break;
				}
				zz = 1;
			}
						
			else{
				spielfeld = spielfeld_updater(spielfeld, zugp2(spielfeld), zz);
				if (sieg_tester(spielfeld)==1){
					break;
				}
				zz = 0;
			}
			
			spielfeld_drucker(spielfeld);		
		}
																	}
													
	public static int zugp1(int[][] playfield){
		int input_p1=0;	
		int legal = 0;
		Scanner so = new Scanner(System.in);	
		do{
			System.out.print("Bitte p1 Zug eingeben: ");		
			input_p1 = so.nextInt();
			for (int i=0; i<6; i++){ // geht von unten nach oben und sucht ne leere stelle in der spalte zugspieler
				if (input_p1 > 6 || input_p1 < 0){ // fängt zu große oder kleine zugzahlen ab mit break.
					break;
				}
				
				if (playfield[input_p1][i]==0){ // wenn stelle leer
					legal = 1;
					break;
				}
			}
						
			if (legal==0){
				System.out.print("\nZug illegal!\n");
			}
		}
		while(legal!=1);
		
		return input_p1;
	}
	
	public static int zugp2(int[][] playfield){
		int input_p2=0;	
		int legal = 0;
		Scanner so = new Scanner(System.in);
		do{
			System.out.print("Bitte p2 Zug eingeben: ");		
			input_p2 = so.nextInt();
			for (int i=0; i<6; i++){ // geht von unten nach oben und sucht ne leere stelle in der spalte zugspieler
				if (input_p2 > 6 || input_p2 < 0){ // fängt zu große oder kleine zugzahlen ab mit break.
					break;
				}
				if (playfield[input_p2][i]==0){ // wenn stelle leer
					legal = 1;
					break;
				}
			}
			if (legal==0){
				System.out.print("\nZug illegal!\n");
			}
		}
		while(legal!=1);	
		
		return input_p2;
	}
	
	public static void spielfeld_drucker(int playfield[][]){	
		int m,n=0;
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // leserlichkeit verbessern
		for(n=5;n!=-1;n--){ // von oben nach unten
			for(m=0;m<7;m++){
				System.out.print(playfield[m][n]); // zeile für zeile
			}
			System.out.print("\n");
		}	
	
	}
	
	public static int[][] spielfeld_updater(int playfield[][], int zugspieler,int zz){
		for (int i=0; i<6; i++){ // geht von unten nach oben und sucht ne leere stelle in der spalte zugspieler
			if (playfield[zugspieler][i]==0){ // wenn stelle leer
				if(zz==0){
					playfield[zugspieler][i]=1;
				}
				else{
					playfield[zugspieler][i]=2;
				}
				break;
			}
		}
		
		return playfield;
	}
	
	public static int sieg_tester(int[][] playfield){
		int score_p1 = 0 ;
		int score_p2 = 0 ;
		int sieger = 0;
			
		//Horizontals Testing, below
		
		for(int i=0; i<6; i++){		// startet bei y 0 und geht bis 6 hoch, --> horizontales testing
			for(int j=0; j<7; j++){	// sammelt werte entlang der x achse um zu sehen ob jemand 4 sammeln kann
				if(playfield[j][i]==0){ // wenn ein feld leer ist kriegen beide spieler einen reset.
					score_p1 = 0;
					score_p2 = 0;
				}
				if (playfield[j][i]==1){
					score_p1 = score_p1+1;	// für jeden 1er kriegt p1 nen punkt
					if (score_p1 == 4){		// bei vier hat er gewonnen und break.
						score_p2=0;
						break;
					}
					score_p2 =  0;
				}
				if (playfield[j][i]==2){
					score_p2 = score_p2+1;
					if (score_p2 == 4){
						score_p1=0;
						break;
					}
					score_p1 = 0;
				}
				
			}
			
			if (score_p1 == 4){		// wenn spieler 1, länge 4 sammeln konnte, spieldfeld + return sieger.
				spielfeld_drucker(playfield);
				System.out.print("Player 1 hat das Spiel gewonnen.");
				sieger = 1;
				break;
			}
		
			if (score_p2 == 4){
				spielfeld_drucker(playfield);
				System.out.print("Player 2 hat das Spiel gewonnen.");
				sieger = 1;
				break;
			}
			
			score_p1 = 0; // zwischen durch auf 0 setzen, nicht dass aus nem anderen testing punkte gesammelt werden.
			score_p2 = 0;
		}
		
		// Vertikales Testing below
		
		for(int j=0; j<7; j++){		// startet bei x 0 und geht rüber bis 7 , --> vert testing.
			for(int i=0; i<6; i++){	// sammelt werte entlang der y achse um zu sehen ob jemand 4 sammeln kann
				if(playfield[j][i]==0){ // wenn ein feld leer ist kriegen beide spieler einen reset.
					score_p1 = 0;
					score_p2 = 0;
				}
				if (playfield[j][i]==1){
					score_p1 = score_p1+1;	// für jeden 1er kriegt p1 nen punkt
					if (score_p1 == 4){		// bei vier hat er gewonnen und break.
						score_p2=0;
						break;
					}
					score_p2 =  0;
				}
				if (playfield[j][i]==2){
					score_p2 = score_p2+1;
					if (score_p2 == 4){
						score_p1=0;
						break;
					}
					score_p1 = 0;
				}
				
			}
			
			if (score_p1 == 4){	// siehe oben bei horizontales testing
				spielfeld_drucker(playfield);
				System.out.print("Player 1 hat das Spiel gewonnen.");
				sieger = 1;
				break;
			}
		
			if (score_p2 == 4){
				spielfeld_drucker(playfield);
				System.out.print("Player 2 hat das Spiel gewonnen.");
				sieger = 1;
				break;
			}
			score_p1 = 0;	// siehe oben bei horizontales testing
			score_p2 = 0;
		
		}
		
		// Diagonales testing richtung oben rechts / und nach unten rechts \
		
		int schwelle = 0 ; // später diagonalen[i] schleifenarbeit zu sparen, nicht mehr aktuell
		
		
		// Die Diagonalen hab ich als paare von x und y aufgefasst, jedes diag[i] ist herbei eine
		// eigenständige diagonale mit den jeweiligen punkepaaren
		
		int[][] diagonalen = {	{0,2,1,3,2,4,3,5}, //d1 oben links noch oben rechts
								{3,0,4,1,5,2,6,3}, //d6 letzte unten nach oben rechts
								{6,2,5,3,4,4,3,5}, //e1 wie d1 nur nach oben links
								{3,0,2,1,1,2,0,3}, //e6
								{0,1,1,2,2,3,3,4,4,5},//d2
								{2,0,3,1,4,2,5,3,6,4},//d5
								{6,1,5,2,4,3,3,4,2,5},//e2
								{4,0,3,1,2,2,1,3,0,4},//e5
								{0,0,1,1,2,2,3,3,4,4,5,5},//d3
								{1,0,2,1,3,2,4,3,5,4,6,5},//d4
								{6,0,5,1,4,2,3,3,2,4,1,5},//e3
								{5,0,4,1,3,2,2,3,1,4,0,5}};//e4
		
		for(int i=0; i<12; i++){
			schwelle = diagonalen[i].length; 	//bsp länge 8 = 8 also 0, 2, 4, 6, wird gecheckt. 
			for(int j=0; j< schwelle;j=j+2){	// zuerst d1 weil i=0 bleibt. j+1 weils zahlenpaare sind und erstes feld=x mit j und y mit j+1
				if(playfield[diagonalen[i][j]][diagonalen[i][j+1]]==0){
					score_p1 = 0;
					score_p2 = 0;
				}
				if (playfield[diagonalen[i][j]][diagonalen[i][j+1]]==1){
					score_p1 = score_p1+1;	
					if (score_p1 == 4){
						score_p2=0;
						break;
					}
					score_p2 =  0;
				}
				if (playfield[diagonalen[i][j]][diagonalen[i][j+1]]==2){
					score_p2 = score_p2+1;
					if (score_p2 == 4){
						score_p1=0;
						break;
					}
					score_p1 = 0;
				}
			}
			
			if (score_p1 == 4){
				spielfeld_drucker(playfield);
				System.out.print("Player 1 hat das Spiel gewonnen.");
				sieger = 1;
				break;
			}
			
			if (score_p2 == 4){
				spielfeld_drucker(playfield);
				System.out.print("Player 2 hat das Spiel gewonnen.");
				sieger = 1;
				break;
			}
			score_p1 = 0; 
			score_p2 = 0;
		}
		
		return sieger;
	}
   
}
