import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        checkArgumentV(args);
        int indexArgumentS = getIndexArgument(args,"-s");
        int indexArgumentO = getIndexArgument(args,"-o");
        int[] values = getIntegerValues(args);
        printHorizontalAndVerticalHistogram(args, values,indexArgumentS,indexArgumentO);
    }

    //Get the Index of the string "-o" and "-s"
    public static int getIndexArgument(String[] args,String chaine) {
        for(int i = 0;i<args.length;i++){
            if(Objects.equals(args[i], chaine)){
                return i;
            }
        }
        return -1;
    }

    //Check if the "-v" argument is in the string args, return error else
    public static void checkArgumentV(String[] args) {
        int j=-1;
        for(int i = 0;i<args.length;i++){
            if(Objects.equals(args[i], "-v")){
                j = i;
            }
        }
        if(j == -1){
            System.out.println("Error ! No -v argument");
            System.exit(1);
        }
    }

    //Return an array values of integer base on the args array
    public static int[] getIntegerValues(String[] args) {
        boolean Valid=true;
        int longeurTableauValues = args.length;
        for (String arg : args) {
            if (Objects.equals(arg, "-v")) {
                longeurTableauValues--;
            }
            if (Objects.equals(arg, "-s")) {
                longeurTableauValues = longeurTableauValues - 2;
            }
            if (Objects.equals(arg, "-o")) {
                longeurTableauValues = longeurTableauValues - 2;
            }
        }
        int values[] = new int[longeurTableauValues];
        int j = 0;
        for (int i = 0; i < args.length; i++) {
            Valid = true;
            if(Objects.equals(args[i], "-v")) {
                Valid = false;
            }
            if(Objects.equals(args[i], "-s")){
                if(args.length > i+2) {
                    i++;
                    Valid = false;
                }else
                    return values;
            }
            if(Objects.equals(args[i], "-o")){
                if(args.length > i+2) {
                    i++;
                    Valid = false;
                }
                else
                    return values;
            }
            else if(Valid == true){
                values[j] = Integer.parseInt(args[i]);
                j++;
            }
        }
        return values;
    }

    //Return an array with the occurences of the array values
    public static int[] getOccurrences(int[] values) {
        int maximunValue = max(values);
        int Occurences[] = new int[maximunValue+1];
        for (int i = 0; i < values.length; i++) {
            Occurences[values[i]] += 1;
        }
        return Occurences;
    }

    // return the highest value in the array occ
    public static int max(int[] occ) {
        int maximum = occ[0];   // start with the first value
        for (int i=1; i<occ.length; i++) {
            if (occ[i] > maximum) {
                maximum = occ[i];   // new maximum
            }
        }
        return maximum;
    }

    //Print the Histogram base on the Occurence of Values, Horizontaly and Verticaly
    public static void printHorizontalAndVerticalHistogram(String[] args, int[] values, int indexArgumentS, int indexArgumentO){
        int[] occ = getOccurrences(values);
        int j = 0;
        int indexParcoursValues;
        String chaine;
        int length=0;
        if(indexArgumentS != -1){
            chaine = ""+args[indexArgumentS+1];
        } else {
            chaine = "*";
        }

        Arrays.sort(values);
        while(occ[j] == 0){
            j++;
        }
        if(indexArgumentO == -1 || Objects.equals(args[indexArgumentO+1], "h")) {
            for (int i = j; i < values[values.length - 1] + 1; i++) {
                System.out.print(i);
                for (int k = 0; k < occ[i]; k++) {
                    System.out.print(chaine);
                }
                System.out.println();
            }
        } else if(Objects.equals(args[indexArgumentO+1], "v")) {
            for (int i = j; i < values[values.length - 1] + 1; i++) {
                indexParcoursValues = 0;
                int max = max(occ);
                if (max != 0) {
                    for (int k = j; k < occ.length; k++) {
                        if(max == occ[k]){
                            length = (int)(Math.log10(values[indexParcoursValues])+1);
                            System.out.print(chaine);
                            for (int l = 0; l < length; l++) {
                                System.out.print(" ");
                            }
                            occ[k]--;
                        } else {
                            length = (int)(Math.log10(values[indexParcoursValues])+1);
                            System.out.print(" ");
                            for (int l = 0; l < length; l++) {
                                System.out.print(" ");
                            }
                        }
                        indexParcoursValues++;
                    }
                    System.out.println("");
                }
            }
            for (int i = j; i < values[values.length - 1]+1; i++) {
                System.out.print(i+" ");
            }
        }
    }
}
