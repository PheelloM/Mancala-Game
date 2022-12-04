package org.example;

public class App {
    public static void main( String[] args ) {

        if(Common.player == 1){
            sowStones(Common.selectedPitIndex, Common.player1Pits, false);
            Common.currentSowingPits = 1;
        }else {
            sowStones(Common.selectedPitIndex, Common.player2Pits, true);
            Common.currentSowingPits = 2;
        }
    }

    //Recursive Method
    public static void sowStones(int selectedPitIndex, int sowingPits[], boolean traverseDirection){

        int numberOfStonesInCurrentPit = sowingPits[selectedPitIndex];
        sowingPits[selectedPitIndex] = 0;

        if(!traverseDirection){
            //traverse backward
            for(int i = selectedPitIndex - 1; i >= 0; i--){
                if(addPlayerStonesToBigPit(numberOfStonesInCurrentPit, Common.player1Pits, i, traverseDirection)){
                    numberOfStonesInCurrentPit--;
                }
                else
                {
                    sowingPits[i] = sowingPits[i] + 1;
                    numberOfStonesInCurrentPit--;
                }
            }
        }
        else
        {
            //traverse forward
            for(int i = selectedPitIndex + 1; i <= sowingPits.length; i++){
                if(addPlayerStonesToBigPit(numberOfStonesInCurrentPit, Common.player1Pits, i, traverseDirection)){
                    numberOfStonesInCurrentPit--;
                }
                else
                {
                    sowingPits[i] = sowingPits[i] + 1;
                    numberOfStonesInCurrentPit--;
                }
            }
        }

        if(numberOfStonesInCurrentPit > 0){

            if(Common.currentSowingPits == 1){
                sowStones(0, Common.player2Pits, true);
            }
            else
            {
                sowStones(6, Common.player1Pits, false);
            }
        }
        else
        {
            if(Common.player == 1) {
                sowStones(selectedPitIndex, Common.player2Pits, false);
                Common.currentSowingPits = 2;
            }
            else
            {
                sowStones(selectedPitIndex, Common.player1Pits, true);
                Common.currentSowingPits = 1;
            }
        }
    }
    private static boolean addPlayerStonesToBigPit(int numberOfStonesInCurrentPit, int sowingPits[], int i, boolean traverseDirection) {

        boolean result = false;
        int emptyPit;
        int oppositePit;
        if(numberOfStonesInCurrentPit == 0 && !traverseDirection){
            emptyPit = sowingPits[i] + 1;
            oppositePit = Common.player2Pits[i];
            Common.play1Total = Common.player1Pits[Common.player1BigPit] + emptyPit + oppositePit;
            emptyPit = 0;
            oppositePit = 0;
            result = true;
        }

        if(numberOfStonesInCurrentPit == 0 && traverseDirection){
            emptyPit = sowingPits[i] + 1;
            oppositePit = Common.player1Pits[i];
            Common.play2Total = Common.player2Pits[Common.player2BigPit] + emptyPit + oppositePit;
            emptyPit = 0;
            oppositePit = 0;
            result = true;
        }

        return result;
    }

}
