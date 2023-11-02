import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Scanner;

class Players {
    // atributos
    private int id;
    private String name;
    private int height;
    private int weight;
    private String college;
    private int birth;
    private String city;
    private String state;

    // construtores
    public Players() {
        id = -1;
        name = "";
        height = -1;
        weight = -1;
        college = "";
        birth = -1;
        city = "";
        state = "";
    }

    // gets e sets
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCollege() {
        return college;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public int getBirth() {
        return birth;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void Read(String line){
        int[] comma = new int[7];
        int j = 0;
        if(line.charAt(line.length()- 1) == ','){
            this.state = "";
        }
        for(int i = 0; i<line.length(); i++){
            if(line.charAt(i) == ','){
                comma[j] = i;
                j++;
            }
        }
        this.id = Integer.parseInt(line.substring(0, comma[0]));
        this.name = line.substring(comma[0]+1, comma[1]);
        this.height = Integer.parseInt(line.substring(comma[1]+1, comma[2]));
        this.weight = Integer.parseInt(line.substring(comma[2]+1, comma[3]));
        this.college = line.substring(comma[3]+1, comma[4]);
        this.birth = Integer.parseInt(line.substring(comma[4]+1, comma[5]));
        this.city = line.substring(comma[5]+1, comma[6]); 
        this.state = line.substring(comma[6]+1, line.length());

    }

    public void Print(){
        System.out.print("[");
        if(this.id == -1){
            System.out.print("nao informado");
        }else{System.out.print(this.id);}
        System.out.print(" ## ");
        if(this.name.equals("")){
            System.out.print("nao informado");
        }else{System.out.print(this.name);}
        System.out.print(" ## ");
        if(this.height == -1){
            System.out.print("nao informado");
        }else{System.out.print(this.height);}
        System.out.print(" ## ");
        if(this.weight == -1){
            System.out.print("nao informado");
        }else{System.out.print(this.weight);}
        System.out.print(" ## ");
        if(this.birth == -1){
            System.out.print("nao informado");
        }else{System.out.print(this.birth);}
        System.out.print(" ## ");
        if(this.college.equals("")){
            System.out.print("nao informado");
        }else{System.out.print(this.college);}
        System.out.print(" ## ");
        if(this.city.equals("")){
            System.out.print("nao informado");
        }else{System.out.print(this.city);}
        System.out.print(" ## ");
        if(this.state.equals("")){
            System.out.print("nao informado");
        }else{System.out.print(this.state);}
        System.out.println("]");
    }

    public Players Clone(Players p){
        Players clone = new Players();
        clone.id = p.id;
        clone.name = p.name;
        clone.height = p.height;
        clone.weight = p.weight;
        clone.college = p.college;
        clone.birth = p.birth;
        clone.city = p.city;
        clone.state = p.state;
        return clone;
    }
}

public class questao3 {
    public static int MAX = 4000;
    public static void main(String[] args) {
        Players[] players = new Players[MAX]; //array de jogadores
        Players[] selection_players = new Players[MAX];
        
        try {
            MyIO.setCharset("ISO-8859-1");
            BufferedReader arq = new BufferedReader(new FileReader("/tmp/players.csv")); //colocar tmp para entregar no verde
            arq.readLine();//ignora a primeira linha
            int i = 0;
            while(arq.ready()){
                players[i] = new Players(); //cria uma instância de Players para cada linha do vetor de jogadores
                players[i].Read(arq.readLine()); //a linha do arquivo é lida pela função, após a criação de um jogador para aquela linha
                i++;
            }
            arq.close();
            String entry = new String();
            entry = MyIO.readLine();
            int select_position = 0;//índice para alocar os jogadores no vetor onde ocorrerá o selection sort
            /*------------- LÊ E ARMAZENA OS JOGADORES CORRETAMENTE -------------------*/
            while(!(entry != null && entry.equals("FIM"))){
                if(entry != null && entry.equals("FIM")){//sem esse if, tenta converter o fim e o programa termina com NumberFormatException
                   break;
                }
                int j = Integer.parseInt(entry); 
                if(select_position < selection_players.length){
                    selection_players[select_position] = new Players();
                    selection_players[select_position].setId(players[j].getId()); // eu não sei por que a função clone não funcionou, mas quando eu coloquei tudo na main funcionou, mesmo que não seja o recomendado
                    selection_players[select_position].setName(players[j].getName()); 
                    selection_players[select_position].setHeight(players[j].getHeight()); 
                    selection_players[select_position].setWeight(players[j].getWeight()); 
                    selection_players[select_position].setCollege(players[j].getCollege()); 
                    selection_players[select_position].setBirth(players[j].getBirth()); 
                    selection_players[select_position].setCity(players[j].getCity()); 
                    selection_players[select_position].setState(players[j].getState()); 
                    //selection_players[select_position].Print();45
                    
                    select_position++;
                }
                entry = MyIO.readLine();
            }
            /*-------------------------------------------------------------------------------------------------------------------------------------- */

            int players_name_count = 0;
            String name_entry = "";
            String[] players_name = new String[MAX];
            Boolean name = false;

            while(players_name_count<MAX){
                name_entry = MyIO.readLine();
                if(name_entry != null && name_entry.equals("FIM")){
                    break;
                }
                players_name[players_name_count] = name_entry;
                //System.out.println(name_entry);
                players_name_count++;
            }

            for(int p = 0; p<players_name_count; p++){
                //System.out.println("Kidult");
                for(int k = 0; k<select_position; k++){
                    //System.out.println("i'm trynna stay afloat but im in deep");
                    if(players_name[p].equals(selection_players[k].getName())){
                        name = true;
                        break;
                    }
                }
                if(name == true){
                    System.out.println("SIM");
                }else{
                    System.out.println("NAO");
                }
                name = false;
            }
            

        } catch (IOException read) { 
            System.out.println("Erro " + read);
        } catch (StringIndexOutOfBoundsException s) { 
            System.out.println("O índice está além do tamanho da string " + s);
        } catch (NullPointerException n) { 
            System.out.println("Ponteiro aponta para endereço inválido " + n);
        } catch (ArrayIndexOutOfBoundsException a) { // index out of the lenght of the archive
            System.out.println("O array não possui tal índice " + a);
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo " + e);
        }

    }
}