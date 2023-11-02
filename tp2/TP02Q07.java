import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

public class TP02Q07 {
    public static int MAX = 4000;
    public static int max = 600;
    public static void main(String[] args) { 
        Players[] players = new Players[MAX];
        Players[] insertion = new Players[MAX];
       
        try {

            MyIO.setCharset("ISO-8859-1");

            BufferedReader arq = new BufferedReader(new FileReader("/tmp/players.csv")); //colocar tmp para entregar no verde
            arq.readLine();
            int i = 0;
            while(arq.ready()){
                players[i] = new Players(); 
                players[i].Read(arq.readLine()); 
                i++;
            }
            arq.close();

            /*-----------ENTRADA------------------ */
            String entry = new String();
            entry = MyIO.readLine();
            int entry_position = 0;
            while(!(entry != null && entry.equals("FIM"))){
                if(entry != null && entry.equals("FIM")){//sem esse if, tenta converter o fim e o programa termina com NumberFormatException
                    break;
                }
                int j = Integer.parseInt(entry);
                if(entry_position<insertion.length){ //os jogadores estão sebdo armazenados no array
                    insertion[entry_position] = new Players();
                    insertion[entry_position].setId(players[j].getId());
                    insertion[entry_position].setName(players[j].getName());
                    insertion[entry_position].setHeight(players[j].getHeight());
                    insertion[entry_position].setWeight(players[j].getWeight());
                    insertion[entry_position].setCollege(players[j].getCollege());
                    insertion[entry_position].setBirth(players[j].getBirth());
                    insertion[entry_position].setCity(players[j].getCity());
                    insertion[entry_position].setState(players[j].getState());
                    //insertion[entry_position].Print();
                    entry_position++;
                }
                entry = MyIO.readLine();
            }

           

            /*----------------- INSERTION SORT-------------- */

            /*public void sort() {
                for (int i = 1; i < n; i++) {
                    int tmp = array[i];
                int j = i - 1;

                while ((j >= 0) && (array[j] > tmp)) {
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = tmp;
            }
            } */
            // k = i e l = j
            for(int k = 1; k<(entry_position); k++){
                Players temp = insertion[k];
                int l = k-1;

                while(((l>=0) && insertion[l].getBirth()>temp.getBirth()) || (l>=0 &&((insertion[l].getBirth() == temp.getBirth()) && (insertion[l].getName().compareTo(temp.getName())>0)))){
                    insertion[l+1] = insertion[l];
                    l--;
                }

                insertion[l+1] = temp;
            }
            /*------------------------------------------------------ */
            
            for(int o = 0; o<entry_position; o++){
                insertion[o].Print();
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
