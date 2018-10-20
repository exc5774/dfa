# dfa
Deterministic Finite Automaton  

This program reads in a file containing all the necessary information to build a DFA. Any line in the DFA file that starts with "#" is a comment line and will not be read. The order in which the DFA program parses the file is as follows:
1. line of space-separated state names
2. line of space-separated characters in the DFA alphabet
3. line with start state (also contained in 1)
4. line of space-separated accept states (also contained in 1)
5. any subsequent line in the file is a space-separated transition function in the format of: "current_state transition result_state"

Once the DFA program parses the file, the program echoes the DFA information and prompts the user for a string input. The program will then determine if a string is accepted or rejected by that particular DFA. The program will continue to prompt the user for input until the user enters "." and the program will terminate.
