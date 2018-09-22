/*
File: Runs a dfa created form a passed in text file. Determines
        whether or not strings passed in by a user are accepted
        on this machine or not. Also has trace functionality to
        let the user see how the states of the machine were
        traversed for a give string.
@author: Elyse Castles (exc5774@rit.edu)
@date: 9/18/2018
 */
import java.io.*;
import java.util.Scanner;
/*
DFA Class:
    Makes and runs a string on a given dfa
 */
public class dfa {
    private machine M;
    /*
    DFA constructor:
    builds new machine with uninitialized fields
    */
    private dfa(){
        this.M = new machine();
    }

    /*
    Reads file line by line and initializes the dfa
    @param reader: Buffered reader of dfa file
     */
    private void setM(BufferedReader reader) throws IOException{
        int c = 0;
        int hit = 0;
        String line;
        //while there is another line to read in file
        while((line = reader.readLine()) != null){
            //set states
            if(c == 0 && !String.valueOf(line.charAt(0)).equals("#")){
                M.setQ(line);
                hit = 1;
            }
            //set alphabet
            if (c == 1 && !String.valueOf(line.charAt(0)).equals("#")){
                M.setE(line);
                hit = 1;

            }
            //set start state
            if (c == 2 && !String.valueOf(line.charAt(0)).equals("#")){
                M.setS(line);
                hit = 1;
            }
            //set accept states
            if (c == 3 && !String.valueOf(line.charAt(0)).equals("#")){
                M.setF(line);
                hit = 1;
            }
            //set transition functions
            if (c == 4 && !String.valueOf(line.charAt(0)).equals("#")){
                M.setD(line);
            }
            //if one field was initialized look for the next field to
            //set from the file
            if (hit == 1){
                c = c + 1;
                hit = 0;
            }
        }
    }

    /*
    Run a string on the machine to determine if it is accepted or not
    @param s: String to accept or reject
     */
    private void runM(String s){
        System.out.println(M.runString(s));
    }

    /*
    Run a string on a machine to determine if it is accepted or not
    and print out the transitions of the states
    @param s: String to trace
     */
    private void traceM(String s){
        System.out.println(M.traceM(s.replace("!","")));
    }

    /*
    Main function:
        1. Reads in file to create dfa
        2. Set DFA
        3. Take user input and determine if it is accepted or not
        4. Take user input until "." is entered
     */
    public static void main(String[] args){
        System.out.print("DFA specification file name: ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        System.out.println(s);
        dfa m = new dfa();
        //Make dfa
        try(BufferedReader reader = new BufferedReader(new FileReader(s))
        ){
            m.setM(reader);
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        //echo dfa to screen
        System.out.println(m.toString());
        String enter;
        int c = 0;
        //while user does not use exit char (".") take a string input
        while(!(enter = scanner.nextLine()).equals(".")){

            if (enter.contains("!")) {
                //trace mode of string on dfa
                m.traceM(enter);

            }
            else if (c != 0){
                //normal node of string on dfa
                m.runM(enter);
            }
            c = 1;
            System.out.print("->");
        }
        System.out.println("goodbye");
    }
    /*
    Return a string representation of a dfa
     */
    @Override
    public String toString() {
        return M.toString();
    }
}
