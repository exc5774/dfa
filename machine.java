/*
Machine Object:
    This is the dfa object
@author: Elyse Castles (exc5774@rit.edu)
@date: 9/18/2018
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class machine {

    private Set<String> Q; //states
    private Set<String> E; //alphabet
    private String S; //start state
    private Set<String> F; //accept states
    private Hashtable<String, String> D; //transition functions
    private String state; //current state of the machine

    /*
    DFA Machine Constructor:
        Initialize the machine
     */
    public machine(){
        this.Q = new HashSet<>();
        this.E = new HashSet<>();
        this.F = new HashSet<>();
        this.D = new Hashtable<>();
        this.state = "";
    }

    /*
    Set the states of the machine
     */
    public void setQ(String q){
        String[] line = q.split(" ");
        this.Q.addAll(Arrays.asList(line));
    }

    /*
    Set the alphabet of the machine
     */
    public void setE(String e){
        String[] line = e.split(" ");
        this.E.addAll(Arrays.asList(line));
    }

    /*
    Set the start state of the machine
     */
    public void setS(String s){
        this.S = s;
        this.state = this.S;
    }

    /*
    Set the accept states of the machine
     */
    public void setF(String f){
        String[] line = f.split(" ");
        this.F.addAll(Arrays.asList(line));
    }

    /*
    Set the transition functions of the machine
     */
    public void setD(String d){
        String[] line = d.split(" ");
        String x = line[0] + "," + line[1];
        D.put(x,line[2]);
    }

    /*
    Run a string on the machine
    @return: "accept" if string is accepted and "reject" if not accepted
     */
    public String runString(String s) {

        for (int i = 0; i < s.length(); i++) {
            String key = this.state + "," + String.valueOf(s.charAt(i));
            this.state = D.get(key);
        }
        if (this.F.contains(this.state)) {
            this.state = this.S;
            return "accept";
        }
        else {
            this.state = this.S;
            return "reject";
        }
    }

    /*
    Run a string on the machine and print out the transitions
    @return: "accept" if string is accepted and "reject" if not
     */
    public String traceM(String s){
        for (int i = 0; i < s.length(); i++) {
            String key = this.state + "," + String.valueOf(s.charAt(i));
            this.state = D.get(key);
            System.out.println(key + " -> " + this.state);
        }
        if (this.F.contains(this.state)) {
            this.state = this.S;
            return "accept";
        }
        else {
            this.state = this.S;
            return "reject";
        }
    }

    /*
    Return a string representation of the machine
    @return: String dfa
     */
    public String toString() {
        String s = "Q = ";
        s = s + this.Q + "\n";
        s = s + "Sigma = " + this.E + "\n";
        s = s + "S = " + this.S + "\n";
        s = s + "F = " + this.F + "\n";
        for (String key : this.D.keySet()){
            s = s + "transition: (" + key + ") -> " + this.D.get(key) + "\n";
        }
        return s;
    }
}
